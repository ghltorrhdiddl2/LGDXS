package com.sj.hashset;

import java.util.HashSet;
import java.util.Iterator;

public class ExHashSet {

	public static void main(String[] args) {
		
		// Set(인터페이스)
		//- 객체(값)을 중복해서 저장할 수 없음, 하나의 null값만 저장가능
		//- 순서(인덱스)의 개념이 없음
		
		// Hash : 다양한 길이를 가진 데이터를 고정된 길이의 데이터로 매핑한 값
		// Hash 함수를 사용하여 데이터르 매칭해 줌 y=f(x) -> 해쉬테이블
		// HashSet : 구체화된 상태
		
		HashSet<String> set = new HashSet<>();

		//1. 추가
		set.add("A");
		set.add("B");
		set.add("C");
		
		//2. Set안에 저장된 데이터 개수
		int size = set.size();
		System.out.println(size);
		
		//3. 현재 Set안에 저장된 데이터 전부 출력
		//Iterator : 순서화는 상관없이 하나씩 가져올 때 사용
		Iterator<String> iter = set.iterator();
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
		for(String s:set)
			System.out.println(s);
		
		set.add("A");  // 중복허용x
		System.out.println(set.size());
		for(String s:set)
			System.out.println(s);
		
		//4. 특정 값이 있는지 확인
		System.out.println(set.contains("A"));
		System.out.println(set.contains("AD"));
		
		//Set 활용 : 채팅방에 들어와 있는 전체 인원에게 메시지를 보낼 때
	}

}
