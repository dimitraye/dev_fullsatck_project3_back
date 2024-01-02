package com.chatop.rentalApp_back.services;

import com.chatop.rentalApp_back.models.Message;
import com.chatop.rentalApp_back.models.Rental;
import com.chatop.rentalApp_back.repositories.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing messages.
 */
@AllArgsConstructor
@Service
public class MessageService {
    private MessageRepository messageRepository;

    /**
     * Saves a new message.
     *
     * @param message The message to be saved.
     * @return The saved message.
     */
    public Message save(Message message) {
        return messageRepository.save(message);
    }

    /**
     * Retrieves all messages.
     *
     * @return A list of all messages.
     */
    public List<Message> findAll() {
        return messageRepository.findAll();
    }
}
