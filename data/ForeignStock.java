package data;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ForeignStock extends Stock implements Functionalities{
	///////////////////////FIELDS/////////////////////////
	protected static String url_head ="https://www.google.com/finance/quote/";
	protected static String url_tail =":NASDAQ";
	protected String code;
	
	
	//////////////////CONSTRUCTOR/////////////////////////
	public ForeignStock(){
		super(null,null);
	}
	
	ForeignStock(String code) throws IOException {
		super(code,url_head+code+url_tail);
		this.code = code;	
		this.type = "foreign";
		initialize();
	}
	//////////////////////////////////////////////////////
	
	///////////////////METHODS////////////////////////////
		protected void initialize() throws IOException {
		try {
			String url = url_head+code+url_tail;
			Document doc = Jsoup.connect(url).get();
		
			this.isExist = true;
			this.name = doc.getElementsByAttributeValue("class", "zzDege").text();
			this.price_t = doc.getElementsByAttributeValue("class", "YMlKec fxKbKc").get(0).text().replace("$","");
			this.price_y = doc.getElementsByAttributeValue("class", "P6K39c").get(0).text().replace("$","");
			this.netChange = String.format("%.2f",(Double.parseDouble(Stock.getPureNumber(this.price_t)) / Double.parseDouble(Stock.getPureNumber(this.price_y)) - 1)*100);
			
			doc = null;
		} catch (IndexOutOfBoundsException e) {
			this.isExist = false;
			return;
		}
	}
	
	public void Fresh() throws IOException {
		Document doc = Jsoup.connect(url_head+this.code+url_tail).get();		
		this.price_t = doc.getElementsByAttributeValue("class", "YMlKec fxKbKc").get(0).text().replace("$","");
		this.netChange = String.format("%.2f",(Double.parseDouble(Stock.getPureNumber(this.price_t)) / Double.parseDouble(Stock.getPureNumber(this.price_y)) - 1)*100);
		doc = null;
	}
	
	public static ArrayList<String> getListOfCode(String name) throws IOException {
		// 준비중인 기능입니다.
		return null;
	}
	
}
	
