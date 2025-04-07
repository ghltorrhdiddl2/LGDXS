package com.sj.arraylist;

public class Pet {
	// 1. Field
	// 이름
	private String name;
	// 종류
	private String species;
	// 좋아하는 음식
	private String favoriteFood;
	// 나이
	private int age;
	// 몸무게
	private double weight;
	
	// 생성자(constructor)
	// --> 객체를 생성하는 순간에 실행되는 메소드
	// --> 필드에 있는 데이터를 객체를 생성하는 순간에 초기화(초기값)를 시켜줄 수 있도록 로직 작성 
	public Pet(String name, String species) {
		this.name = name;
		this.species = species;
	}
	// 생성자의 특징
	// 1) 리턴타입을 지정하지 않는다(void 작성 안함)
	// 2) 생성자 이름은 클래스 이름과 동일해야 함(대소문자까지) -> 오버로딩
	// 3) 생성자도 결국 메소드임
	// 4) 매개변수가 아무것도 없는 생성자를 기본 생성자(default constructor), 생략 가능
	// 단, 새로운 나만의 생성자를 만들게 되면 기본 생성자는 덮어씌워져 사라짐
	// 5) 생성자 오버로딩(중복정의) 가능
	
	// 오버로딩이란?
	// --> 메소드의 이름과 리턴타입이 동일한 상태에서
	//     매개변수의 개수와 타입을 다르게 *중복으로 정의*하는 메소드 기법

	public Pet() {
	}


	// 2. Method
	// getter 메소드
	// ---> 클래스 내부에 있는 필드 값을 외부(다른 클래스)에서 가져갈 수 있는 메소드
	public String getName() {
		return name;
	}
	// setter 메소드
	// ---> 클래스 내부에 있는 필드 값을 외부(다른 클래스)에서 수정할 수 있도록 하는 메소드
	public void setName(String name) {
		this.name = name;
	}
	public String getSpecies() {
		return species;
	}
	public void setSpecies(String species) {
		this.species = species;
	}
	public String getFavoriteFood() {
		return favoriteFood;
	}
	public void setFavoriteFood(String favoriteFood) {
		this.favoriteFood = favoriteFood;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}

}
