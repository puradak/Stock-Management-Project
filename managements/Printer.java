package managements;

public class Printer {

	public Printer() {
	}
	
	public void printOf(String...name) {
		for(String str : name) {
			switch(str.toLowerCase()) {
			case "wronginput":
				System.out.println("잘못된 주식코드입니다.");
				break;
			case "wrongcode" :
				System.out.println("잘못 입력하셨습니다.");
				break;
			case "cancle" :
				System.out.println("작업을 취소합니다.");
				System.out.println();
				break;
			case "notchange" :
				System.out.println("변경되지 않았습니다.");
				System.out.println();
				break;
			case "printempty" :
				System.out.println("목록이 비었습니다. 주식을 추가하십시오.");
				System.out.println();
				break;
			case "askgomain":
				System.out.println("메인 메뉴를 돌아가시려면 m을 입력해주세요.");
				printOf("Input");
				break;
			case "printgomain" :
				System.out.println("메인 메뉴로 돌아갑니다.");
				System.out.println();
				break;
			case "checked":
				System.out.println("확인되었습니다.");
				System.out.println();
				break;
			case "input" :
				System.out.print("입력 : ");
				break;
			case "inputcode":
				System.out.println("주식 코드를 입력하세요.");
				printOf("Input");
				break;
			case "lines" :
				System.out.println("─────────────────────────────────────────────");
				break;
			default:
				break;
			}
		}
	}
}