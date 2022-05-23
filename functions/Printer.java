package functions;

public class Printer {

	public Printer() {
	}
	
	public void printOf(String...name) {
		for(String str : name) {
			switch(str.toLowerCase()) {
			case "wronginput":
				System.out.println("�߸� �Է��ϼ̽��ϴ�.\n");
				break;
			case "wrongcode" :
				System.out.println("�߸��� �ֽ��ڵ��Դϴ�.");
				break;
			case "cancle" :
				System.out.println("�۾��� ����մϴ�.\n");
				break;
			case "notchange" :
				System.out.println("������� �ʾҽ��ϴ�.\n");
				break;
			case "empty" :
				System.out.println("��� �ֽ� ����� ������ϴ�. �ֽ��� �߰��Ͻʽÿ�.\n");
				break;
			case "exist" :
				System.out.println("�̹� �����ϴ� �ֽ��Դϴ�.");
				break;
			case "askgomain":
				System.out.println("���� �޴��� ���ư��÷��� m�� �Է����ּ���.");
				printOf("Input");
				break;
			case "checked":
				System.out.println("Ȯ�εǾ����ϴ�.\n");
				break;
			case "input" :
				System.out.print("�Է� : ");
				break;
			case "inputcode":
				System.out.println("�ֽ� �ڵ带 �Է��ϼ���.");
				printOf("Input");
				break;
			case "lines" :
				System.out.println("������������������������������������������������������������������������������������������\n");
				break;
			case "menu" :
				System.out.println("���� �޴��� ����մϴ�.\n");
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
				System.out.println("���� �ֽ� ���� ������ �����ϼ���. (�Է� : 1,2,3,4)\n"
						+ "1. �����ֽ� �ϰ� ����"
						+ ", 2. �����ֽ� �ϰ� ����"
						+ ", 3. �� �ϳ��� �ֽĸ� ����"
						+ ", 4. ���"
				);
				break;
			default:
				System.out.println(str);
				break;
			}
		}
	}
}