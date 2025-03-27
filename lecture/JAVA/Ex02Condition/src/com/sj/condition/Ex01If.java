package com.sj.condition;

import java.util.Scanner;

public class Ex01If {

	public static void main(String[] args) {
		//나이 입력 -> 20 이상인지 확인
		Scanner scn = new Scanner(System.in);
		System.out.print("나이 입력 : ");
		int age=scn.nextInt();
		
		//{}생략 가능 -> 실행문장이 1줄 일때
		if (age >= 20) {
			System.out.println("성인입니다.");
		}else {
			System.out.println("미성년자 입니다.");
		}
		System.out.println("끝");
		
		System.out.print("숫자 입력: ");
		int num = scn.nextInt();
		scn.close(); //scn자원 회수
		if (num%3==0 || num%5==0) {
			System.out.println("3 또는 5의 배수 입니다");
		}
	}

}
