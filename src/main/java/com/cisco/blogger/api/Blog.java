package com.cisco.blogger.api;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Blog {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private int blogId;
	private String title;
	private String createTime;
	private String lastUpdate;
	private String content;
	private int likeCount;
	@ManyToOne(cascade = { CascadeType.MERGE })
	private User blogOwner;
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER, mappedBy = "blog")
	private List<Comment> comments;
	@OneToMany(cascade = { CascadeType.ALL })
	private List<Image> imageList;

	public int getBlogId() {
		return blogId;
	}

	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
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
		if (null != comments)
			return comments;
		else
			return new ArrayList<Comment>();
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Image> getImageList() {
		return imageList;
	}

	public void setImageList(List<Image> imageList) {
		this.imageList = imageList;
	}

}
