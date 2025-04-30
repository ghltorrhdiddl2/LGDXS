package com.lgdx.entity;

public class Team {
	// 팀원의 정보 (이름, 전화번호, 나이, 성별, 별명)
	// 저장할 수 있는 데이터 타입
	private String name; // 이름
	private String phone; // 전화번호
	private int age; // 나이
	private char gender; // 성별
	private String nick; // 별명
	
	
	public Team(String name, String phone, int age, char gender, String nick) {
		super();
		this.name = name;
		this.phone = phone;
		this.age = age;
		this.gender = gender;
		this.nick = nick;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
}
	
	
