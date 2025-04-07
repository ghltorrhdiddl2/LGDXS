package 상속기초;

public class Child extends Parent{

	//새로운 클래스, 자식클래스, 서브클래스
	//한식당 2호점
	// extends --> 연장하다, 확장하다의 의미!
	// : class를 선언하는 구간에 적는다!
	
	//1. 김치찌개 메뉴를 만든다
	/**
	 * 김치찌개 메뉴를 만드는 메소드
	 * @author 이도연
	 * @since 25.4.4
	 * @return 없음
	 * @param 매개변수 없음
	 */
	public void makeKimchi() {
		System.out.println("맛있는 김치찌개를 만든다~~");
	}
	
	//2. 부모님 가게의 게장 메뉴를 변경
	// @ : annotation(주석) --> 특정 기능을 가지고 있지는 않음
	// --> 자동완성된 메소드 위에 마우스 올렸을 때 포스트잇 모양에 표시해주는 역할!
	@Override
	public void makeGae() {
		System.out.println("아주아주 맛있는 게장을 만든다~");
	}
	// 메소드 오버라이딩(method overriding)
	//: 상속이 전제되어 있어야 함!
	//: 부모 클래스가 가지고 있는 메소드의 틀(리턴타입, 메소드명, 매개변수)을 
	//  그대로 가지고 와서 {} 안쪽에 있는 로직만 *재정의*하는 기법
	
	//오버라이딩 - 재정의
	//오버로딩 - 중복정의

}
