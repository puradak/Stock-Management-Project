package managements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import data.LocalStock;
import data.Stock;
import exceptions.NotPositiveNumberExeption;

public class MenuFunction extends Printer{
	// 객체 하나만 쓰기
	private static MenuFunction function = new MenuFunction();
	private ToolFunction tool = ToolFunction.getToolFunctionObject();
	private MenuFunction() {}
	
	// 주식 정보 리스트
	protected static ArrayList<Stock> localStockList = new ArrayList<Stock>();
	protected static ArrayList<Stock> foreignStockList = new ArrayList<Stock>();
	
	// 스캐너 인스턴스
	Scanner input = new Scanner(System.in);
	
	// kind Enum 인스턴스
	
	// 객체 넘겨주기
	public static MenuFunction getFunctionObject() {
		return function;
	}

	
	// 1번 메뉴
	public void show_all() throws IOException {
		if(tool.isEmpty()) return;
		ArrayList<Stock> stockList;
		for(int i=0; i<2; i++) {
			System.out.println("================"+tool.kind[i]+"주식================");
			stockList = tool.getList(i);
			for(Stock stock : stockList) {
				tool.getFreshedInfo(stock);
			}
			if(tool.getList((i+1)%2).isEmpty()) 
				while(true)
					if(tool.isCorrectWith("m", input)) return;
		}
	}
	
	// 2번 메뉴
	public void addStock() throws IOException {
		printOf("InputCode");
		String code = input.nextLine();
		if(tool.isExistStock(code))	return;
		
		Stock stock = Stock.createStock(code);

		if(!stock.getExist()) {
			printOf("WrongCode");
			stock = null;
			return;
		}

		System.out.println("주식 명 : "+stock.getName());
		printOf("Checked");
		
		System.out.println("보유중인 수량을 입력하세요.");
		printOf("Input");
		
		int asset=0;
		try {
			asset = tool.readInt(input);		
		} 
		catch(InputMismatchException e) {
			printOf("WrongInput","Cancle");
			return;
		}
		catch(NotPositiveNumberExeption e) {
			printOf("WrongInput","Cancle");
			return;
		}
		
		if(stock.getType().equals("local")) localStockList.add(stock);
		if(stock.getType().equals("foreign")) foreignStockList.add(stock);
		stock.setAsset(asset);				
		
		input.nextLine();
		
		System.out.println("(선택사항) 이 주식에 대한 메모를 남기시겠습니까? (Y/N)");
		printOf("Input");
		String check = input.nextLine();
		if(check.toLowerCase().equals("y")) {
			printOf("Input");
			stock.setDescription(input.nextLine());
		}
		else if(check.toLowerCase().equals("n")) {
			stock.setDescription("(설정되지 않음)");
		}
		else {
			stock.setDescription("(설정되지 않음)");
			printOf("WrongInput","Cancle");
		}
		
		return;
	}
	
	// 3번 메뉴
	public void removeStock() throws IOException {
		if(tool.isEmpty()) return;
				
		printOf("InputCode");
		String code = input.nextLine();
		Stock stock = tool.getElementByCode(code);
		if(tool.isNull(stock)) return;
		
		while(true) {
			System.out.println("정말 이 주식을 목록에서 삭제하시겠습니까? (Y/N)");
			printOf("WrongInput","Cancle");
			String check = input.nextLine();
			if(check.toLowerCase().equals("y")) {
				if(stock.getType().equals("local")) localStockList.remove(stock);
				if(stock.getType().equals("foreign")) foreignStockList.remove(stock);
						
				System.out.println("주식이 목록에서 삭제되었습니다.");
				stock = null;
				break;
			}
			else if(check.toLowerCase().equals("n")){
				printOf("Cancle");
				break;
			}
			else {
				printOf("WrongInput");
			}
		}
				
		return;
	}
	
	// 4번 메뉴
	public void editStock() {
		if(tool.isEmpty()) return;
		printOf("InputCode");
		
		Stock stock = tool.getElementByCode(input.nextLine());
		if(tool.isNull(stock)) return;
		
		System.out.println("보유 주식의 수를 변경하시겠습니까? (Y/N)");
		printOf("WrongInput","Cancle");
		
		if(input.nextLine().toLowerCase().equals("y")) {
			System.out.println("변경된 보유 주식 수를 입력하세요.");
			printOf("Checked");
			
			int lastAsset = stock.getAsset();
			int currAsset = lastAsset;
			
			try {
				currAsset = tool.readInt(input);
			}
			catch(InputMismatchException e) {
				printOf("NotChanged");
			}
			catch(NotPositiveNumberExeption e) {
				printOf("NotChanged");
			}
			finally {
				stock.setAsset(currAsset);				
			}
			System.out.println(
					"변경되었습니다. : ("
					+lastAsset+"주 → "
					+stock.getAsset
					()+"주)\n"
					);
		}

		input.nextLine();
		
		System.out.println("해당 주식의 설명을 변경하시겠습니까? (Y/N)");
		printOf("Input");
		
		if(input.nextLine().toLowerCase().equals("y")) {
			System.out.println("주식의 설명을 입력하세요.");
			printOf("Input");
			
			String lastDesc = stock.getDescription();
			stock.setDescription(input.nextLine());
			System.out.println(
					"변경되었습니다. "
					+ "("+lastDesc+" → "
					+stock.getDescription()
					+")");
		}
		else {
			printOf("Cancle");
			return;
		}
		return;
	}
	
	// 5번 메뉴
	public String searchStock() throws IOException, NotPositiveNumberExeption {

		System.out.println("코드를 검색할 주식의 이름을 입력하세요.");
		printOf("Input");
		String name = tool.readKorean(input);
		if(name.isEmpty()) return input.nextLine();
		
		ArrayList<String> nameList = LocalStock.getListOfCode(name.toLowerCase());
		
		int count = 1;
		for(String str : nameList) {
			System.out.println(count + " : "+str);
			count ++;
		}
		
		int number = 0;
		System.out.println("코드를 검색할 주식명의 순번을 입력하세요.");
		printOf("Input");
		
		try {
			number = tool.readInt(input);			
		}
		catch (InputMismatchException e){
			printOf("WrongInput","Cancle");
		}
		finally {
			if(number > nameList.size()) {
				printOf("WrongInput","Cancle");
				return input.nextLine();
			}
		}
		
		String code = LocalStock.findCodeByName(nameList,number);
		System.out.println(nameList.get(number-1)+"의 주식 코드는 "+code+" 입니다.");
		
		return input.nextLine();
	}
	
	// 6번 메뉴
	public void statistic() throws IOException {
		if(tool.isEmpty()) return;
		for(int i=0; i<2; i++) {
			System.out.println("---------------<보유 "+tool.kind[i]+" 주식 현황>---------------");
			for(Stock stock : localStockList) {
				tool.getStatisticInfo_each(stock, tool.kind[i]);
			}
			tool.getStatisticInfo_total(i);
			System.out.println();
		}
		
		//달러 시세에 따른 주식 총액의 원/달러 환산액을 구하는 코드 삽입
	}
}
