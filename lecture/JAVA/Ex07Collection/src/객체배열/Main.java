package 객체배열;

public class Main {

	public static void main(String[] args) {
		// 1. 객체 배열 생성
		// 자료형[] 배열이름 = new 자료형[칸수];
		// 포켓몬 자료형을 3개 보관한 수 있는 bag 배열 생성
		Pokemon[] bag = new Pokemon[3];
		
		// 객체 배열의 0번 인덱스에 들어있는 데이터 출력
		System.out.println(bag[0]);  // null
		
		// 2. 객체 배열의 0번 인덱스에 포켓몬 넣어보자
//		Pokemon pika = new Pokemon("피카츄","전기","백만볼트",100,10);
//		bag[0]=pika;
		bag[0] = new Pokemon("피카츄","전기","백만볼트",100,10);
		System.out.println(bag[0]); //주소값: 객체배열.Pokemon@626b2d4a
		System.out.println(bag[0].getName());
	
		// 3. bag 배열의 1,2 인덱스에도 포켓몬 넣어주기
		// 1번 인덱스
		// 리자몽, 불, 화염방사, 150, 15
		// 2번 인덱스
		// 마자용, 에스퍼, 애교부리기, 90, 9
		bag[1] = new Pokemon("리자몽","불","화염방사",150,15);
		bag[2] = new Pokemon("마자용","에스퍼","애교부리기",90,9);
		
		// 4. 배열 안에 들어있는 포켓몬 정보를 출력 -> 이름, 타입, hp
		System.out.println("===== 포켓몬 정보 출력 =====");
		System.out.println("이름\t타입\thp");
		for(int i=0;i<bag.length;i++)
			System.out.println(bag[i].getName()+"\t"+bag[i].getType()+"\t"+bag[i].getHp());
		
		System.out.println();
		// for-each문
		for(Pokemon p: bag) {
			System.out.println(p.getName()+"\t"+p.getType()+"\t"+p.getHp());
		}

	}

}
