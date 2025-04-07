package 닌텐도게임;

public class GameMain {
// 닌텐도 게임기 (실행할 수 있는 공간)
	public static void main(String[] args) {
		
		//마리오 게임칩 꺼내오기
		Mario m = new Mario();
		//게임칩 넣기
		on(m);
		// 젤다 게임칩 꺼내오기
		Zelda z = new Zelda();
		on(z);
		// 원리 : Mario m = new Zelda(); --> 업캐스팅됨
		// 포켓몬고 게임칩 꺼내오기
		PokemonGo p = new PokemonGo();
		on(p);	
		
	}

	// 게임칩을 넣어서 작동시키는 메소드
	private static void on(GameChip g) {
		g.gameStart();
	}
	//메소드 오버로딩 기법으로 풀어내는 방법
//	private static void on(Zelda z) {
//		z.gameStart();	
//	}
//	private static void on(PokemonGo p) {
//		p.gameStart();
//	}


}
