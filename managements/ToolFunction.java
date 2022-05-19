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
	
	// 객체 하나만 쓰기
	private static ToolFunction tool = new ToolFunction();
	private ToolFunction() {}
	
	// 객체 넘겨주기
	public static ToolFunction getToolFunctionObject() {
		return tool;
	}
	String[] kind = {"국내", "국외"};
	
	public ArrayList<Stock> getList(int number){
		if(number == 0) return MenuFunction.localStockList;
		else return MenuFunction.foreignStockList;
	}
	
	// 리스트에 등록된 주식인지 검사 (유 : T / 무 : F)
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
	
	// 보유 주식 리스트에 해당 코드를 가진 객체를 반환 - 존재하지 않으면 null 반환
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
		System.out.println("주식명　　: "+stock.getName()
		+"("+stock.getCode()+")");
		System.out.println("현재가　　: "+stock.getPrice_t()+"원");
		System.out.println(
						"보유자산　　: "
						+tool.getTotalAsset(stock,"dot","today")
						+"원 ("+stock.getAsset()
						+"주)");
		System.out.println("전일대비 : "+stock.getNetChange()+"%");
		System.out.println("설명　　　: "+stock.getDescription());
		System.out.println("======================================");
	}
	
	public void getStatisticInfo_each(Stock stock, String type) throws IOException {
		String currency;
		if(type.equals("국내")) currency = "원";
		else currency = "USD";
		
		stock.Fresh();
		System.out.println(
				stock.getName()
				+"("+stock.getCode()+") : "
				+getTotalAsset(stock,"dot","today")
				+currency+" ("+stock.getAsset()+" 주)"
				);
	}
	
	public void getStatisticInfo_total(int type) throws IOException {
		String currency;
		if(type == 0) currency = "원";
		else currency = "USD";
		
		System.out.println(""
				+"보유 국내 주식 총액 : "
				+tool.seperateNumber(getWealthOf("y",type))
				+currency+" → " + seperateNumber(getWealthOf("t",type))
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
			System.out.println("준비되지 않은 기능입니다.");
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