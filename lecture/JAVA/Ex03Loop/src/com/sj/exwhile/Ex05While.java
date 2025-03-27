package com.sj.exwhile;
import java.util.Random;
import java.util.Scanner;

public class Ex05While {

	public static void main(String[] args) {
		
		boolean isContinue=true;
		int suc = 0;
		int fa = 0;
		Random ran=new Random();
		
		int num1=ran.nextInt(10)+1; //10: 0~9 +1 -> 1~10
		int num2=ran.nextInt(10)+1;
		Scanner sc = new Scanner(System.in);
		while(isContinue) {
			
			System.out.print(num1+"+"+num2+"=");
			int answer = sc.nextInt();
			
			if (answer==(num1+num2)) {
				suc++;
				System.out.println("Success!");
				num1=ran.nextInt(10)+1;
				num2=ran.nextInt(10)+1;
			}	
			else {
				fa++;
				System.out.println("Fail!");
				
			}
			System.out.print("계속하시겠습니까?");
			if (sc.next().equalsIgnoreCase("N")) {
				System.out.println("종료!");
				System.out.println("맞춘개수 : "+suc);	
				System.out.println("틀린개수 : "+fa);	
				isContinue=false;
			}	
		}
		sc.close();
		
		
		/*int suc = 0, fa = 0;
        Random ran = new Random();
        Scanner sc = new Scanner(System.in);

        while (true) {
            int num1 = ran.nextInt(10) + 1;
            int num2 = ran.nextInt(10) + 1;

            while (true) { // 틀리면 같은 문제 반복
                System.out.print(num1 + "+" + num2 + "=");
                int answer = sc.nextInt();

                if (answer == num1 + num2) {
                    suc++;
                    System.out.println("Success!");
                    break; // 맞히면 새 문제로 이동
                } else {
                    fa++;
                    System.out.println("Fail!");
                }
            }

            System.out.print("계속하시겠습니까? (Y/N): ");
            if (sc.next().equalsIgnoreCase("N")) break;
        }

        System.out.println("종료! 맞춘 개수: " + suc + ", 틀린 개수: " + fa);
        sc.close();*/
		
		
		/*Scanner sc = new Scanner(System.in);
	    Random rd = new Random();
	      
	      //isContinue = true -> 계속 진행 / false => 반복중단
	    boolean isContinue = true;
	      //맞췄는지 틀렸는지 판단 (첫번째 문제와 맞췄을경우에만 새로운 문제를 내기 위함)
	    boolean isCorrect = true;
	      
	    int num1 = 0; //첫번째 랜덤 숫자
	    int num2 = 0; //두번째 랜덤 숫자
	      
	    int correctCnt = 0; //맞춘 개수
	    int wrongCnt = 0; //틀린 개수
	      
	    while(isContinue) {
	         
	         if(isCorrect) { //첫번째 문자와 이전 문제를 맞췄을 경우에만 새문제 뽑기
	            num1 = rd.nextInt(10)+1;
	            num2 = rd.nextInt(10)+1;
	         }
	         System.out.print(num1 + "+" + num2 +"=");
	         int input = sc.nextInt();
	         
	         if(input==num1+num2) {//정답
	            correctCnt++; //맞춘개수 1증가 
	            isCorrect = true; //새문제를 뽑을 수 있도록함
	            System.out.println("Success!");
	         }else { //오답
	            wrongCnt++; //틀린개수 1증가
	            isCorrect = false; //새문제를 뽑지 않도록 함
	            System.out.println("Fail!");
	         }
	         
	         System.out.print("계속하시겠습니까? (Y/N)");
	         String yn = sc.next();
	         
	         if(yn.equalsIgnoreCase("n")) {
	            isContinue = false;
	         }
	      }
	      
	      System.out.println("종료");
	      System.out.println("맞춘개수 : " + correctCnt);
	      System.out.println("틀린개수 : " + wrongCnt);*/
		
	}
}
