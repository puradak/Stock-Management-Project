package managements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import Data.LocalStock;
import Data.Stock;
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
			stock.Fresh();
			System.out.println("�ֽĸ���: "+stock.getName()+"("+stock.getCode()+")");
			System.out.println("���簡����: "+stock.getPrice_t()+"��");
			System.out.println("�����ڻꡡ��: "+tool.getTotalAsset(stock,"dot","today")+"�� ("+stock.getAsset()+"��)");
			System.out.println("���ϴ�� : "+stock.getNetChange()+"%");
			System.out.println("��������: "+stock.getDescription());
			System.out.println("======================================");
		}
		
		if(!foreignStockList.isEmpty()) 
			System.out.println("================�����ֽ�================");
		else {
			while(tool.isCorrectWith("m",input)) return;
		}
		
		for(Stock stock : foreignStockList) {
			stock.Fresh();
			System.out.println("�ֽĸ���: "+stock.getName());
			System.out.println("���簡����: "+stock.getPrice_t()+" USD");
			System.out.println("�����ڻꡡ��: "+tool.getTotalAsset(stock,"dot","today")+" USD ("+stock.getAsset()+"��)");
			System.out.println("���ϴ�� : "+stock.getNetChange()+"%");
			System.out.println("��������: "+stock.getDescription());
			System.out.println("======================================");
		}
		printAskGoMain();
		while(input.nextLine().toLowerCase().equals("m")) return;
	}
	
	// 2�� �޴�
	public void addStock() throws IOException {
		
		printInputCode();
		String code = input.nextLine();
		if(tool.isExistStock(code)) {
			printWrongCode();
			
			
			return;
		}
		Stock stock = Stock.createStock(code);

		if(!stock.getExist()) {
			printWrongCode();
			
			
			stock = null;
			return;
		}
		if(stock.getType().equals("local")) localStockList.add(stock);
		if(stock.getType().equals("foreign")) foreignStockList.add(stock);
		
		printChecked();
		System.out.println("�ֽ� �� : "+stock.getName());
		System.out.println();
		System.out.println("�������� ������ �Է��ϼ���.");
		printInput();
		
		int asset=0;
		try {
			asset = tool.readInt(input);		
		} 
		catch(InputMismatchException e) {
			printWrongInput();
			printCancle();
			
			
			return;
		}
		catch(NotPositiveNumberExeption e) {
			printWrongInput();
			printCancle();
			
			
			return;
		}
		finally {
			stock.setAsset(asset);				
		}
		
		input.nextLine();
		
		System.out.println("(���û���) �� �ֽĿ� ���� �޸� ����ðڽ��ϱ�? (Y/N)");
		printInput();
		String check = input.nextLine();
		if(check.toLowerCase().equals("y")) {
			printInput();
			stock.setDescription(input.nextLine());
		}
		else if(check.toLowerCase().equals("n")) {
			stock.setDescription("(�������� ����)");
		}
		else {
			stock.setDescription("(�������� ����)");
			printWrongInput();
			printCancle();
		}
		
		
		
		return;
	}
	
	// 3�� �޴�
	public void removeStock() throws IOException {
		if(tool.isEmpty()) return;
				
		printInputCode();
		String code = input.nextLine();
		Stock stock = tool.getElementByCode(code);
		if(tool.isNull(stock)) return;
		
		while(true) {
			System.out.print("���� �� �ֽ��� ��Ͽ��� �����Ͻðڽ��ϱ�? (Y/N)");
			printInput();
			String check = input.nextLine();
			if(check.toLowerCase().equals("y")) {
				if(stock.getType().equals("local")) localStockList.remove(stock);
				if(stock.getType().equals("foreign")) foreignStockList.remove(stock);
						
				System.out.println("�ֽ��� ��Ͽ��� �����Ǿ����ϴ�.");
				stock = null;
				break;
			}
			else if(check.toLowerCase().equals("n")){
				printCancle();
				break;
			}
			else {
				printWrongInput();
			}
		}
		
		
		return;
	}
	
	// 4�� �޴�
	public void editStock() {
		if(tool.isEmpty()) return;
		printInputCode();
		
		Stock stock = tool.getElementByCode(input.nextLine());
		if(tool.isNull(stock)) return;
		
		System.out.println("���� �ֽ��� ���� �����Ͻðڽ��ϱ�? (Y/N)");
		printInput();
		
		if(input.nextLine().toLowerCase().equals("y")) {
			System.out.println("����� ���� �ֽ� ���� �Է��ϼ���.");
			printInput();
			
			int lastAsset = stock.getAsset();
			int currAsset = lastAsset;
			
			try {
				currAsset = tool.readInt(input);
			}
			catch(InputMismatchException e) {
				printNotChanged();
			}
			catch(NotPositiveNumberExeption e) {
				printNotChanged();
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
		printInput();
		
		if(input.nextLine().toLowerCase().equals("y")) {
			System.out.println("�ֽ��� ������ �Է��ϼ���.");
			printInput();
			
			String lastDesc = stock.getDescription();
			stock.setDescription(input.nextLine());
			System.out.println(
					"����Ǿ����ϴ�. "
					+ "("+lastDesc+" �� "
					+stock.getDescription()
					+")");
		}
		else {
			printCancle();
			return;
		}
		return;
	}
	
	// 5�� �޴�
	public String searchStock() throws IOException {
		System.out.println("�˻��� �ֽ��� �����ֽ��̸� L��, �����ֽ��̸� F�� �Է����ּ���.");
		printInput();
		String type = input.next().toLowerCase();
		if(type.equals("f")) {
			System.out.print("�غ����� ����Դϴ�.");
			return input.nextLine();
		}
		else if (!type.equals("l")) {
			printWrongInput();
			return input.nextLine();
		}
		
		System.out.println("�ڵ带 �˻��� �ֽ��� �̸��� �Է��ϼ���.");
		printInput();
		String name = input.next();
		ArrayList<String> nameList = LocalStock.getListOfCode(name.toLowerCase());
		
		int count = 1;
		for(String str : nameList) {
			System.out.println(count + " : "+str);
			count ++;
		}
		
		int number = 0;
		System.out.println("�ڵ带 �˻��� �ֽĸ��� ������ �Է��ϼ���.");
		printInput();
		
		try {
			number = tool.readInt(input);			
		}
		catch (InputMismatchException e){
			printWrongInput();
			printCancle();
		}
		catch (NotPositiveNumberExeption e) {
			printWrongInput();
			printCancle();
		}
		finally {
			if(number > nameList.size()) {
				printWrongInput();
				printCancle();
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
			printEmpty();
			return;
		}
		
		System.out.println("---------------<���� ���� �ֽ� ��Ȳ>---------------");
		for(Stock stock : localStockList) {
			stock.Fresh();
			System.out.println(
					stock.getName()
					+"("+stock.getCode()+") : "
					+tool.getTotalAsset(stock,"dot","today")+"�� ("
					+stock.getAsset()+" ��)"
					);
		}
		System.out.println(""
				+"���� ���� �ֽ� �Ѿ� : "
				+tool.seperateNumber(tool.getWealthOf("y","local"))
				+"�� �� " + tool.seperateNumber(tool.getWealthOf("t","local"))
				+"�� (" + tool.getWealthOf("k","local") + "%)"
				);
		System.out.println();
		
		System.out.println("---------------<���� ���� �ֽ� ��Ȳ>---------------");
		for(Stock stock : foreignStockList) {
			stock.Fresh();
			System.out.println(
					stock.getName()
					+"("+stock.getCode()+") : "
					+tool.getTotalAsset(stock,"dot","today")
					+" USD ("+stock.getAsset()+" ��)"
					);
		}
		System.out.println("���� ���� �ֽ� �Ѿ� : "
						+tool.seperateNumber(tool.getWealthOf("y","foreign"))
						+" USD �� "+tool.seperateNumber(tool.getWealthOf("t","foreign"))
						+" USD ("+tool.getWealthOf("k","foreign")+"%)"
						);
		//�޷� �ü��� ���� �ֽ� �Ѿ��� ��/�޷� ȯ����� ���ϴ� �ڵ� ����
	}
}
