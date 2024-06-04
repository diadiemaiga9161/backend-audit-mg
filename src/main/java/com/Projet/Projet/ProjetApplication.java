package com.Projet.Projet;

import com.Projet.Projet.utilisateur.Role.Role;
import com.Projet.Projet.utilisateur.Role.RoleRepository;
import com.Projet.Projet.utilisateur.User.ServiceUtilisateur;
import com.Projet.Projet.utilisateur.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.Projet.Projet.utilisateur.Role.ERole.*;

@SpringBootApplication
public class ProjetApplication {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProjetApplication.class, args);
	}
	@Bean
	CommandLineRunner run(ServiceUtilisateur userService){
		return args -> {
			if (userRepository.findAll().size()==0 && roleRepository.findAll().size()==0){
				Role r1=userService.saveRole(new Role(null, ROLE_SUPERADMIN));
				Role r2 = userService.saveRole(new Role(null, ROLE_ADMIN));
				Role r3 = userService.saveRole(new Role(null, ROLE_AUDITEUR));
				Role r6 = userService.saveRole(new Role(null, ROLE_ENTRPRISE));
//				User u1= userService.saveUser( new User("MAIGA", "Amadou", "12345678", "91613489", "Diatoula","Masculin", "doumaiga3@gmail.com"));
//				userService.addRoleToUser(u1.getTelephone(), r1.getName());
			}
		};
	}
}
