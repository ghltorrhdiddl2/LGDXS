package com.sj.operation;

public class Ex01산술연산 {

	public static void main(String[] args) {
		System.out.println(5+2);
		System.out.println(5-2);
		System.out.println(5*2);
		System.out.println(5/2);
		System.out.println(5%2);
		//정수, 정수 연산 -> 무조건 결과는 정수
		
		System.out.println(5.0/2);
		System.out.println(5.0%2);
		//정수, 실수 연산 -> 무조건 결과는 실수
		
		// + => 문자열 더하기 (+ 외에는 다른 연산자 사용 불가!)
		System.out.println("Hello"+" Word!");
		System.out.println("Hello"+ 7);
		//정수 7 -> 문자열로 컴파일러가 변환함!
		System.out.println(Integer.parseInt("3")+7);
		//문자열 -> int
	}

}
