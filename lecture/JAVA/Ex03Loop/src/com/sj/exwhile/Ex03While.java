package com.sj.exwhile;

import java.util.Scanner;

public class Ex03While {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int count=0;
		//id : smhrd, pw:1234
		while (true) {
		
		System.out.print("아이디 : ");
		String id = sc.next();
		System.out.print("비밀번호 : ");
		String pw = sc.next();
		
		if (id.equals("smhrd") && pw.equals("1234")) {
			System.out.println("로그인 성공");
			break;}
		else {
			System.out.println("로그인 실패");
			count++;
			if (count>=3) {
				System.out.println("본인인증을 해주세요");
				break;}
			System.out.print("계속 하시겠습니까?");
			String answer=sc.next();
			if (answer.equals("N"))
				break;
			}
		}
		
	}

}
