package managements;

import java.io.IOException;
import java.util.Scanner;

import exceptions.NotInRangeException;
import exceptions.NotPositiveNumberExeption;

public class MenuManagement { 	
	public static void main(String[] args) throws IOException, NotInRangeException, NotPositiveNumberExeption {
		Printer printer = new Printer();
		Scanner input = new Scanner(System.in);
		ToolFunction tool = ToolFunction.getToolFunctionObject();
		MenuFunction function = MenuFunction.getFunctionObject();
		SaveManager saver = new SaveManager();
		
		function.cleanMenuLog();
		
		while(true) {
			printer.printOf("Menu","이동할 메뉴의 번호를 입력하세요","input");
			String code = String.valueOf(tool.readInt(input,0,6));
			
			if(!tool.isValidMenu(input, code)) {
				printer.printOf("WrongInput","0부터 6까지의 정수 하나를 입력하세요");
				saver.saveLog(code, false);
				continue;
			}
			
			saver.saveLog(code, true);
			printer.printOf("Lines");
			if(code.equals("1")) function.show_all(); 
			if(code.equals("2")) function.addStock(); 
			if(code.equals("3")) function.removeStock();
			if(code.equals("4")) function.editStock();
			if(code.equals("5")) function.searchStock();
			if(code.equals("6")) function.statistic(); 
			if(code.equals("0")) { input.close(); function.saveStocks(); break; }
			printer.printOf("Lines");
		}
		return;
	}
}
