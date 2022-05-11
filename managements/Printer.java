package managements;

public class Printer {

	public Printer() {
	}
	
	public void printWrongInput() {
		System.out.println("잘못 입력하셨습니다.");
		return;
	}
	
	public void printNotChanged() {
		System.out.println("변경되지 않았습니다.");
		System.out.println();
		return;
	}
	
	public void printWrongCode() {
		System.out.println("잘못된 주식코드입니다.");
		return;
	}
	
	public void printEmpty() {
		System.out.println("목록이 비었습니다. 주식을 추가하십시오.");
		System.out.println();
		return;
	}
	
	public void printAskGoMain() {
		System.out.println("메인 메뉴를 돌아가시려면 m을 입력해주세요.");
		printInput();
	}
	
	public void printGoMain() {
		System.out.println("메인 메뉴로 돌아갑니다.");
		System.out.println();
		return;
	}
	
	public void printChecked() {
		System.out.println("확인되었습니다.");
		System.out.println();
		return;
	}
	
	public void printInput() {
		System.out.print("입력 : ");
		return;
	}
	
	public void printInputCode() {
		System.out.println("주식 코드를 입력하세요.");
		printInput();
		return;
	}
	
	public void printLines() {
		System.out.println("─────────────────────────────────────────────");
		return;
	}
	
	public void printCancle() {
		System.out.println("작업을 취소합니다.");
		System.out.println();
		return;
	}
}
