package managements;

public class Printer {
	
	public Printer() {
	}
	
	public void printWrongInput() {
		System.out.println("�߸� �Է��ϼ̽��ϴ�.");
		printGoMain();
		return;
	}
	
	public void printEmpty() {
		System.out.println("����� ������ϴ�. �ֽ��� �߰��Ͻʽÿ�.");
		return;
	}
	
	public void printGoMain() {
		System.out.println("���� �޴��� ���ư��ϴ�.");
		return;
	}
	
	public void printWrongAsset() {
		System.out.println("�߸� �Է��ϼ̽��ϴ�. 1�ַ� �����Ǿ����ϴ�.");
		System.out.println("Edit Stock �޴����� ���� �����մϴ�.");
		return;
	}
	
	public void printInput() {
		System.out.print("�Է� : ");
		return;
	}
}
