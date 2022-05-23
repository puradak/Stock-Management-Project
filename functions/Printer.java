package functions;

public class Printer {

	public Printer() {
	}
	
	public void printOf(String...name) {
		for(String str : name) {
			switch(str.toLowerCase()) {
			case "wronginput":
				System.out.println("잘못 입력하셨습니다.\n");
				break;
			case "wrongcode" :
				System.out.println("잘못된 주식코드입니다.");
				break;
			case "cancle" :
				System.out.println("작업을 취소합니다.\n");
				break;
			case "notchange" :
				System.out.println("변경되지 않았습니다.\n");
				break;
			case "empty" :
				System.out.println("모든 주식 목록이 비었습니다. 주식을 추가하십시오.\n");
				break;
			case "exist" :
				System.out.println("이미 존재하는 주식입니다.");
				break;
			case "askgomain":
				System.out.println("메인 메뉴를 돌아가시려면 m을 입력해주세요.");
				printOf("Input");
				break;
			case "checked":
				System.out.println("확인되었습니다.\n");
				break;
			case "input" :
				System.out.print("입력 : ");
				break;
			case "inputcode":
				System.out.println("주식 코드를 입력하세요.");
				printOf("Input");
				break;
			case "lines" :
				System.out.println("─────────────────────────────────────────────\n");
				break;
			case "menu" :
				System.out.println("메인 메뉴를 출력합니다.\n");
				System.out.println("==================MAIN MENU==================");
				System.out.println("            1.Show Stock Table");
				System.out.println("            2.Add Stock");
				System.out.println("            3.Remove Stock");
				System.out.println("            4.Edit Stock");
				System.out.println("            5.Search the Code of Stock");
				System.out.println("            6.Show Statistics");
				System.out.println("            0.Exit the program");
				System.out.println("==================MAIN MENU==================\n");
				break;
			case "delete?" :
				System.out.println("보유 주식 삭제 유형을 선택하세요. (입력 : 1,2,3,4)\n"
						+ "1. 국내주식 일괄 삭제"
						+ ", 2. 국외주식 일괄 삭제"
						+ ", 3. 단 하나의 주식만 삭제"
						+ ", 4. 취소"
				);
				break;
			default:
				System.out.println(str);
				break;
			}
		}
	}
}