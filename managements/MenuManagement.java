package managements;

import java.util.Scanner;

public class MenuManagement {

	public static void main(String[] args) {
	
		Scanner input = new Scanner(System.in);
		
		String ticker = "005930";
		String name = "삼성전자";
		String price = "100000";
		String sign = "상승";
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
				System.out.println("가격 : "+ price);
				System.out.println("전일대비"+ sign +" : "+value+"%");
				System.out.println("보유 : "+trade+"주");
				System.out.println("자산 : "+asset+"원");
				System.out.println("=============================");
				break;
			case 2:
			case 3:
			case 4:
			case 5:
				System.out.println(num+"번 메뉴는 차후 구현 예정 기능입니다.");
				break;
			default:
				System.out.println("메뉴에 없는 번호입니다.");
				break;
			}
			System.out.println();
		}
		System.out.println("프로그램을 종료합니다.");
	}
}
