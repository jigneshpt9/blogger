package com.cisco.blogger.rs;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cisco.blogger.api.DuplicateUserException;
import com.cisco.blogger.api.User;
import com.cisco.blogger.api.UserException;
import com.cisco.blogger.service.UserService;
import com.cisco.blogger.service.UserServiceImpl;


@Path("/user")
public class UserRootResource {
	
	UserService userService = new UserServiceImpl();
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON})
	@Produces({ MediaType.APPLICATION_JSON})
	public Response addUser(User user) {
		try { 	
			System.out.println("in addUser");
		    userService.registerUser(user);
		    return Response.status(201).entity(user).header("location", "/user" + user.getEmailId()).build();

		} catch(DuplicateUserException due) {
			return Response.status(500).build();
		}
	}

	@GET
	@Path("/{emailId}/login/{key}")
	@Consumes({ MediaType.APPLICATION_JSON})
	@Produces({ MediaType.APPLICATION_JSON})	
	public Response userlogin(@PathParam("emailId") String emailId, @PathParam("key") String key) {
	      System.out.println("in userlogin");		  
		  User user =  userService.findUser(emailId);
		  //TBD for password authentication
		  
		  if (user == null)
			  return Response.status(404).build();
		  else {
			    user = userService.validateUser(emailId, key);
			    if (user != null ){
			        return Response.status(201).entity(user).header("location", "/user" + user.getEmailId()).build();
			    }
			    else
			    {
				     return Response.status(401).build();
			    }
		  }	
	}

		
	@POST
	@Path("/{emailId}")
	@Consumes({ MediaType.APPLICATION_JSON})
	@Produces({ MediaType.APPLICATION_JSON})	
	public Response updateUser(@PathParam("emailId") String emailId, User user ) {
		try { 	
	      System.out.println("in updateuser");		  
		  userService.updateUser(user);
		  return Response.ok().entity(user).header("location", "/user" + user.getEmailId()).build();

		} catch(UserException ue) {
			return Response.status(500).build();
		}
	}
	

}




