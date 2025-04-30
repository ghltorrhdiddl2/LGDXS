package com.lgdx.entity;

import lombok.Data;

@Data
public class Board {
	private int idx;
	private String title;
	private String writer;
	private String contents;
	private int count;
	private String indate;
}
