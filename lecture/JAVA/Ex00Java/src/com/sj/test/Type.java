package com.sj.test;

public class Type {

	public static void main(String[] args) {
		// 자료형 -> 기본(primitive)-8가지 종류/ 참조(referece)
		// 기본 -> 논리/정수/부동소수점
		//1. 논리(boolean-true/false): 1bit(1byte)
		boolean sw=true;
		//-> 조건식/반복문의 조건식
		//-> toggle 구현(true-스위키 켜는거/false-끄는거)
		//2. 정수(숫자-byte,short,int,long/문자-char)
		//2-1. char:2byte(음수x)
		char c = 'a';
		char c1='곰';
		//char c2='AB'; //char은 문자1개만 저장할 수 있음
		char c3=65;
		
		System.out.println(c);
		System.out.println(c1);
		System.out.println(c3);
		System.out.println((char)(c3+1));
		
		//2-2. byte:1byte(8bit)
		byte b=1;
		byte b1 = (byte)1; //강제 형변환
		//byte b1=200; //overflow
		
		//2-3. short: 2byte
		short s =10;
		//2-4. int:4byte (정수의 기본형태)
		int i=100;
		//2-5. long:8byte
		//long l=2300000000; (int형을 벗어남)
		long l=2300000000L;  // L을 붙여서 long형으로 변환
		
		//3. 실수(float, double)
		//3-1. float (4byte): int < float
		float f=3.14f; // f를 붙여서 float형으로 변환
		
		//3-2. double (8byte): long < double
		double db=3.14;  //실수는 double이 기본형
		
		//+ 문자열: String (기본x, 참조형)
		String str = "Hello Word!";
		System.out.println(str);
		
		//참조형(Reference Type):값을 저장/자기만의 기능(메서드)도 포함
		//str.
	}

}
