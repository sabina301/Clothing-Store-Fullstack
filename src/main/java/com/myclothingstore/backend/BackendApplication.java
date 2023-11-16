package com.myclothingstore.backend;

import com.myclothingstore.backend.entity.CartEntity;
import com.myclothingstore.backend.entity.UserEntity;
import com.myclothingstore.backend.model.Role;
import com.myclothingstore.backend.repository.RoleRepository;
import com.myclothingstore.backend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncode){
		return args ->{
			if(roleRepository.findByAuthority("ADMIN").isPresent()) return;
			Role adminRole = roleRepository.save(new Role("ADMIN"));
			roleRepository.save(new Role("USER"));
			Set<Role> roles = new HashSet<>();
			roles.add(adminRole);
			UserEntity admin =
					new UserEntity(1L, "admin", passwordEncode.encode("password"), roles, new CartEntity(), new HashSet<>());
			userRepository.save(admin);
		};
	}

}
