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
	private static LoadManager loader = LoadManager.getLoadManagerObject(); 
	private SaveManager saver = new SaveManager();
	private ToolFunction tool = ToolFunction.getToolFunctionObject();
	private MenuFunction() {}
	// �ֽ� ���� ����Ʈ
	protected static ArrayList<Stock> localStockList = loader.LoadList("local");
	protected static ArrayList<Stock> foreignStockList = loader.LoadList("foreign");	
	
	// ��ĳ�� �ν��Ͻ�
	Scanner input = new Scanner(System.in);
	
	// ��ü �Ѱ��ֱ�
	public static MenuFunction getFunctionObject() {
		return function;
	}
	
	public void cleanMenuLog() {
		loader.cleanMenuLog();
		return;
	}
	
	// 1�� �޴�
	public void show_all() throws IOException {
		if(tool.isEmpty()) return;
		ArrayList<Stock> stockList;
		for(int i=0; i<2; i++) {
			System.out.println("================"+tool.kind[i]+"�ֽ�================");
			stockList = tool.getList(i);
			for(Stock stock : stockList) {
				tool.getFreshedInfo(stock);
			}
		}
		tool.pause(input);
	}
	
	// 2�� �޴�
	public void addStock() throws IOException {
		printOf("InputCode");
		String code = input.nextLine();
		if(tool.isExistStock(code))	return;
		
		Stock stock = Stock.createStock(code);

		if(!stock.getExist()) {
			printOf("WrongCode");
			stock = null;
			return;
		}

		printOf("�ֽ� �� : "+stock.getName()
		,"Checked" ,"�������� ������ �Է��ϼ���.", "Input");
		
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
		
		if(stock.getType().equals("local")) localStockList.add(stock);
		if(stock.getType().equals("foreign")) foreignStockList.add(stock);
		stock.setAsset(asset);				
		
		input.nextLine();
		
		printOf("(���û���) �� �ֽĿ� ���� �޸� ����ðڽ��ϱ�? (Y/N)", "Input");
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
		
		printOf("delete?","input");
		int answer = 0;
		while(answer<1) {
			answer = tool.readInt(input,1,4);
			if(answer == -1) { 
				printOf("WrongInput","delete?","input");
				continue;
			}
			else if (answer == 3) break;
			else if(answer == 4) {
				printOf("Cancle");
				return;
			}
			else {
				input.nextLine();
				printOf("���� �����Ͻðڽ��ϱ�? (Y/N)","input");
				if(input.nextLine().toLowerCase().equals("y")) {
					if(answer == 1) localStockList = new ArrayList<Stock>();
					else foreignStockList = new ArrayList<Stock>();
					System.out.println("�����Ǿ����ϴ�.");
					return;
				}
				else {
					printOf("Cancle");
					return;
				}
			}
		}
		
		printOf("InputCode");
		String code = input.nextLine();
		Stock stock = tool.getElementByCode(code);
		if(tool.isNull(stock)) return;
		
		while(true) {
			printOf("���� �� �ֽ��� ��Ͽ��� �����Ͻðڽ��ϱ�? (Y/N)", "Input");
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
		
		if(input.nextLine().toLowerCase().equals("y")) {
			System.out.println();
			printOf("����� ���� �ֽ� ���� �Է��ϼ���.", "Input");
			
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
		else System.out.println("�������� �ʽ��ϴ�.");

		input.nextLine();
		
		printOf("�ش� �ֽ��� ������ �����Ͻðڽ��ϱ�? (Y/N)", "Input");
		
		if(input.nextLine().toLowerCase().equals("y")) {
			System.out.println();
			printOf("�ֽ��� ������ �Է��ϼ���.", "Input");
			
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
	public void searchStock() throws IOException, NotPositiveNumberExeption {
		printOf("�ڵ带 �˻��� �ֽ��� �̸��� �Է��ϼ���.", "Input");
		String name = tool.readKorean(input);
		if(name.isEmpty()) return;
		
		ArrayList<String> nameList = LocalStock.getListOfCode(name.toLowerCase());
		
		int count = 1;
		try {
			for(String str : nameList) {
				System.out.println(count + " : "+str);
				count ++;
			}
		}
		catch (NullPointerException e) {
			printOf("�ش� �ܾ �����ϴ� �ֽ��� �������� �ʽ��ϴ�.","Cancle");
			tool.pause(input);
			return;
		}
		int number = 0;
		printOf("�ڵ带 �˻��� �ֽĸ��� ������ �Է��ϼ���.", "Input");
		
		try {
			number = tool.readInt(input);			
		}
		catch (InputMismatchException e){
			printOf("WrongInput","Cancle");
		}
		finally {
			if(number > nameList.size()) {
				printOf("WrongInput","Cancle");
				return;
			}
		}
		
		String code = LocalStock.findCodeByName(nameList,number);
		System.out.println(nameList.get(number-1)+"�� �ֽ� �ڵ�� "+code+" �Դϴ�.");
		tool.pause(input);
		return;
	}
	
	// 6�� �޴�
	public void statistic() throws IOException {
		if(tool.isEmpty()) return;
		for(int i=0; i<2; i++) {
			System.out.println("---------------<���� "+tool.kind[i]+" �ֽ� ��Ȳ>---------------");
			for(Stock stock : tool.getList(i)) {
				tool.getStatisticInfo_each(stock, tool.kind[i]);
			}
			tool.getStatisticInfo_total(i);
			System.out.println();
		}
		tool.pause(input);
	}

	
	// 0�� �޴�
	public void saveStocks() {
		System.out.println("�ֽ� ������ �����մϴ�.");
		if(!saver.saveObject()) {
			printOf("���忡 �����Ͽ����ϴ�.","���α׷��� �����մϴ�.","Lines");
			System.exit(-1);
		}
		else printOf("���忡 �����Ͽ����ϴ�.", "���α׷��� �����մϴ�.");
		return;
	}
}