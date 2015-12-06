package com.windowgarden.app.model;

public class Message {

	public static final int TYPE_RESEIVED = 0;
	
	public static final int TYPE_SENT = 1;

	private String content;
	
	private int type;
	
	private int ImageId;

	public Message(String content, int type, int ImageId) {
		this.content = content;
		this.type = type;
		this.ImageId = ImageId;
	}
	
	public String getContent() {
		return content;
	}
	
	public int getType() {
		return type;
	}
	
	public int getImageId() {
		return ImageId;
	}
	
}
