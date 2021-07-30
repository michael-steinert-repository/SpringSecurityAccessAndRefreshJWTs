package com.example.SpringSecurityAccessAndRefreshJWT;

import com.example.SpringSecurityAccessAndRefreshJWT.model.AppUser;
import com.example.SpringSecurityAccessAndRefreshJWT.model.UserRole;
import com.example.SpringSecurityAccessAndRefreshJWT.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@SpringBootApplication
public class SpringSecurityAccessAndRefreshJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityAccessAndRefreshJwtApplication.class, args);
	}

	/* The Runners runs after the Application was initialized */
	@Bean
	CommandLineRunner commandLineRunner(UserService userService) {
		return args -> {
			userService.saveRole(new UserRole(null, "ROLE_USER"));
			userService.saveRole(new UserRole(null, "ROLE_ADMIN"));

			userService.saveUser(new AppUser(null, "Michael", "Michael", "123", Collections.EMPTY_LIST));
			userService.saveUser(new AppUser(null, "Marie", "Marie", "123", Collections.EMPTY_LIST));

			userService.addRoleToUser("Michael", "ROLE_USER");
			userService.addRoleToUser("Michael", "ROLE_ADMIN");
			userService.addRoleToUser("Marie", "ROLE_USER");
		};
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
