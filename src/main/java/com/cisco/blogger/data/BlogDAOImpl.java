package com.cisco.blogger.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.cisco.blogger.api.Blog;
import com.cisco.blogger.api.Comment;
import com.cisco.blogger.api.Reply;

public class BlogDAOImpl implements BlogDAO {

	static EntityManager em;

	public BlogDAOImpl() {

		if (null == em) {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("blogger");
			em = factory.createEntityManager();
		}

	}

	public int createBlog(Blog blog) {

		em.getTransaction().begin();
		em.persist(blog);
		em.getTransaction().commit();
		System.out.println("BlogAdded-" + blog.getBlogId());

		return blog.getBlogId();

	}

	public Blog updateBlog(Blog blog) {

		em.getTransaction().begin();
		em.merge(blog);
		em.getTransaction().commit();
		System.out.println("BlogUpdated-" + blog.getBlogId());
		return blog;
	}

	public List<Blog> searchBlogs(String keyword) {
		System.out.println("In search method for keyword:" + keyword);
		List<Blog> blogSearchList = null;

		Query searchQuery = em
				.createQuery("SELECT b FROM Blog b WHERE (b.title LIKE :keyword OR b.content LIKE :keyword)");
		searchQuery.setParameter("keyword", "%" + keyword + "%");

		blogSearchList = searchQuery.getResultList();

		return blogSearchList;
	}

	public Blog viewBlog(int blogId) {
		Blog blogSearch = em.find(Blog.class, blogId);

		return blogSearch;
	}

	public List<Blog> listAllBlogs() {
		List<Blog> blogList = em.createQuery("SELECT b FROM Blog b").getResultList();

		if (null != blogList && !blogList.isEmpty()) {
			return blogList;
		} else {
			return null;
		}
	}

	public void addComment(int blogId, Comment comment) {
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
