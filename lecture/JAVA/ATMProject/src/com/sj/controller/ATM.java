package com.sj.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sj.model.Account;
import com.sj.model.BasicAccount;
import com.sj.model.BusinessAccount;
import com.sj.model.SavingsAccount;
import com.sj.view.ATMView;

public class ATM implements ATMService{
	Scanner sc = new Scanner(System.in);
	//회원들의 계좌정보를 저장해 놓을 공간 만들기!(ArrayList)
	List<Account> accountList = new ArrayList<>();  //List로 업캐스팅해놓으면 좋음
	ATMView view = new ATMView();
	
	
	public void run() {
		while(true) {
			int menu = view.getMenuSelection(sc);
			
			if(menu==1) {//계좌 신설
				view.printMessage("계좌 타입을 선택하시오");
				
				int inputType = view.getAccountTypeSelection(sc);
				
				String inputName = view.promptName(sc);
				String inputAccountNumber = view.promptAccountNumber(sc);
				int inputMoney = view.promptInputMoney(sc);
				
				boolean result = addAccount(inputName, inputAccountNumber, inputMoney, inputType);
				if(result)
					view.printMessage("계좌 신설 성공");
				else
					view.printMessage("계좌 신설 실패");
			}else if(menu==2) {
				String inputName = view.promptName(sc);
				String inputAccountNumber = view.promptAccountNumber(sc);
				
				int result = searchMyBalance(inputName, inputAccountNumber);
				if(result == -1) {
					view.printMessage("이름과 계좌번호를 다시 입력하세요");
				}else {
					view.printMessage("잔액 : "+result);
				}	
			}else if(menu==3) {//계좌 타입 확인
				String inputAccountNumber = view.promptAccountNumber(sc);
				searchMyAccountType(inputAccountNumber);
			}else if(menu==4) {
				String inputAccountNumber = view.promptAccountNumber(sc);
				int inputMoney = view.promptInputMoney(sc);
				
				boolean result = deposit(inputAccountNumber, inputMoney);
				if(result)
					view.printMessage("입금 성공");
				else
					view.printMessage("입금 실패");
			}else if(menu==5) {
				String inputAccountNumber = view.promptAccountNumber(sc);
				int inputMoney = view.promptOutputMoney(sc);
				
				boolean result = withdraw(inputAccountNumber, inputMoney);
				if(result)
					view.printMessage("출금 성공");
				else
					view.printMessage("출금 실패");
			}else if(menu==6) {
				break;
			}
		}
	}
	
	//1. 계좌 추가
	@Override
	public boolean addAccount(String name, String accountNumber, int inputMoney, int accountType) {
		//accountNumber가 기존에 있던 계좌번호와 겹치는 경우에는 계좌를 추가하지 않고 false반환
		//계좌번호가 겹치지 않으면 계좌 신설해서 accountList에 추가하고 true 반환
		for(Account account:accountList) {
			if(accountNumber.equals(account.getAccountNumber())) {
				return false; //return은 만나는 순간 값 반환하고 메서드 종료!
			}
		}
		//중복되는 계좌번호가 없으면
		if(accountType==1) {
			accountList.add(new BasicAccount(name,accountNumber,inputMoney));
		}else if(accountType==2) {
			accountList.add(new SavingsAccount(name,accountNumber,inputMoney));
		}else if(accountType==1) {
			accountList.add(new BusinessAccount(name,accountNumber,inputMoney));
		}
		return true;
	}

	@Override
	public int searchMyBalance(String name, String accountNumber) {
		//현재 저장된 계좌 정보중에 사용자가 입력한 이름/계좌번호가 같이 저장되어있는 Account 객체가 있는지 확인
		//있으면 -> 해당 계좌의 잔액을 반환
		//없으면 -> -1을 반환
		for(Account account:accountList) {
			if(name.equals(account.getName()) && accountNumber.equals(account.getAccountNumber())) {
				return account.getAccountBalance(); //return은 만나는 순간 값 반환하고 메서드 종료!
			}
		}
		return -1;
	}

	@Override
	public void searchMyAccountType(String accountNumber) {
		//입력받은 계좌번호로 실제 계좌가 있는지 확인후에
		//있으면 해당 계좌의 타입 출력(Account 객체 안에 정의되어있는 메서드 활용)
		//없으면 "해당 계좌를 찾을 수 없습니다" 출력
		for(Account account:accountList) {
			if(accountNumber.equals(account.getAccountNumber())) {
				account.printAccountType();//출력
				return; //반환값 없음/메서드 마무리(끝)
			}
		}
		view.printMessage("해당 계좌를 찾을 수 없습니다");
	}

	@Override
	public boolean deposit(String accountNumber, int inputMoney) {
		//입력한 계좌가 있는지 확인 후에 없으면 false 반환
		//있으면, 입력한 입금금액이 마이너스 금액을 입력한 경우에도 false 반환
		//입금금액이 +금액일 경우, 현재 잔액에서 추가 금액을 합산한 금액으로 accountBalance 수정 -> true 반환
		if(inputMoney>0) {
			for(Account account:accountList) {
				if(accountNumber.equals(account.getAccountNumber())) {
					account.setAccountBalance(account.getAccountBalance()+inputMoney);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean withdraw(String accountNumber, int inputMoney) {
		//입력한 계좌번호가 있는지 확인 후에 없으면 false 반환
		//있으면, 입력한 출금금액이 마이너스 금액/출금했을 때 잔액이 마이너스가 되는 경우 false 반환
		//그 나머지 경우에는 입력한 금액만큼 뺀 금액으로 accountBalance 수정 -> true 반환
		if(inputMoney>0) {
			for(Account account:accountList) {
				if(accountNumber.equals(account.getAccountNumber())) {
					if(account.getAccountBalance()-inputMoney<0)
						return false;
					else { // 출금0
						account.setAccountBalance(account.getAccountBalance()-inputMoney);
						return true;
					}
				}
			}
		}
		return false;
	}

}
