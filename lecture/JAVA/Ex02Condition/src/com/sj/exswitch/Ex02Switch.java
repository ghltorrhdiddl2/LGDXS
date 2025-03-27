package com.sj.exswitch;

import java.util.Scanner;

public class Ex02Switch {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		/*System.out.print("입력 : ");
		String s = sc.next();
		
		switch(s) {
		case "고양이":
			System.out.println("야옹");
			break;
		case "영어":
			System.out.println("cat");
			break;
		case "중국어":
			System.out.println("miao");
		}*/
		
		System.out.print("월(1~12) 입력 : ");
		int m = sc.nextInt();
		
		switch(m) {
		case 1,2,12:
			System.out.printf("%d월은 겨울입니다",m);
			break;
		case 3: case 4: case 5:
			System.out.printf("%d월은 봄입니다",m);
			break;
		case 6: case 7: case 8:
			System.out.printf("%d월은 여름입니다",m);
			break;
		case 9: case 10: case 11:
			System.out.printf("%d월은 가을입니다",m);
			break;
		default:
			System.out.println("잘못된 입력입니다");
		}
		
	}

}
