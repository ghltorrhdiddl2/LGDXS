import java.util.Scanner;

public class Login {

	public static void main(String[] args) {
		boolean run = true;
		String[] idArray = new String[3];
		String[] pwArray = new String[3];
		Scanner sc = new Scanner(System.in);
		int cnt = 0;
		while(run) {
			System.out.println("1.회원가입 2.로그인 3.종료");
			int op = sc.nextInt();
			switch(op) {
			case 1:
				System.out.println("===회원가입===");
				
				if(cnt<3) {
					System.out.print("id 입력 : ");
					idArray[cnt]=sc.next();
					System.out.print("pw 입력 : ");
					pwArray[cnt]=sc.next();
					cnt++;
				}else {
					System.out.println("3명 초과");
					break;
				}
				break;
			case 2:
				System.out.println("===로그인===");
				System.out.print("id 입력 : ");
				String idd = sc.next();
				System.out.print("pw 입력 : ");
				String pww = sc.next();
				
				boolean succ = false;
				for(int i=0;i<idArray.length;i++) {
					if(idArray[i]!=null && idArray[i].equals(idd) && pwArray[i].equals(pww)) {
						succ=true;
						break;
					}
				}
				if(succ)
					System.out.println("로그인 성공!");
				else
					System.out.println("로그인 실패..");
				break;
			case 3:
				run=false; 
				System.out.println("프로그램 종료");
				break;
			}
		}
		sc.close();
	}
}
