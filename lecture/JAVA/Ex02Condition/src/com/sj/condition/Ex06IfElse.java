package com.sj.condition;

import java.util.Scanner;

public class Ex06IfElse {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		
		System.out.print("잔액 : ");
		int money = scn.nextInt();
		System.out.print("성인/청소년/어린이 : ");
		String age = scn.next();
		
		if (age.equals("성인")&&(money-1800)>0)
			System.out.println("감사합니다");
		else if(age.equals("청년")&&(money-1500)>0)
			System.out.println("반갑습니다");
		else if(age.equals("어린이")&&(money-1000)>0)
			System.out.println("안녕하세요");
		else
			System.out.println("잔액이 부족합니다");
	}
}
