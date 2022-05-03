package Data;

import java.io.IOException;

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
		String url = url_head+code+url_tail;
		Document doc = Jsoup.connect(url).get();
		if(doc.getElementsByAttributeValue("class", "b4EnYd").text().equals("검색어와 일치하는 결과를 찾을 수 없습니다.")) {
			this.isExist = false;
			return;
		}
		this.isExist = true;
		this.name = doc.getElementsByAttributeValue("class", "zzDege").text();
		this.price_t = doc.getElementsByAttributeValue("class", "YMlKec fxKbKc").get(0).text().replace("$","");
		this.price_y = doc.getElementsByAttributeValue("class", "P6K39c").get(0).text().replace("$","");
		this.netChange = String.format("%.2f",(Double.parseDouble(Stock.getPureNumber(this.price_t)) / Double.parseDouble(Stock.getPureNumber(this.price_y)) - 1)*100);
		
		doc = null;
	}
	
	public void Fresh() throws IOException {
		Document doc = Jsoup.connect(url_head+this.code+url_tail).get();		
		this.price_t = doc.getElementsByAttributeValue("class", "YMlKec fxKbKc").get(0).text().replace("$","");
		this.netChange = String.format("%.2f",(Double.parseDouble(Stock.getPureNumber(this.price_t)) / Double.parseDouble(Stock.getPureNumber(this.price_y)) - 1)*100);
		doc = null;
	}
	//////////////////////////////////////////////////////
	
	//////////////GETTER AND SETTER///////////////////////
	
	//////////////////////////////////////////////////////
	
}
	
