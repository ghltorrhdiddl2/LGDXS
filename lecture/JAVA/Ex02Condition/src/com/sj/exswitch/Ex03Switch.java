package com.sj.exswitch;

import java.util.Scanner;

public class Ex03Switch {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("넣을 금액 : ");
		int money = sc.nextInt();
		System.out.println("--메뉴--");
		System.out.print("1.콜라(1800원) 2.파워에이드(2000원) 3.탄산수(1200원) >> ");
		int me = sc.nextInt();
		int rest=0;
		switch(me) {
		case 1:
			rest = money-1800;
			break;
		case 2:
			rest = money-2000;
			break;
		case 3:
			rest = money-1200;
			break;
		default:
			System.out.println("잘못 입력하셨습니다");
		}
		sc.close();
		if (rest<0)
			System.out.println("잔액이 부족합니다");
		else
			System.out.println("잔돈 :"+rest);
		
		if (rest/1000>0) System.out.println("천원 :"+rest/1000);
		if (rest%1000/500>0) System.out.println("오백원 :"+rest%1000/500);
		if (rest%500/100>0) System.out.println("백원 :"+rest%500/100);
	}

}
