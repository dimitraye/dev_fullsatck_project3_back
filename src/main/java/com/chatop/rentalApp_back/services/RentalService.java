package com.chatop.rentalApp_back.services;

import com.chatop.rentalApp_back.models.Rental;
import com.chatop.rentalApp_back.repositories.RentalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentalService {
    private RentalRepository rentalRepository;

    //getAll
    public List<Rental> findAll() {
        return rentalRepository.findAll();
    }

    //getOne
    public Optional<Rental> findById(Integer id) {
        return rentalRepository.findById(id);
    }

    //Create
    public Rental save(Rental rental) {
        return rentalRepository.save(rental);
    }

    //Update
    public Rental updatePatient(Rental rental) {
        return rentalRepository.save(rental);
    }
}
