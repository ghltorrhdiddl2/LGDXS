
public class Ex04Array {

	public static void main(String[] args) {
		int[] array = {4,3,6,7,2,8,22,71,9};
		
		//홀수 값 출력하고 총 몇개인지 출력
		int odd=0;
		System.out.print("arry에 들어있는 홀수는 ");
		for(int i=0;i<array.length;i++) {
			if (array[i]%2==1) {
				odd++;
				System.out.print(array[i]+" ");
			}	
		}
		System.out.printf("이며, 총 %d개 입니다.",odd);
	}

}
