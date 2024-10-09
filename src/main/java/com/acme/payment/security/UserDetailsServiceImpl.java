package com.acme.payment.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;
import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if ("admin".equals(username)) {
			return new User("admin", "password", new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("user not found: " + username);
		}
	}
}