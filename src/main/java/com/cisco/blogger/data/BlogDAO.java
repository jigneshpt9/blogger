package com.cisco.blogger.data;

import java.util.List;

import com.cisco.blogger.api.Blog;
import com.cisco.blogger.api.Comment;
import com.cisco.blogger.api.Reply;

public interface BlogDAO {
	
	public int createBlog(Blog blog);
	
	public Blog updateBlog(Blog blog);
	public Blog viewBlog(int blogId);
	public List<Blog> listAllBlogs();
	
	/*public List<Blog> searchBlogs(String keyword);
	
	
	
	
	
	public void addComment(int blogId,Comment comment);
	
	public int upvoteComment(int commentId);
	
	public int undoLikeComment(int commentId);
	
	public void replyOnComment(int commentId, Reply reply);
	
	*/

}
