package com.sj.condition;

import java.util.Scanner;

public class Ex05IfElse {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		
		System.out.print("점수 입력 : ");
		int score = scn.nextInt();

		if (score>=90)
			System.out.println("A학점");
		else if (score>=80)
			System.out.println("B학점");
		else if (score>=70)
			System.out.println("C학점");
		else
			System.out.println("F학점");
		
		System.out.print("알파벳 입력 : ");
		String s = scn.next();
		
		//기본타입(Primitive): 8가지
		//참조타입(Reference): 기본타입 외의 타입
		//String : 참조타입
		//실제 값이 메모리에 저장될 때 기본타입/참조타입 위치가 다름!
		//기본 타입 -> stack 영역
		//참도 타입 -> heap 영역
		//==: stack에 저장된 값을 비교할 때 사용
		//
		if (s.equals("A"))
			System.out.println("A입력");
		else if (s.equals("B"))
			System.out.println("B입력");
		else if (s.equals("C"))
			System.out.println("C입력");
		else
			System.out.println("A,B,C 외 입력");
		
	}
}
