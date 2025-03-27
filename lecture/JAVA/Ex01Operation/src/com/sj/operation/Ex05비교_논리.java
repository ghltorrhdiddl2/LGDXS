package com.sj.operation;

public class Ex05비교_논리 {

	public static void main(String[] args) {
		int a=3;
		int b=10;
		
		System.out.println(a>b);
		boolean result = a>b;
		
		System.out.println(a==b);
		System.out.println(a!=b);
		
		//논리연산자: 피연산자와 연산결과는 모두 boolean
		// and (&&) -> 양쪽 항이 모두 T일 때만 T
		//-> 로그인 (아이디-?, 비밀번호-?)
		System.out.println((3<5)&&(4>5));
		//or(||) -> 양쪽항이 모두 false일 때만 false
		System.out.println((3<5)||(4>5));
	}

}
