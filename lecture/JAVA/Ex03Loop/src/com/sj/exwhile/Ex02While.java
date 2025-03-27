package com.sj.exwhile;

import java.util.Scanner;

public class Ex02While {

	public static void main(String[] args) {
		//키보드로 1~10까지 수중 한 개의 수를 계속 입력받다가
		//입력값이 -1이 되면 반복을 멈춤(프로그램 종료)
		
		Scanner sc = new Scanner(System.in);
		/*int num=0;
		while (num != -1) {
			System.out.print("숫자 입력(1~10) : ");
			 num = sc.nextInt();
		}*/
		
//		do {
//			System.out.print("숫자 입력(1~10) : ");
//		}while(sc.nextInt() != -1);
		
		int input = 0;
		int sum = 0;
		int odd=0;
		int even=0;
		while(input!=-1) {
			System.out.print("숫자 입력");
			input = sc.nextInt();
			sum += input;
			if (input%2==0)
				even++;
			else
				odd++;	
		}
		System.out.println("누적값 : "+(sum+1));
		System.out.println("홀수갯수 : "+(odd-1));
		System.out.println("짝수갯수 : "+even);

	}

}
