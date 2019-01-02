package fr.spark.socle.services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.spark.socle.entities.User;

@Service
public class UserDetailsSeviceImpl implements UserDetailsService {
	@Autowired
	private AccountService accountService;
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = accountService.findUserByUserName(userName);
		if(user==null) {
			throw new UsernameNotFoundException(userName);
		}
		Collection<GrantedAuthority> authorities= new ArrayList<>();
		user.getRoles().forEach(r->{
			authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
		});
		
		return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),authorities);
	}

}
