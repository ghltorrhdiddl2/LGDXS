package com.sj.exfor;

public class Ex01For {

	public static void main(String[] args) {
		//for(초기화구문;조건식;반복후구문)
		//초기화구문 : for문 안쪽에서 사용할 변수 선언(생략은 가능)
		//반복후 구문 : 일반적으로 증감연산자 사용(정해진 횟수만큼 반복할 수 있도록)
		for(int i=1;i<=3;i++) {
			if(i%2==1)
				System.out.println(i);
		}
		// 실행순서 : 1245 324 3245 32
		
		for(int i=96;i>=53;i-=2) {
			System.out.println(i);
		}
		
		//21~57까지 수 중에 홀수만 출력
		for(int i=21;i<=57;i+=2) {
			System.out.println(i);
		}
	}
}
