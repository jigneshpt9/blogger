package com.cisco.blogger.service;

import java.util.logging.Logger;

import com.cisco.blogger.api.DuplicateUserException;
import com.cisco.blogger.api.User;
import com.cisco.blogger.api.UserLoginFailedException;
import com.cisco.blogger.data.UserDAO;
import com.cisco.blogger.data.UserDAOImpl;

public class UserServiceImpl implements UserService {

	UserDAO userdao = UserDAOImpl.getInstance();

	private static UserService userService = null;

	// Set logger
	private Logger logger = Logger.getLogger(getClass().getName());
	
	private UserServiceImpl() {

	}

	public static UserService getInstance() {
		if (userService == null) {
			userService = new UserServiceImpl();
		}
		return userService;
	}

	public void registerUser(User user) {
		logger.info("in registerUser entry");
		if (ifUserExists(user.getEmailId())) {
			throw new DuplicateUserException(
					"Duplicate user register request received with email id" + user.getEmailId());
		}
		System.out.println("in registerUser exit");
		userdao.create(user);

	}

	public void updateUser(User user) {
		userdao.updateUser(user);
	}

	public User validateUser(String emailId, String password) {
		System.out.println("in validate user");
		User user = userdao.validateUser(emailId, password);
		if (null == user) {
				throw new UserLoginFailedException();
		} else {
			return user;
		}
	}

	public boolean ifUserExists(String emailId) {
		User result = userdao.findUser(emailId);
		if (result != null) {
			return true;
		}
		return false;
	}

	@Override
	public User findUser(String emailId) {
		return userdao.findUser(emailId);
	}
}
