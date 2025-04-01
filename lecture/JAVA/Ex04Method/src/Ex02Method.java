import java.util.Scanner;

public class Ex02Method {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("첫번째 수 : ");
		int num1 = sc.nextInt();
		System.out.print("두번째 수 : ");
		int num2 = sc.nextInt();
		
//		getMinusVal(num1,num2);
		
		int rs = getAbsoluteVal(num1,num2);
		System.out.println("결과 : "+rs);
	}
	
	public static void getMinusVal(int a,int b) {
		System.out.println("결과 : "+(a-b));
	}
	
	public static int getAbsoluteVal(int a,int b) {
		return (a-b)>0?(a-b):(b-a);
	}
	
	//정수,실수
	public static double getAbsoluteVal(int a,double b) {
		return (a-b)>0?(a-b):(b-a);
	}
	
	//메서드 이름은 같게 하고 매개인자의 타입, 개수, 순서를 다르게 하는 것을
	// => 메서드 오버로딩(Overloading) => 메서드 중복정의
	// 실수,실수
	public static double getAbsoluteVal(double a,double b) {
		return (a-b)>0?(a-b):(b-a);
	}
}