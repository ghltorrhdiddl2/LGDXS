package com.lgdx.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lgdx.entity.Board;
import com.lgdx.mapper.BoardMapper;

@Controller
public class BoardController {
	
	
	@Autowired //Spring Memory에 올라갈 Mapper객체를 자동 주입받는다
	private BoardMapper mapper;
	
	@RequestMapping("/boardList.do")
	public String boardList(Model model) {
		System.out.println("게시글 목록페이지 이동");
		ArrayList<Board> list = mapper.boardList();
		//게시글 목록 Model에 저장(Key[String], Value[Object])
		model.addAttribute("list", list);
		return "boardList";
	}
	@GetMapping("/boardInsert.do")
	public String boardInsert() {
		System.out.println("게시글 작성 페이지로 이동");
		return "boardInsert";
	}
	
	@PostMapping("/boardInsert.do")
	public String boardInsert(Board vo) {
		System.out.println("게시글 작성 기능");
		System.out.println(vo.toString());
		mapper.boardInsert(vo);
		return "redirect:/boardList.do";
	}
	
	@GetMapping("/boardContents.do")
	public String boardContents(@RequestParam("idx") int idx, Model model) {
		System.out.println("게시글 상세보기 기능");
		
		mapper.boardCount(idx);
		
		Board vo = mapper.boardContents(idx); //vo: value object
		System.out.println(vo.toString());    // toString() : 다 가져옴
		model.addAttribute("vo",vo);   // "vo"라는 이름으로 vo저장
		return "boardContents";
	}
	@GetMapping("/boardDelete.do")
	public String boardDelete(@RequestParam("idx") int idx) {
		System.out.println("게시글 삭제 기능");
		mapper.boardDelete(idx);
		return "redirect:/boardList.do";
	}
	
	@GetMapping("/boardUpdate.do")
	public String boardUpdate(@RequestParam("idx") int idx, Model model) {
		System.out.println("게시글 수정페이지 이동");
		Board vo = mapper.boardContents(idx);
		model.addAttribute("vo", vo);
		return "boardUpdate";
	}
	@PostMapping("/boardUpdate.do")
	public String boardUpdate(Board vo) {
		System.out.println("게시글 수정 기능");
		System.out.println(vo.toString());
		mapper.boardUpdate(vo);
		return "redirect:boardContents.do?idx="+vo.getIdx();
	}
}
