package 인터페이스;

public class Main {

	public static void main(String[] args) {
		
		//인터페이스는 객체 생성이 가능할까?
		// --> 익명의 내부 자료형태(안드로이드에서 많이 사용)는 객체 생성이 가능
		// --> 일반적인 형태로 객체 생성은 불가!
		Temp t = new Temp() {
			
			@Override
			public void temp() {
				// TODO Auto-generated method stub
				
			}
		};

	}

}
