package com.chatop.rentalApp_back.config;

import com.chatop.rentalApp_back.services.JpaUserDetailsService;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

/**
 * The SecurityConfig class configures the security settings for the application.
 * It defines security filters, authorization rules, and authentication mechanisms.
 */
@AllArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

  JpaUserDetailsService jpaUserDetailsService;
  private final RsaKeyProperties rsaKeys;

    /**
     * Configures the security filter chain.
     *
     * @param http HttpSecurity object for configuring security settings.
     * @return SecurityFilterChain configured with rules for various endpoints.
     * @throws Exception if there is an error in configuring security settings.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests( auth -> auth
                        .requestMatchers(antMatcher("/pictures/**")).permitAll()
                        .requestMatchers(antMatcher("/api/auth/login")).permitAll()
                        .requestMatchers(antMatcher("/api/auth/register")).permitAll()

                        .requestMatchers(antMatcher("/swagger-ui/**")).permitAll()
                        .requestMatchers(antMatcher("/v3/api-docs/**")).permitAll()
                        .requestMatchers(antMatcher("/swagger-resources/**")).permitAll()
                        .requestMatchers(antMatcher("/configuration/ui")).permitAll()
                        .requestMatchers(antMatcher("/configuration/security")).permitAll()

                        .anyRequest().authenticated()
                )
                .headers(headers -> headers.frameOptions().sameOrigin()
                        .cacheControl().disable()
                )
                .userDetailsService(jpaUserDetailsService)
                .sessionManagement(session ->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    /**
     * Configures the JwtDecoder bean using the RSA public key.
     *
     * @return JwtDecoder configured with the RSA public key.
     */
    @Bean
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(rsaKeys.publicKey()).build();
    }

    /**
     * Configures the JwtEncoder bean using the RSA public and private keys.
     *
     * @return JwtEncoder configured with the RSA public and private keys.
     */
    @Bean
    JwtEncoder jwtEncoder() {
        JWK jwk = new RSAKey.Builder(rsaKeys.publicKey()).privateKey(rsaKeys.privateKey()).build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }

    /**
     * Configures the PasswordEncoder bean for encoding passwords.
     *
     * @return BCryptPasswordEncoder as the PasswordEncoder.
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
      }
}