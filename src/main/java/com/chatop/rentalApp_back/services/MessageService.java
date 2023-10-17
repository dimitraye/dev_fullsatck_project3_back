package com.chatop.rentalApp_back.services;

import com.chatop.rentalApp_back.models.Message;
import com.chatop.rentalApp_back.repositories.MessageRepository;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    private MessageRepository messageRepository;

    //Create Message
    public Message save(Message message) {
        return messageRepository.save(message);
    }
}
