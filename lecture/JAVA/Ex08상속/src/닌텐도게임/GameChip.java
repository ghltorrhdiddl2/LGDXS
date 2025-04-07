package 닌텐도게임;

public abstract class GameChip {
	//1. 추상클래스(abstract)
	// - 추상메소드를 하나라도 가지고 있다면 반드시 추상클래스가 되어야 함
	// - 추상클래스도 일반메소드를 가지고 있을 수 있다
	// - abstract 키워드를 사용해 선언
	
	public void temp() {
		System.out.println("임의로 만든 메소드");
	}
	
	public abstract void gameStart();  // abstract:추상화
	//2. 추상메소드
	// - {}가 없는 메소드
	// - 선언(틀: 리턴타입,매개변수,메소드명)은 되어있으나 로직이 구현되어 있지 않은 메소드
	// - abstract 키워드를 사용하여 선언
	
}
