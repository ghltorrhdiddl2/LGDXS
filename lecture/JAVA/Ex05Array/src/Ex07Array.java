import java.util.Scanner;

public class Ex07Array {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int[] answer = {1,4,3,2,1};
		String[] user = new String[5];
		int score = 0;
		
		System.out.println("==채점하기==\n답을 입력하세요");
		for(int i=0;i<answer.length;i++) {
			System.out.printf("%d번답 >> ",(i+1));
			int num = sc.nextInt();
			if (num == answer[i]) {
				user[i]="O";
				score++;
			}
			else
				user[i]="X";
		}
		System.out.println("정답확인");
		for(String j:user)
			System.out.print(j+" ");
		System.out.print("총점 : "+(score*20));
		
		sc.close();
	}
}
