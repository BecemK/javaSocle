package fr.spark.socle.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.spark.socle.entities.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private AuthenticationManager authenticationManager;
	
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		User user= null;
		try {
			user = new ObjectMapper().readValue(request.getInputStream(), User.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		System.out.println("++++++++++++++");
		System.out.println("username:"+ user.getUserName());
		System.out.println("password:"+ user.getPassword());
		
		return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				user.getUserName(),user.getPassword() ));
	}
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		org.springframework.security.core.userdetails.User springUser= 
				(org.springframework.security.core.userdetails.User) authResult.getPrincipal();
		
		String jwt = Jwts.builder()
				.setSubject(springUser.getUsername())
				.setExpiration(
						new Date(System.currentTimeMillis()+ SecurityParams.EXPIRATIONTIME))
				.signWith(SignatureAlgorithm.HS256, SecurityParams.SECRET)
				.claim("roles", springUser.getAuthorities())
				.compact();
		response.addHeader(SecurityParams.HEADER_STRING, SecurityParams.TOKEN_PREFIX +jwt);
		
//		super.successfulAuthentication(request, response, chain, authResult);
	}

}
