package com.sj.product;

import java.util.ArrayList;
import java.util.Scanner;

public class ProductMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<Product> productList = new ArrayList<>();
		
		//상품 종류 갯수 입력
		System.out.print("등록할 상품 종류 : ");
		int productCnt = sc.nextInt();
		
		//상품 종류 갯수 만큼 물건이름/단가/수량 입력
		for(int i=1;i<=productCnt;i++) {
			System.out.print(i+") 물건 이름 : ");
			String name = sc.next();
			System.out.print(i+") 단가 : ");
			int unitPrice = sc.nextInt();
			System.out.print(i+") 수량 : ");
			int amount = sc.nextInt();
			
			Product product = new Product(name, unitPrice, amount);
			productList.add(product);
		}
		
		//제품명 / 단가 / 수량 출력
		System.out.println("제품명\t단가\t수량");
		int total = 0;
		for(Product p:productList) {
			System.out.println(p.getName()+"\t"+p.getUnitPrice()+"원\t"+p.getAmount()+"개");
			total+=p.getUnitPrice()*p.getAmount();
		}
		System.out.println("총 금액 : "+total+"원");
		sc.close();
	}

}
