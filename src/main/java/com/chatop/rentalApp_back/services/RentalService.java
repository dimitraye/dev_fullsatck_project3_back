package com.chatop.rentalApp_back.services;

import com.chatop.rentalApp_back.models.Rental;
import com.chatop.rentalApp_back.repositories.RentalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing rentals.
 */
@AllArgsConstructor
@Service
public class RentalService {

    private RentalRepository rentalRepository;

    /**
     * Retrieves all rentals.
     *
     * @return A list of all rentals.
     */
    public List<Rental> findAll() {
        return rentalRepository.findAll();
    }

    /**
     * Retrieves a rental by its ID.
     *
     * @param id The ID of the rental.
     * @return An Optional containing the rental, or empty if not found.
     */
    public Optional<Rental> findById(Integer id) {
        return rentalRepository.findById(id);
    }

    /**
     * Saves a new rental.
     *
     * @param rental The rental to be saved.
     * @return The saved rental.
     */
    public Rental save(Rental rental) {
        return rentalRepository.save(rental);
    }
}
