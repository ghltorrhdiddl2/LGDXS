
public class PiggyBank {

	private int money = 0;
	
	//입금기능
	public void deposit(int m) {
		money += m;
	}
	
	//출금기능
	public void withdraw(int m) {
		money -= m;
	}
	
	//잔액 보여주기
	public void showMoney() {
		System.out.println("잔액 : "+money);
	}

}
