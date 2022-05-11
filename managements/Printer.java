package managements;

public class Printer {

	public Printer() {
	}
	
	public void printOf(String...name) {
		for(String str : name) {
			switch(str.toLowerCase()) {
			case "wronginput":
				System.out.println("�߸� �Է��ϼ̽��ϴ�.");
				break;
			case "wrongcode" :
				System.out.println("�߸��� �ֽ��ڵ��Դϴ�.");
				break;
			case "cancle" :
				System.out.println("�۾��� ����մϴ�.");
				System.out.println();
				break;
			case "notchange" :
				System.out.println("������� �ʾҽ��ϴ�.");
				System.out.println();
				break;
			case "printempty" :
				System.out.println("����� ������ϴ�. �ֽ��� �߰��Ͻʽÿ�.");
				System.out.println();
				break;
			case "askgomain":
				System.out.println("���� �޴��� ���ư��÷��� m�� �Է����ּ���.");
				printOf("Input");
				break;
			case "printgomain" :
				System.out.println("���� �޴��� ���ư��ϴ�.");
				System.out.println();
				break;
			case "checked":
				System.out.println("Ȯ�εǾ����ϴ�.");
				System.out.println();
				break;
			case "input" :
				System.out.print("�Է� : ");
				break;
			case "inputcode":
				System.out.println("�ֽ� �ڵ带 �Է��ϼ���.");
				printOf("Input");
				break;
			case "lines" :
				System.out.println("������������������������������������������������������������������������������������������");
				break;
			default:
				break;
			}
		}
	}
	
	public void printMenu() {
		System.out.println();
		System.out.println("==================MAIN MENU==================");
		System.out.println("            1.Show Stock Table");
		System.out.println("            2.Add Stock");
		System.out.println("            3.Remove Stock");
		System.out.println("            4.Edit Stock");
		System.out.println("            5.Search the Code of Stock");
		System.out.println("            6.Show Statistics");
		System.out.println("            0.Exit the program");
		System.out.println("==================MAIN MENU==================");
		return;
	}
}