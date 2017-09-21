package com.cisco.blogger.service;

import java.security.Key;

import com.cisco.blogger.api.User;

public interface AutorizationService {
	public Key generateKey();
	
	public boolean verifyToken(String jwtToken);
	
	public User authenticateUser(String user, String password);
}
