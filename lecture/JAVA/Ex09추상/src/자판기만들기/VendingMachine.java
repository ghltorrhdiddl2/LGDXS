package 자판기만들기;

public interface VendingMachine {

	// 0. 언어 선택
	public void selectedLanguage();
	// 1. 메뉴 보여주기 기능
	public void showMenu();
	// 2. 항목 선택하기 기능
	public void selectItem();
	// 3. 결제하기 기능
	public void pay();
	
}
