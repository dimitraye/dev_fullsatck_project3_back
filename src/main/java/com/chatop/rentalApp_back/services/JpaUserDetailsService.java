package com.chatop.rentalApp_back.services;

import com.chatop.rentalApp_back.models.SecurityUser;
import com.chatop.rentalApp_back.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Service Spring Security qui gère l'authentification sécurisée du user
 */
@Service
public class JpaUserDetailsService implements UserDetailsService{

  private final UserRepository userRepository;

  public JpaUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  /**
   * Load a user by its username.
   * @param email
   * @return
   * @throws UsernameNotFoundException
   */
  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return userRepository
            .findByEmail(email)
            .map(SecurityUser::new)
            .orElseThrow(() -> new UsernameNotFoundException("Email not found: " + email));
  }
}
