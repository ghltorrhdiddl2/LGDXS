package com.sj.operation;

import java.util.Scanner;

public class Ex07삼항연산 {
	public static void main(String[] args) {
		//삼항연산자: (조건식)?(실행문1):(실행문2)
		//조건식 : 결과값이 true/false가 나오는 식(비교)
		//실행문1: 조건식의 결과가 true일때 실행하는 문장
		//실행문2: 조건식의 결과가 false일때 실행하는 문장
		Scanner scan = new Scanner(System.in);
//		System.out.println("정수를 입력하세요 : ");
//		int n = scan.nextInt();
//		String s = n%2==0?"짝수":"홀수";
//		System.out.printf("%d는(은) %s입니다.",n,s);
		
		System.out.print("첫번째 정수 입력 : ");
		int a = scan.nextInt();
		System.out.print("두번째 정수 입력 : ");
		int b = scan.nextInt();
		int sub = a>b?a-b:b-a;
		System.out.println("두 수의 차 : "+sub);
		
	}
}
