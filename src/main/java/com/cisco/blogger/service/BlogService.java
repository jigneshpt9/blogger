package com.cisco.blogger.service;

import java.util.List;

import com.cisco.blogger.api.Blog;
import com.cisco.blogger.api.Comment;

public interface BlogService {
	
	public String createBlog(Blog blog);
	
//	public void updateBlog(String blogId, Blog blog);
	
	public Blog getBlogById(String blogId);
	public List<Blog> listAllBlogs();
	
	
	public List<Blog> searchBlogs(String keyword);
	
	
	
	public void addComment(String blogId,Comment comment);
	

}
