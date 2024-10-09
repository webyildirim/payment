package com.acme.payment.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtils jwtUtils;

	@PostMapping("/login")
	public String authenticateUser(@RequestBody LoginRequest loginRequest) {

		try {
			System.out.println("request ok");
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
			System.out.println("authentication ok");
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtUtils.generateJwtToken(((UserDetails) authentication.getPrincipal()).getUsername());
			return jwt;

		} catch (BadCredentialsException e) {
			return "Invalid username or password";
		} catch (Exception e) {
			return "exception" + e.getMessage();
		}
	}
}