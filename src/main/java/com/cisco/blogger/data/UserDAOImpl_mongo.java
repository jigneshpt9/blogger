package com.cisco.blogger.data;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

import com.cisco.blogger.api.User;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;

public class UserDAOImpl_mongo implements UserDAO {

	 static String dbName = new String("bloggerDb");    
	 static MongoClient mongo = new MongoClient("172.31.34.32");
     static Morphia morphia = new Morphia();
     static Datastore datastore = morphia.createDatastore( mongo, dbName); 	     
	
	public UserDAOImpl_mongo() {
		    
		     morphia.mapPackage("com.cisco.blogger.data");
		         
	}
				
	public void create(User user) {
	    datastore.save(user);
		System.out.println("User registered");
	}

	public User findUser(String emailId){

		Query<User> userQueryDS = datastore.createQuery(User.class);
		userQueryDS.field("emailId").equal(emailId);
		User foundUser = userQueryDS.get();
		return foundUser;
	}
	
	public void updateUser(User user) {
		  datastore.save(user);
	    //em.merge(user);
	   	//em.getTransaction().commit();
	}

	public User validateUser(String emailId, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
