package com.sj.model;

public class BusinessAccount extends Account{

	public BusinessAccount(String name, String accountNumber, int accountBalance) {
		super(name, accountNumber, accountBalance);
	}

	@Override
	public void printAccountType() {
		System.out.println("사업자 계좌입니다.");
	}

}
