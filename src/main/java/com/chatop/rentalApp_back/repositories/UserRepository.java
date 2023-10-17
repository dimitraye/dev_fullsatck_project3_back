package com.chatop.rentalApp_back.repositories;

import com.chatop.rentalApp_back.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * Find a user by its username(email).
     * @param email
     * @return the user.
     */
    Optional<User> findByEmail (String email);
}
