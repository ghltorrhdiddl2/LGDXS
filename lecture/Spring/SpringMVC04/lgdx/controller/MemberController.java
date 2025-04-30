package com.lgdx.controller;

import javax.servlet.http.Cookie;
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
		
		if(info != null) {
			System.out.println("로그인 성공!");
			// 세션 나이설정 
			session.setMaxInactiveInterval(60 * 10);
			session.setAttribute("info", info);
		}else {
			System.out.println("로그인 실패");
		}
		
		return "redirect:/boardList.do";
	}
	
	@GetMapping("/logout.do")
	public String logout(HttpSession session) {
		System.out.println("로그아웃 기능");
		// 1.세션의 특정 이름의 값을 삭제하는 방법
		// session.removeAttribute("info");
		// 2.해당 client의 세션 전체를 만료시키는 방법
		session.invalidate();
		return "redirect:/boardList.do";
	}
	
	@GetMapping("/update.do")
	public String update() {
		System.out.println("회원정보수정 이동기능");
		return "update";
	}
	
	@PostMapping("/update.do")
	public String update(Member vo, HttpSession session) {
		System.out.println("회원정보수정 기능");
		int cnt = mapper.update(vo);
		
		if(cnt > 0) {
			session.setAttribute("info", vo);
		}
		
		return "redirect:/boardList.do";
	}
	
	
	
	

}

// 쿠키와 세션.
// HTTP 특징
// 1. TCP/IP 기반
// 2. Request가 먼저 발생한 다음 요청에 맞는 Response가 일어난다 그러고 나서 연결을 끊는다
// 3. 연결을 끊어주다 보니 전에 있던 Client의 정보를 유지할 수 없다

// Client의 정보를 유지시키기 위한 방법 - Cookie, Session
// Cookie
// - 클라이언트의 정보를 클라이언트 PC (브라우저)에 저장하는 방식

// 특징 : Cookie안에 값은 Text형태의 저장만 가능하다, 브라우저 종료시 쿠키는 만료된다
//       단. 쿠키의 나이를 설정할 수 있다
// 장점 : Session에 비해 빠르다
// 단점 : 개수가 제한되어있다, 분실의 위험이 있다, 보안에 취약하다

// Session
// - 클라이언트의 정보를 서버에 저장하는 방식
// 특징 : Session의 저장값은 Object형태로 저장가능하다, 브라우저 종료 시 자동 만료된다
//       브라우저 1개당 하나의 세션이 발급된다, 브라우저를 끄지 않더라도 기본 만료시간은 30분이다 (조절가능)


// 장점 : 많은 양의 데이터 저장 가능, 보안이 상대적으로 강력하다
// 단점 : 서버의 자원을 활용





























