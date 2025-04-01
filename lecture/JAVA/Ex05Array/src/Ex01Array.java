
public class Ex01Array {

	public static void main(String[] args) {
		
		//변수선언
		int[] array;  // 이 형식을 더 선호함
		
		//배열 생성
		array = new int[3];  // 3칸짜리 배열 생성
		System.out.println(array[0]); // 배열 생성될때 0으로 자동 초기화됨
		
		//선언과 배열 생성을 한번에 -> 0으로 초기화
		int[] array2 = new int[3];
		
		//배열 생성과 동시에 초기화
		int[] array3 = {4,7,3,5};
		
		System.out.println(array3[2]);
		
		
		//------
		int[] score = new int[5]; //완성형 1
		score[0] = 1;
		score[1] = 2;
		score[2] = 3;
		score[3] = 4;
		score[4] = 5;
		int[] score2 = {10, 20, 30, 40, 50}; //완성형 2
		
	}

}
