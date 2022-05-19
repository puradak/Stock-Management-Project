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
	private static LoadManager loader = LoadManager.getLoadManagerObject(); 
	private SaveManager saver = new SaveManager();
	private ToolFunction tool = ToolFunction.getToolFunctionObject();
	private MenuFunction() {}
	// 주식 정보 리스트
	protected static ArrayList<Stock> localStockList = loader.LoadList("local");
	protected static ArrayList<Stock> foreignStockList = loader.LoadList("foreign");	
	
	// 스캐너 인스턴스
	Scanner input = new Scanner(System.in);
	
	// 객체 넘겨주기
	public static MenuFunction getFunctionObject() {
		return function;
	}
	
	public void cleanMenuLog() {
		loader.cleanMenuLog();
		return;
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
		}
		tool.pause(input);
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

		printOf("주식 명 : "+stock.getName()
		,"Checked" ,"보유중인 수량을 입력하세요.", "Input");
		
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
		
		printOf("(선택사항) 이 주식에 대한 메모를 남기시겠습니까? (Y/N)", "Input");
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
		
		printOf("delete?","input");
		int answer = 0;
		while(answer<1) {
			answer = tool.readInt(input,1,4);
			if(answer == -1) { 
				printOf("WrongInput","delete?","input");
				continue;
			}
			else if (answer == 3) break;
			else if(answer == 4) {
				printOf("Cancle");
				return;
			}
			else {
				input.nextLine();
				printOf("정말 삭제하시겠습니까? (Y/N)","input");
				if(input.nextLine().toLowerCase().equals("y")) {
					if(answer == 1) localStockList = new ArrayList<Stock>();
					else foreignStockList = new ArrayList<Stock>();
					System.out.println("삭제되었습니다.");
					return;
				}
				else {
					printOf("Cancle");
					return;
				}
			}
		}
		
		printOf("InputCode");
		String code = input.nextLine();
		Stock stock = tool.getElementByCode(code);
		if(tool.isNull(stock)) return;
		
		while(true) {
			printOf("정말 이 주식을 목록에서 삭제하시겠습니까? (Y/N)", "Input");
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
		
		if(input.nextLine().toLowerCase().equals("y")) {
			System.out.println();
			printOf("변경된 보유 주식 수를 입력하세요.", "Input");
			
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
		else System.out.println("변경하지 않습니다.");

		input.nextLine();
		
		printOf("해당 주식의 설명을 변경하시겠습니까? (Y/N)", "Input");
		
		if(input.nextLine().toLowerCase().equals("y")) {
			System.out.println();
			printOf("주식의 설명을 입력하세요.", "Input");
			
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
	public void searchStock() throws IOException, NotPositiveNumberExeption {
		printOf("코드를 검색할 주식의 이름을 입력하세요.", "Input");
		String name = tool.readKorean(input);
		if(name.isEmpty()) return;
		
		ArrayList<String> nameList = LocalStock.getListOfCode(name.toLowerCase());
		
		int count = 1;
		try {
			for(String str : nameList) {
				System.out.println(count + " : "+str);
				count ++;
			}
		}
		catch (NullPointerException e) {
			printOf("해당 단어를 포함하는 주식이 존재하지 않습니다.","Cancle");
			tool.pause(input);
			return;
		}
		int number = 0;
		printOf("코드를 검색할 주식명의 순번을 입력하세요.", "Input");
		
		try {
			number = tool.readInt(input);			
		}
		catch (InputMismatchException e){
			printOf("WrongInput","Cancle");
		}
		finally {
			if(number > nameList.size()) {
				printOf("WrongInput","Cancle");
				return;
			}
		}
		
		String code = LocalStock.findCodeByName(nameList,number);
		System.out.println(nameList.get(number-1)+"의 주식 코드는 "+code+" 입니다.");
		tool.pause(input);
		return;
	}
	
	// 6번 메뉴
	public void statistic() throws IOException {
		if(tool.isEmpty()) return;
		for(int i=0; i<2; i++) {
			System.out.println("---------------<보유 "+tool.kind[i]+" 주식 현황>---------------");
			for(Stock stock : tool.getList(i)) {
				tool.getStatisticInfo_each(stock, tool.kind[i]);
			}
			tool.getStatisticInfo_total(i);
			System.out.println();
		}
		tool.pause(input);
	}

	
	// 0번 메뉴
	public void saveStocks() {
		System.out.println("주식 정보를 저장합니다.");
		if(!saver.saveObject()) {
			printOf("저장에 실패하였습니다.","프로그램을 종료합니다.","Lines");
			System.exit(-1);
		}
		else printOf("저장에 성공하였습니다.", "프로그램을 종료합니다.");
		return;
	}
}