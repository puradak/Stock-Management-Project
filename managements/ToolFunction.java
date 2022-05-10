package managements;

import java.io.IOException;
import java.util.Scanner;

import Data.Stock;
import exceptions.NotPositiveNumberExeption;

public class ToolFunction {
	
	// 객체 하나만 쓰기
	private static ToolFunction tool = new ToolFunction();
	private ToolFunction() {}
	
	// 객체 넘겨주기
	public static ToolFunction getToolFunctionObject() {
		return tool;
	}
	
	// 리스트에 등록된 주식인지 검사 (유 : T / 무 : F)
	public boolean isExistStock(String code) {
		for(Stock stock : MenuFunction.localStockList) {
			if(stock.getCode().equals(code)) return true;
		}
		
		for(Stock stock : MenuFunction.foreignStockList) {
			if(stock.getCode().equals(code)) return true;
		}
		
		return false;
	}
	
	// 보유한 주식의 시세(또는 전일 마감 가격)와 현재 보유한 주식 수의 곱을 반환
	// 최대 10회까지 기록을 저장할 수 있는 리스트 추가 예정
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
	
	// 3자리 이상의 수를 3자리마다 쉼표로 구분하여 반환함
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
	
	// 보유한 모든 주식에 대한 총 자산과 전일 대비 증감량을 반환
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
	
	// 보유 주식 리스트에 해당 코드를 가진 객체를 반환 - 존재하지 않으면 null 반환
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
		else System.out.println("확인되었습니다.");
		
		return number;
	}
	
}