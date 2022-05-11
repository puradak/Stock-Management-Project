package managements;

import java.io.IOException;
import java.util.Scanner;

import data.Stock;
import exceptions.NotPositiveNumberExeption;

public class ToolFunction extends Printer{
	
	// ��ü �ϳ��� ����
	private static ToolFunction tool = new ToolFunction();
	private ToolFunction() {}
	
	// ��ü �Ѱ��ֱ�
	public static ToolFunction getToolFunctionObject() {
		return tool;
	}
		
	// ����Ʈ�� ��ϵ� �ֽ����� �˻� (�� : T / �� : F)
	public boolean isExistStock(String code) {
		for(Stock stock : MenuFunction.localStockList) {
			if(stock.getCode().equals(code)) return true;
		}
		
		for(Stock stock : MenuFunction.foreignStockList) {
			if(stock.getCode().equals(code)) return true;
		}
		
		return false;
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
	
	// ������ ��� �ֽĿ� ���� �� �ڻ�� ���� ��� �������� ��ȯ
	public String getWealthOf(String typeOfWealth, String typeOfStock) throws IOException{
		double wy=0, wt=0;
		if(typeOfStock.equals("local")) {
			for(Stock stock : MenuFunction.localStockList) {
				stock.Fresh();
				wt += Double.parseDouble(getTotalAsset(stock,"noDot","today"));
				wy += Double.parseDouble(getTotalAsset(stock,"noDot","yesterday"));
			}
		}
		if(typeOfStock.equals("foreign")) {
			for(Stock stock : MenuFunction.foreignStockList) {
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
	
	// ���� �ֽ� ����Ʈ�� �ش� �ڵ带 ���� ��ü�� ��ȯ - �������� ������ null ��ȯ
	public Stock getElementByCode(String code) {
		for(Stock s : MenuFunction.localStockList) {
			if(s.getCode().equals(code)) return s;
		}
		for(Stock s : MenuFunction.foreignStockList) {
			if(s.getCode().equals(code))  return s;
		}
		return null;
	}
	
	public int readInt(Scanner input) throws NotPositiveNumberExeption {
		int number = input.nextInt();
		
		if(number <= 0) throw new NotPositiveNumberExeption(number);
		else printOf("Checked");
		
		return number;
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
			printOf("WongCode");
			printOf("Cancle");
			return true;
		}
		printOf("Checked");
		return false;
	}
	
	public void getFreshedInfo(Stock stock) throws IOException {
		stock.Fresh();
		System.out.println("�ֽĸ���: "+stock.getName()
		+"("+stock.getCode()+")");
		System.out.println("���簡����: "+stock.getPrice_t()+"��");
		System.out.println("�����ڻꡡ��: "+tool.getTotalAsset(stock,"dot","today")
		+"�� ("+stock.getAsset()
		+"��)");
		System.out.println("���ϴ�� : "+stock.getNetChange()+"%");
		System.out.println("��������: "+stock.getDescription());
		System.out.println("======================================");
	}
	
	public void getStatisticInfo_each(Stock stock, String type) throws IOException {
		String currency;
		if(type.equals("local")) currency = "��";
		else currency = "USD";
		
		stock.Fresh();
		System.out.println(
				stock.getName()
				+"("+stock.getCode()+") : "
				+getTotalAsset(stock,"dot","today")
				+currency+" ("+stock.getAsset()+" ��)"
				);
	}
	
	public void getStatisticInfo_total(String type) throws IOException {
		String currency;
		if(type.equals("local")) currency = "��";
		else currency = "USD";
		
		System.out.println(""
				+"���� ���� �ֽ� �Ѿ� : "
				+tool.seperateNumber(getWealthOf("y",type))
				+currency+" �� " + seperateNumber(getWealthOf("t",type))
				+currency+" (" + getWealthOf("k",type) + "%)"
				);
	}
}