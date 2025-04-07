package 상속기초;

public class Main {

	public static void main(String[] args) {
		// 1. Parent 자료형 p 객체 생성
		Parent p = new Parent();
		// 2. Child 자료형 c 객체 생성
		Child c = new Child();
		
		p.makeGae();
		c.makeGae();

		// 상속의 특징 3가지
		//1. 다중상속이 불가능
		//2. 상속의 횟수에 제한이 없다
		//3. 모든 클래스는 Object(최상위 클래스)를 상속받는다
		//---> Object == 클래스 공통의 조상
		
		//객체의 Casting(형변환)
		//Reference type에서의 형변환은 반드시 상속이 전제되어 있어야 함
		//--> 객체 내 필드, 메소드의 접근권한을 기준으로 강제 vs 자동 결정!
		
		//1) UpCasting(업캐스팅)
		// : 자식(서브,하위)클래스가 부모(슈퍼,상위)클래스 타입으로 자동으로 형변환
		// ex) 강아지는 동물이다 --> 말 됨! 자동으로 형변황
		// ex) 모든 동물은 강아지다 --> 말 안됨!
		Parent p2 = new Child();
		//부모자료형 레퍼런스 변수명 = new 자식자료형();
		p2.makeGae();
		//만약에, 자식클래스가 부모클래스의 메소드를 재정의(오버라이딩)한 경우
		// 업캐스팅 객체는 자식클래스의 메소드를 호출
		
		//2) DownCasting(다운캐스팅)
		// : 부모 클래스가 자식 클래스로 강제 형변환
		/*Child c2 = (Child) new Parent();*/
		// 코드에서 빨간중이 뜬다 == Syntax Error! (구문 오류)
		// Exception(예외) 발생!
		// : 구문 오류는 없으나 실행했을 때 예외상황이 발생하는 것을 의미
		
		//3) DoownCasting(다운캐스팅) -> 진짜!
		// : 업캐스팅된 객체를 강제 형변환으로 본래의 자료형태로 되돌려 놓는 것
		// 전제조건: 업캐스팅된 객체만 다운캐스팅 가능!
		Child c2 = (Child) p2;  //child로 바꿀거야
		c2.makeKimchi();
		//--> 본래 자신이 가지고 있던 메소드 사용 가능하게 바뀜
	}

}
