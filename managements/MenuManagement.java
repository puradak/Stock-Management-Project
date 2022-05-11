package managements;

import java.io.IOException;
import java.util.Scanner;

import exceptions.NotInRangeEcxeption;

public class MenuManagement { 
	public static void main(String[] args) throws IOException {
		Printer printer = new Printer();
		Scanner input = new Scanner(System.in);
		MenuFunction function = MenuFunction.getFunctionObject();
		while(true) {
			printer.printOf("GoMain");
			printer.printMenu();
			System.out.print("�̵��� �޴��� ��ȣ�� �Է��ϼ��� : ");
			String code = "";
			try{
				code = input.nextLine();
				int number = Integer.parseInt(code);
				if(0>number || number>6) throw new NotInRangeEcxeption();
			}
			catch(NotInRangeEcxeption e) {
				System.out.println("0���� 6������ ���ڸ� �Է��ϼ���.");
				System.out.println();
				continue;
			}
			catch(NumberFormatException e) {
				printer.printOf("WrongInput");
				System.out.println();
				continue;
			}
			printer.printOf("Lines");
			if(code.equals("1")) function.show_all(); 
			if(code.equals("2")) function.addStock(); 
			if(code.equals("3")) function.removeStock();
			if(code.equals("4")) function.editStock();
			if(code.equals("5")) function.searchStock();
			if(code.equals("6")) function.statistic(); 
			if(code.equals("0")) { System.out.println("See you again."); break; }
			printer.printOf("Lines");
		}
		input.close();
		return;
	}
}
