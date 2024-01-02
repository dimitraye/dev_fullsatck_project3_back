package com.chatop.rentalApp_back.repositories;

import com.chatop.rentalApp_back.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


/**
 * Repository interface for managing User entities.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * Finds a user by its username (email).
     *
     * @param email The email of the user to be found.
     * @return An Optional containing the user, or empty if not found.
     */
    Optional<User> findByEmail (String email);
}
