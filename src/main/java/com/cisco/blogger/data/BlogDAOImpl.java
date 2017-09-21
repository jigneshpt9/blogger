package com.cisco.blogger.data;

import java.util.List;
import java.util.logging.Logger;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import com.cisco.blogger.api.Blog;
import com.cisco.blogger.api.Comment;
import com.mongodb.MongoClient;

public class BlogDAOImpl extends BasicDAO<Blog, String> implements BlogDAO {
	private Logger logger = Logger.getLogger(getClass().getName());

	private static String dbName = new String("BloggerDb");
	private static MongoClient mongo = new MongoClient("172.31.34.32");
	private static Morphia morphia = new Morphia();
	private static Datastore datastore = morphia.createDatastore(mongo, dbName);

	private BlogDAOImpl() {
		this(Blog.class, datastore);
	}

	private BlogDAOImpl(Class<Blog> entityClass, Datastore ds) {
		super(entityClass, ds);
	}

	private static BlogDAO dao = null;

  

    public static BlogDAOImpl getInstance() {
        if (dao == null) {
            dao = new BlogDAOImpl();
        }
        return (BlogDAOImpl) dao;
}
	

	@Override
	public String createBlog(Blog blog) {

		save(blog);
		System.out.println("BlogCreated" + blog.getId());
		return blog.getId();

	}

	@Override
	public Blog getBlogById(String blogId) {
		Blog blog = get(blogId);
		return blog;
	}

	@Override
	public void updateBlog(Blog blog) {
		Blog blogFound = getBlogById(blog.getId());
		if (null != blogFound) {
			logger.info("BlogUpdated-" + blog.getId());

		} else {
			logger.info("new blog inserted to DB through UPDATE process");
		}
		save(blog);
	}

	@Override
	public List<Blog> searchBlogs(String keyword) {
		logger.info("In search method for keyword:" + keyword);
		List<Blog> blogSearchList = null;

		Query<Blog> query = createQuery().field("content").contains(keyword);
		blogSearchList = query.asList();

		return blogSearchList;

	}

	@Override
	public List<Blog> listAllBlogs() {

		List<Blog> blogList = createQuery().order("lastUpdated").asList();
		return blogList;
	}

	@Override
	public void addComment(String blogId, Comment comment) {
		Blog blog = getBlogById(blogId);
		List<Comment> comments = blog.getComments();

		comments.add(comment);
		updateBlog(blog);

	}

}
