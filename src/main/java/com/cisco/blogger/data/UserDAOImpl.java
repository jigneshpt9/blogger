package com.cisco.blogger.data;

import java.util.logging.Logger;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import com.cisco.blogger.api.User;
import com.mongodb.MongoClient;

public class UserDAOImpl extends BasicDAO<User, String> implements UserDAO {

	static String dbName = new String("BloggerDb");
	static MongoClient mongo = new MongoClient("172.31.34.32");
	static Morphia morphia = new Morphia();
	static Datastore datastore = morphia.createDatastore(mongo, dbName);

	private static UserDAO userDAO = null;
	Logger logger = Logger.getLogger(getClass().getName());
	private UserDAOImpl() {
		this(User.class, datastore);
	}

	private UserDAOImpl(Class<User> entityClass, Datastore ds) {
		super(entityClass, ds);
	}

	public static UserDAO getInstance() {

		if (null == userDAO) {
			userDAO = new UserDAOImpl();
		}
		return userDAO;

	}

	@Override
	public void create(User user) {
		save(user);
		logger.info("User registered");
	}

	@Override
	public User validateUser(String emailId, String password) {

		Query<User> userQueryDS = createQuery().field("emailId").equal(emailId).field("password").equal(password);
		User foundUser = userQueryDS.get();
		return foundUser;
	}

	@Override
	public void updateUser(User user) {
		save(user);

	}

	@Override
	public User findUser(String emailId) {
		Query<User> userQueryDS = createQuery().field("emailId").equal(emailId);
		User foundUser = userQueryDS.get();
		return foundUser;
	}

}



