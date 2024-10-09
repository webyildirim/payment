package com.acme.payment.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {
	private final String jwtSecret = "secretKeyThatShouldBeVeryLongForSecurityPurpose";
	private final long jwtExpirationMs = 86400000;

	public String generateJwtToken(String username) {
		Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
		String jwtToken = Jwts.builder().setSubject(username).setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(key, SignatureAlgorithm.HS256).compact();
		System.out.println("jwt token " + jwtToken);
		return jwtToken;
	}

	// JWT'den kullanıcı adını alma
	public String getUsernameFromJwtToken(String token) {
		Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateJwtToken(String authToken) {
		try {
			Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(authToken);
			return true;
		} catch (JwtException e) {
			return false;
		}
	}
}