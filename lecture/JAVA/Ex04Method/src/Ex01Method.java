
public class Ex01Method {
	
	//main 메서드: 출발점 -> 사전에 메모리에 올려놔야지만 시작할 수 있음(static)
	public static void main(String[] args) {
		//1번 메서드 호출, 반환값이 있는 경우에는 해당 타입의 변수에 담아줄 수 있음!
		int a = addNumber(3,5);
		System.out.println(a);
		
		//2번 메서드 호출
		addNumber2(5,4);
		
		//3번 메서드 호출
		print(); 
	}
	
	//메서드 안에 메서드 만드는 것은 문법적으로 불가능!, 클래스 안에 종속되어야 함!
	//1. 매개변수 o, 반환값 O
	public static int addNumber(int num1,int num2) {
		int result = num1 + num2;
		return result;
	}
	
	//2. 매개변수 O, 반환값 X
	public static void addNumber2(int num1,int num2) {
		int result=num1+num2;
		System.out.println("결과 : "+result);
	}
	
	//3. 매개변수 X, 반환값 X
	public static void print() {
		System.out.println("출력!");
	}
}
