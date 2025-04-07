package com.sj.arraylist;

import java.util.ArrayList;

public class Ex01ArrayList {

	public static void main(String[] args) {
		//1. ArrayList(같은 타입의 값만 저장할 수 있음) 생성
		//<Reference 타입> : Generic
		//=> ArrayList를 생성할 건데 이 안에는 같은 타입의 값만 저장할 수 있기 때문에
		// 데이터 타입을 지정해야 함 -> 컴파일 시점에 확정
		ArrayList<String> al = new ArrayList<>();
		// + ArrayList는 가변길이 int[3]
		// -> ArrayList 기본 크기가 10 (연속된 공간을 할당)
		
		//2. 데이터 삽입 -> 뒷공간에 차근차근 넣기/인덱스를 지정해서 추가
		al.add("Hello");
		al.add("Java");
		al.add("Python");
		
		//3. ArrayList에 저장된 데이터 개수 확인
		//arr.length : 배열의 길이 자체
		int size = al.size();
		System.out.println(size);
		
		//4. 특정 인덱스에 있는 값 확인
		String val1 = al.get(0);
		System.out.println(val1);
		System.out.println(al.get(1));
		System.out.println(al.get(2));
		//System.out.println(al.get(3)); <- 범위를 벗어남
		
		//5. 인덱스 번호 지정해서 데이터 추가
		al.add(1, "Dart");
		//a1.add(5,"Flutter"); <- 범위를 벗어남
		
		System.out.println("=========");
		//6. ArrayList의 전체 데이터 출력 -> for
		for(int i=0;i<al.size();i++)
			System.out.println(al.get(i));
		System.out.println("=========");
		for(String s:al)
			System.out.println(s);
		
		//7. 삭제 : 특정 위치의 값 삭제, 전체 삭제
		System.out.println("=========");
		al.remove(1);
		for(String s:al)
			System.out.println(s);
		// 전체 삭제
		al.clear();
		System.out.println(al.size());
	}

}
