package com.cisco.blogger.service;

import java.util.List;

import com.cisco.blogger.api.Blog;
import com.cisco.blogger.api.Comment;
import com.cisco.blogger.api.Reply;

public interface BlogService {
	
	public int createBlog(Blog blog);
	
	public Blog updateBlog(Blog blog);
	
	public List<Blog> searchBlogs(String keyword);
	
	public Blog viewBlog(int blogId);
	
	public List<Blog> listAllBlogs();
	
	public void addComment(int blogId,Comment comment);
	
	public int upvoteComment(int commentId);
	
	public int undoLikeComment(int commentId);
	
	public void replyOnComment(int commentId, Reply reply);

}
