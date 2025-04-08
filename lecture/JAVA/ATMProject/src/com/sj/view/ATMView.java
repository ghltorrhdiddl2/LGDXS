package com.sj.view;
import java.util.Scanner;

public class ATMView {

	public int getMenuSelection(Scanner sc) {
		System.out.print("[1]계좌 신설 [2]잔액 확인 [3]계좌 타입 확인 [4]입금"
				+ " [5]출금 [6]종료 >> ");
		return sc.nextInt();
	}
	public int getAccountTypeSelection(Scanner sc) {
		System.out.print("[1]입출금 [2]저축 [3]사업자 >> ");
		return sc.nextInt();
	}
	public String promptName(Scanner sc) {
		System.out.print("이름 >> ");
		return sc.next();
		
	}
	public String promptAccountNumber(Scanner sc) {
		System.out.print("계좌번호 >> ");
		return sc.next();
	}
	public int promptInputMoney(Scanner sc) {
		System.out.print("입금할 금액 >> ");
		return sc.nextInt();
	}
	public int promptOutputMoney(Scanner sc) {
		System.out.print("출금할 금액 >> ");
		return sc.nextInt();
	}
	
	public void printMessage(String message) {
		System.out.println(message);
	}
	
}
