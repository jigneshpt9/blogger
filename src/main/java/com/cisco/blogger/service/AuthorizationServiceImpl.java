package com.cisco.blogger.service;

import java.security.Key;
import java.util.logging.Logger;

import javax.crypto.spec.SecretKeySpec;

import com.cisco.blogger.api.User;
import com.cisco.blogger.data.UserDAO;
import com.cisco.blogger.data.UserDAOImpl;

public class AuthorizationServiceImpl implements AutorizationService {

    Logger logger = Logger.getLogger(getClass().getName());
    
    UserDAO userDAO = UserDAOImpl.getInstance();
	private static AutorizationService authService = null;

	private AuthorizationServiceImpl() {

	}

	public static AutorizationService getInstance() {
		if (authService == null) {
			authService = new AuthorizationServiceImpl();
		}
		return authService;
	}

	@Override
	public Key generateKey() {
		String keyString = "cmad-blogger-world";
		Key key = new SecretKeySpec(keyString.getBytes(), 0, keyString.getBytes().length, "DES");
		logger.info("Key generated: " + key);
		return key;
	}

	@Override
	public boolean verifyToken(String jwtToken) {
		// TODO Auto-generated method stub
		return false;
	}
	

//
//	public static void main(String args[]) {
//
//		Key generatedKey = AuthorizationServiceImpl.getInstance().generateKey();
//		
//		System.out.println("generatedKey"+generatedKey.getEncoded());
//
//	}

	@Override
	public User authenticateUser(String emailId, String password) {
		
	return userDAO.validateUser(emailId, password);
	
	}
}
