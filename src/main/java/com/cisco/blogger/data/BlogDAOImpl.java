package com.cisco.blogger.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.cisco.blogger.api.Blog;
import com.cisco.blogger.api.Comment;
import com.cisco.blogger.api.Reply;
import com.mongodb.MongoClient;

public class BlogDAOImpl implements BlogDAO {

	static String dbName = new String("bloggerDb");    
	static MongoClient mongo = new MongoClient("172.31.34.32");
    static Morphia morphia = new Morphia();
    static Datastore datastore = morphia.createDatastore( mongo, dbName); 	     
	

	public BlogDAOImpl() {

	    morphia.mapPackage("com.cisco.blogger.data");
	}

	public int createBlog(Blog blog) {

	    datastore.save(blog);
	    System.out.println("BlogCreated" + blog.getBlogId());
		return blog.getBlogId();

	}

	public Blog updateBlog(Blog blog) {

		datastore.save(blog);
		System.out.println("BlogUpdated-" + blog.getBlogId());
		return blog;
	}
	
	public Blog viewBlog(int blogId) {
		//Blog blogSearch = em.find(Blog.class, blogId);
		Blog blog1 = new Blog();
		blog1.setBlogId(1);
		blog1.setContent("hi this is my blog");
		blog1.setTitle("CMAD5");
		return blog1;
	}

	/*
	public List<Blog> searchBlogs(String keyword) {
		System.out.println("In search method for keyword:" + keyword);
		List<Blog> blogSearchList = null;

		Query searchQuery = em
				.createQuery("SELECT b FROM Blog b WHERE (b.title LIKE :keyword OR b.content LIKE :keyword)");
		searchQuery.setParameter("keyword", "%" + keyword + "%");
     List<Blog> blogSearchList = null;


		return blogSearchList;
	}*/




	public List<Blog> listAllBlogs() {
	/*	List<Blog> blogList = em.createQuery("SELECT b FROM Blog b").getResultList();

		if (null != blogList && !blogList.isEmpty()) {
			return blogList;
		} else {
			return null;
		}*/
		List<Blog> blogList = new ArrayList<Blog>();
		
		Blog b = new Blog();
		b.setBlogId(1);
		b.setTitle("Blog 1");
		b.setContent(" Blog 1 test");
		
		
		blogList.add(1, b);
		
		b.setBlogId(2);
		b.setTitle("Blog 2");
		b.setContent(" Blog 2 test");
		
		blogList.add(2, b);
		
		return blogList;
	}
}
/*	public void addComment(int blogId, Comment comment) {
		Blog blog = viewBlog(blogId);
		List<Comment> comments = blog.getComments();

		comments.add(comment);
		updateBlog(blog);

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
		Comment comment = em.find(Comment.class, commentId);
		comment.getReplyList().add(reply);

		em.getTransaction().begin();
		em.merge(comment);
		em.getTransaction().commit();

	}

}
*/