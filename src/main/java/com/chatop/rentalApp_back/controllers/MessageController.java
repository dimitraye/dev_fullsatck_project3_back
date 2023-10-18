package com.chatop.messageApp_back.controllers;

import com.chatop.rentalApp_back.models.Message;
import com.chatop.rentalApp_back.models.MessageResponse;
import com.chatop.rentalApp_back.models.Rental;
import com.chatop.rentalApp_back.models.User;
import com.chatop.rentalApp_back.services.FileStorageService;
import com.chatop.rentalApp_back.services.MessageService;
import com.chatop.rentalApp_back.services.RentalService;
import com.chatop.rentalApp_back.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

/**
 * Manage the requests linked to a Message
 */
@Slf4j
@RestController
@RequestMapping("api/messages")
@AllArgsConstructor
public class MessageController {
    private final MessageService messageService;
    private final UserService userService;
    private final RentalService rentalService;
    private final FileStorageService fileStorageService;


    @GetMapping
    public List<Message> findAll(Principal principal) {
        return messageService.findAll();
    }

    /**
     * Manage the creation of a message when calling this endpoint
     * @param , user, description
     * @return
     * @throws JsonProcessingException
     */
    @PostMapping
    public ResponseEntity<Object> add(@RequestBody MessageResponse message, Principal principal) {

        Message newMessage = new Message();

        newMessage.setMessage(message.getMessage());
        Optional<Rental> newRental = rentalService.findById(message.getRental_id());

        User user = userService.getUserById(message.getUser_id());
        if(user == null || newRental.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        newMessage.setUser(user);
        newMessage.setRental(newRental.get());

        log.info("Saving the new message");

        return  new ResponseEntity<>(messageService.save(newMessage), HttpStatus.CREATED);
    }
}
