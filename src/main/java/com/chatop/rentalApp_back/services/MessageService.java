package com.chatop.rentalApp_back.services;

import com.chatop.rentalApp_back.models.Message;
import com.chatop.rentalApp_back.models.Rental;
import com.chatop.rentalApp_back.repositories.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class MessageService {
    private MessageRepository messageRepository;

    //Create Message
    public Message save(Message message) {
        return messageRepository.save(message);
    }

    public List<Message> findAll() {
        return messageRepository.findAll();
    }
}
