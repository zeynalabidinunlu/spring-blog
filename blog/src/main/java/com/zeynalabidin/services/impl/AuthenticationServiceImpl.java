package com.zeynalabidin.services.impl;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap.KeySetView;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.zeynalabidin.services.AuthenticationService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

	private AuthenticationManager authenticationManager;
	private final UserDetailsService userDetailsService;

	@Value("${jwt.secret}")
	private String secretKey;

	private final Long jwtExpireMsLong = 86400000L;

	@Override
	public UserDetails authenticate(String email, String password) {

		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		return userDetailsService.loadUserByUsername(email);
	}

	@Override
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
	return	Jwts.builder().setClaims(claims).
		setSubject(userDetails.getUsername()).
		setIssuedAt(new Date(System.currentTimeMillis())).
		setExpiration(new Date(System.currentTimeMillis()+jwtExpireMsLong)).
		signWith(getSingingKey(),SignatureAlgorithm.HS256).
		compact();
	}

	
	private Key getSingingKey() {
		byte[] keyBytes = secretKey.getBytes();
	return Keys.hmacShaKeyFor(keyBytes);
	}
}
