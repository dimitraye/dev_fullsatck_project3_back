package com.chatop.rentalApp_back;

import com.chatop.rentalApp_back.config.RsaKeyProperties;
import com.chatop.rentalApp_back.models.Message;
import com.chatop.rentalApp_back.models.Rental;
import com.chatop.rentalApp_back.models.User;
import com.chatop.rentalApp_back.repositories.UserRepository;
import com.chatop.rentalApp_back.services.MessageService;
import com.chatop.rentalApp_back.services.RentalService;
import com.chatop.rentalApp_back.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class RentalAppBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentalAppBackApplication.class, args);
	}
/*
	@Bean
	CommandLineRunner runner(PasswordEncoder encoder,
							 UserService userService,
							 RentalService rentalService,
							 MessageService messageService) {

		return args -> {
			//--------------
			List<User> users = new ArrayList<>();
			List<User> usersFromDB = new ArrayList<>();
			List<Rental> rentals = new ArrayList<>();


			List<String> firstnames = List.of("user", "admin", "Jean", "Anna", "Olivier", "Sophie", "Omar", "Greg");
			for (int i =0; i < 8; i++) {
				User user = new User();
				user.setName(firstnames.get(i));
				user.setPassword(firstnames.get(i));
				user.setEmail(firstnames.get(i).toLowerCase() + "@mail.com");
				//user.setPassword(encoder.encode(firstnames.get(i)));
				log.info("Tour: " + i);
				log.info("user:"+ user.getEmail());
				users.add(user);
			}
			try {
				log.info("users:"+ users);
				users.forEach(user -> {
					User userFromDB = userService.save(user);
					System.out.println("User saved, " + userFromDB);

					usersFromDB.add(userFromDB);
				});
				log.info("usersFromDB:"+ usersFromDB);

				System.out.println("Users Saved!");

			} catch (Exception e){
				System.out.println("Unable to save users: " + e.getMessage());
			}

			//Add Buddies to users
			Rental rental = new Rental();
			rental.setName("Appartement de Luxe");
			rental.setSurface(100);
			rental.setPrice(1500);
			rental.setPicture("image1.jpg");
			rental.setDescription("Un magnifique appartement de luxe avec vue sur la mer.");
			// Supposons que vous ayez un objet User appelé "owner" que vous avez créé précédemment
			rental.setOwner(usersFromDB.get(0));
			rentalService.save(rental);


			Message message = new Message();
			message.setRental(rental);
			message.setUser(usersFromDB.get(1));
			message.setMessage("Je voudrais louer cet appartement !");
			messageService.save(message);
		};
	}*/

}
