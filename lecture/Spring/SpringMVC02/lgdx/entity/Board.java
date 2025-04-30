package com.lgdx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Board {
	// 게시글 내용을 하나로 묶어주기 위한 클래스
	private int idx; // 번호
	private String title; // 제목
	private String contents; // 내용
	private String writer; // 작성자
	private int count; // 조회수
	private String indate; // 날짜
	
	
	
	
}
