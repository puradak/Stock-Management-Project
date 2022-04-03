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
		
		System.out.print("주식 코드를 입력하세요. : ");
		String code = input.nextLine();
		if(isExistStock(code)) {
			System.out.println("이미 등록했거나 잘못된 주식코드입니다. 작업을 취소합니다.");
			return;
		}
		Stock stock = new Stock(code);
		if(!stock.getExist()) {
			System.out.println("존재하지 않는 주식입니다. 작업을 취소합니다.");
			stock = null;
			return;
		}
		stockList.add(stock);
		
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
		if(stockList.isEmpty()) {
			System.out.println("목록이 비었습니다. 작업을 취소합니다.");
			return;
		}
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
				stockList.remove(stock);
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
	
	public void show_all() throws IOException {
		if(stockList.isEmpty()) {
			System.out.println("목록이 비었습니다. 주식을 추가하십시오.");
			return;
		}
		System.out.println("======================================");
		for(Stock stock : stockList) {
			stock.Fresh();
			System.out.println("주식명　　: "+stock.getName()+"("+stock.getCode()+")");
			System.out.println("현재가　　: "+stock.getPrice());
			System.out.println("보유주　　: "+getTotalAsset(stock,"dot")+"원 ("+stock.getAsset()+"주)");
			System.out.println("전일대비 "+stock.getNetChange()+"%");
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
		if(stockList.isEmpty()) {
			System.out.println("등록된 주식이 없습니다.");
			System.out.println("작업을 취소합니다.");
			return;
		}
		for(Stock stock : stockList) {
			stock.Fresh();
			System.out.println(stock.getName()+ "("+stock.getCode()+") : "+getTotalAsset(stock,"dot")+"원 ("+stock.getAsset()+" 주)");
		}
		System.out.println("보유 주가총액 : "+seperateNumber(getWealthOf("y"))+"원 → "+seperateNumber(getWealthOf("t"))+"원 ("+getWealthOf("k")+"%)");
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