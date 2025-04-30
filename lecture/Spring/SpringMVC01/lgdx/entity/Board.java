package com.lgdx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor  // → 모든 필드를 받는 생성자 자동 생성, Args:매개변수
@NoArgsConstructor   // → 아무것도 안 받는 생성자 만들기
public class Board {
	//게시글 내용을 하나로 묶어주기 위한 클래스
	private int idx; //번호
	private String title; //제목
	private String contents; //내용
	private String writer; //작성자
	private int count; //조회수
	private String indate; //날짜

}
