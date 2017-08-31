package com.cisco.blogger.data;

import com.cisco.blogger.api.User;

public interface UserDAO {

	public void create(User user);
	public User findUser(String emailId);

	public void updateUser(User user);

	public User validateUser(String emailId, String password);

}
