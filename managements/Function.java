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
		
		System.out.print("주식 코드를 입력하세요. : ");
		String code = input.nextLine();
		if(isExistStock(code)) {
			System.out.println("이미 등록했거나 잘못된 주식코드입니다. 작업을 취소합니다.");
			return;
		}
		Stock stock = Stock.createStock(code);

		if(!stock.getExist()) {
			System.out.println("존재하지 않는 주식입니다. 작업을 취소합니다.");
			stock = null;
			return;
		}
		if(stock.getType().equals("local")) localStockList.add(stock);
		if(stock.getType().equals("foreign")) foreignStockList.add(stock);
		
		System.out.println("확인되었습니다.");
		
		System.out.print("보유중인 수량을 입력하세요. : ");
		int asset = input.nextInt();
		stock.setAsset(asset);
		System.out.println("확인되었습니다.");
		
		input.nextLine();
		
		System.out.print("(선택사항) 이 주식에 대한 메모를 남기시겠습니까? (Y/N) : ");
		String check = input.nextLine();
		if(check.toLowerCase().equals("y")) {
			System.out.print("입력 : ");
			stock.setDescription(input.nextLine());
		}
		else if(check.toLowerCase().equals("n")) {
			stock.setDescription("empty");
		}
		else {
			System.out.println("잘못 입력하셨습니다. 메모는 차후에 수정이 가능합니다. 메뉴로 돌아갑니다.");
		}
		
		System.out.println("---------------------------");
		
		return;
	}
	
	public void removeStock() throws IOException {
		System.out.println("---------------------------");
		System.out.print("주식 코드를 입력하세요. : ");
		Stock stock = getElementByCode(input.nextLine());
		if(stock == null) {
			System.out.println("잘못되었거나, 목록에 존재하지 않는 주식코드입니다. 작업을 취소합니다.");
			return;
		}
		
		System.out.println("확인되었습니다.");
		while(true) {
			System.out.print("정말 이 주식을 목록에서 삭제하시겠습니까? (Y/N) : ");
			String check = input.nextLine();
			if(check.toLowerCase().equals("y")) {
				if(stock.getType().equals("local")) localStockList.remove(stock);
				if(stock.getType().equals("foreign")) foreignStockList.remove(stock);
						
				System.out.println("주식이 목록에서 삭제되었습니다.");
				stock = null;
				break;
			}
			else if(check.toLowerCase().equals("n")){
				System.out.println("취소되었습니다.");
				break;
			}
			else {
			System.out.println("잘못 입력하셨습니다. Y/N 중 하나를 입력하세요.");
			}
		}
		System.out.println("---------------------------");
		return;
	}
	
	public void searchStock() {
		if(localStockList.isEmpty() && foreignStockList.isEmpty()) {
			System.out.println("목록이 비었습니다. 주식을 추가하십시오.");
			return;
		}
		System.out.println("---------------------------");
		System.out.print("검색할 주식의 코드를 입력하세요. : ");
		Stock stock = getElementByCode(input.nextLine());
		if(stock == null) {
			System.out.println("잘못되었거나, 목록에 존재하지 않는 주식코드입니다. 작업을 취소합니다.");
			return;
		}
		System.out.println();
		String mark, code;
		if(stock.getType().equals("local")) { 
			System.out.print("[ 국내 ");
			mark = "원";
			code = "("+stock.getCode()+")";
		}
		else {
			System.out.print("[ 국외 ");
			mark = "USD";
			code = "";
		}
		System.out.println("주식 정보 ]");
		System.out.println(stock.getName()+code);
		System.out.println("시세 : "+stock.getPrice_t()+mark);
		System.out.println("전일 : "+stock.getPrice_y()+mark);
		System.out.println("전일 대비 : "+stock.getNetChange()+"%");
		System.out.println("자산 : "+getTotalAsset(stock, "dot", "today")+mark+" ("+stock.getAsset()+"주)");
		System.out.println("---------------------------");
		
		return;
	}
	
	public void show_all() throws IOException {
		if(localStockList.isEmpty() && foreignStockList.isEmpty()) {
			System.out.println("목록이 비었습니다. 주식을 추가하십시오.");
			return;
		}
		System.out.println("================국내주식================");
		for(Stock stock : localStockList) {
			stock.Fresh();
			System.out.println("주식명　　: "+stock.getName()+"("+stock.getCode()+")");
			System.out.println("현재가　　: "+stock.getPrice_t()+"원");
			System.out.println("보유자산　　: "+getTotalAsset(stock,"dot","today")+"원 ("+stock.getAsset()+"주)");
			System.out.println("전일대비 : "+stock.getNetChange()+"%");
			System.out.println("설명　　　: "+stock.getDescription());
			System.out.println("======================================");
		}
		System.out.println("================국외주식================");
		for(Stock stock : foreignStockList) {
			stock.Fresh();
			System.out.println("주식명　　: "+stock.getName());
			System.out.println("현재가　　: "+stock.getPrice_t()+" USD");
			System.out.println("보유자산　　: "+getTotalAsset(stock,"dot","today")+" USD ("+stock.getAsset()+"주)");
			System.out.println("전일대비 : "+stock.getNetChange()+"%");
			System.out.println("설명　　　: "+stock.getDescription());
			System.out.println("======================================");
		}
		return;
	}
	
	public void editStock() {

		System.out.print("수정할 주식의 주식코드를 입력하세요. : ");
		Stock stock = getElementByCode(input.nextLine());
		if(stock == null){
			System.out.println("추가하지 않았거나 존재하지 않는 주식 코드입니다.");
			System.out.println("작업을 취소합니다.");
			return;
		}
		
		System.out.print("보유 주식의 수를 변경하시겠습니까? (Y/N) : ");
		if(input.nextLine().toLowerCase().equals("y")) {
			System.out.print("보유 주식 수를 새로 입력하세요. : ");
			int lastAsset = stock.getAsset();
			stock.setAsset(input.nextInt());
			System.out.println("변경 되었습니다.("+lastAsset+"주 → "+stock.getAsset()+"주)\n");
		}
		else System.out.println("작업을 취소합니다.");
		input.nextLine().toLowerCase();
		
		System.out.print("해당 주식의 설명을 변경하시겠습니까? (Y/N) : ");
		if(input.nextLine().toLowerCase().equals("y")) {
			System.out.print("주식의 설명을 입력하세요. : ");
			String lastDesc = stock.getDescription();
			stock.setDescription(input.nextLine());
			System.out.print("변경되었습니다. ");
			System.out.println("("+lastDesc+" → "+stock.getDescription()+")");
		}
		else {
			System.out.println("작업을 취소합니다.");
			return;
		}
		
		return;
	}
	
	public void statistic() throws IOException {
		
		if(localStockList.isEmpty() && foreignStockList.isEmpty()) {
			System.out.println("등록된 주식이 없습니다.");
			System.out.println("작업을 취소합니다.");
			return;
		}
		System.out.println("---------------<보유 국내 주식 현황>---------------");
		for(Stock stock : localStockList) {
			stock.Fresh();
			System.out.println(stock.getName()+ "("+stock.getCode()+") : "+getTotalAsset(stock,"dot","today")+"원 ("+stock.getAsset()+" 주)");
		}
		System.out.println("보유 국내 주식 총액 : "+seperateNumber(getWealthOf("y","local"))+"원 → "+seperateNumber(getWealthOf("t","local"))+"원 ("+getWealthOf("k","local")+"%)");
		System.out.println();
		
		System.out.println("---------------<보유 국외 주식 현황>---------------");
		for(Stock stock : foreignStockList) {
			stock.Fresh();
			System.out.println(stock.getName()+ "("+stock.getCode()+") : "+getTotalAsset(stock,"dot","today")+" USD ("+stock.getAsset()+" 주)");
		}
		System.out.println("보유 국외 주식 총액 : "+seperateNumber(getWealthOf("y","foreign"))+" USD → "+seperateNumber(getWealthOf("t","foreign"))+" USD ("+getWealthOf("k","foreign")+"%)");
		//달러 시세에 따른 주식 총액의 원/달러 환산액을 구하는 코드 삽입
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
		System.out.print("코드를 검색할 주식의 이름을 입력하세요 : ");
		String name = input.nextLine();
		String result = Stock.getListOfCode(name.toLowerCase()); 
	}
}