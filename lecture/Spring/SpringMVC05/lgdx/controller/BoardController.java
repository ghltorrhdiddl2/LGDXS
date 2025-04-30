package com.lgdx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lgdx.entity.Board;
import com.lgdx.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	@GetMapping("/boardList.do")
	public String boardList(Model model) {
		List<Board> list = service.boardList();
//		System.out.println(list.toString());
		model.addAttribute("list",list); //모델에 List 데이터 담음
		return "boardList";
	}
	
	@GetMapping("/boardContents.do")
	public String boardContents(@RequestParam("idx") int idx, Model model) {
		System.out.println("게시글 상세보기 기능");
		service.boardCount(idx); // 조회수 올려주는 기능
		Board vo = service.boardContents(idx);
		model.addAttribute("vo",vo);
		return "boardContents";
	}
	
	@GetMapping("/boardInsert.do")
	public String boardInsert() {
		System.out.println("게시글 작성 페이지로 이동");
		return "boardInsert";
	}
	
	@PostMapping("/boardInsert.do")
	public String boardInsert(Board vo) {
		System.out.println("게시글 작성 기능");
		service.boardInsert(vo);
		return "redirect:/boardList.do";
	}
	
	@GetMapping("/boardDelete.do")
	public String boardDelete(@RequestParam("idx") int idx) {
		System.out.println("게시글 삭제 기능");
		service.boardDelete(idx);
		return "redirect:/boardList.do";
	}
	
	@GetMapping("/boardUpdate.do")
	public String boardUpdate(@RequestParam("idx") int idx, Model model) {
		System.out.println("게시글 수정 페이지 이동");
		Board vo = service.boardContents(idx);
		model.addAttribute("vo", vo);
		return "boardUpdate";
	}
	
	@PostMapping("/boardUpdate.do")
	public String boardUpdate(Board vo) {
		System.out.println("게시글 수정 기능");
		System.out.println(vo.toString());
		service.boardUpdate(vo);
		return "redirect:/boardList.do";
	}
	
}
