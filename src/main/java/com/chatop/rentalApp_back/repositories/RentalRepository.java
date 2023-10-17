package com.chatop.rentalApp_back.repositories;

import com.chatop.rentalApp_back.models.Rental;
import com.chatop.rentalApp_back.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {
}
