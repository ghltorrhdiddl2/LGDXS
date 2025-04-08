package 자판기만들기;

import java.util.Scanner;

public class Korean implements VendingMachine{

	@Override
	public void selectedLanguage() {
		System.out.println("안녕하세요. 무엇을 도와드릴까요?");
	}
	
	@Override
	public void showMenu() {
		System.out.println("===== 자판기 메뉴 =====");
		System.out.println("[1] 콜라\t1000원");
		System.out.println("[2] 사이다\t800원");
		System.out.println("[3] 커피\t1500원");
	}

	@Override
	public void selectItem() {
		Scanner sc = new Scanner(System.in);
		System.out.print("원하는 음료를 선택해주세요 >> ");
		int drink = sc.nextInt();
		if(drink==1) {
			System.out.println("콜라를 선택하셨습니다!");
		}else if(drink==2) {
			System.out.println("사디아를 선택하셨습니다!");
		}else if(drink==3) {
			System.out.println("커피를 선택하셨습니다!");
		}else
			System.out.println("잘못된 번호입니다.");
	}

	@Override
	public void pay() {
		System.out.println("결제 중입니다... 감사합니다!");
	}
	
}
