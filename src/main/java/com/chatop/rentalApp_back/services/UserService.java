package com.chatop.rentalApp_back.services;

import com.chatop.rentalApp_back.models.User;
import com.chatop.rentalApp_back.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    //Create Message
    public User getUserById(Integer id) {
        return userRepository.getById(id);
    }
}
