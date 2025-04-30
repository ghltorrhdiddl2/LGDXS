package com.lgdx.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lgdx.entity.Board;
import com.lgdx.mapper.BoardMapper;

@Controller
public class BoardController {
	
	@Autowired // Spring Memory에 올라간 Mapper객체를 주입받는다
	private BoardMapper mapper;

	@RequestMapping("/")
	public String main() {
		System.out.println("메인페이지 이동");
		return "main";
	}
	
	//게시글 정보 전체 돌려주는 기능
	@RequestMapping("/boardList.do")
	public @ResponseBody ArrayList<Board> boardList(){
		System.out.println("게시글 정보 전체 돌려주기");
		return mapper.boardList();
	}
	
	//게시글 작성 기능
	@PostMapping("/boardInsert.do")
	public @ResponseBody void boardInsert(Board vo) {
		System.out.println("게시글 작성 기능");
		mapper.boardInsert(vo);
	}
	
	@GetMapping("/boardDelete.do")
	public @ResponseBody void boardDelte(@RequestParam("idx") int idx) {
		System.out.println("게시글 삭제 기능");
		mapper.boardDelete(idx);
	}
	
	@PostMapping("/boardUpdate.do")
	public @ResponseBody void boardUpdate(Board vo) {
		System.out.println("게시글 수정 기능");
		mapper.boardUpdate(vo);
	}
	
	//게시글 상세보기 기능
	@GetMapping("/boardContents.do")
	public @ResponseBody Board boardContents(@RequestParam("idx") int idx){
		System.out.println("게시글 상세보기 기능");
		return mapper.boardContents(idx);
	}
	
	//게시글 조회수 기능
	@GetMapping("/boardCount.do")
	public @ResponseBody void boardCount(@RequestParam("idx") int idx) {
		System.out.println("게시글 조회수 기능");
		mapper.boardCount(idx);
	}
}

