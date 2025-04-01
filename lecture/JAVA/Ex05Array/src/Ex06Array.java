import java.util.Scanner;

public class Ex06Array {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] array = new int[5];
		
		for(int i=0;i<array.length;i++) {
			System.out.printf("%d번째 입력>>",(i+1));
			int num = sc.nextInt();
			array[i]=num;
		}
		System.out.print("입력된 점수: ");
		for(int i:array) 
			System.out.print(i+" ");
		
		sc.close();
		
		int max = array[0];
		int min = array[0];
		int total = 0;
		for(int num:array) {
			max = num>max?num:max;
			min = num<min?num:min;
			total+=num;
		}

		System.out.println("\n최고 점수: "+max);
		System.out.println("최저 점수: "+min);
		System.out.println("총합 점수: "+total);
		System.out.println("평균 점수: "+((float)total/array.length));
	}
}
