package com.sj.exfor;

import java.util.Scanner;

public class Ex02For {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int a = sc.nextInt();
		for(int i=1;i<=a;i++) {
			System.out.print(i+" ");
		}
		
		int b1=sc.nextInt();
		int b2=sc.nextInt();
		int sum = 0;
		for(int i=Math.min(b1,b2);i<=Math.max(b1,b2);i++) {
			System.out.println(i);
			sum+=i;
		}
		System.out.println(sum);
		
		//삼항연산자
		int min = (b1>b2)?b2:b1;
		int max = (b1>b2)?b1:b2;
		int sum2 = 0;
		for(int i=min;i<=max;i++) {
			System.out.print(i+" ");
			sum2+=i;
		}
		System.out.println("총합 : "+sum);
		sc.close();
	}
}
