package com.sj.model;

public class SavingsAccount extends Account{

	public SavingsAccount(String name, String accountNumber, int accountBalance) {
		super(name, accountNumber, accountBalance);
	}

	@Override
	public void printAccountType() {
		System.out.println("저축계좌 입니다!");	
	}
	
}
