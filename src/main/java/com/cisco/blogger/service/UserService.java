package com.cisco.blogger.service;

import com.cisco.blogger.api.User;

public interface UserService {

	public void registerUser(User user);
	
	public void updateUser(User user);
	
	public User validateUser(String emailId, String password);
	
	public boolean ifUserExists(String emaildId);
	
	public User findUser(String emailId);
	
	
}
