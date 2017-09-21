package com.cisco.blogger.service;

import java.util.List;

import com.cisco.blogger.api.Blog;
import com.cisco.blogger.api.Comment;
import com.cisco.blogger.data.BlogDAO;
import com.cisco.blogger.data.BlogDAOImpl;

public class BlogServiceImpl implements BlogService {

	BlogDAO blogDAO = BlogDAOImpl.getInstance();
	
	private static BlogService blogService = null;

    private BlogServiceImpl() {
    	
    }

    public static BlogService getInstance() {
        if (blogService == null) {
            blogService = new BlogServiceImpl();
        }
        return blogService;
}

	public String createBlog(Blog blog) {

		String blogId = blogDAO.createBlog(blog);

		return blogId;

	}

	public void updateBlog(Blog blog) {
		blogDAO.updateBlog(blog);
	}

	public Blog getBlogById(String blogId) {
		return blogDAO.getBlogById(blogId);
	}

	public List<Blog> listAllBlogs() {

		return blogDAO.listAllBlogs();
	}

	public List<Blog> searchBlogs(String keyword) {
		return blogDAO.searchBlogs(keyword);
	}

	public void addComment(String blogId, Comment comment) {

		blogDAO.addComment(blogId, comment);

	}

}

