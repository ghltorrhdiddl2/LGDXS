package com.sj.arraylist;

public class PetMain {

	public static void main(String[] args) {
		// Pet 자료형 pet1 객체 생성
		Pet pet1 = new Pet();
		
		pet1.setName("둥이");
		pet1.setSpecies("강아지");
		pet1.setFavoriteFood("소고기 육포");
		pet1.setAge(4);
		pet1.setWeight(5.0);
		
		// 이름, 나이 출력
		System.out.println(pet1.getName());
		System.out.println(pet1.getAge());
		// 메소드 이름 위에 마우스 올려서 포스트잇 모양이 뜨면
		// 초록색 동그라미 우측에 있는 자료형이 return타입을 의미한다!
		
		// Pet 자료형 pet2 객체 생성
		Pet pet2 = new Pet("쪼꼬","푸들");
		// new Pet() --> 객체를 생성하는 순간에 실행되는 메소드
		// *생성자(constructor)*
		System.out.println(pet2.getName());
	}
}
