package com.chatop.rentalApp_back.services;

import com.chatop.rentalApp_back.models.SecurityUser;
import com.chatop.rentalApp_back.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Service that handles secure user authentication for Spring Security.
 */
@Service
public class JpaUserDetailsService implements UserDetailsService{
  private final UserRepository userRepository;

  /**
   * Constructs a JpaUserDetailsService with the specified UserRepository.
   *
   * @param userRepository The UserRepository to retrieve user information.
   */
  public JpaUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  /**
   * Loads a user by its username (email).
   *
   * @param email The email of the user.
   * @return A UserDetails object representing the authenticated user.
   * @throws UsernameNotFoundException If the user with the given email is not found.
   */
  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return userRepository
            .findByEmail(email)
            .map(SecurityUser::new)
            .orElseThrow(() -> new UsernameNotFoundException("Email not found: " + email));
  }
}
