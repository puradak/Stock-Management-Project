package managements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import data.LocalStock;
import data.Stock;
import exceptions.NotPositiveNumberExeption;

public class MenuFunction extends Printer{
	// ��ü �ϳ��� ����
	private static MenuFunction function = new MenuFunction();
	private ToolFunction tool = ToolFunction.getToolFunctionObject();
	private MenuFunction() {}
	
	// �ֽ� ���� ����Ʈ
	protected static ArrayList<Stock> localStockList = new ArrayList<Stock>();
	protected static ArrayList<Stock> foreignStockList = new ArrayList<Stock>();
	
	// ��ĳ�� �ν��Ͻ�
	Scanner input = new Scanner(System.in);
	
	// ��ü �Ѱ��ֱ�
	public static MenuFunction getFunctionObject() {
		return function;
	}

	
	// 1�� �޴�
	public void show_all() throws IOException {
		if(tool.isEmpty()) return;
		
		System.out.println("================�����ֽ�================");
		for(Stock stock : localStockList) {
			tool.getFreshedInfo(stock);
		}
		
		if(!foreignStockList.isEmpty()) 
			System.out.println("================�����ֽ�================");
		else {
			while(true) if(tool.isCorrectWith("m", input)) return;
		}
		
		for(Stock stock : foreignStockList) {
			tool.getFreshedInfo(stock);
		}
		
		while(true) if(tool.isCorrectWith("m", input)) return;
	}
	
	// 2�� �޴�
	public void addStock() throws IOException {
		
		printOf("InputCode");
		String code = input.nextLine();
		if(tool.isExistStock(code)) {
			printOf("WrongCode");
			return;
		}
		Stock stock = Stock.createStock(code);

		if(!stock.getExist()) {
			printOf("WrongCode");
			stock = null;
			return;
		}
		if(stock.getType().equals("local")) localStockList.add(stock);
		if(stock.getType().equals("foreign")) foreignStockList.add(stock);
		
		printOf("Checked");
		System.out.println("�ֽ� �� : "+stock.getName());
		System.out.println();
		System.out.println("�������� ������ �Է��ϼ���.");
		printOf("Input");
		
		int asset=0;
		try {
			asset = tool.readInt(input);		
		} 
		catch(InputMismatchException e) {
			printOf("WrongInput","Cancle");
			return;
		}
		catch(NotPositiveNumberExeption e) {
			printOf("WrongInput","Cancle");
			return;
		}
		finally {
			stock.setAsset(asset);				
		}
		
		input.nextLine();
		
		System.out.println("(���û���) �� �ֽĿ� ���� �޸� ����ðڽ��ϱ�? (Y/N)");
		printOf("Input");
		String check = input.nextLine();
		if(check.toLowerCase().equals("y")) {
			printOf("Input");
			stock.setDescription(input.nextLine());
		}
		else if(check.toLowerCase().equals("n")) {
			stock.setDescription("(�������� ����)");
		}
		else {
			stock.setDescription("(�������� ����)");
			printOf("WrongInput","Cancle");
		}
		
		return;
	}
	
	// 3�� �޴�
	public void removeStock() throws IOException {
		if(tool.isEmpty()) return;
				
		printOf("InputCode");
		String code = input.nextLine();
		Stock stock = tool.getElementByCode(code);
		if(tool.isNull(stock)) return;
		
		while(true) {
			System.out.println("���� �� �ֽ��� ��Ͽ��� �����Ͻðڽ��ϱ�? (Y/N)");
			printOf("WrongInput","Cancle");
			String check = input.nextLine();
			if(check.toLowerCase().equals("y")) {
				if(stock.getType().equals("local")) localStockList.remove(stock);
				if(stock.getType().equals("foreign")) foreignStockList.remove(stock);
						
				System.out.println("�ֽ��� ��Ͽ��� �����Ǿ����ϴ�.");
				stock = null;
				break;
			}
			else if(check.toLowerCase().equals("n")){
				printOf("Cancle");
				break;
			}
			else {
				printOf("WrongInput");
			}
		}
		
		
		return;
	}
	
	// 4�� �޴�
	public void editStock() {
		if(tool.isEmpty()) return;
		printOf("InputCode");
		
		Stock stock = tool.getElementByCode(input.nextLine());
		if(tool.isNull(stock)) return;
		
		System.out.println("���� �ֽ��� ���� �����Ͻðڽ��ϱ�? (Y/N)");
		printOf("WrongInput","Cancle");
		
		if(input.nextLine().toLowerCase().equals("y")) {
			System.out.println("����� ���� �ֽ� ���� �Է��ϼ���.");
			printOf("Checked");
			
			int lastAsset = stock.getAsset();
			int currAsset = lastAsset;
			
			try {
				currAsset = tool.readInt(input);
			}
			catch(InputMismatchException e) {
				printOf("NotChanged");
			}
			catch(NotPositiveNumberExeption e) {
				printOf("NotChanged");
			}
			finally {
				stock.setAsset(currAsset);				
			}
			System.out.println(
					"����Ǿ����ϴ�. : ("
					+lastAsset+"�� �� "
					+stock.getAsset
					()+"��)\n"
					);
		}

		input.nextLine();
		
		System.out.println("�ش� �ֽ��� ������ �����Ͻðڽ��ϱ�? (Y/N)");
		printOf("Input");
		
		if(input.nextLine().toLowerCase().equals("y")) {
			System.out.println("�ֽ��� ������ �Է��ϼ���.");
			printOf("Input");
			
			String lastDesc = stock.getDescription();
			stock.setDescription(input.nextLine());
			System.out.println(
					"����Ǿ����ϴ�. "
					+ "("+lastDesc+" �� "
					+stock.getDescription()
					+")");
		}
		else {
			printOf("Cancle");
			return;
		}
		return;
	}
	
	// 5�� �޴�
	public String searchStock() throws IOException {
		System.out.println("�˻��� �ֽ��� �����ֽ��̸� L��, �����ֽ��̸� F�� �Է����ּ���.");
		printOf("Input");
		String type = input.next().toLowerCase();
		if(type.equals("f")) {
			System.out.println("�غ����� ����Դϴ�.");
			return input.nextLine();
		}
		else if (!type.equals("l")) {
			printOf("WrongInput");
			return input.nextLine();
		}
		
		System.out.println("�ڵ带 �˻��� �ֽ��� �̸��� �Է��ϼ���.");
		printOf("Input");
		String name = input.next();
		ArrayList<String> nameList = LocalStock.getListOfCode(name.toLowerCase());
		
		int count = 1;
		for(String str : nameList) {
			System.out.println(count + " : "+str);
			count ++;
		}
		
		int number = 0;
		System.out.println("�ڵ带 �˻��� �ֽĸ��� ������ �Է��ϼ���.");
		printOf("Input");
		
		try {
			number = tool.readInt(input);			
		}
		catch (InputMismatchException e){
			printOf("WrongInput","Cancle");
		}
		catch (NotPositiveNumberExeption e) {
			printOf("WrongInput","Cancle");
		}
		finally {
			if(number > nameList.size()) {
				printOf("WrongInput","Cancle");
				return input.nextLine();
			}
		}
		
		String code = LocalStock.findCodeByName(nameList,number);
		System.out.println(nameList.get(number-1)+"�� �ֽ� �ڵ�� "+code+" �Դϴ�.");
		
		return input.nextLine();
	}
	
	// 6�� �޴�
	public void statistic() throws IOException {
		if(tool.isEmpty()) {
			printOf("Empty");
			return;
		}
		
		System.out.println("---------------<���� ���� �ֽ� ��Ȳ>---------------");
		for(Stock stock : localStockList) {
			tool.getStatisticInfo_each(stock, "local");
		}
		tool.getStatisticInfo_total("local");
		System.out.println();
		
		System.out.println("---------------<���� ���� �ֽ� ��Ȳ>---------------");
		for(Stock stock : foreignStockList) {
			tool.getStatisticInfo_each(stock, "foreign");
		}
		tool.getStatisticInfo_total("foreign");
		//�޷� �ü��� ���� �ֽ� �Ѿ��� ��/�޷� ȯ����� ���ϴ� �ڵ� ����
	}
}
