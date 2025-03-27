package com.sj.operation;

import java.util.Scanner;

public class quiz {

	public static void main(String[] args) {
		int num1=7;
		int num2=3;
		
		System.out.println("더하기 결과 :"+(num1+num2));
		System.out.println("빼기 결과 :"+(num1-num2));
		System.out.println("곱하기 결과 :"+(num1*num2));
		System.out.println("나누기기 결과 :"+((double)num1/num2));
		
		System.out.println();
		
		Scanner scan = new Scanner(System.in);
		System.out.print("초 입력:");
		int sec = scan.nextInt();
		System.out.printf("%d시 %d분 %d초",sec/3600,sec%3600/60,sec%3600%60);
	}

}
