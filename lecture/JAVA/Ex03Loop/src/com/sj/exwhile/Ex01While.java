package com.sj.exwhile;

public class Ex01While {

	public static void main(String[] args) {
		//1부터 5까지의 수를 순서대로 출력해라
		int i=0;
		while (i<5) {
			i++;
			System.out.println(i);		
		}
		
		i=0;
		while (i<5) {
			i++;
			System.out.println(i);
			
			if (i==2)
				break;
		}
		
		int j=0;
		do {
			j++;
			System.out.println(j);
		}while(j<4);
		
	}

}
