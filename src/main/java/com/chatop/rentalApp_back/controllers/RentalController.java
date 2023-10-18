package com.chatop.rentalApp_back.controllers;

import com.chatop.rentalApp_back.models.Rental;
import com.chatop.rentalApp_back.services.FileStorageService;
import com.chatop.rentalApp_back.services.RentalService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

/**
 * Manage the requests linked to a Rental
 */
@Slf4j
@RestController
@RequestMapping("api/rentals")
@AllArgsConstructor
public class RentalController {
    private final RentalService rentalService;
    private final FileStorageService fileStorageService;



    /**
     * Find all the rentals when calling this endpoint
     * @return a list of rentals
     */
    @GetMapping
    public List<Rental> findAll(Principal principal) {

        return rentalService.findAll();
    }

    /**
     * Find a rental based on its Id when calling this endpoint
     * if the rental does exist in the Data Base.
     * @param id
     * @return rental
     */
    @GetMapping("/{id}")
    public ResponseEntity<Rental> find(@PathVariable Integer id){
        Rental rentalFromDB = rentalService.findById(id).orElse(null);

        if(rentalFromDB == null) {
            log.error("Error : id Rental doesn't exist in the Data Base.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        log.info("Returning the rental's informations");
        return new ResponseEntity<>(rentalFromDB, HttpStatus.OK);
    }

    /**
     * Manage the creation of a rental when calling this endpoint
     * @param name, surface, price, description, picture
     * @return
     * @throws JsonProcessingException
     */
    @PostMapping("/{id}")
    public ResponseEntity<Object> addRental(@RequestParam String name,
                                            @RequestParam int surface,
                                            @RequestParam int price,
                                            @RequestParam("file") MultipartFile picture,
                                            @RequestParam String description) {
        Rental rental =new Rental();

        rental.setName(name);
        rental.setSurface(surface);
        rental.setPrice(price);
        String filename = fileStorageService.storeFile(picture);
        rental.setPicture(filename);
        rental.setDescription(description);
        log.info("Saving the new rental");

        return  new ResponseEntity<>(rentalService.save(rental), HttpStatus.CREATED);
    }

    /**
     * Manage the update of a rental when calling this endpoint
     * @param id
     * @param
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id,
                                         @RequestParam String name,
                                         @RequestParam int surface,
                                         @RequestParam int price,
                                         @RequestParam String description) {
        Optional<Rental> rentalFormDB = rentalService.findById(id);

        if (rentalFormDB.isPresent()) {
            Rental rental = rentalFormDB.get();

            rental.setName(name);
            rental.setSurface(surface);
            rental.setPrice(price);
            rental.setDescription(description);
            log.info("Saving the new rental");
            return  new ResponseEntity<>(rentalService.save(rental), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
