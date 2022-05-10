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
		System.out.println("================�����ֽ�================");
		for(Stock stock : foreignStockList) {
			stock.Fresh();
			System.out.println("�ֽĸ���: "+stock.getName());
			System.out.println("���簡����: "+stock.getPrice_t()+" USD");
			System.out.println("�����ڻꡡ��: "+tool.getTotalAsset(stock,"dot","today")+" USD ("+stock.getAsset()+"��)");
			System.out.println("���ϴ�� : "+stock.getNetChange()+"%");
			System.out.println("��������: "+stock.getDescription());
			System.out.println("======================================");
		}
		return;
	}
	
	// 2�� �޴�
	public void addStock() throws IOException {
		System.out.println("---------------------------");
		
		System.out.println("�ֽ� �ڵ带 �Է��ϼ���.");
		printInput();
		String code = input.nextLine();
		if(tool.isExistStock(code)) {
			System.out.println("�̹� ����߰ų� �߸��� �ֽ��ڵ��Դϴ�. �۾��� ����մϴ�.");
			return;
		}
		Stock stock = Stock.createStock(code);

		if(!stock.getExist()) {
			System.out.println("�������� �ʴ� �ֽ��Դϴ�. �۾��� ����մϴ�.");
			stock = null;
			return;
		}
		if(stock.getType().equals("local")) localStockList.add(stock);
		if(stock.getType().equals("foreign")) foreignStockList.add(stock);
		
		System.out.println("Ȯ�εǾ����ϴ�.");
		
		System.out.println("�������� ������ �Է��ϼ���.");
		printInput();
		
		int asset = 1;

		try {
			asset = tool.readInt(input);		
		} 
		catch(InputMismatchException e) {
			printWrongAsset();
		}
		catch(NotPositiveNumberExeption e) {
			printWrongAsset();
		}
		finally {
			stock.setAsset(asset);				
		}
		
		input.nextLine();
		
		System.out.println("(���û���) �� �ֽĿ� ���� �޸� ����ðڽ��ϱ�? (Y/N) : ");
		String check = input.nextLine();
		if(check.toLowerCase().equals("y")) {
			System.out.print("�Է� : ");
			stock.setDescription(input.nextLine());
		}
		else if(check.toLowerCase().equals("n")) {
			stock.setDescription("(�������� ����)");
		}
		else {
			stock.setDescription("(�������� ����)");
			printWrongInput();
		}
		
		System.out.println("---------------------------");
		
		return;
	}
	
	// 3�� �޴�
	public void removeStock() throws IOException {
		if(tool.isEmpty()) return;
		
		System.out.println("---------------------------");
		System.out.print("�ֽ� �ڵ带 �Է��ϼ���.");
		printInput();
		String code = input.nextLine();
		Stock stock = tool.getElementByCode(code);
		if(stock == null) {
			System.out.println("�߸��Ǿ��ų�, ��Ͽ� �������� �ʴ� �ֽ��ڵ��Դϴ�. �۾��� ����մϴ�.");
			return;
		}
		
		System.out.println("Ȯ�εǾ����ϴ�.");
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
				System.out.println("��ҵǾ����ϴ�.");
				break;
			}
			else {
			System.out.println("�߸� �Է��ϼ̽��ϴ�. Y/N �� �ϳ��� �Է��ϼ���.");
			}
		}
		System.out.println("---------------------------");
		return;
	}
	
	// 4�� �޴�
	public void editStock() {
		if(tool.isEmpty()) return;
		
		System.out.println("������ �ֽ��� �ֽ��ڵ带 �Է��ϼ���.");
		printInput();
		Stock stock = tool.getElementByCode(input.nextLine());
		if(stock == null){
			System.out.println("�߰����� �ʾҰų� �������� �ʴ� �ֽ� �ڵ��Դϴ�.");
			System.out.println("���� �޴��� ���ư��ϴ�.");
			return;
		}
		
		System.out.println("���� �ֽ��� ���� �����Ͻðڽ��ϱ�? (Y/N)");
		printInput();
		if(input.nextLine().toLowerCase().equals("y")) {
			System.out.print("���� �ֽ� ���� ���� �Է��ϼ���. : ");
			int lastAsset = stock.getAsset();
			
			int currAsset = lastAsset;
			try {
				currAsset = tool.readInt(input);
			}
			catch(InputMismatchException e) {
				System.out.println("�߸� �Է��ϼ̽��ϴ�. ������� �ʾҽ��ϴ�.");
			}
			catch(NotPositiveNumberExeption e) {
				System.out.println("�߸� �Է��ϼ̽��ϴ�. ������� �ʾҽ��ϴ�.");
			}
			finally {
				stock.setAsset(currAsset);				
			}
			
			System.out.println("�۾� ���� : ("+lastAsset+"�� �� "+stock.getAsset()+"��)\n");
		}

		input.nextLine();
		
		System.out.print("�ش� �ֽ��� ������ �����Ͻðڽ��ϱ�? (Y/N) : ");
		if(input.nextLine().toLowerCase().equals("y")) {
			System.out.print("�ֽ��� ������ �Է��ϼ���. : ");
			String lastDesc = stock.getDescription();
			stock.setDescription(input.nextLine());
			System.out.print("����Ǿ����ϴ�. ");
			System.out.println("("+lastDesc+" �� "+stock.getDescription()+")");
		}
		else {
			System.out.println("�۾��� ����մϴ�.");
			System.out.println("���� �޴��� ���ư��ϴ�.");
			return;
		}
		
		return;
	}
	
	// 5�� �޴�
	public String searchStock() throws IOException {
		System.out.print("�˻��� �ֽ��� �����ֽ��̸� L��, �����ֽ��̸� F�� �Է����ּ��� : ");
		String type = input.next().toLowerCase();
		if(type.equals("f")) {
			System.out.println("�غ����� ����Դϴ�. ���� �޴��� ���ư��ϴ�.");
			return input.nextLine();
		}
		else if (!type.equals("l")) {
			printWrongInput();
			return input.nextLine();
		}
		
		System.out.print("�ڵ带 �˻��� �ֽ��� �̸��� �Է��ϼ��� : ");
		String name = input.next();
		ArrayList<String> nameList = LocalStock.getListOfCode(name.toLowerCase());
		
		int count = 1;
		for(String str : nameList) {
			System.out.println(count + " : "+str);
			count ++;
		}
		int number = 0;
		System.out.print("�ڵ带 �˻��� �ֽĸ��� ������ �Է��ϼ��� : ");
		
		try {
			number = tool.readInt(input);			
		}
		catch (InputMismatchException e){
			printWrongInput();
			return input.nextLine();
		}
		catch (NotPositiveNumberExeption e) {
			printWrongInput();
			return input.nextLine();
		}
		finally {
			if(number > nameList.size()) {
				printWrongInput();
				return input.nextLine();
			}
		}
		
		
		String code = LocalStock.findCodeByName(nameList,number);
		System.out.println(nameList.get(number-1)+"�� �ֽ� �ڵ�� "+code+" �Դϴ�.");
		printGoMain();
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
