package managements;

import java.util.Scanner;

public class MenuManagement {

	public static void main(String[] args) {
	
		Scanner input = new Scanner(System.in);
		
		String ticker = "005930";
		String name = "�Ｚ����";
		String price = "100000";
		String sign = "���";
		int trade = 100;
		int asset = trade * Integer.parseInt(price);
		double value = 10;
		
		int num = 6;
		while(num != 0) {
			System.out.println("1.Show Stock Table");
			System.out.println("2.Add Stock");
			System.out.println("3.Remove Stock");
			System.out.println("4.Edit Stock");
			System.out.println("5.Statistics");
			System.out.println("0.Exit the program");
			System.out.print("Select : ");
			num = input.nextInt();
			System.out.println();
			switch(num) {
			case 0:
				break;
			case 1:
				System.out.println("=============================");
				System.out.println(name+" ("+ticker+")");
				System.out.println("���� : "+ price);
				System.out.println("���ϴ��"+ sign +" : "+value+"%");
				System.out.println("���� : "+trade+"��");
				System.out.println("�ڻ� : "+asset+"��");
				System.out.println("=============================");
				break;
			case 2:
			case 3:
			case 4:
			case 5:
				System.out.println(num+"�� �޴��� ���� ���� ���� ����Դϴ�.");
				break;
			default:
				System.out.println("�޴��� ���� ��ȣ�Դϴ�.");
				break;
			}
			System.out.println();
		}
		System.out.println("���α׷��� �����մϴ�.");
	}
}
