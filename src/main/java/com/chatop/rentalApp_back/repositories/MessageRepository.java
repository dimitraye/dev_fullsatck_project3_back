package com.chatop.rentalApp_back.repositories;

import com.chatop.rentalApp_back.models.Message;
import com.chatop.rentalApp_back.models.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
}
