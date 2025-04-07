import java.util.Scanner;

public class Exam06 {

	public static void main(String[] args) {
		boolean run = true;
		int studentNum=0;
		int[] scores=null;
		Scanner sc = new Scanner(System.in);
		
		while(run) {
			System.out.println("---------------------------");
			System.out.println("1.학생수 |2.점수입력 |3.점수리스트 |4.분석 |5.종료");
			System.out.println("---------------------------");
			System.out.print("선택> ");
			int selectNo = sc.nextInt();
			
			if(selectNo==1) {
				System.out.print("학생수> ");
				int studentN = sc.nextInt();
				scores = new int[studentN];
			}else if(selectNo==2) {
				for(int i=0;i<scores.length;i++) {
					System.out.printf("scores[%d]> ",i);
					scores[i]=sc.nextInt();
				}
			}else if(selectNo==3) {
				for(int i=0;i<scores.length;i++)
					System.out.printf("scores[%d]> %d\n",i,scores[i]);
			}else if(selectNo==4) {
				int max = scores[0];
				int total = 0;
				for(int s:scores) {
					max = s>max?s:max;
					total += s;
				}
				System.out.println("최고점수: "+max);
				System.out.println("평균점수: "+(double)total/scores.length);
			}else if(selectNo==5)
				run=false;
		}
		sc.close();
		System.out.println("프로그램 종료");
	}

}
