package com.cisco.blogger.api;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Image {
	@Id
	private int imageId;
	private String imageLocation;

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public String getImageLocation() {
		return imageLocation;
	}

	public void setImageLocation(String imageLocation) {
		this.imageLocation = imageLocation;
	}

}
