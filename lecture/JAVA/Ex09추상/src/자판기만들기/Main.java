package 자판기만들기;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[1]Kor/[2]Eng?");
		int language = sc.nextInt();
		
		if(language==1) {
			VendingMachine machine = new Korean();
			machine.showMenu();
			machine.selectItem();
			machine.pay();
		}else {
			VendingMachine machine = new English();
			machine.showMenu();
			machine.selectItem();
			machine.pay();
		}
	}

}
