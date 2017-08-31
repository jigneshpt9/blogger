package com.cisco.blogger.api;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Reply {
	@Id
	private int replyId;
	private int replyText;

	public int getReplyId() {
		return replyId;
	}

	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}

	public int getReplyText() {
		return replyText;
	}

	public void setReplyText(int replyText) {
		this.replyText = replyText;
	}

}
