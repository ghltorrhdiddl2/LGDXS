package 포켓몬게임;

import java.util.Scanner;

public class PokemonMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Pokemon pika = new Pokemon("피카츄","전기","백만볼트",100,10);
		Pokemon lizamon = new Pokemon("리자몽","불","화염방사",150,15);
		
		while(true) {
			System.out.println("===== 포켓몬을 선택하세요 ======");
			System.out.print("[1]피카츄 [2]리자몽 >> ");
			int choice = sc.nextInt();
			if(choice==1) {
				// 피카츄가 리자몽을 공격
				System.out.println("===== 공격을 선택하세요 =====");
				System.out.print("[1]일반공격 [2]스킬공격 >> ");
				int choiceAtk = sc.nextInt();
				if(choiceAtk==1) {
					// 일반공격
					// 리자몽의 hp를 피카츄의 공격력만큼 감소
					lizamon.setHp(lizamon.getHp()-pika.getAtk());
				}else {
					// 스킬공격
					// 리자몽의 hp를 피카츄의 공격력*1.5 만큼 감소
					lizamon.setHp(lizamon.getHp()-(int)(pika.getAtk()*1.5));
				}
				// 두 마리 포켓몬의 남은 hp 출력
				System.out.println("===== 남은 hp =====");
				System.out.println("피카츄 hp:"+pika.getHp());
				System.out.println("리자몽 hp:"+lizamon.getHp());
			}else if(choice==2){
				// 리자몽이 피카츄를 공격
				System.out.println("===== 공격을 선택하세요 =====");
				System.out.print("[1]일반공격 [2]스킬공격 >> ");
				int choiceAtk = sc.nextInt();
				if(choiceAtk==1) {
					// 일반공격
					// 피카츄의 hp를 리자몽의 공격력만큼 감소
					pika.setHp(pika.getHp()-lizamon.getAtk());
				}else {
					// 스킬공격
					// 피카츄의 hp를 리자몽의 공격력*1.5 만큼 감소
					pika.setHp(pika.getHp()-(int)(lizamon.getAtk()*1.5));
				}
				// 두 마리 포켓몬의 남은 hp 출력
				System.out.println("===== 남은 hp =====");
				System.out.println("피카츄 hp:"+pika.getHp());
				System.out.println("리자몽 hp:"+lizamon.getHp());
			}else
				System.out.println("다시 포켓몬을 선택해주세요!");
			// 두 마리의 포켓몬 중 한마리라도 hp가 0보다 작거나 같았을 때 프로그램 종료
			// 승자가 누구인치 출력
			if(pika.getHp()<=0 || lizamon.getHp()<=0) {
				if(pika.getHp()<=0) {
					System.out.println("리자몽의 승리!");
					System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
							+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⠉⢉⡁⡈⠑⠶⣶⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
							+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⡀⡠⢊⠜⠀⠀⢏⠅⠈⠈⠁⠒⠢⡍⠈⠒⢄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠠⢄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
							+ "⠀⠀⠀⠀⠀⠀⠀⡠⣊⡀⠤⠄⡦⡀⠇⠀⠀⠀⠈⠪⢔⡀⠀⠀⠀⠈⠀⣍⠐⠤⠇⠀⠀⠀⠀⠀⠀⠀⠈⡌⢦⡀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
							+ "⠀⠀⠀⢀⡠⣴⠟⠊⠁⠀⠀⠀⠈⠸⡰⡀⠀⠀⠀⠀⠀⠀⠈⢳⡄⠀⠀⢋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣫⡖⠪⣔⠤⡀⠀⠀⠀⠀⠀\r\n"
							+ "⠀⠀⢠⢣⠊⠀⠀⠀⠀⠀⠀⠀⣸⡀⢣⢣⠀⠀⠀⠀⠀⠀⠀⠘⠘⠀⠀⠘⡀⠀⠀⠀⠀⠀⠀⠀⠀⣠⡗⠁⡆⠀⠀⠑⢕⢄⠀⠀⠀⠀\r\n"
							+ "⠀⢠⡣⠁⠀⠀⠀⠀⠀⠀⠀⢀⢿⠁⠈⣌⠢⢀⠀⠀⠀⠀⠀⠀⡇⠀⠀⠀⢃⠀⠀⠀⠀⠀⠀⠀⡰⡝⢠⠀⠃⠀⠀⠀⠀⠑⢕⠤⡀⠀\r\n"
							+ "⡠⡱⠁⠀⢀⠠⠤⠤⢄⡀⠀⠈⠇⠀⠀⠀⠏⠑⠪⢖⠤⡀⠀⠀⡇⠀⠀⠀⠘⠀⠀⠀⠀⣀⠤⣒⡝⠀⠃⠰⡄⠀⠀⠀⠀⠀⠀⠙⣵⠀\r\n"
							+ "⣧⠁⢠⠖⠁⠀⠀⠀⠀⠑⢄⢸⠀⠀⠀⠀⠀⠀⠀⠀⠉⢪⣉⣣⠁⠀⠀⠀⠀⢷⠈⢩⠕⠂⠉⠀⠀⠀⠀⢀⠀⠀⢀⡀⠀⠀⠀⠀⢸⡆\r\n"
							+ "⣿⠰⠁⠀⠀⠀⠀⠀⠀⠀⠈⢞⢄⠔⠉⠁⡨⠓⠒⠒⠀⠁⠀⠘⠆⠀⠀⠀⠀⠘⠉⠑⠒⠒⠒⢄⠠⢄⠀⢸⠀⡰⠁⢈⡶⣄⡀⠠⠀⣧\r\n"
							+ "⢫⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⠃⣀⠠⠎⢀⠞⠁⠈⣟⠧⡠⠃⡀⠄⠒⠒⠢⢄⠰⣖⠊⠉⠑⠄⠑⢄⣱⡸⠜⠀⠀⢸⢀⡟⡎⢢⠀⣿\r\n"
							+ "⠈⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢮⣽⠄⣀⡈⡆⠀⡠⠃⠀⡰⠊⠀⠀⠀⠀⠀⠀⠑⣽⡀⠀⠀⠜⠀⠀⣄⡛⡄⠀⠀⣸⢸⣥⢳⠀⡆⢿\r\n"
							+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⠺⠀⠷⠃⡰⠁⠀⡜⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣇⠀⠈⠳⠉⣦⠕⠉⠁⠀⠸⣵⠸⢹⢯⡀⢸⡜\r\n"
							+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⠀⠀⠀⡔⠊⠀⠀⢰⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡄⠀⠀⠀⠀⠀⠀⠀⠀⢹⠙⢇⠀⠇⣽⠈⠀\r\n"
							+ "⠀⠀⠀⠀⠀⠀⠀⢀⠤⢊⣉⣀⠀⣀⣩⢋⠀⠀⠀⠀⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠘⠲⡄⠀⠀⠀⠀⠀⠀⠸⣀⣠⣦⡞⠂⠀⠀\r\n"
							+ "⠀⠀⠀⠀⠀⠀⡠⠃⠀⠀⠀⠀⠀⢠⠃⠃⠀⠀⠀⠀⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠆⠀⠈⠊⠢⡀⠀⠀⠀⠀⡠⣾⠏⠀⠀⠀⠀⠀\r\n"
							+ "⠀⠀⠀⠀⠀⢰⠁⠀⠀⠀⠀⠀⠀⡌⢰⠀⠀⡀⢀⡠⠃⠀⠀⠀⠀⠀⠀⠀⠀⢀⠔⠁⠀⠀⠀⠀⠀⣷⠒⢒⣨⢕⡺⠁⠀⠀⠀⠀⠀⠀\r\n"
							+ "⠀⠀⠀⠀⠀⠈⠳⡀⠀⠀⠐⠒⢈⡃⠀⠀⠈⣰⠉⠒⠀⠠⢤⣄⡤⠤⠤⣒⡊⠙⠛⠒⢄⠀⠀⠈⠀⣏⠩⠅⠂⠁⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
							+ "⠀⠀⠀⠀⠀⠀⠀⠈⠒⠀⠀⠀⣞⡱⢄⠀⠔⠈⠈⠉⠉⠁⠀⠀⠀⠀⠀⠀⠀⠈⠉⠁⠸⣀⢉⠶⣪⢝⢄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
							+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣣⢵⣩⠾⣺⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠑⠋⠐⠯⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
							+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠁⠀⠈⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
				}else {
					System.out.println("피카츄의 승리!");
					System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
							+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⣿⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
							+ "⢠⣤⣤⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡘⠙⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
							+ "⠀⠙⢿⣿⣿⠉⠐⠢⠄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡀⠇⠀⠈⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
							+ "⠀⠀⠀⠉⠻⣆⠄⡀⠀⠀⠑⠢⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⠀⢀⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
							+ "⠀⠀⠀⠀⠀⠈⠑⠠⢁⡢⢄⡀⠈⠒⠒⠀⠀⠀⠒⠠⠄⡀⢸⠀⠀⣼⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
							+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢉⠊⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⠄⡰⡌⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
							+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠆⢠⡤⢤⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
							+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⡸⡀⠘⠿⠟⠀⠀⢀⠀⠀⠀⣠⢲⡄⠀⠐⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
							+ "⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⢰⠀⠀⠐⣦⣶⣶⣤⣀⡀⠙⠿⠃⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
							+ "⠀⠀⠀⠀⠀⠀⠀⠀⠘⡠⠃⠀⠀⠀⡏⠉⠉⠪⡟⠀⠀⠀⡠⠄⡡⠄⠒⠂⢸⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
							+ "⠀⠀⠀⠀⠀⠀⠀⠀⢀⣱⣀⡀⠀⠀⢣⠀⢀⠜⠀⠀⠀⡼⠀⡰⠀⠀⠀⠀⣠⣏⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
							+ "⠀⠀⠀⠀⠀⢀⠔⠉⠀⠀⠀⠀⠉⠒⢄⠉⠁⠀⠀⠀⢀⡸⠚⠀⠀⠀⣠⠜⠀⠀⠀⠀⠀⠀⠉⠉⠐⠒⠠⠤⣀⡀\r\n"
							+ "⠀⠀⠀⠀⠀⢸⠀⠀⠀⠀⠀⠀⠀⠀⢰⠯⠄⠀⠀⠈⠁⠀⠀⣀⠔⡪⢻⠀⡆⠄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡠⠊\r\n"
							+ "⠀⠀⠀⠀⠀⠀⠙⢅⣀⠀⣀⡠⠔⠊⠁⠀⠀⠀⠀⠀⠀⠀⢌⡠⠊⡀⡈⢰⠀⠀⠀⠁⠂⢄⡀⠀⠀⠀⢀⠜⠁⠀\r\n"
							+ "⠀⠀⠀⠀⠀⠀⠀⠀⠎⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⡿⢐⠀⣁⡇⡈⢀⠎⡁⠐⠒⠠⠬⠖⣄⡔⡁⠀⠀⠀\r\n"
							+ "⠀⠀⠀⠀⠀⠀⠀⠰⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢙⠘⡈⠒⡄⢇⠎⠀⠀⠀⠀⠀⠀⠂⠰⠀⠀⠀⠀⠀\r\n"
							+ "⠀⠀⠀⠀⠀⠀⠀⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⡤⣿⣿⠊⠉⠘⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
							+ "⠀⠀⠀⠀⠀⢀⡘⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠱⠚⠛⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
							+ "⠀⠀⠀⠀⠀⢸⠇⣀⣀⠀⠀⠀⠀⠀⢀⡀⠀⠀⠀⠀⠀⢀⠀⠀⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
							+ "⠀⠀⠀⠀⠀⠈⣈⠣⢄⠀⣀⡀⠠⠤⠤⠤⠤⠀⣀⣀⣀⣀⡨⠾⢄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
							+ "⠀⠀⠀⠀⠀⡾⠿⠃⠓⠒⠈⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠀⠒⠒⠊⢽⠷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
				}	
				break;
			}
		}
	}

}
