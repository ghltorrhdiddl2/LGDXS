package com.sj.hashmap;

import java.util.Collection;
import java.util.HashMap;

public class ExHashMap {

	public static void main(String[] args) {
		
		//key(?)-value(?)
		HashMap<String,Integer> map = new HashMap<>();
		
		//1. 추가
		map.put("a", 10);
		map.put("b", 100);
		map.put("c", 20);
		map.put("d", 200);
		
		//2. 데이터 확인
		int size = map.size();
		System.out.println(size);
		
		System.out.println(map.get("c"));
		
		//3. 데이터 삭제
		map.remove("c");
		System.out.println(map.size());
		
		//4. 데이터 변경
		map.replace("d",2000);
		System.out.println(map.get("d"));
		
		//5. 전체 value 출력
		Collection<Integer> values = map.values();
		System.out.println(values);  // [10, 100, 2000]
		
		// 중복키 허용x
		// 새롭게 넣은 값 저장
		map.put("a", 1000);
		System.out.println(map.get("a")); // 덮어쓰기 됨
		
		//Map 활용
		//회원데이터VO - Member 클래스 new Member(아이디, ...)
		//영화정보VO  - Movie new Movie(영화제목,...)
		
		//계속 쓰이지는 않을거 같지만 특정 기능을 위해 값들을 잠시 묶어야 할때
		HashMap<String, String> map2 = new HashMap<>();
		map2.put("아이디","영화제목");
	}

}
