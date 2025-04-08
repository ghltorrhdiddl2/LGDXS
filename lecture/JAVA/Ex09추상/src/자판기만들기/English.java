package 자판기만들기;

import java.util.Scanner;

public class English implements VendingMachine{

	@Override
	public void selectedLanguage() {
		System.out.println("Hello. May I help you?");
	}
	
	@Override
	public void showMenu() {
		System.out.println("===== vending machine menu =====");
		System.out.println("[1] Cola\t1$");
		System.out.println("[2] Sprite\t0.8$");
		System.out.println("[3] Coffee\t1.5$");
	}

	@Override
	public void selectItem() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Please select the drink you want >> ");
		int drink = sc.nextInt();
		if(drink==1) {
			System.out.println("You chose Cola!");
		}else if(drink==2) {
			System.out.println("You chose Sprite!");
		}else if(drink==3) {
			System.out.println("You chose Coffee!");
		}else
			System.out.println("Wrong select number.");
	}

	@Override
	public void pay() {
		System.out.println("Payment is in progress... Thank you!");
	}

}
