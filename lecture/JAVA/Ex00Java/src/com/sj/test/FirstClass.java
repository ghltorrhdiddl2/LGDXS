package com.sj.test;

public class FirstClass {

	//static : 정적, 프로그램이 시작하자마자 바로 사용할 수 있는 도구를 지칭하는 키워드
	//void : 이 메서드(함수)결과값의 타입(int, String, boolean, void-결과가 없을 때)
	//main : 메서드(함수)의 이름 -> 프로그램의 시작점
	//() : 메서드를 호출할 때 넘겨줄 매개인자(파라미터)의 개수, 타입, 이름 등을 지정하는 자리
	public static void main(String[] args) {
		
		//정수 값을 저장하는 변수 선언
		int a;
		a=10;
		
		int b=10;
		
		//명명규칙 + 변수 선언시 주의할 점
		//1. 이름에 키워드(예약어-if,while,int,public..)사용 불가
		//2. 대소문자 구분함
		int num;
		int Num;
		//3. 변수 이름의 길이 제한 없음 -> 변수 이름 지을때는 의미있이 지어주기!
		int studentName; //카멜케이스
		//4. 숫자로 시작할 수 없음
		//5. 특수문자는 _,$만 허용함
		int $a;
		int _a;
		//6. 한글 사용할 수 있음(but 지양)
		int 학생이름;
		
		//fianl : 상수(변할 수 없는 수) 선언시 사용
		final String familyName="강";
		
		//familyName은 변경 불가능하게 바꿔보기
		//변수 -> 변할 수 있는 수
		//familyName="이"; //-> 오류
		
		System.out.println(); //sysout + ctrl+space
		System.out.print(a);
	}

}
