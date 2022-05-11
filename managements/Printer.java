package managements;

public class Printer {

	public Printer() {
	}
	
	public void printOf(String...name) {
		for(String str : name) {
			switch(str.toLowerCase()) {
			case "wronginput":
				System.out.println("�߸��� �ֽ��ڵ��Դϴ�.");
				break;
			case "wrongcode" :
				System.out.println("�߸� �Է��ϼ̽��ϴ�.");
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
}