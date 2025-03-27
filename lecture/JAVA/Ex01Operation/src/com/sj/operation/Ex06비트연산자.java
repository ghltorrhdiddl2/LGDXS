package com.sj.operation;

public class Ex06비트연산자 {

	public static void main(String[] args) {
		int a=4;
		int b=6;
		//비트 연산자 : &(and), |(or), ^(xor)
		//XOR : 두 수가 다른 경우에 TRUE/ 같으면 FALSE
		System.out.println(a&b);   //4
		System.out.println(a|b);   //6
		System.out.println(a^b);   //2 -> 
		//a : 0100(2)
		//b : 0110(2)
		//& : 0100(2) -> 4
		//| : 0110(2) -> 6
		//^ : 0010(2) -> 2
	}

}
