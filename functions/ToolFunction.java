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
	// 객체 하나만 쓰기
	private static ToolFunction tool = new ToolFunction();
	private ToolFunction() {} 
	
	// 객체 넘겨주기
	public static ToolFunction getToolFunctionObject() {
		return tool;
	}
	String[] kind = {"국내", "국외"};
	
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

	// 리스트에 등록된 주식인지 검사 (유 : T / 무 : F)
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
	
	// 보유한 모든 주식에 대한 총 자산과 전일 대비 증감량을 반환
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
	
	// 보유 주식 리스트에 해당 코드를 가진 객체를 반환 - 존재하지 않으면 null 반환
	
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