package fr.spark.socle.services;

import fr.spark.socle.entities.Role;
import fr.spark.socle.entities.User;

public interface AccountService {
 
	public User saveUser(User user);
	public Role saveRole(Role role);
	public void addRoleToUser(String role, String userName);
	public User findUserByUserName(String userName);
}
