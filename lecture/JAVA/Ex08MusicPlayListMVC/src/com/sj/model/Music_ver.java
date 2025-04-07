package com.smhrd.model;

public class Music {
	
	private String title; //제목
	private String singer; //가수이름
	private int playTime; //시간
	
	public Music(String title, String singer, int playTime) {
		super();
		this.title = title;
		this.singer = singer;
		this.playTime = playTime;
	}

	public String getTitle() {
		return title;
	}

	public String getSinger() {
		return singer;
	}

	public int getPlayTime() {
		return playTime;
	}
}
