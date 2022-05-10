package managements;

public class Printer {
	
	public Printer() {
	}
	
	public void printWrongInput() {
		System.out.println("잘못 입력하셨습니다.");
		printGoMain();
		return;
	}
	
	public void printEmpty() {
		System.out.println("목록이 비었습니다. 주식을 추가하십시오.");
		return;
	}
	
	public void printGoMain() {
		System.out.println("메인 메뉴로 돌아갑니다.");
		return;
	}
	
	public void printWrongAsset() {
		System.out.println("잘못 입력하셨습니다. 1주로 설정되었습니다.");
		System.out.println("Edit Stock 메뉴에서 수정 가능합니다.");
		return;
	}
	
	public void printInput() {
		System.out.print("입력 : ");
		return;
	}
}
