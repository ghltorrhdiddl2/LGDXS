package com.sj.exfor;

public class Ex03For {

	public static void main(String[] args) {
		//구구단2단 출력
		/*for(int i=1;i<=9;i++) {
			System.out.printf("2*%d=%d\n",i,i*2);
		}
		
		for(int i=2;i<=9;i++) {
			for(int j=1;j<=9;j++) {
				System.out.printf("%d*%d=%d\n",i,j,i*j);
			}
		}*/
		
		for(int i=2;i<=9;i++) {
			System.out.printf("%d단 :\t",i);
			for(int j=1;j<=9;j++)
				System.out.printf("%d*%d=%d\t",i,j,i*j);
			System.out.println();
		}
	}
}
