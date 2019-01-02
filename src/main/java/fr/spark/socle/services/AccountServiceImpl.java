package fr.spark.socle.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.spark.socle.dao.RoleRepository;
import fr.spark.socle.dao.UserRepository;
import fr.spark.socle.entities.Role;
import fr.spark.socle.entities.User;

@Service
@Transactional
public class AccountServiceImpl implements AccountService{

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public User saveUser(User user) {
		String passw = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(passw);
		return userRepository.save(user); 
	}

	@Override
	public Role saveRole(Role role) {
		// TODO Auto-generated method stub
		return roleRepository.save(role);
	}

	@Override
	public void addRoleToUser(String roleName, String userName) {
		User user= userRepository.findByUserName(userName);
		Role role = roleRepository.findByRoleName(roleName);
		user.getRoles().add(role);
		
		
	}

	@Override
	public User findUserByUserName(String userName) {
		
		return userRepository.findByUserName(userName);
	}

	public BCryptPasswordEncoder getbCryptPasswordEncoder() {
		return bCryptPasswordEncoder;
	}

	public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

}
