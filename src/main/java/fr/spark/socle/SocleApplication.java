package fr.spark.socle;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import fr.spark.socle.dao.TaskRepository;
import fr.spark.socle.entities.Role;
import fr.spark.socle.entities.Task;
import fr.spark.socle.entities.User;
import fr.spark.socle.services.AccountService;

@SpringBootApplication
public class SocleApplication implements CommandLineRunner {
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private AccountService accountService;
	
	public static void main(String[] args) {
		SpringApplication.run(SocleApplication.class, args);
	}
	

	@Override
	public void run(String... args) throws Exception {
		
		accountService.saveUser(new User("admin", "1234"));
		accountService.saveUser(new User("user", "1234"));
		accountService.saveRole(new Role("ADMIN"));
		accountService.saveRole(new Role("USER"));
		
		accountService.addRoleToUser("ADMIN", "admin");
		accountService.addRoleToUser("USER", "user");
		
		
		Stream.of("T1", "T2", "T3").forEach(t-> {
			taskRepository.save(new Task(t));
		});
		System.out.println("***********************");
		taskRepository.findAll().forEach(t-> {
			System.out.println(t.getTaskName());
		});
	}
	
	@Bean
	public BCryptPasswordEncoder getBCPE() {
		return new BCryptPasswordEncoder();
	}

}

