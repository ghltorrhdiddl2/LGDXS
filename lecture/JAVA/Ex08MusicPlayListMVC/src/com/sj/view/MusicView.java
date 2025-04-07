package com.sj.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.sj.model.MusicData;


public class MusicView {

	//전체 메뉴 출력(추가,삭제,종료)
	public void showMenu() {
		System.out.print("[1]노래 추가 [2]노래 삭제 [3]종료 >> ");
	}
	//노래 추가 메뉴 출력
	public void showAddMenu() {
		System.out.print("[1]마지막위치에 추가 [2]원하는 위치에 추가 >> ");
	}
	//
	public void showDeleteMenu() {
		System.out.print("[1]선택삭제 [2]전체삭제 >> ");
	}
	
	
	//노래 제목 입력 + 입력한 값을 결과값으로 리턴(컨트롤로에게 넘겨주기 위해)
	public String promptTitle(Scanner sc) {
		System.out.print("추가할 노래 제목 입력 : ");
		String song = sc.next();
		return song;
	}
	//가수 입력 + 입력한 값을 결과값으로 리턴
	public String promptSinger(Scanner sc) {
		System.out.print("추가할 노래 가수 입력 : ");
		String singer = sc.next();
		return singer;
	}
	//
	public int promptPlayTime(Scanner sc) {
		System.out.print("추가할 노래 플레이타임(초) 입력 : ");
		int playTime = sc.nextInt();
		return playTime;
	}
	public int promptIndex(Scanner sc, String message) {
		System.out.print(message);
		int location = sc.nextInt();
		return location;
	}
	
	
	//단순 메세지 출력
	public void printMessage(String message) {
		System.out.println(message);
	}
	
	
	public void printMusicList(ArrayList<MusicData> musicList) {
		System.out.println("====== 현재 재생 목록 ======");
		if(musicList.size()==0) {
			System.out.println("재생 목록이 없습니다.");
		}else {
			for(int i=0;i<musicList.size();i++) {
				int min =  musicList.get(i).getPlayTime()/60;
				int sec = musicList.get(i).getPlayTime()%60;
				System.out.println((i+1)+") "+musicList.get(i).getTitle()+"-"
				+musicList.get(i).getSinger()+" "+min+"분 "+sec+"초");
			}	
		}
		System.out.println("=======================");
	}
}
