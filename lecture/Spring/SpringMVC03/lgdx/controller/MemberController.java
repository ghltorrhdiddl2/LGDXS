package com.lgdx.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.lgdx.entity.Member;
import com.lgdx.mapper.MemberMapper;

@Controller
public class MemberController {
	
	@Autowired
	private MemberMapper mapper;
	
	@GetMapping("/login.do")
	public String login() {
		System.out.println("로그인페이지 이동");
		return "login";
	}
	
	@PostMapping("/join.do")
	public String join(Member vo) {
		System.out.println("회원가입 기능");
		System.out.println(vo.toString());
		mapper.join(vo);
		return "redirect:/boardList.do";
	}
	
	@PostMapping("/login.do")
	public String login(Member vo, HttpSession session) {
		System.out.println("로그인 기능");
		System.out.println(vo.toString());
		Member info = mapper.login(vo);
		if(info != null ) {
			System.out.println("로그인 성공!");
			//세션 나이설정
			session.setMaxInactiveInterval(60*10); //10초 뒤, 새로고침하면 로그아웃 됨
			session.setAttribute("info", info); //로그인 성공시, 세션 저장
			
		}else {
			System.out.println("로그인 실패");
		}
		return "redirect:/boardList.do";
	}
	
	@GetMapping("/logout.do")
	public String logout(HttpSession session) {
		System.out.println("로그아웃 기능");
		//1. 세션의 특정 이름의 값을 삭제하는 방법
//		session.removeAttribute("info"); //세션 이름 info로 설정해뒀었음
		//2. 해당 client의 세션 전체를 만료시키는 방법
		session.invalidate();  //invalidate : 무효화하다
		return "redirect:/boardList.do";
	}
	
	@GetMapping("/update.do")
	public String update() {
		System.out.println("회원정보 수정 페이지 이동");
		return "update";
	}
	
	@PostMapping("/update.do")
	public String update(Member vo, HttpSession session) {
		System.out.println("회원정보 수정 기능");
//		System.out.println(vo.toString());
//		session.setAttribute("info", mapper.update(vo));
		int cnt = mapper.update(vo);
		if(cnt>0) {
			session.setAttribute("info",vo);
		}
		return "redirect:/boardList.do";
	}
}
