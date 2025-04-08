//1명의 회원계좌 정보를 묶어줄 수 있는 VO(Value Object) 클래스
//Account 클래스에는 계좌 종류에 상관없이 어떠한 계좌든 간에 공통적으로 가져야 하는 속성/기능만 정의해둠!
//입출금계좌 -> "입출금계좌 입니다" 출력
//저축계좌 -> "저축계좌 입니다" 출력
//비지니스용계좌 -> "비즈니스계좌 입니다" 출력
package com.sj.model;

public abstract class Account {
	//회원이름, 계좌번호, 계좌잔액
	private String name;
	private String accountNumber;
	private int accountBalance;
	
	//생성자, 일반메서드
	public Account(String name, String accountNumber, int accountBalance) {
		super();
		this.name = name;
		this.accountNumber = accountNumber;
		this.accountBalance = accountBalance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(int accountBalance) {
		this.accountBalance = accountBalance;
	}
	// 계좌정보를 출력해주는 메서드 정의 -> 계좌종류에 따라 출력되는 메시지 달라짐! => 추상메서드
	public abstract void printAccountType();
	
}
