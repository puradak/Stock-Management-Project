package managements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Data.Stock;

public class Function {
	private ArrayList<Stock> localStockList = new ArrayList<Stock>();
	private ArrayList<Stock> foreignStockList = new ArrayList<Stock>();
	
	Scanner input = new Scanner(System.in);
	
	public void addStock() throws IOException {
		System.out.println("---------------------------");
		
		System.out.print("�ֽ� �ڵ带 �Է��ϼ���. : ");
		String code = input.nextLine();
		if(isExistStock(code)) {
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
		
		System.out.print("�������� ������ �Է��ϼ���. : ");
		int asset = input.nextInt();
		stock.setAsset(asset);
		System.out.println("Ȯ�εǾ����ϴ�.");
		
		input.nextLine();
		
		System.out.print("(���û���) �� �ֽĿ� ���� �޸� ����ðڽ��ϱ�? (Y/N) : ");
		String check = input.nextLine();
		if(check.toLowerCase().equals("y")) {
			System.out.print("�Է� : ");
			stock.setDescription(input.nextLine());
		}
		else if(check.toLowerCase().equals("n")) {
			stock.setDescription("empty");
		}
		else {
			System.out.println("�߸� �Է��ϼ̽��ϴ�. �޸�� ���Ŀ� ������ �����մϴ�. �޴��� ���ư��ϴ�.");
		}
		
		System.out.println("---------------------------");
		
		return;
	}
	
	public void removeStock() throws IOException {
		System.out.println("---------------------------");
		System.out.print("�ֽ� �ڵ带 �Է��ϼ���. : ");
		Stock stock = getElementByCode(input.nextLine());
		if(stock == null) {
			System.out.println("�߸��Ǿ��ų�, ��Ͽ� �������� �ʴ� �ֽ��ڵ��Դϴ�. �۾��� ����մϴ�.");
			return;
		}
		
		System.out.println("Ȯ�εǾ����ϴ�.");
		while(true) {
			System.out.print("���� �� �ֽ��� ��Ͽ��� �����Ͻðڽ��ϱ�? (Y/N) : ");
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
	
	public void searchStock() {
		if(localStockList.isEmpty() && foreignStockList.isEmpty()) {
			System.out.println("����� ������ϴ�. �ֽ��� �߰��Ͻʽÿ�.");
			return;
		}
		System.out.println("---------------------------");
		System.out.print("�˻��� �ֽ��� �ڵ带 �Է��ϼ���. : ");
		Stock stock = getElementByCode(input.nextLine());
		if(stock == null) {
			System.out.println("�߸��Ǿ��ų�, ��Ͽ� �������� �ʴ� �ֽ��ڵ��Դϴ�. �۾��� ����մϴ�.");
			return;
		}
		System.out.println();
		String mark, code;
		if(stock.getType().equals("local")) { 
			System.out.print("[ ���� ");
			mark = "��";
			code = "("+stock.getCode()+")";
		}
		else {
			System.out.print("[ ���� ");
			mark = "USD";
			code = "";
		}
		System.out.println("�ֽ� ���� ]");
		System.out.println(stock.getName()+code);
		System.out.println("�ü� : "+stock.getPrice_t()+mark);
		System.out.println("���� : "+stock.getPrice_y()+mark);
		System.out.println("���� ��� : "+stock.getNetChange()+"%");
		System.out.println("�ڻ� : "+getTotalAsset(stock, "dot", "today")+mark+" ("+stock.getAsset()+"��)");
		System.out.println("---------------------------");
		
		return;
	}
	
	public void show_all() throws IOException {
		if(localStockList.isEmpty() && foreignStockList.isEmpty()) {
			System.out.println("����� ������ϴ�. �ֽ��� �߰��Ͻʽÿ�.");
			return;
		}
		System.out.println("================�����ֽ�================");
		for(Stock stock : localStockList) {
			stock.Fresh();
			System.out.println("�ֽĸ���: "+stock.getName()+"("+stock.getCode()+")");
			System.out.println("���簡����: "+stock.getPrice_t()+"��");
			System.out.println("�����ڻꡡ��: "+getTotalAsset(stock,"dot","today")+"�� ("+stock.getAsset()+"��)");
			System.out.println("���ϴ�� : "+stock.getNetChange()+"%");
			System.out.println("��������: "+stock.getDescription());
			System.out.println("======================================");
		}
		System.out.println("================�����ֽ�================");
		for(Stock stock : foreignStockList) {
			stock.Fresh();
			System.out.println("�ֽĸ���: "+stock.getName());
			System.out.println("���簡����: "+stock.getPrice_t()+" USD");
			System.out.println("�����ڻꡡ��: "+getTotalAsset(stock,"dot","today")+" USD ("+stock.getAsset()+"��)");
			System.out.println("���ϴ�� : "+stock.getNetChange()+"%");
			System.out.println("��������: "+stock.getDescription());
			System.out.println("======================================");
		}
		return;
	}
	
	public void editStock() {

		System.out.print("������ �ֽ��� �ֽ��ڵ带 �Է��ϼ���. : ");
		Stock stock = getElementByCode(input.nextLine());
		if(stock == null){
			System.out.println("�߰����� �ʾҰų� �������� �ʴ� �ֽ� �ڵ��Դϴ�.");
			System.out.println("�۾��� ����մϴ�.");
			return;
		}
		
		System.out.print("���� �ֽ��� ���� �����Ͻðڽ��ϱ�? (Y/N) : ");
		if(input.nextLine().toLowerCase().equals("y")) {
			System.out.print("���� �ֽ� ���� ���� �Է��ϼ���. : ");
			int lastAsset = stock.getAsset();
			stock.setAsset(input.nextInt());
			System.out.println("���� �Ǿ����ϴ�.("+lastAsset+"�� �� "+stock.getAsset()+"��)\n");
		}
		else System.out.println("�۾��� ����մϴ�.");
		input.nextLine().toLowerCase();
		
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
			return;
		}
		
		return;
	}
	
	public void statistic() throws IOException {
		
		if(localStockList.isEmpty() && foreignStockList.isEmpty()) {
			System.out.println("��ϵ� �ֽ��� �����ϴ�.");
			System.out.println("�۾��� ����մϴ�.");
			return;
		}
		System.out.println("---------------<���� ���� �ֽ� ��Ȳ>---------------");
		for(Stock stock : localStockList) {
			stock.Fresh();
			System.out.println(stock.getName()+ "("+stock.getCode()+") : "+getTotalAsset(stock,"dot","today")+"�� ("+stock.getAsset()+" ��)");
		}
		System.out.println("���� ���� �ֽ� �Ѿ� : "+seperateNumber(getWealthOf("y","local"))+"�� �� "+seperateNumber(getWealthOf("t","local"))+"�� ("+getWealthOf("k","local")+"%)");
		System.out.println();
		
		System.out.println("---------------<���� ���� �ֽ� ��Ȳ>---------------");
		for(Stock stock : foreignStockList) {
			stock.Fresh();
			System.out.println(stock.getName()+ "("+stock.getCode()+") : "+getTotalAsset(stock,"dot","today")+" USD ("+stock.getAsset()+" ��)");
		}
		System.out.println("���� ���� �ֽ� �Ѿ� : "+seperateNumber(getWealthOf("y","foreign"))+" USD �� "+seperateNumber(getWealthOf("t","foreign"))+" USD ("+getWealthOf("k","foreign")+"%)");
		//�޷� �ü��� ���� �ֽ� �Ѿ��� ��/�޷� ȯ����� ���ϴ� �ڵ� ����
	}
	
	public boolean isExistStock(String code) {
		for(Stock stock : localStockList) {
			if(stock.getCode().equals(code)) return true;
		}
		
		for(Stock stock : foreignStockList) {
			if(stock.getCode().equals(code)) return true;
		}
		
		return false;
	}
	
	public String getTotalAsset(Stock stock, String type, String time) {
		String temp = "";
		if(time.equals("today")) temp = Stock.getPureNumber(stock.getPrice_t());
		else if(time.equals("yesterday")) temp = Stock.getPureNumber(stock.getPrice_y());
		//remove dots from price (string)
		
		temp = String.format("%.2f", (Double.parseDouble(temp) * stock.getAsset()));
		if(type.equals("noDot")) return temp;
		//get total asset without dots
		
		
		return seperateNumber(temp);
		// return total asset with dots
	}
	
	public String seperateNumber(String number) {
		String temp1 = "";
		String fraction = "";
		String result = "";
		int count = 100000000;
		for(int i=0; i<number.length(); i++) {
			if(i>count) {
				fraction += number.charAt(i);
				continue;
			}
			if(number.charAt(i) == '.') count = i;
			else temp1 += number.charAt(i);
		}
		for(int i=0; i<temp1.length();i++) {
			if(i!=0 && (temp1.length()-i)%3 == 0) result += ",";
			result += temp1.charAt(i);
		}
		if(fraction.equals("")) fraction = "00";
		return result+"."+fraction;
	}
	
	public String getWealthOf(String typeOfWealth, String typeOfStock) throws IOException{
		double wy=0, wt=0;
		if(typeOfStock.equals("local")) {
			for(Stock stock : localStockList) {
				stock.Fresh();
				wt += Double.parseDouble(getTotalAsset(stock,"noDot","today"));
				wy += Double.parseDouble(getTotalAsset(stock,"noDot","yesterday"));
			}
		}
		if(typeOfStock.equals("foreign")) {
			for(Stock stock : foreignStockList) {
				stock.Fresh();
				wt += Double.parseDouble(getTotalAsset(stock,"noDot","today"));
				wy += Double.parseDouble(getTotalAsset(stock,"noDot","yesterday"));
			}
		}
		if(typeOfWealth.equals("y")) return String.format("%.2f", wy);
		else if(typeOfWealth.equals("t")) return String.format("%.2f", wt);
		else if(typeOfWealth.equals("k")) return String.format("%.2f", (double)100*(wt/wy-1));
		
		return null;
	}
	
	public Stock getElementByCode(String code) {
		for(Stock s : localStockList) {
			if(s.getCode().equals(code)) return s;
		}
		for(Stock s : foreignStockList) {
			if(s.getCode().equals(code))  return s;
		}
		return null;
	}
	
	public void getCodeByName() throws IOException {
		System.out.print("�ڵ带 �˻��� �ֽ��� �̸��� �Է��ϼ��� : ");
		String name = input.nextLine();
		String result = Stock.getListOfCode(name.toLowerCase()); 
	}
}