package com.sj.operation;

public class Ex4대입_증감 {

	public static void main(String[] args) {
		//대입연산자
		int a=10;
		//복합대입연산자
		int b=10;
		b=b+20; //대입
		b+=20;  //복합대입
		b-=20;
		b*=20;
		b/=20;
		b%=20;
		
		//증감연산자(단항연산자): 변수에 저장된 값을 1증가 혹은 1감소 해주는 연산자
		int c = 1;
		int d;
		System.out.println(c);
		d = c++;
		System.out.println(c);
		System.out.println("d: "+d);
		d=++c;
		System.out.println(c);
		System.out.println("d: "+d);
	}
}
