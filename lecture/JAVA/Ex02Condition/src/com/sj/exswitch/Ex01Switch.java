package com.sj.exswitch;

import java.util.Scanner;

public class Ex01Switch {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("숫자입력(1~3) : ");
		int num = sc.nextInt();
		
		switch(num) {
		case 1:
			System.out.println("1입력");
			break; //switch, 반복문과 같이 사용되서 특정 블럭을 끝내고 다음 문장으로 넘어가고 싶을떄 사용
		case 2:
			System.out.println("2입력");
			break;
		case 3:
			System.out.println("3입력");
			break;
		default:
			System.out.println("다시 입력해주세요");
		}
	}

}
