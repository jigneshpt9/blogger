package com.cisco.blogger.service;

import java.util.List;

import com.cisco.blogger.api.Blog;
import com.cisco.blogger.api.Comment;
import com.cisco.blogger.api.Reply;
import com.cisco.blogger.data.BlogDAO;
import com.cisco.blogger.data.BlogDAOImpl;

public class BlogServiceImpl implements BlogService {

	BlogDAO blogDAO = new BlogDAOImpl();

	public int createBlog(Blog blog) {

		int blogId = blogDAO.createBlog(blog);

		return blogId;

	}

	public Blog updateBlog(Blog blog) {
		Blog updatedBlog = blogDAO.updateBlog(blog);
		return updatedBlog;
	}

	public List<Blog> searchBlogs(String keyword) {
		return blogDAO.searchBlogs(keyword);
	}

	public Blog viewBlog(int blogId) {
		return blogDAO.viewBlog(blogId);
	}

	public List<Blog> listAllBlogs() {

		return blogDAO.listAllBlogs();
	}

	public void addComment(int blogId, Comment comment) {

		blogDAO.addComment(blogId, comment);

	}

	public int upvoteComment(int commentId) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int undoLikeComment(int commentId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void replyOnComment(int commentId, Reply reply) {
		blogDAO.replyOnComment(commentId, reply);
		
		
	}

}
