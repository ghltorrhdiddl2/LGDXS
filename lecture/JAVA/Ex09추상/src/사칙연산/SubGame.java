package 사칙연산;

import java.util.Random;

public class SubGame implements IGame{
	
	private Random random = new Random();
	private int num1;
	private int num2;
	
	public SubGame() {
	}

	@Override
	public void makeRandom() {
		num1 = random.nextInt(10)+1;
		num2 = random.nextInt(10)+1;
	}

	@Override
	public String getQuizMsg() {
		return num1 + "-" + num2 + "=";
	}

	@Override
	public boolean checkAnswer(int answer) {
		if(answer == Math.max(num1, num2) - Math.min(num1, num2)) {
			return true;
		}
		return false;
	}

}
