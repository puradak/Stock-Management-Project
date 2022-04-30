package managements;

import java.io.IOException;
import java.util.Scanner;

public class MenuManagement { 
	public static void main(String[] args) throws IOException {
		
		Scanner input = new Scanner(System.in);
		Function function = new Function();
		
		while(true) {
			displayMenu();
			System.out.print("ют╥б :");
			String code = input.nextLine();
			if(code.equals("1")) { function.show_all(); continue; }
			if(code.equals("2")) { function.addStock(); continue; }
			if(code.equals("3")) { function.removeStock(); continue; }
			if(code.equals("4")) { function.editStock(); continue; }
			if(code.equals("5")) { function.searchStock(); continue; }
			if(code.equals("6")) { function.statistic(); continue; }
			if(code.equals("0")) { System.out.println("See you again."); break; }
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
		System.out.println("            5.Search Stock");
		System.out.println("            6.Show Statistics");
		System.out.println("            0.Exit the program");
		System.out.println("==================MAIN MENU==================");
	}
}
