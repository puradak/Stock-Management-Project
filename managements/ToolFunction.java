package managements;

import data.Stock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import exceptions.NotAKoreanException;
import exceptions.NotInRangeException;
import exceptions.NotPositiveNumberExeption;

public class ToolFunction extends Printer{
	
	// ��ü �ϳ��� ����
	private static ToolFunction tool = new ToolFunction();
	private ToolFunction() {}
	
	// ��ü �Ѱ��ֱ�
	public static ToolFunction getToolFunctionObject() {
		return tool;
	}
	String[] kind = {"����", "����"};
	
	public ArrayList<Stock> getList(int number){
		if(number == 0) return MenuFunction.localStockList;
		else return MenuFunction.foreignStockList;
	}
	
	// ����Ʈ�� ��ϵ� �ֽ����� �˻� (�� : T / �� : F)
	public boolean isExistStock(String code) {
		ArrayList<Stock> stockList;
		for(int i=0; i<2; i++) {
			stockList = getList(i);
			for(Stock stock : stockList) {
				if(stock.getCode().equals(code)) {
					printOf("exist","cancle");
					return true;
				}
			}
		}		
		return false;
	}
	
	public boolean isCorrectWith(String str, Scanner input) {
		printOf("AskGoMain");
		if(input.nextLine().toLowerCase().equals(str)) return true;
		else return false;
	}
	
	public boolean isEmpty() {
		if(MenuFunction.localStockList.isEmpty() && MenuFunction.foreignStockList.isEmpty()) {
			printOf("Empty");
			return true;
		}
		return false;
	}
	
	public boolean isNull(Stock stock) {
		if(stock == null) {
			printOf("WrongCode","Cancle");
			return true;
		}
		printOf("Checked");
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
		ArrayList<Stock> stockList = getList(typeOfStock);

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
	
	// ���� �ֽ� ����Ʈ�� �ش� �ڵ带 ���� ��ü�� ��ȯ - �������� ������ null ��ȯ
	public Stock getElementByCode(String code) {
		for(Stock str : MenuFunction.localStockList) {
			if(str.getCode().equals(code)) return str;
		}
		for(Stock str : MenuFunction.foreignStockList) {
			if(str.getCode().equals(code)) return str;
		}
		return null;
	}
	
	public void getFreshedInfo(Stock stock) throws IOException {
		stock.Fresh();
		System.out.println("�ֽĸ���: "+stock.getName()
		+"("+stock.getCode()+")");
		System.out.println("���簡����: "+stock.getPrice_t()+"��");
		System.out.println(
						"�����ڻꡡ��: "
						+tool.getTotalAsset(stock,"dot","today")
						+"�� ("+stock.getAsset()
						+"��)");
		System.out.println("���ϴ�� : "+stock.getNetChange()+"%");
		System.out.println("��������: "+stock.getDescription());
		System.out.println("======================================");
	}
	
	public void getStatisticInfo_each(Stock stock, String type) throws IOException {
		String currency;
		if(type.equals("����")) currency = "��";
		else currency = "USD";
		
		stock.Fresh();
		System.out.println(
				stock.getName()
				+"("+stock.getCode()+") : "
				+getTotalAsset(stock,"dot","today")
				+currency+" ("+stock.getAsset()+" ��)"
				);
	}
	
	public void getStatisticInfo_total(int type) throws IOException {
		String currency;
		if(type == 0) currency = "��";
		else currency = "USD";
		
		System.out.println(""
				+"���� ���� �ֽ� �Ѿ� : "
				+tool.seperateNumber(getWealthOf("y",type))
				+currency+" �� " + seperateNumber(getWealthOf("t",type))
				+currency+" (" + getWealthOf("k",type) + "%)"
				);
	}
	
	public int readInt(Scanner input) throws NotPositiveNumberExeption {
		int number = input.nextInt();
		
		if(number <= 0) throw new NotPositiveNumberExeption(number);
		
		else printOf("Checked");
		
		return number;
	}
	
	public int readInt(Scanner input, int from, int to) {
		int number = -1;
		try {
			number = input.nextInt();
			if(number >= from && number <= to) return number;
			else throw new NotInRangeException(number);
		} catch( InputMismatchException e) {
			return -1;
		} catch ( NotInRangeException e ) {
			return -1;
		}
	}
	
	public String readKorean(Scanner input) {
		String word;
		try {
			word = input.nextLine().toLowerCase();
			if(word.charAt(0) >= 'a' 
			&& word.charAt(0) <= 'z') {
				throw new NotAKoreanException(word);
			}
		} catch (NotAKoreanException e){
			System.out.println("�غ���� ���� ����Դϴ�.");
			return null;
		}
		
		return word;
	}
	
	public void pause(Scanner input) {
		printOf("AskGoMain");
		while(true)
			if(input.nextLine().toLowerCase().equals("m")) return;
	}
	

	
	
}