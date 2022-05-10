package managements;

import java.io.IOException;
import java.util.Scanner;

import Data.Stock;
import exceptions.NotPositiveNumberExeption;

public class ToolFunction {
	
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
		else System.out.println("Ȯ�εǾ����ϴ�.");
		
		return number;
	}
	
}