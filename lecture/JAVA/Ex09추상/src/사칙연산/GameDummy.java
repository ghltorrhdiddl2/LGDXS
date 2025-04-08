package 사칙연산;

public class GameDummy implements IGame{

	@Override
	public void makeRandom() {
	}

	@Override
	public String getQuizMsg() {
		return "2+3=";
	}

	@Override
	public boolean checkAnswer(int answer) {
		return true;
	}

}
