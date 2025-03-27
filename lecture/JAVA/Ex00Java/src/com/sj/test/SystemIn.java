package com.sj.test;

import java.util.Scanner;

public class SystemIn {

	public static void main(String[] args) {
		//출력 : System.out
		//입력 : System.in(KeyCode) + 변환도구(Scanner)
		
		//새로운 Scanner라는 도구(객체)를 만들겠다!
		// + 그 도구를 만들때 System.in(입력) 도구를 사용하겠다!
		Scanner scan = new Scanner(System.in);
		
		String s = scan.next();//문자열(숫자) 입력받기
		System.out.println(s);
		//Buffer : 임시 저장공간
		String s1 = scan.nextLine(); // 띄어쓰기 포함된 문자 처리
		System.out.println(s1);
		
		int num1=scan.nextInt();
		int num2=scan.nextInt();
		System.out.println(num1+num2);
	}

}
