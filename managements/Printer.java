package managements;

public class Printer {

	public Printer() {
	}
	
	public void printWrongInput() {
		System.out.println("�߸� �Է��ϼ̽��ϴ�.");
		return;
	}
	
	public void printNotChanged() {
		System.out.println("������� �ʾҽ��ϴ�.");
		System.out.println();
		return;
	}
	
	public void printWrongCode() {
		System.out.println("�߸��� �ֽ��ڵ��Դϴ�.");
		return;
	}
	
	public void printEmpty() {
		System.out.println("����� ������ϴ�. �ֽ��� �߰��Ͻʽÿ�.");
		System.out.println();
		return;
	}
	
	public void printAskGoMain() {
		System.out.println("���� �޴��� ���ư��÷��� m�� �Է����ּ���.");
		printInput();
	}
	
	public void printGoMain() {
		System.out.println("���� �޴��� ���ư��ϴ�.");
		System.out.println();
		return;
	}
	
	public void printChecked() {
		System.out.println("Ȯ�εǾ����ϴ�.");
		System.out.println();
		return;
	}
	
	public void printInput() {
		System.out.print("�Է� : ");
		return;
	}
	
	public void printInputCode() {
		System.out.println("�ֽ� �ڵ带 �Է��ϼ���.");
		printInput();
		return;
	}
	
	public void printLines() {
		System.out.println("������������������������������������������������������������������������������������������");
		return;
	}
	
	public void printCancle() {
		System.out.println("�۾��� ����մϴ�.");
		System.out.println();
		return;
	}
}
