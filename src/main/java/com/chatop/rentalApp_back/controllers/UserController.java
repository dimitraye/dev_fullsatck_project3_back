package com.chatop.rentalApp_back.controllers;

import com.chatop.rentalApp_back.dto.UserDTO;
import com.chatop.rentalApp_back.models.User;
import com.chatop.rentalApp_back.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;


/**
 * Manage the requests linked to a user
 */
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class UserController {

    UserService userService;

    PasswordEncoder passwordEncoder;

    /**
     * Manage the user registration
     * @param user

     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<?> save(@RequestBody User user) {

        if (StringUtils.isEmpty(user.getEmail()) ||
                StringUtils.isEmpty(user.getName()) ||
                StringUtils.isEmpty(user.getPassword()) ) {
            return ResponseEntity.badRequest().body("A user field is missing or empty.");
        }

        Optional<User> userFromDB = userService.findByEmail(user.getEmail());

        if (userFromDB.isPresent()) {
            return ResponseEntity.badRequest().body("Email is already used");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        //sauvegarde du user
        try {
            userService.save(user);
            //Envoie un message sur la page de redirection
        } catch (Exception e) {
            log.error("Unable to save user.", e);
            return ResponseEntity.badRequest().body("Unable to save user.");
        }

        //creation du token
        String generateToken = userService.generateToken(user.getEmail());
        return ResponseEntity.ok(Collections.singletonMap("token", generateToken));
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginForm) {
        String email = loginForm.get("email");
        String password = loginForm.get("password");

        if (StringUtils.isEmpty(email) ||
                StringUtils.isEmpty(password) ) {
            return ResponseEntity.badRequest().body("A user field is missing or empty.");
        }

        Optional<User> userFromDB = userService.findByEmail(email);

        if (!userFromDB.isPresent()) {
            return ResponseEntity.badRequest().body("User isn't registered.");
        }

        //Vérification du password
        if (!passwordEncoder.matches(password, userFromDB.get().getPassword())){
            return ResponseEntity.badRequest().body("Email or Password is incorrect.");
        }

        //creation du token
        String generateToken = userService.generateToken(email);
        return ResponseEntity.ok(Collections.singletonMap("token", generateToken));
    }

    /**
     * Return the login  page
     * @param principal
     * @return
     */
    @GetMapping("/me")
    public ResponseEntity<?> me(Principal principal) {
        String email = principal.getName();

        Optional<User> user = userService.findByEmail(email);

        if (user.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }

        return new ResponseEntity<>(new UserDTO(user.get()), HttpStatus.OK);
    }


}
