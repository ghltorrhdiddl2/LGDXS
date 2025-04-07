package com.smhrd.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.smhrd.model.Music;

public class MusicView {

	//전체 메뉴 출력(추가, 삭제, 종료)
	public void showMenu() {
		System.out.print("[1]노래 추가 [2]노래 삭제 [3]종료 >>");
	}
	
	//노래 추가 메뉴 출력
	public void showAddMenu() {
		System.out.print("[1]마지막위치에 추가 [2]원하는 위치에 추가 >> ");
	}
	
	//노래 삭제 메뉴 출력
	public void showDeleteMenu() {
		System.out.print("[1]선택 삭제 [2]전체 삭제 >> ");
	}
	
	
	//노래 제목 입력 + 입력한 값을 결과값으로 리턴(컨트롤러한테 넘겨주려고!)
	public String promptTitle(Scanner sc) {
		System.out.print("추가할 노래 제목 입력 : ");
		String inputMusicTitle = sc.next();
		return inputMusicTitle;
	}
	
	//가수 입력 + 입력한 값을 결과값으로 리턴
	public String promptSinger(Scanner sc) {
		System.out.print("추가할 노래 가수 입력 : ");
		String inputSinger = sc.next();
		return inputSinger;
	}
	
	public int promptPlayTime(Scanner sc) {
		System.out.print("추가할 노래 플레이타임 입력 : ");
		int inputPlayTime = sc.nextInt();
		return inputPlayTime;
	}
	
	public int promptIndex(Scanner sc, String message) {
		System.out.print(message);
		int inputIndex = sc.nextInt();
		return inputIndex;
	}
	
	//단순 메세지 출력
	public void printMessage(String message) {
		System.out.println(message);
	}
	
	public void printMusicList(ArrayList<Music> musicList) {
		//1. 현재 재생 목록 출력 - 아무것도 없으면(재생목록이 없습니다 출력) / 재생목록 출력
		System.out.println("======현재 재생 목록======");
		if(musicList.size()==0) { //출력할 것 이 없을때(노래가 아무것도 없을때)
			System.out.println("재생 목록이 없습니다.");
		}else { //노래가 1개 이상 있는경우
			for(int i=0; i<musicList.size(); i++) {
				int min = musicList.get(i).getPlayTime()/60;
				int sec = musicList.get(i).getPlayTime()%60;
				System.out.println((i+1)+". "+ musicList.get(i).getTitle()+"-"+musicList.get(i).getSinger()+" "+min+"분"+sec+"초");
			}
		}
		System.out.println("=====================");
	}
	
}

