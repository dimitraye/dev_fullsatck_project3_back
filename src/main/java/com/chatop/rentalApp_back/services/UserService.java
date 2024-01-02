package com.chatop.rentalApp_back.services;

import com.chatop.rentalApp_back.models.User;
import com.chatop.rentalApp_back.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

/**
 * Service class for managing users.
 */
@AllArgsConstructor
@Service
public class UserService {
    private UserRepository userRepository;
    private final JwtEncoder encoder;

    /**
     * Retrieves a user by ID.
     *
     * @param id The ID of the user.
     * @return The user with the specified ID.
     */
    public User getUserById(Integer id) {
        return userRepository.getById(id);
    }


    /**
     * Saves a new user.
     *
     * @param user The user to be saved.
     * @return The saved user.
     */
    public User save(User user) {
        return userRepository.save(user);
    }

    /**
     * Generates a JWT token for the specified email.
     *
     * @param email The email for which the token should be generated.
     * @return The generated JWT token.
     */
    public String generateToken(String email ) {
        Instant now = Instant.now();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("security-service")
                .issuedAt(now)
                .expiresAt(now.plus(5, ChronoUnit.HOURS))
                .subject(email)
                .claim("scope", "read")
                .build();
        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    /**
     * Retrieves a user by email.
     *
     * @param email The email of the user.
     * @return An Optional containing the user, or empty if not found.
     */
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
