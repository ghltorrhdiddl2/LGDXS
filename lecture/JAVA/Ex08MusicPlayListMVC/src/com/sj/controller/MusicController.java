package com.sj.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.sj.model.MusicData;
import com.sj.view.MusicView;

public class MusicController {
	//비즈니스로직 : View(화면), Model(데이터) 빼고 전부 다
	//클라이언트에 직접적으로 요청을 받아서 처리해주는 역할
	
	Scanner sc = new Scanner(System.in);
	ArrayList<MusicData> musicList = new ArrayList<>();
	MusicView view = new MusicView();
	
	public void run() {
		while(true) {
			view.showMenu();
			int op = sc.nextInt();
			if(op==1) {
				addMusic();
			}else if(op==2) {
				deleteMusic();
			}else if(op==3) {
				view.printMessage("프로그램이 종료되었습니다.");
				break;
			}
		}
	}
	
	// 노래 추가
	public void addMusic() {
		view.printMusicList(musicList);
		view.showAddMenu();
		int add = sc.nextInt();
		String song = view.promptTitle(sc);
		String singer = view.promptSinger(sc);
		int playTime = view.promptPlayTime(sc);
		
		MusicData music = new MusicData(song,singer,playTime);
		if(add==1) {
			musicList.add(music);
		}else {
			int location = view.promptIndex(sc,"추가할 위치 번호 입력 : ");
			musicList.add((location-1),music);
		}
		view.printMessage("노래 추가가 완료 되었습니다");
	}
	
	//노래 삭제
	public void deleteMusic() {
		view.printMusicList(musicList);
		view.showDeleteMenu();
		int del = sc.nextInt();
		if(del==1) {
			int delNum = view.promptIndex(sc,"삭제할 노래 번호 입력 : ");
			
			if(delNum<=musicList.size()) {
				musicList.remove(delNum-1);
				view.printMessage("노래 삭제가 완료되었습니다");
				System.out.println("노래가 삭제되었습니다.");
			}else
				view.printMessage("번호를 다시 입력해주세요");
		}else {
			musicList.clear();
			view.printMessage("전체 노래가 삭제되었습니다");
		}
	}
	
}
