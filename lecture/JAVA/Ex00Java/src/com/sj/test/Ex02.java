package com.sj.test;

import java.util.Scanner;

public class Ex02 {

	public static void main(String[] args) {
		System.out.println("[필수 정보 입력]");
		Scanner scn = new Scanner(System.in);
		System.out.print("1. 이름: ");
		String name = scn.next();
		System.out.print("2. 주민번호 앞 6자리: ");
		String num = scn.next();
		System.out.print("3. 전화번호: ");
		String phone = scn.next();
		
		System.out.println("\n[입력된 내용]");
		System.out.println("1. 이름: "+name);
		System.out.println("2. 주민번호 앞 6자리: "+num);
		System.out.println("3. 전화번호: "+phone);
	}
}
