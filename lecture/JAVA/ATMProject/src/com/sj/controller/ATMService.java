package com.sj.controller;

public interface ATMService {
	//ATM 기계들이 공통적으로 가져야하는 기능들의 정의 (상세기능 x)
	//1. 새로운 계좌 추가
	//회원이름, 계좌번호, 초기 입금액, 계좌 타입을 받아 계죄를 추가한 후 TRUE(성공)/FALSE(실패) 결과값으로 반환
	public boolean addAccount(String name, String accountNumber, 
			int inputMoney, int accountType);
	//2. 나의 현재 잔액 검색
	//회원이름, 계좌번호 입력하면 현재 잔액 or 0을 반환
	public int searchMyBalance(String name, String accountNumber);
	//3. 나의 계좌 타입 검색
	//계좌번호를 입력하면 해당 계좌의 타입을 출력
	public void searchMyAccountType(String accountNumber);
	//4. 입금
	//계좌번호, 입금할 금액을 받아 해당 입금금액을 추가한 후 true(성공)/false(실패) 결과값 반환
	public boolean deposit(String accountNumber, int inputMoney);
	//5. 출금
	//계좌번호, 출금할 금액을 받아 해당 출금금액을 뺀 후 true(성공)/false(실패) 결과값 반환
	public boolean withdraw(String accountNumber, int inputMoney);
}
