package com.cisco.blogger.data;

import java.util.List;
import java.util.logging.Logger;

import org.bson.types.ObjectId;
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
		ObjectId _id = new ObjectId();
		blog.set_id(_id.toHexString());
		save(blog);
		logger.info("BlogCreated" + blog.get_id());
		return blog.get_id();

	}

	
	
	@Override
	public Blog getBlogById(String blogId) {
		
			Blog blog = findOne("_id",blogId);
		
		return blog;
	}

//	@Override
//	public void updateBlog(String blogId, Blog blog) {
//		Blog blogFound = getBlogById(blogId);
//		if (null != blogFound) {
//			logger.info("BlogUpdated-" + blogId);
//			save(blog);
//		} else {
//			logger.info("new blog inserted to DB through UPDATE process");
//			save(blog);
//		}
//		
//	}

	@Override
	public List<Blog> searchBlogs(String keyword) {
		logger.info("In search method for keyword:" + keyword);
		List<Blog> blogSearchList = null;

		Query<Blog> query = createQuery();
		
		query.or(query.criteria("content").contains(keyword),
				query.criteria("title").contains(keyword));
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
		logger.info(blog.get_id());
		blog.addComment(comment);
		save(blog);

	}

}
