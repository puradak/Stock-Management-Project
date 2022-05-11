package managements;

import java.io.IOException;
import java.util.Scanner;

public class MenuManagement { 
	public static void main(String[] args) throws IOException {
		Printer printer = new Printer();
		Scanner input = new Scanner(System.in);
		MenuFunction function = MenuFunction.getFunctionObject();
		while(true) {
			printer.printGoMain();
			displayMenu();
			System.out.print("이동할 메뉴의 번호를 입력하세요 : ");
			String code = input.nextLine();
			printer.printLines();
			if(code.equals("1")) function.show_all(); 
			if(code.equals("2")) function.addStock(); 
			if(code.equals("3")) function.removeStock();
			if(code.equals("4")) function.editStock();
			if(code.equals("5")) function.searchStock();
			if(code.equals("6")) function.statistic(); 
			if(code.equals("0")) { System.out.println("See you again."); break; }
			printer.printLines();
		}
		input.close();
		return;
	}
	
	static void displayMenu() {
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
	}
}
