package Data;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class LocalStock extends Stock implements Functionalities{
	
	///////////////////////FIELDS/////////////////////////
	protected static String url_head ="https://finance.naver.com/item/main.nhn?code=";
	protected String code;
	//////////////////////////////////////////////////////
	
	//////////////////CONSTRUCTOR/////////////////////////
	public LocalStock() {
		super(null,null);
	}
	
	public LocalStock(String code) throws IOException {
		super(code, url_head+code);
		this.code = code;
		this.type = "local";
		initialize();
	}
	//////////////////////////////////////////////////////
	
	///////////////////METHODS////////////////////////////
	protected void initialize() throws IOException {
		String url = url_head + code;
		Document doc = Jsoup.connect(url).get();
		if(doc.getElementsByTag("head").select("title").text().equals(": ³×ÀÌ¹ö ±ÝÀ¶")) {
			this.isExist = false;
			return;
		}
		this.isExist = true;
		this.name = doc.getElementsByAttribute("href").select("a").get(17).text();
		this.price_t = doc.getElementsByAttributeValue("class", "no_today").select("span").get(0).text();
		this.price_y = doc.getElementsByAttributeValue("class", "first").get(0).select("span").get(1).text();
		this.netChange = String.format("%.2f",(Double.parseDouble(Stock.getPureNumber(this.price_t)) / Double.parseDouble(Stock.getPureNumber(this.price_y)) -1)*100);
		doc = null;
	}
	
	public void Fresh() throws IOException {
		Document doc = Jsoup.connect(url).get();		
		this.price_t = doc.getElementsByAttributeValue("class", "no_today").select("span").get(0).text();
		this.netChange = String.format("%.2f",(Double.parseDouble(Stock.getPureNumber(this.price_t)) / Double.parseDouble(Stock.getPureNumber(this.price_y)) -1)*100);
		doc = null;
	}
	
	public static String getListOfCode(String name) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}
	//////////////////////////////////////////////////////
	
	//////////////GETTER AND SETTER///////////////////////	
	
	//////////////////////////////////////////////////////
}