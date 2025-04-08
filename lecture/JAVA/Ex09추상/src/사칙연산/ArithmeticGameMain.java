package 사칙연산;

import java.util.Scanner;

public class ArithmeticGameMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		IGame game = new SubGame();
		
		for(int i=1;i<6;i++) {
			game.makeRandom();
			for(int j=1;j<4;j++) {
				System.out.print(game.getQuizMsg());
				int answer = sc.nextInt();
				if(game.checkAnswer(answer)) {
					System.out.println("잘 맞췄어요!");
					break;
				}else {
					if(j==1) {
						System.out.println("다시 생각해볼까요?");
						System.out.println("남은 기회는 "+(3-j)+"번 입니다.");
					}
					else if(j==2) {
						System.out.println("한 번 더!");
						System.out.println("남은 기회는 "+(3-j)+"번 입니다.");
					}
					else if(j==3) {
						System.out.println("그냥 다음 문제로 넘어가요..");
						break;
					}
				}
			}
			System.out.println(i+"번 게임을 진행하였습니다");
		}

	}

}
