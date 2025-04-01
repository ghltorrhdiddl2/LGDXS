
public class Ex05Array {
	public static void main(String[] args) {
		
		// 가장 큰 숫자 찾기
		int[] array = {-7,-6,-21,-1,-2};
		
		int tmp = array[0];
		for(int i=0;i<array.length;i++) {
			if (tmp<array[i]) {
				tmp = array[i];
			}
		}
		System.out.println("가장 큰 수 " +tmp);
		
		int tmp2 = array[0];
		for(int i=0;i<array.length;i++) {
			if (tmp2>array[i]) {
				tmp2 = array[i];
			}
		}
		System.out.println("가장 작은 수 " +tmp2);
	}
}
