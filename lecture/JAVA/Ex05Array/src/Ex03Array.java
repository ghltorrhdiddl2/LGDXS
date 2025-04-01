
public class Ex03Array {

	public static void main(String[] args) {
		int[] array = {13,5,7,17,3,16,19,20,2,1};
		System.out.println("array 길이 : "+array.length);
		
		// 배열에 들어있는 모든 데이터를 출력
		for(int i=0; i<array.length; i++)
			System.out.print(array[i]+" ");
		
		// 배열에 들어있는 데이터 모두 더하기
		int sum = 0;
		for(int i=0; i<array.length; i++)
			sum+=array[i];
		System.out.println("\n합 : "+sum);
	}
}
