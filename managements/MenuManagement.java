package managements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class MenuManagement {
	
	static ArrayList<String[]> stockList = new ArrayList<String[]>();
	static Scanner input = new Scanner(System.in);
	public static void main(String[] args) throws IOException {
	
		int num = 6;
		while(num != 0) {
			display();
			num = input.nextInt();
			System.out.println();
			switch(num) {
			case 0:
				break;
			case 1:
				if(!showStock()) System.out.println("�߸��� �����ڵ��Դϴ�.");
				break;
			case 2:
				if(!addStock()) System.out.println("�߸��� �����ڵ��Դϴ�.");
				break;
			case 3:
				if(!removeStock()) System.out.println("�߸��� �����ڵ��Դϴ�.");
				break;
			case 4:
				if(!editStock()) System.out.println("�߸��� �����ڵ��Դϴ�.");
				break;
			case 5:
				if(!statistic()) System.out.println("������ ������ �����ϴ�.");
				break;
			default:
				System.out.println("�޴��� ���� ��ȣ�Դϴ�.");
				break;
			}
		}
		
		System.out.println("���α׷��� �����մϴ�.");
		input.close();
		return;
	}
	
	public static boolean statistic() {
		 
		input = new Scanner(System.in);
		if(stockList.size() == 0) return false;
		System.out.print("������ ���� ��� : ");
		int netAsset = 0;
		for(int i=0; i<stockList.size(); i++) {
			String[] stock = stockList.get(i);
			System.out.print(stock[1]+"("+ stock[0] +") ");
			updateStock(stock);
			netAsset += Integer.parseInt(stock[6]);
		}
		System.out.println();
		System.out.println("���� �ڻ� : "+netAsset+" ��");
		return true;
	}
	
	public static boolean editStock() {
		 
		input = new Scanner(System.in);
		System.out.print("���� �ڵ� �Է� : ");
		String ticker = input.nextLine();
		String[] stock = getElementByTicker(ticker);
		if(stock == null) return false;
		System.out.print("���� �Է� : ");
		String trade = input.nextLine();
		stock[5] = trade;
		return true;
	}
	public static boolean removeStock() {
		 
		input = new Scanner(System.in);
		System.out.print("���� �ڵ� �Է� : ");
		String ticker = input.nextLine();
		for(int i=0; i<stockList.size(); i++) {
			if(stockList.get(i)[0].equals(ticker)) {
				stockList.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public static boolean addStock() throws IOException {
		System.out.print("���� �ڵ� �Է� : ");
		 
		input = new Scanner(System.in);
		String ticker = input.nextLine();
		String url = "https://finance.naver.com/item/main.nhn?code="+ticker;
		String name,price="",sign,value,trade,asset,strPrice = "";
		Document doc = Jsoup.connect(url).get();
		if(doc.getElementsByTag("head").select("title").text().equals(": ���̹� ����")||doc.getElementsByTag("head").select("title").text().equals("���̹� :: ������ ��� ����, ���̹�")) {
			return false;
		}
		name = doc.getElementsByAttribute("href").select("a").get(17).text();
		price = doc.getElementsByAttributeValue("class", "no_today").select("span").get(0).text();
		sign = doc.getElementsByAttributeValue("class", "no_exday").select("span").get(1).text();
		value = doc.getElementsByAttributeValue("class", "no_exday").select("span").get(11).text();
		System.out.print("���� �Է� : ");
		trade = input.nextLine();
		
		for(int i=0; i<price.length(); i++) {
			if(price.charAt(i)==',') continue;
			strPrice+=price.charAt(i);
		}
		price = strPrice;
		asset = ""+Integer.parseInt(price)*Integer.parseInt(trade);
		
		String[] strArr = {ticker, name, price, sign, value, trade, asset};
		stockList.add(strArr);
		return true;
	}
	
	public static void display() {
		 
		input = new Scanner(System.in);
		System.out.println("1.Show Stock Table");
		System.out.println("2.Add Stock");
		System.out.println("3.Remove Stock");
		System.out.println("4.Edit Stock");
		System.out.println("5.Statistics");
		System.out.println("0.Exit the program");
		System.out.print("Select : ");
	}
	
	public static boolean showStock() {
		
		input = new Scanner(System.in);
		System.out.print("���� �ڵ� �Է� : ");
		String ticker = input.nextLine();
		String[] stock = getElementByTicker(ticker);
		updateStock(stock);
		if(stock == null) return false;
		System.out.println("=============================");
		System.out.println(stock[1]+" ("+ticker+")");
		System.out.println("���� : "+ stock[2]);
		System.out.println("���ϴ��"+ stock[3] +" : "+stock[4]+"%");
		System.out.println("���� : "+stock[5]+"��");
		System.out.println("�ڻ� : "+stock[6]+"��");
		System.out.println("=============================");
		return true;
	}
	
	public static void updateStock(String[] stock) {
		stock[6] = ""+Integer.parseInt(stock[2])*Integer.parseInt(stock[5]) ;
	}
	
	public static String[] getElementByTicker(String ticker) {
		for(int i=0;i<stockList.size();i++) {
			if(stockList.get(i)[0].equals(ticker)) return stockList.get(i);
		}
		return null;
	}
}
