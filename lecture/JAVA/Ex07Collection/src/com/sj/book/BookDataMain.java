package com.sj.book;

import java.util.ArrayList;
import java.util.Scanner;

public class BookDataMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<BookData> bookList = new ArrayList<>();
		
		while(true) {
			System.out.print("1)책 추가 2)책 삭제 3)내가 살 수 있는 책 4)종료" );
			int op = sc.nextInt();
			if(op==4) {
				System.out.println("종료");
				break;
			}else if(op==1) {
				System.out.print("책 이름 : ");
				String title = sc.next();
				System.out.print("가격 : ");
				int price = sc.nextInt();
				System.out.print("작가 : ");
				String author = sc.next();
				
				BookData book = new BookData(title,price,author);
				bookList.add(book);
			}else if(op==2) {
				System.out.println("\t책제목\t가격\t작가");
				int cnt = 1;
				for(BookData b:bookList) {
					System.out.println(cnt+".\t"+b.getTitle()+"\t"+b.getPrice()+"원\t"+b.getWriter());
					cnt++;
				}
				System.out.print("삭제하고 싶은 책 >> ");
				int del = sc.nextInt();
				bookList.remove((del-1));
			}else if(op==3) {
				System.out.print("금액을 입력하세요 : ");
				int money = sc.nextInt();
				System.out.println("구매 가능한 책 목록");
				for(BookData b:bookList) {
					if(b.getPrice()<=money)
						System.out.println("["+b.getTitle()+", "+b.getWriter()+", "+b.getPrice()+"원 ]");
				}
			}
		}

	}

}
