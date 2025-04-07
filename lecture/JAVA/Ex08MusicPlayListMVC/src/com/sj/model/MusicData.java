package com.sj.model;

public class MusicData {

	private String title;
	private String singer;
	private int playTime;
	
	public MusicData(String title, String singer, int playTime) {
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
