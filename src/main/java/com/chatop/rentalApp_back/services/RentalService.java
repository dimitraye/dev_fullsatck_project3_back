package com.chatop.rentalApp_back.services;

import com.chatop.rentalApp_back.models.Rental;
import com.chatop.rentalApp_back.repositories.RentalRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
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

    public String paramTojson(String paramIn) {
        if (paramIn.startsWith("{")) {
            //log.info("Param already in Json format");
            return paramIn;
        }
        paramIn = paramIn.replaceAll("=", "\":\"");
        paramIn = paramIn.replaceAll("&", "\",\"");
        return "{\"" + paramIn + "\"}";
    }
}
