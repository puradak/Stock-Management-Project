package managements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import Data.LocalStock;
import Data.Stock;
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
	
	// 객체 넘겨주기
	public static MenuFunction getFunctionObject() {
		return function;
	}
	
	// 1번 메뉴
	public void show_all() throws IOException {
		if(tool.isEmpty()) return;
		
		System.out.println("================국내주식================");
		for(Stock stock : localStockList) {
			stock.Fresh();
			System.out.println("주식명　　: "+stock.getName()+"("+stock.getCode()+")");
			System.out.println("현재가　　: "+stock.getPrice_t()+"원");
			System.out.println("보유자산　　: "+tool.getTotalAsset(stock,"dot","today")+"원 ("+stock.getAsset()+"주)");
			System.out.println("전일대비 : "+stock.getNetChange()+"%");
			System.out.println("설명　　　: "+stock.getDescription());
			System.out.println("======================================");
		}
		
		if(!foreignStockList.isEmpty()) 
			System.out.println("================국외주식================");
		else {
			while(tool.isCorrectWith("m",input)) return;
		}
		
		for(Stock stock : foreignStockList) {
			stock.Fresh();
			System.out.println("주식명　　: "+stock.getName());
			System.out.println("현재가　　: "+stock.getPrice_t()+" USD");
			System.out.println("보유자산　　: "+tool.getTotalAsset(stock,"dot","today")+" USD ("+stock.getAsset()+"주)");
			System.out.println("전일대비 : "+stock.getNetChange()+"%");
			System.out.println("설명　　　: "+stock.getDescription());
			System.out.println("======================================");
		}
		printAskGoMain();
		while(input.nextLine().toLowerCase().equals("m")) return;
	}
	
	// 2번 메뉴
	public void addStock() throws IOException {
		
		printInputCode();
		String code = input.nextLine();
		if(tool.isExistStock(code)) {
			printWrongCode();
			
			
			return;
		}
		Stock stock = Stock.createStock(code);

		if(!stock.getExist()) {
			printWrongCode();
			
			
			stock = null;
			return;
		}
		if(stock.getType().equals("local")) localStockList.add(stock);
		if(stock.getType().equals("foreign")) foreignStockList.add(stock);
		
		printChecked();
		System.out.println("주식 명 : "+stock.getName());
		System.out.println();
		System.out.println("보유중인 수량을 입력하세요.");
		printInput();
		
		int asset=0;
		try {
			asset = tool.readInt(input);		
		} 
		catch(InputMismatchException e) {
			printWrongInput();
			printCancle();
			
			
			return;
		}
		catch(NotPositiveNumberExeption e) {
			printWrongInput();
			printCancle();
			
			
			return;
		}
		finally {
			stock.setAsset(asset);				
		}
		
		input.nextLine();
		
		System.out.println("(선택사항) 이 주식에 대한 메모를 남기시겠습니까? (Y/N)");
		printInput();
		String check = input.nextLine();
		if(check.toLowerCase().equals("y")) {
			printInput();
			stock.setDescription(input.nextLine());
		}
		else if(check.toLowerCase().equals("n")) {
			stock.setDescription("(설정되지 않음)");
		}
		else {
			stock.setDescription("(설정되지 않음)");
			printWrongInput();
			printCancle();
		}
		
		
		
		return;
	}
	
	// 3번 메뉴
	public void removeStock() throws IOException {
		if(tool.isEmpty()) return;
				
		printInputCode();
		String code = input.nextLine();
		Stock stock = tool.getElementByCode(code);
		if(tool.isNull(stock)) return;
		
		while(true) {
			System.out.print("정말 이 주식을 목록에서 삭제하시겠습니까? (Y/N)");
			printInput();
			String check = input.nextLine();
			if(check.toLowerCase().equals("y")) {
				if(stock.getType().equals("local")) localStockList.remove(stock);
				if(stock.getType().equals("foreign")) foreignStockList.remove(stock);
						
				System.out.println("주식이 목록에서 삭제되었습니다.");
				stock = null;
				break;
			}
			else if(check.toLowerCase().equals("n")){
				printCancle();
				break;
			}
			else {
				printWrongInput();
			}
		}
		
		
		return;
	}
	
	// 4번 메뉴
	public void editStock() {
		if(tool.isEmpty()) return;
		printInputCode();
		
		Stock stock = tool.getElementByCode(input.nextLine());
		if(tool.isNull(stock)) return;
		
		System.out.println("보유 주식의 수를 변경하시겠습니까? (Y/N)");
		printInput();
		
		if(input.nextLine().toLowerCase().equals("y")) {
			System.out.println("변경된 보유 주식 수를 입력하세요.");
			printInput();
			
			int lastAsset = stock.getAsset();
			int currAsset = lastAsset;
			
			try {
				currAsset = tool.readInt(input);
			}
			catch(InputMismatchException e) {
				printNotChanged();
			}
			catch(NotPositiveNumberExeption e) {
				printNotChanged();
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
		printInput();
		
		if(input.nextLine().toLowerCase().equals("y")) {
			System.out.println("주식의 설명을 입력하세요.");
			printInput();
			
			String lastDesc = stock.getDescription();
			stock.setDescription(input.nextLine());
			System.out.println(
					"변경되었습니다. "
					+ "("+lastDesc+" → "
					+stock.getDescription()
					+")");
		}
		else {
			printCancle();
			return;
		}
		return;
	}
	
	// 5번 메뉴
	public String searchStock() throws IOException {
		System.out.println("검색할 주식이 국내주식이면 L을, 국외주식이면 F를 입력해주세요.");
		printInput();
		String type = input.next().toLowerCase();
		if(type.equals("f")) {
			System.out.print("준비중인 기능입니다.");
			return input.nextLine();
		}
		else if (!type.equals("l")) {
			printWrongInput();
			return input.nextLine();
		}
		
		System.out.println("코드를 검색할 주식의 이름을 입력하세요.");
		printInput();
		String name = input.next();
		ArrayList<String> nameList = LocalStock.getListOfCode(name.toLowerCase());
		
		int count = 1;
		for(String str : nameList) {
			System.out.println(count + " : "+str);
			count ++;
		}
		
		int number = 0;
		System.out.println("코드를 검색할 주식명의 순번을 입력하세요.");
		printInput();
		
		try {
			number = tool.readInt(input);			
		}
		catch (InputMismatchException e){
			printWrongInput();
			printCancle();
		}
		catch (NotPositiveNumberExeption e) {
			printWrongInput();
			printCancle();
		}
		finally {
			if(number > nameList.size()) {
				printWrongInput();
				printCancle();
				return input.nextLine();
			}
		}
		
		String code = LocalStock.findCodeByName(nameList,number);
		System.out.println(nameList.get(number-1)+"의 주식 코드는 "+code+" 입니다.");
		
		return input.nextLine();
	}
	
	// 6번 메뉴
	public void statistic() throws IOException {
		if(tool.isEmpty()) {
			printEmpty();
			return;
		}
		
		System.out.println("---------------<보유 국내 주식 현황>---------------");
		for(Stock stock : localStockList) {
			stock.Fresh();
			System.out.println(
					stock.getName()
					+"("+stock.getCode()+") : "
					+tool.getTotalAsset(stock,"dot","today")+"원 ("
					+stock.getAsset()+" 주)"
					);
		}
		System.out.println(""
				+"보유 국내 주식 총액 : "
				+tool.seperateNumber(tool.getWealthOf("y","local"))
				+"원 → " + tool.seperateNumber(tool.getWealthOf("t","local"))
				+"원 (" + tool.getWealthOf("k","local") + "%)"
				);
		System.out.println();
		
		System.out.println("---------------<보유 국외 주식 현황>---------------");
		for(Stock stock : foreignStockList) {
			stock.Fresh();
			System.out.println(
					stock.getName()
					+"("+stock.getCode()+") : "
					+tool.getTotalAsset(stock,"dot","today")
					+" USD ("+stock.getAsset()+" 주)"
					);
		}
		System.out.println("보유 국외 주식 총액 : "
						+tool.seperateNumber(tool.getWealthOf("y","foreign"))
						+" USD → "+tool.seperateNumber(tool.getWealthOf("t","foreign"))
						+" USD ("+tool.getWealthOf("k","foreign")+"%)"
						);
		//달러 시세에 따른 주식 총액의 원/달러 환산액을 구하는 코드 삽입
	}
}
