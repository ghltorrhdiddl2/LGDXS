
public class PiggyBankMain {

	public static void main(String[] args) {
		//레퍼런스 변수 선언
		PiggyBank bank1;
		
		//객체 생성
		bank1 = new PiggyBank();
		System.out.println(bank1); //주소값 확인
		
		bank1.deposit(1000);
		bank1.showMoney();
		bank1.withdraw(500);
		bank1.showMoney();
		
		//bank2객체 생성
		PiggyBank bank2 = new PiggyBank();
		System.out.println(bank2);
		bank2.showMoney();
		
		//private으로 변수를 선언했기 때문에 money 접근이 불가능함
		//bank1.money = 15000;
		bank1.showMoney();
	}
}
