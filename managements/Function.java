package managements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Data.Stock;

public class Function {
	private ArrayList<Stock> stockList = new ArrayList<Stock>();
	Scanner input = new Scanner(System.in);
	
	public void addStock() throws IOException {
		System.out.println("---------------------------");
		
		System.out.print("�ֽ� �ڵ带 �Է��ϼ���. : ");
		String code = input.nextLine();
		if(isExistStock(code)) {
			System.out.println("�̹� ����߰ų� �߸��� �ֽ��ڵ��Դϴ�. �۾��� ����մϴ�.");
			return;
		}
		Stock stock = new Stock(code);
		if(!stock.getExist()) {
			System.out.println("�������� �ʴ� �ֽ��Դϴ�. �۾��� ����մϴ�.");
			stock = null;
			return;
		}
		stockList.add(stock);
		
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
		if(stockList.isEmpty()) {
			System.out.println("����� ������ϴ�. �۾��� ����մϴ�.");
			return;
		}
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
				stockList.remove(stock);
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
	
	public void show_all() throws IOException {
		if(stockList.isEmpty()) {
			System.out.println("����� ������ϴ�. �ֽ��� �߰��Ͻʽÿ�.");
			return;
		}
		System.out.println("======================================");
		for(Stock stock : stockList) {
			stock.Fresh();
			System.out.println("�ֽĸ���: "+stock.getName()+"("+stock.getCode()+")");
			System.out.println("���簡����: "+stock.getPrice());
			System.out.println("�����֡���: "+getTotalAsset(stock,"dot")+"�� ("+stock.getAsset()+"��)");
			System.out.println("���ϴ�� "+stock.getNetChange()+"%");
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
		if(stockList.isEmpty()) {
			System.out.println("��ϵ� �ֽ��� �����ϴ�.");
			System.out.println("�۾��� ����մϴ�.");
			return;
		}
		for(Stock stock : stockList) {
			stock.Fresh();
			System.out.println(stock.getName()+ "("+stock.getCode()+") : "+getTotalAsset(stock,"dot")+"�� ("+stock.getAsset()+" ��)");
		}
		System.out.println("���� �ְ��Ѿ� : "+seperateNumber(getWealthOf("y"))+"�� �� "+seperateNumber(getWealthOf("t"))+"�� ("+getWealthOf("k")+"%)");
	}
	
	public boolean isExistStock(String code) {
		for(Stock stock : stockList) {
			if(stock.getCode().equals(code)) {
				return true;
			}
		}
		return false;
	}
	
	public String getTotalAsset(Stock stock, String type) {
		String temp = "";
		for(int i=0; i<stock.getPrice().length(); i++) {
			if(stock.getPrice().charAt(i)==',') continue;
			else temp += stock.getPrice().charAt(i);
		}
		//remove dots from price (string)
		
		temp = ""+Integer.parseInt(temp) * stock.getAsset();
		if(type.equals("noDot")) return temp;
		//get total asset without dots
		
		
		return seperateNumber(temp);
		// return total asset with dots
	}
	
	public String seperateNumber(String number) {
		String result = "";
		for(int i = 0; i<number.length(); i++) {
			if(i!=0 && (number.length()-i)%3 == 0) {
				result += ",";
			}
			result += number.charAt(i);
		}
		return result;
	}
	
	public String getWealthOf(String type) throws IOException{
		int wy=0, wt=0;
		for(Stock stock : stockList) {
			stock.Fresh();
			wt += Integer.parseInt(getTotalAsset(stock,"noDot"));
			wy += (int)(Integer.parseInt(getTotalAsset(stock,"noDot"))/(double)(1+0.01*stock.getNetChangeValue()));
		}
		if(type.equals("y")) return ""+wy;
		else if(type.equals("t")) return ""+wt;
		else if(type.equals("k")) return String.format("%.2f", 100*((double)wt/wy-1));
		
		return null;
	}
	
	public Stock getElementByCode(String code) {
		for(Stock s : stockList) {
			if(s.getCode().equals(code)) return s;
		}
		return null;
	}
}