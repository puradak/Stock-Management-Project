package managements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import Data.LocalStock;
import Data.Stock;
import exceptions.NotPositiveNumberExeption;

public class MenuFunction {
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
		if(localStockList.isEmpty() && foreignStockList.isEmpty()) {
			System.out.println("목록이 비었습니다. 주식을 추가하십시오.");
			return;
		}
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
		System.out.println("================국외주식================");
		for(Stock stock : foreignStockList) {
			stock.Fresh();
			System.out.println("주식명　　: "+stock.getName());
			System.out.println("현재가　　: "+stock.getPrice_t()+" USD");
			System.out.println("보유자산　　: "+tool.getTotalAsset(stock,"dot","today")+" USD ("+stock.getAsset()+"주)");
			System.out.println("전일대비 : "+stock.getNetChange()+"%");
			System.out.println("설명　　　: "+stock.getDescription());
			System.out.println("======================================");
		}
		return;
	}
	
	// 2번 메뉴
	public void addStock() throws IOException {
		System.out.println("---------------------------");
		
		System.out.print("주식 코드를 입력하세요. : ");
		String code = input.nextLine();
		if(tool.isExistStock(code)) {
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
		
		int asset = 1;

		try {
			asset = tool.readInt(input);		
		} 
		catch(InputMismatchException e) {
			System.out.println("잘못 입력하셨습니다. 1주로 설정되었습니다.");
			System.out.println("Edit Stock 메뉴에서 수정 가능합니다.");
		}
		catch(NotPositiveNumberExeption e) {
			System.out.println("잘못 입력하셨습니다. 1주로 설정되었습니다.");
			System.out.println("Edit Stock 메뉴에서 수정 가능합니다.");
		}
		finally {
			stock.setAsset(asset);				
		}
		
		input.nextLine();
		
		System.out.print("(선택사항) 이 주식에 대한 메모를 남기시겠습니까? (Y/N) : ");
		String check = input.nextLine();
		if(check.toLowerCase().equals("y")) {
			System.out.print("입력 : ");
			stock.setDescription(input.nextLine());
		}
		else if(check.toLowerCase().equals("n")) {
			stock.setDescription("(설정되지 않음)");
		}
		else {
			stock.setDescription("(설정되지 않음)");
			System.out.println("잘못 입력하셨습니다. 메모는 Edit Stock메뉴에서 수정이 가능합니다.");
			System.out.println("메인 메뉴로 돌아갑니다.");
		}
		
		System.out.println("---------------------------");
		
		return;
	}
	
	// 3번 메뉴
	public void removeStock() throws IOException {
		System.out.println("---------------------------");
		System.out.print("주식 코드를 입력하세요. : ");
		String code = input.nextLine();
		Stock stock = tool.getElementByCode(code);
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
	
	// 4번 메뉴
	public void editStock() {

		System.out.print("수정할 주식의 주식코드를 입력하세요. : ");
		Stock stock = tool.getElementByCode(input.nextLine());
		if(stock == null){
			System.out.println("추가하지 않았거나 존재하지 않는 주식 코드입니다.");
			System.out.println("메인 메뉴로 돌아갑니다.");
			return;
		}
		
		System.out.print("보유 주식의 수를 변경하시겠습니까? (Y/N) : ");
		if(input.nextLine().toLowerCase().equals("y")) {
			System.out.print("보유 주식 수를 새로 입력하세요. : ");
			int lastAsset = stock.getAsset();
			
			int currAsset = lastAsset;
			try {
				currAsset = tool.readInt(input);
			}
			catch(InputMismatchException e) {
				System.out.println("잘못 입력하셨습니다. 변경되지 않았습니다.");
			}
			catch(NotPositiveNumberExeption e) {
				System.out.println("잘못 입력하셨습니다. 변경되지 않았습니다.");
			}
			finally {
				stock.setAsset(currAsset);				
			}
			
			System.out.println("작업 내용 : ("+lastAsset+"주 → "+stock.getAsset()+"주)\n");
		}

		input.nextLine();
		
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
			System.out.println("메인 메뉴로 돌아갑니다.");
			return;
		}
		
		return;
	}
	
	// 5번 메뉴
	public String searchStock() throws IOException {
		System.out.print("검색할 주식이 국내주식이면 L을, 국외주식이면 F를 입력해주세요 : ");
		String type = input.next().toLowerCase();
		if(type.equals("f")) {
			System.out.println("준비중인 기능입니다. 메인 메뉴로 돌아갑니다.");
			return input.nextLine();
		}
		else if (!type.equals("l")) {
			System.out.println("잘못 입력하셨습니다. 메인 메뉴로 돌아갑니다.");
			return input.nextLine();
		}
		
		System.out.print("코드를 검색할 주식의 이름을 입력하세요 : ");
		String name = input.next();
		ArrayList<String> nameList = LocalStock.getListOfCode(name.toLowerCase());
		
		int count = 1;
		for(String str : nameList) {
			System.out.println(count + " : "+str);
			count ++;
		}
		int number = 0;
		System.out.print("코드를 검색할 주식명의 순번을 입력하세요 : ");
		
		try {
			number = tool.readInt(input);			
		}
		catch (InputMismatchException e){
			System.out.println("잘못 입력하셨습니다.");
			System.out.println("메인 메뉴로 돌아갑니다.");
			return input.nextLine();
		}
		catch (NotPositiveNumberExeption e) {
			System.out.println("잘못 입력하셨습니다.");
			System.out.println("메인 메뉴로 돌아갑니다.");
			return input.nextLine();
		}
		finally {
			if(number > nameList.size()) {
				System.out.println("잘못 입력하셨습니다.");
				System.out.println("메인 메뉴로 돌아갑니다.");
				return input.nextLine();
			}
		}
		
		
		String code = LocalStock.findCodeByName(nameList,number);
		System.out.println(nameList.get(number-1)+"의 주식 코드는 "+code+" 입니다.");
		System.out.println("메인 메뉴로 돌아갑니다.");
		return input.nextLine();
	}
	
	// 6번 메뉴
	public void statistic() throws IOException {
		
		if(localStockList.isEmpty() && foreignStockList.isEmpty()) {
			System.out.println("등록된 주식이 없습니다.");
			System.out.println("작업을 취소합니다.");
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
		System.out.println("보유 국외 주식 총액 : "+tool.seperateNumber(tool.getWealthOf("y","foreign"))+" USD → "+tool.seperateNumber(tool.getWealthOf("t","foreign"))+" USD ("+tool.getWealthOf("k","foreign")+"%)");
		//달러 시세에 따른 주식 총액의 원/달러 환산액을 구하는 코드 삽입
	}
}
