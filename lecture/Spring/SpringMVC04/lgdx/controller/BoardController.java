package com.lgdx.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lgdx.entity.Board;
import com.lgdx.mapper.BoardMapper;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Controller
public class BoardController {
	
	@Autowired // Spring Memory에 올라간 Mapper객체를 주입받는다
	private BoardMapper mapper;

	@RequestMapping("/boardList.do")
	public String boardList(Model model) {
		System.out.println("게시글 목록페이지 이동");
		ArrayList<Board> list = mapper.boardList();
		// 게시글 목록 Model에 저장 (Ket[String], Value[Object])
		model.addAttribute("list", list);
		return "boardList";
	}
	
	@GetMapping("/boardInsert.do")
	public String boardInsert() {
		System.out.println("게시글 작성 페이지 이동");
		return "boardInsert";
	}
	
	@PostMapping("/boardInsert.do")
	public String boardInsert(HttpServletRequest request) {
		System.out.println("게시글 작성 기능");
		//파일 업로드 기능
		MultipartRequest multi = null; //파일업로드 객체
		int maxSize = 10*1024*1000; //최대 파일 크기(-> 저거 아마 10MB)
		String savePath = request.getRealPath("resources/upload");
		System.out.println("파일 저장 위치 : "+savePath);
		String encType = "UTF-8"; //파일명 인코딩 타입
		//파일 중복이름 방지
		DefaultFileRenamePolicy dp = new DefaultFileRenamePolicy();
		
		//MultiPart 객체생성
		try {
			multi = new MultipartRequest(request,savePath,maxSize,encType,dp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String id = multi.getParameter("id");
		String title = multi.getParameter("title");
		String filename = multi.getFilesystemName("filename");
		String contents = multi.getParameter("contents");
		String writer = multi.getParameter("writer");
		
		Board vo = new Board(0, id, title, filename, contents, writer, 0, null);
		System.out.println(vo.toString());
		
		//이미지 파일만 저장하게 하기
		File file = multi.getFile("filename");  //File -> 자동완성해주기
		if(file != null) {
			String ext = file.getName().substring(file.getName().lastIndexOf(".")+1);
			ext = ext.toUpperCase();
			boolean result = ext.equals("JPG")||ext.equals("PNG")||ext.equals("GIF")||ext.equals("JPEG");
			if(!result) {
				if(file.exists()) {//해당 파일 존재 확인
					file.delete(); //-> 이상한 파일
					return "redirect:/boardInsert.do";
				}
			}
		}
		
		mapper.boardInsert(vo);
		return "redirect:/boardList.do";
	}
	
	@GetMapping("/boardContents.do")
	public String boardContents(@RequestParam("idx") int idx, Model model) {
		System.out.println("게시글 상세보기 기능");
		
		mapper.boardCount(idx);
		
		Board vo = mapper.boardContents(idx);
		System.out.println(vo.toString());
		model.addAttribute("vo", vo);
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
		System.out.println("게시글 수정기능");
		mapper.boardUpdate(vo);
		return "redirect:/boardContents.do?idx=" + vo.getIdx();
	}
	
	
}




























