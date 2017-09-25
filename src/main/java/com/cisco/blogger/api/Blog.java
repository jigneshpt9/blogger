package com.cisco.blogger.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.PrePersist;
import org.mongodb.morphia.annotations.Property;



@Entity
public class Blog {
	
	@Id
	@Property("_id")
	private ObjectId _id;
	private String title;
	private Date lastUpdated;
	private String content;
	private int likeCount;
	private User blogOwner;
	private List<Comment> comments;
	
	@PrePersist
    public void prePersist() {
        this.lastUpdated = new Date();
}
	


	public ObjectId get_id() {
		return _id;
	}



	public void set_id(ObjectId _id) {
		this._id = _id;
	}



	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdate(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public User getBlogOwner() {
		return blogOwner;
	}

	public void setBlogOwner(User blogOwner) {
		this.blogOwner = blogOwner;
	}

	public List<Comment> getComments() {
		
		return comments;
		
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	public void addComment(Comment comment) {
		if(this.comments != null) {
			this.comments.add(comment)
;		} else {
		this.comments = new ArrayList<Comment>();
		this.comments.add(comment);
	
	}
}
	}



