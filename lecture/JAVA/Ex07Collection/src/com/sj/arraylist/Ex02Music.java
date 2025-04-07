package com.sj.arraylist;

import java.util.ArrayList;
import java.util.Scanner;

public class Ex02Music {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<String> musicList = new ArrayList<>();
		
		while(true) {
			System.out.print("[1]노래 추가 [2]노래 삭제 [3]종료 >> ");
			int op = sc.nextInt();
			if(op==1) {
				printMusicList(musicList);
				System.out.print("[1]마지막위치에 추가 [2]원하는 위치에 추가 >> ");
				int add = sc.nextInt();
				System.out.print("추가할 노래 입력 : ");
				sc.nextLine();  // 버퍼 비워주기
				String song = sc.nextLine(); // 띄어쓰기 포함 하고싶을 때
				if(add==1) {
					musicList.add(song);
				}else {
					System.out.print("추가할 위치 입력 : ");
					int location = sc.nextInt();
					musicList.add((location-1),song);
				}
				System.out.println("추가가 완료되었습니다.");
			}else if(op==2) {
				System.out.println("====== 현재 재생 목록 ======");
				if(musicList.size()==0) {
					System.out.println("재생 목록이 없습니다.");
					System.out.println("=======================");
				}else {
					for(int i=0;i<musicList.size();i++)
						System.out.println((i+1)+". "+musicList.get(i));
					System.out.println("=======================");
					System.out.print("[1]선택삭제 [2]전체삭제 >> ");
					int del = sc.nextInt();
					if(del==1) {
						System.out.print("삭제할 노래 선책 >> ");
						int delNum = sc.nextInt();
						if(delNum<=musicList.size()) {
							musicList.remove(delNum-1);
							System.out.println("노래가 삭제되었습니다.");
						}else
							System.out.println("번호를 다시 입력해주세요");
					}else {
						musicList.clear();
						System.out.println("전체list가 삭제되었습니다.");
					}
				}
			}else if(op==3) {
				System.out.println("프로그램이 종료되었습니다.");
				break;
			}
		}
		sc.close();
	}
	
	public static void printMusicList(ArrayList<String> musicList) {
		System.out.println("====== 현재 재생 목록 ======");
		if(musicList.size()==0) {
			System.out.println("재생 목록이 없습니다.");
		}else {
			for(int i=0;i<musicList.size();i++)
				System.out.println((i+1)+". "+musicList.get(i));
		}
		System.out.println("=======================");
	}
}
