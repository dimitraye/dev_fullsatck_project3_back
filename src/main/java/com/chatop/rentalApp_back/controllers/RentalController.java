package com.chatop.rentalApp_back.controllers;

import com.chatop.rentalApp_back.dto.RentalDTO;
import com.chatop.rentalApp_back.models.Rental;
import com.chatop.rentalApp_back.models.User;
import com.chatop.rentalApp_back.services.FileStorageService;
import com.chatop.rentalApp_back.services.RentalService;
import com.chatop.rentalApp_back.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Manage the requests linked to a Rental
 */
@Slf4j
@RestController
@RequestMapping("/api/rentals")
@AllArgsConstructor
public class RentalController {
    private final RentalService rentalService;
    private final FileStorageService fileStorageService;

    private final UserService userService;



    /**
     * Find all the rentals when calling this endpoint
     * @return a list of rentals
     */
    @GetMapping
    public ResponseEntity<?> findAll(Principal principal) {
        List<Rental> rentals = rentalService.findAll();
        Stream<RentalDTO> rentalDTOStream = rentals.stream().map(rental -> new RentalDTO(rental));
        return new ResponseEntity<>(Map.of("rentals",rentalDTOStream), HttpStatus.OK);
    }

    /**
     * Find a rental based on its Id when calling this endpoint
     * if the rental does exist in the Data Base.
     * @param id
     * @return rental
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable Integer id){
        Rental rentalFromDB = rentalService.findById(id).orElse(null);

        if(rentalFromDB == null) {
            log.error("Error : id Rental doesn't exist in the Data Base.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        log.info("Returning the rental's informations");
        return new ResponseEntity<>(new RentalDTO(rentalFromDB), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addRental(@RequestParam String name,
                                            @RequestParam double surface,
                                            @RequestParam double price,
                                            @RequestParam MultipartFile picture,
                                            @RequestParam String description,
                                            Principal principal) {

        String email = principal.getName();
        Optional<User> user = userService.findByEmail(email);
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().body("Owner not found");
        }


        Rental rental =new Rental();
        rental.setOwner(user.get());
        rental.setName(name);
        rental.setSurface(surface);
        rental.setPrice(price);

        // Handle the picture (store it and get its path)
        String fileName = fileStorageService.storeFile(picture);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/pictures/")
                .path(fileName)
                .toUriString();


        rental.setPicture(fileDownloadUri);
        rental.setDescription(description);
        log.info("Saving the new rental");
        rentalService.save(rental);
        return  new ResponseEntity<>(Map.of("message","Rental created !"), HttpStatus.CREATED);
    }

    /**
     * Manage the update of a rental when calling this endpoint
     * @param id
     * @param
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id,
                                         @RequestParam String name,
                                         @RequestParam double surface,
                                         @RequestParam double price,
                                         @RequestParam String description) {
        Optional<Rental> rentalFormDB = rentalService.findById(id);

        if (rentalFormDB.isPresent()) {
            Rental rental = rentalFormDB.get();

            rental.setName(name);
            rental.setSurface(surface);
            rental.setPrice(price);
            rental.setDescription(description);
            log.info("Saving the new rental");
            rentalService.save(rental);
            return  new ResponseEntity<>(Map.of("message","Rental updated !"), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
