
public class Ex02Array {

	public static void main(String[] args) {
		
		int[] array1 = new int[3];
		
		System.out.println(array1[1]);
		array1[1]=4;
		System.out.println(array1[1]);
		
		
		//레퍼전스 변수 초기화 -> null
		int[] array2=null;
		System.out.println(array2);
		
		//주소값 복사 -> 같은 객체를 가리키게 됨
		array2 = array1;
		System.out.println(array2[1]);
		
		array2[1]=10;
		System.out.println(array2[1]);  //10
		System.out.println(array1[1]);  //4 -> 10 : 주소값이 같아졌으므로
		
		System.out.println();
		System.out.println(array1);
		System.out.println(array2); // -> 주소값이 같음을 확인
		
		// 기본데이터 타입 복사 -> 값을 복사
		int num1=10;
		int num2=num1;
		num2=15;
		System.out.println(num1);
		System.out.println(num2);
		
		//배열의 크기(길이)
		System.out.println(array1.length);
	}

}
