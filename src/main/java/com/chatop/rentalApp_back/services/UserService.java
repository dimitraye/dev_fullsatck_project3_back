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

@AllArgsConstructor
@Service
public class UserService {
    private UserRepository userRepository;
    private final JwtEncoder encoder;

    //Create Message
    public User getUserById(Integer id) {
        return userRepository.getById(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

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

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
