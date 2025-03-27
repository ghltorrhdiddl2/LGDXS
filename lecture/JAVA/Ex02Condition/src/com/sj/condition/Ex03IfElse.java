package com.sj.condition;

import java.util.Scanner;

public class Ex03IfElse {

	public static void main(String[] args) {
		//점수 3개를 입력받아서 평균이 80점 이상이면 합격, 그렇지 않으면 불합격 출력
		Scanner sc = new Scanner(System.in);
		/*System.out.print("JAVA 점수 : ");
		int score1 = sc.nextInt();
		System.out.print("Python 점수 : ");
		int score2 = sc.nextInt();
		System.out.print("Web 점수 : ");
		int score3 = sc.nextInt();
		double avg = (score1+score2+score3)/3.0;
		if (avg>=80)
			System.out.println("합격");
		else
			System.out.println("불합격");*/
		
		System.out.print("총 금액 : ");
		int price = sc.nextInt();
		System.out.print("사람 명 수 : ");
		int p = sc.nextInt();
		double per = price/p*1.0;
		if (per<10000) {
			System.out.println("10000원 미만 지불");
		}else {
			System.out.println("10000원 이상 지불");
			}
	}
}
