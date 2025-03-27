package com.sj.exwhile;

import java.util.Scanner;

public class Ex04While {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//isContinue=true -> 계속 진행 /false -> 반복중단
		boolean isContinue=true;
		
		while(isContinue) {
			System.out.print("정수1 : ");
			int num1=sc.nextInt();
			System.out.print("정수2 : ");
			int num2=sc.nextInt();
			System.out.print("연산자(+/-) : ");
			String op=sc.next();
			
			if (op.equals("+"))
				System.out.println(num1+op+num2+" = "+(num1+num2));
			else
				System.out.println(num1+op+num2+" = "+(num1-num2));
			
			System.out.print("계속하시겠습니까? (Y/N)");
			String answer=sc.next();
			if (answer.equalsIgnoreCase("N")) //소대문자 상관x
				isContinue=false;
				//break;
		}
	}
}
