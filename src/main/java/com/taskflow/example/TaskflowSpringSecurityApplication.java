package com.taskflow.example;

import com.taskflow.example.model.AppUser;
import com.taskflow.example.model.enums.Role;
import com.taskflow.example.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class TaskflowSpringSecurityApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(TaskflowSpringSecurityApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		AppUser adminAccount = userRepository.findByRole(Role.MANAGER);
		if (null  == adminAccount ) {
			AppUser user = AppUser.builder()
					.email("admin@gmail.com")
					.firstname("admin")
					.lastname("admin")
					.role(Role.MANAGER)
					.password(new BCryptPasswordEncoder().encode("admin"))
					.build();
			userRepository.save(user);
		}


	}
}
