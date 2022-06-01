package functions;

import data.Stock;
import log_management.LoadManager;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class ToolFunction {
	
	public ArrayList<String> localNameList = new ArrayList<String>();
	public ArrayList<String> foreignNameList = new ArrayList<String>();
	private LoadManager loader = LoadManager.getLoadManagerObject();
	// ��ü �ϳ��� ����
	private static ToolFunction tool = new ToolFunction();
	private ToolFunction() {} 
	
	// ��ü �Ѱ��ֱ�
	public static ToolFunction getToolFunctionObject() {
		return tool;
	}
	String[] kind = {"����", "����"};
	
	public void freshNameList() {
		for(int i=0; i<2; i++) {
			for(int j=0; j<loader.getList(i).size(); j++) {
				getNameList(i).add(loader.getList(i).get(j).getName());
			}
		}
	}
	
	public ArrayList<String> getNameList(int number) {
		if(number == 0) return localNameList;
		else return foreignNameList;
	}

	// ����Ʈ�� ��ϵ� �ֽ����� �˻� (�� : T / �� : F)
	public boolean isExistStock(String code) {
		ArrayList<Stock> stockList;
		for(int i=0; i<2; i++) {
			stockList = loader.getList(i);
			for(Stock stock : stockList) {
				if(stock.getCode().equals(code)) {
					return true;
				}
			}
		}		
		return false;
	}
	
	public boolean isCorrectWith(String str, Scanner input) {
		if(input.nextLine().toLowerCase().equals(str)) return true;
		else return false;
	}
	
	public boolean isNull(Stock stock) {
		if(stock == null) {
			return true;
		}
		return false;
	}

	public boolean isValidMenu(Scanner input, String str) {
		if(str.equals("-1")) {
			input.nextLine(); 
			return false;}
		else return true;
	}

	// 3�ڸ� �̻��� ���� 3�ڸ����� ��ǥ�� �����Ͽ� ��ȯ��
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
	
	// ������ �ֽ��� �ü�(�Ǵ� ���� ���� ����)�� ���� ������ �ֽ� ���� ���� ��ȯ
	// �ִ� 10ȸ���� ����� ������ �� �ִ� ����Ʈ �߰� ����
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
	
	// ������ ��� �ֽĿ� ���� �� �ڻ�� ���� ��� �������� ��ȯ
	public String getWealthOf(String typeOfWealth, int typeOfStock) throws IOException{
		ArrayList<Stock> stockList = loader.getList(typeOfStock);

		double wy=0, wt=0;
		for(Stock stock : stockList) {
			stock.Fresh();
			wt += Double.parseDouble(getTotalAsset(stock,"noDot","today"));
			wy += Double.parseDouble(getTotalAsset(stock,"noDot","yesterday"));
		}
		
		if(typeOfWealth.equals("y")) 
			return String.format("%.2f", wy);
		else if(typeOfWealth.equals("t")) 
			return String.format("%.2f", wt);
		else if(typeOfWealth.equals("k")) 
			return String.format("%.2f", (double)100*(wt/wy-1));
		
		return null;
	}
	
	public Stock getElementByName(String name) {
		for(int i=0; i<2; i++) {
			for(Stock s : loader.getList(i)) {
				if(s.getName().equals(name)) return s;
			}
		}
		return null;
	}
	
	// ���� �ֽ� ����Ʈ�� �ش� �ڵ带 ���� ��ü�� ��ȯ - �������� ������ null ��ȯ
	
	public boolean isOpen(Stock stock) {
		int Hour = LocalTime.now().getHour();
		int Min = LocalTime.now().getMinute();
		if(stock.getType().equals("local")) {
			if(Hour >= 9 && Hour < 15) return true;
			else if(Hour == 15 && Min<= 30) return true;
			else return false;
		}
		if(stock.getType().equals("foreign")) {
			if(Hour==23 && Min >= 30) return true;
			else if(Hour>23 && Hour<=6) return true;
			else return false;
		}
		return false;
	}
	
	
}