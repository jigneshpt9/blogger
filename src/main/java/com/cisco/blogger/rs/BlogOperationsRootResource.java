package com.cisco.blogger.rs;

import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cisco.blogger.api.Blog;
import com.cisco.blogger.api.BlogCreateException;
import com.cisco.blogger.api.BlogException;
import com.cisco.blogger.api.BlogNotFoundException;
import com.cisco.blogger.api.BlogUpdateException;
import com.cisco.blogger.api.Comment;
import com.cisco.blogger.api.User;
import com.cisco.blogger.service.BlogService;
import com.cisco.blogger.service.BlogServiceImpl;
import com.cisco.blogger.service.JwtTokenNeeded;
import com.cisco.blogger.service.UserService;
import com.cisco.blogger.service.UserServiceImpl;

@Path("/blog")
public class BlogOperationsRootResource {

	BlogService blogService = BlogServiceImpl.getInstance();
	UserService userService = UserServiceImpl.getInstance();

	private Logger logger = Logger.getLogger(getClass().getName());

	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@JwtTokenNeeded
	public Response add(Blog blog) {

		// User userObject = userService.findUser(blog.getBlogOwner().getEmailId());
		// blog.setBlogOwner(userObject);

		if (null == blog || null == blog.getTitle() || blog.getTitle().isEmpty()) {
			logger.info("Tittle is empty block");
			throw new BlogCreateException("Blog couldnot be created");
		}
		String blogId = blogService.createBlog(blog);
		return Response.ok().entity(blog).header("location", "/blogger/blog/view/" + blogId).build();

	}

//	@POST
//	@Path("/{blogId}")
//	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	//@JwtTokenNeeded
//	public Response update(@PathParam("blogId") String blogId, Blog blog) {
//
//		User userObject = userService.findUser(blog.getBlogOwner().getEmailId());
//		
//		if (null != userObject) {
//			blogService.updateBlog(blogId,blog);
//		} else {
//			throw new BlogUpdateException("No user found in database");
//		}
//
//		return Response.ok().entity(blog).header("location", "/blogger/blog/view/" + blogId).build();
//
//	}

	@GET
	@Path("/view/{blogId}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getBlogById(@PathParam("blogId") String blogId) {

		Blog blog = blogService.getBlogById(blogId);
		if (null == blog) {
			throw new BlogNotFoundException("Blog for the blogId doesnot exist");
		}
		return Response.ok().entity(blog).build();

	}

	@GET
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response listAllBlogs() {

		List<Blog> blogs = blogService.listAllBlogs();
		if (null == blogs || blogs.isEmpty()) {
			throw new BlogNotFoundException("Blogs for the keyword doesnot exist");
		}
		return Response.ok().entity(blogs).build();

	}

	@GET
	@Path("/{keyWord}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response searchBlog(@PathParam("keyWord") String keyWord) {
		try {
			List<Blog> blogs = blogService.searchBlogs(keyWord);

			return Response.ok().entity(blogs).build();

		} catch (BlogCreateException bce) {
			return Response.status(400).build();
		} catch (BlogException be) {
			return Response.status(500).build();
		}

	}

	@POST
	@Path("/comment/{blogId}")
	//@JwtTokenNeeded
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response addComment(@PathParam("blogId") String blogId, Comment comment) {

		blogService.addComment(blogId, comment);

		return Response.status(200).build();

	}

}
