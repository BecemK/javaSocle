package fr.spark.socle.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.spark.socle.entities.User;
import fr.spark.socle.services.AccountService;

@RestController
public class AccountRestController {

	@Autowired
	AccountService accountService;
	
	@PostMapping("/register")
	public User registerUser(@RequestBody RegisterForm userForm) {
		if(!userForm.getPassword().equals(userForm.getRePassword())){
			throw new RuntimeException("vous devez cofirmer votre password !!");
		}
		User user= accountService.findUserByUserName(userForm.getUserName());
		if(user==null)
			throw new RuntimeException("cet utilisateur déjà existe !!");
			User userA= new User();
			user.setUserName(userForm.getUserName());
			user.setPassword(userForm.getPassword());
			return accountService.saveUser(userA);
		
	
	}
}
