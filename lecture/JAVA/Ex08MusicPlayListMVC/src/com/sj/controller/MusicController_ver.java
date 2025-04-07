package com.smhrd.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.smhrd.model.Music;
import com.smhrd.view.MusicView;

public class MusicController {
	//비즈니스로직 : View(화면),Model(데이터) 빼고 전부다
	//클라이어트에 직접적으로 요청을 받아서 처리해주는 역할
	
	Scanner sc = new Scanner(System.in);
	ArrayList<Music> musicList = new ArrayList<>();
	MusicView view = new MusicView();
	
	//플레이리스트의 전원(전체 구조)
	public void run() {
		while(true) {
			view.showMenu(); //전체 메뉴 출력
			int choice = sc.nextInt();
			
			if(choice == 1) {
				
				addMusic();
				
			}else if(choice == 2) {
				
				deleteMusic();
				
			}else if(choice == 3) {
				break;
			}
		}
	}
	
	//노래 추가
	public void addMusic() {
		view.printMusicList(musicList);

		view.showAddMenu();
		int choice2 = sc.nextInt();
		if(choice2 == 1) {
			String inputTitle = view.promptTitle(sc);
			String inputSinger = view.promptSinger(sc);
			int inputPlayTime = view.promptPlayTime(sc);
			
			musicList.add(new Music(inputTitle, inputSinger, inputPlayTime));
			view.printMessage("노래 추가가 완료되었습니다");
		}else if(choice2 == 2) {
			String inputTitle = view.promptTitle(sc);
			String inputSinger = view.promptSinger(sc);
			int inputPlayTime = view.promptPlayTime(sc);
			int inputIndex = view.promptIndex(sc, "추가할 위치 번호 입력 : ");
			
			if(inputIndex < musicList.size()+2) {
				musicList.add(inputIndex-1, new Music(inputTitle, inputSinger, inputPlayTime));
				view.printMessage("노래 추가가 완료 되었습니다");
			}else {
				view.printMessage("위치를 다시 입력하세요!");
			}
		}
	}
	
	//노래 삭제
	public void deleteMusic() {
		view.printMusicList(musicList);
		
		if(!musicList.isEmpty()) { //1곡 이상이 저장되어 있을 때
			view.showDeleteMenu();
			int choice3 = sc.nextInt();
			
			if(choice3 == 1) {
				int inputIndex = view.promptIndex(sc, "삭제할 노래 번호 입력 : ");
				
				if(inputIndex<=musicList.size()) {
					musicList.remove(inputIndex-1);
					view.printMessage("노래 삭제가 완료되었습니다");
				}else {
					view.printMessage("번호를 다시 입력해주세요");
				}
			}else if(choice3 == 2) {
				musicList.clear();
				view.printMessage("전체 노래가 삭제되었습니다");
			}
		}
	}
}


















