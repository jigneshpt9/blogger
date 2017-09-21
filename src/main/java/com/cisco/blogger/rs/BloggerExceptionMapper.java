package com.cisco.blogger.rs;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.cisco.blogger.api.BlogCreateException;
import com.cisco.blogger.api.BlogException;
import com.cisco.blogger.api.BlogNotFoundException;
import com.cisco.blogger.api.BlogUpdateException;
import com.cisco.blogger.api.DuplicateUserException;
import com.cisco.blogger.api.UserException;

@Provider
public class BloggerExceptionMapper implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable t) {
		t.printStackTrace();
		if (t instanceof DuplicateUserException) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} if (t instanceof UserException) {
			return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
		} else if (t instanceof BlogCreateException) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else if (t instanceof BlogUpdateException) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else if (t instanceof BlogNotFoundException) {
			return Response.status(Response.Status.NO_CONTENT).build();
		}  else if (t instanceof BlogException) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else if (t instanceof NotAuthorizedException) {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		} else if (t instanceof NotFoundException) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

}
