package com.sj.model;

public class BasicAccount extends Account{

	//BasicAccount 기본생성자는 부모 Account의 기본생성자만을 호출하려고 함
	//그러나 Account 안에는 기본생성자가 없음/ String, String, int 인자를 넘겨주는 생성자만 만들어져 있음
	//위 생성자를 호출하는 BasicAccount 생성자를 꼭 만들어줘야 함
	public BasicAccount(String name, String accountNumber, int accountBalance) {
		super(name, accountNumber, accountBalance);
	}

	@Override
	public void printAccountType() {
		System.out.println("입출금 계좌입니다!");
	}

}
