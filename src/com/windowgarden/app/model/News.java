package com.windowgarden.app.model;

public class News {

	public static final int NORMAL_ITEM = 0;
	
	public static final int HOT_ITEM = 1;
	
	public static final int RECENT_ITEM = 2;
	
	private String title;
	
	private int type;
	
	private String userName;
	
	private String postDate;
	
	private String replyNum;
	
	public News(String title, int type, String userName, 
			String postDate, String replyNum) {
		this.title = title;
		this.type = type;
		this.userName = userName;
		this.postDate = postDate;
		this.replyNum = replyNum;
	}
	
	public String getTitle() {
		return title;
	}

	public int getType() {
		return type;
	}

	public String getUserName() {
		return userName;
	}

	public String getPostDate() {
		return postDate;
	}

	public String getReplyNum() {
		return replyNum;
	}
}

