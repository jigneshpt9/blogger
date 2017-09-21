package com.cisco.blogger.service;

import java.io.IOException;
import java.security.Key;
import java.util.logging.Logger;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import io.jsonwebtoken.Jwts;
@Provider
@JwtTokenNeeded
@Priority(Priorities.AUTHENTICATION)
public class JwtTokenNeededFilter implements ContainerRequestFilter {

	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Override
	public void filter(ContainerRequestContext requestCtx) throws IOException {
		String token = null;
		// Get the HTTP Authorization header from the request
		String authorizationHeader = requestCtx.getHeaderString(HttpHeaders.AUTHORIZATION);
		logger.info("Auth Header : " + authorizationHeader);

		if(null!=authorizationHeader) {
		// Extract the token from the HTTP Authorization header
		 token = authorizationHeader.substring("Bearer".length()).trim();
		 logger.info(token);
	}
		
		try {
//			// Validate the token
		     Key key = AuthorizationServiceImpl.getInstance().generateKey();

			Jwts.parser().setSigningKey(key).parseClaimsJws(token);
			
		logger.info("valid token : " + token);

	} catch (Exception e) {
		logger.severe("invalid token : " + token);
		requestCtx.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());	}
	}

}
