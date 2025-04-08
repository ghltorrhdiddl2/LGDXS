package 사칙연산;

import java.util.Random;

public class DiviGame implements IGame{
	
	private Random random = new Random();
	private int num1;
	private int num2;
	
	public DiviGame() {
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public void makeRandom() {
		// TODO Auto-generated method stub
		num1 = random.nextInt(10)+1;
		num2 = random.nextInt(10)+1;
	}

	@Override
	public String getQuizMsg() {
		// TODO Auto-generated method stub
		return num1 + "/" + num2 + "=";
	}

	@Override
	public boolean checkAnswer(int answer) {
		// TODO Auto-generated method stub
		
		if(answer == num1/num2) {
			return true;
		}
		return false;
	}
	


}
