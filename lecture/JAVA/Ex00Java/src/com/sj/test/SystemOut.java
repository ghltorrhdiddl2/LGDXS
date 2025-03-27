package com.sj.test;

public class SystemOut {

	public static void main(String[] args) {
		//ctrl+F11 : 실행 단축키
		System.out.print("Hello");
		System.out.print("Hello");
		System.out.print("Hello");
		//ctrl+alt+(위아래)방향키 : 코드 복사
		//print(): 출력
		
		System.out.println("World!");
		System.out.println("World!");
		System.out.println("World!");
		//println(): 출력+개행
		
		//1. 떡볶이(1000원)
		//2. 어묵(2000원)
		System.out.printf("%d. %s (%d원)\n",1,"떡볶이",1000);
		System.out.printf("%d. %s (%d원)",2,"어묵",2000);
	}

}
