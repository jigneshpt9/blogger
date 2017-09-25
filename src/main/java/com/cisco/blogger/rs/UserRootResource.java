package com.cisco.blogger.rs;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.cisco.blogger.api.User;
import com.cisco.blogger.api.UserException;
import com.cisco.blogger.service.AuthorizationServiceImpl;
import com.cisco.blogger.service.AutorizationService;
import com.cisco.blogger.service.JwtTokenNeeded;
import com.cisco.blogger.service.UserService;
import com.cisco.blogger.service.UserServiceImpl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Path("/user")
public class UserRootResource {
	
	UserService userService = UserServiceImpl.getInstance();
	AutorizationService authService = AuthorizationServiceImpl.getInstance();
	@Context
	UriInfo uriInfo;
	Logger logger = Logger.getLogger(getClass().getName());
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON})
	@Produces({ MediaType.APPLICATION_JSON})
	public Response addUser(User user) {
		 	
			System.out.println("in addUser");
		    userService.registerUser(user);
		    return Response.status(201).entity(user).header("location", "/user" + user.getEmailId()).build();

		
	}

	@GET
	@Path("/{emailId}/login/{key}")
	@Consumes({ MediaType.APPLICATION_JSON})
	@Produces({ MediaType.APPLICATION_JSON})	
	public Response userlogin(@PathParam("emailId") String emailId, @PathParam("key") String key) {
	      System.out.println("in userlogin");		  
		  User user =  userService.findUser(emailId);
		  
		  if (user == null)
			  return Response.status(404).build();
		  else {
			    user = userService.validateUser(emailId, key);
			    String tokenGenerated = issueToken(emailId);
				logger.info("Token generated:"+tokenGenerated);
				
				// Return the token on the response
		        return Response.ok().entity(user).header(AUTHORIZATION, "Bearer " + tokenGenerated).build();	    
		  
		  }	
	}

		
	@POST
	@Path("/{emailId}")
	@Consumes({ MediaType.APPLICATION_JSON})
	@Produces({ MediaType.APPLICATION_JSON})	
	@JwtTokenNeeded
	public Response updateUser(@PathParam("emailId") String emailId, User user ) {
		try { 	
	      System.out.println("in updateuser");		  
		  userService.updateUser(user);
		  return Response.ok().entity(user).header("location", "/user" + user.getEmailId()).build();

		} catch(UserException ue) {
			return Response.status(500).build();
		}
	}
	
	/**
	 * Method to generate token to be issued to user for further JWT.
	 * @param userId
	 * @return
	 */

	private String issueToken(String userId) {
        Key key = authService.generateKey();
        String jwtToken = Jwts.builder()
                .setSubject(userId)
                .setIssuer(uriInfo.getAbsolutePath().toString())
                .setIssuedAt(new Date())
                .setExpiration(toDate(LocalDateTime.now().plusMinutes(15L)))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
        return jwtToken;
    }
	
	 private Date toDate(LocalDateTime localDateTime) {
	        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}
	
}




