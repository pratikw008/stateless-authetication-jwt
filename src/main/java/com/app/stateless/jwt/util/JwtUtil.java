package com.app.stateless.jwt.util;

import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	
	@Value("${app.secret}")
	private String secret;
	
	/**
	 * 
	 * @param id pk
	 * @param subject username
	 * @return Token
	 */
	public String generateToken(String id, String subject) {
		return Jwts.builder()
				   .setId(id)
				   .setSubject(subject)
				   .setIssuer("Amazon")
				   .setIssuedAt(new Date(System.currentTimeMillis()))
				   .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(10)))
				   .signWith(SignatureAlgorithm.HS256, Base64.getEncoder()
						   									 .encode(secret.getBytes()))
				   .compact();
	}
	
	/**
	 * Get Claims Using Token,Secret
	 * @param token
	 * @return Claims
	 */
	public Claims getClaims(String token) {
		return Jwts.parser()
				   .setSigningKey(Base64.getEncoder()
						   				.encode(secret.getBytes()))
				   .parseClaimsJws(token)
				   .getBody();
	}
	
	/**
	 * Get Expiry Date
	 * @param token
	 * @return ExpDate
	 */
	public Date getExpDate(String token) {
		return getClaims(token).getExpiration();
	}

	/**
	 * Get Subject (username)
	 * @param token
	 * @return Subject
	 */
	public String getUsername(String token) {
		return getClaims(token).getSubject();
	}
	
	/**
	 * Validate ExpDate
	 * @param token
	 * @return true if getExpDate comes before currentDate
	 */
	public boolean isTokenExp(String token) {
		return getExpDate(token).before(new Date(System.currentTimeMillis()));
	}
	
	/**
	 * 
	 * @param token
	 * @param usernameFrmDb
	 * @return true if username/subject equals unFrmDb & token not Exp 
	 */
	public boolean validToken(String token, String usernameFrmDb) {
		return (this.getUsername(token).equals(usernameFrmDb) && !isTokenExp(token));
	}
}
