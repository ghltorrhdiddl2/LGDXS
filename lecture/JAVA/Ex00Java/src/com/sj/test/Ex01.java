package com.sj.test;

import java.util.Scanner;

public class Ex01 {

	public static void main(String[] args) {
		//1. "이름 : " 출력
		//2. 이름 입력받기
		
		System.out.print("이름 : ");
		Scanner scan = new Scanner(System.in);
		String name = scan.next();
		System.out.print("나이 : ");
		int age = scan.nextInt();
		
		scan.close(); //scanner 자원 반납
		
		System.out.println();
		System.out.println("이름은 "+name+"이고 나이는 "+age+"입니다.");
		System.out.printf("이름은 %s이고 나이는 %d입니다.",name,age);
	}
}