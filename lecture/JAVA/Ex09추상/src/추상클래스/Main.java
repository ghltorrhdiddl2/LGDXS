package 추상클래스;

public class Main {

	public static void main(String[] args) {
		RegularEmployee emp = new RegularEmployee("SMHRD001","홍길동",4000,200);
		System.out.println(emp.print());
		System.out.println(emp.getMoneyPay());
		
		TempEmployee tmp = new TempEmployee("SMHRD002","박이수",3000);
		System.out.println(tmp.print());
		
		PartTimeEmployee part = new PartTimeEmployee("SMHRD003","임성훈",10,10);
		System.out.println(part.print());
		System.out.println(part.getMoneyPay());
	}
}
