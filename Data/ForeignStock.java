package Data;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ForeignStock extends Stock{
	///////////////////////FIELDS/////////////////////////
	protected static String url_head ="https://finance.yahoo.com/quote/";
	protected String code;
	
	
	//////////////////CONSTRUCTOR/////////////////////////
	public ForeignStock(){
		super(null,null);
	}
	
	ForeignStock(String code) throws IOException {
		super(code,url_head+code);
		this.code = code;	
		this.type = "foreign";
		initialize();
	}
	//////////////////////////////////////////////////////
	
	///////////////////METHODS////////////////////////////
		private void initialize() throws IOException {
		String url = url_head+code;
		Document doc = Jsoup.connect(url).get();
		if(doc.getElementsByAttributeValue("class", "Fw(b) Fz(36px) Mb(-4px) D(ib)").text().equals("")) {
			this.isExist = false;
			return;
		}
		this.isExist = true;
		this.name = doc.getElementsByAttributeValue("class", "D(ib) Fz(18px)").text();
		this.price_t = doc.getElementsByAttributeValue("class", "Fw(b) Fz(36px) Mb(-4px) D(ib)").text();
		this.price_y = doc.getElementsByAttributeValue("class", "Ta(end) Fw(600) Lh(14px)").get(0).text();
		this.netChange = String.format("%.2f",(Double.parseDouble(Stock.getPureNumber(this.price_t)) / Double.parseDouble(Stock.getPureNumber(this.price_y)) - 1)*100);
		
		doc = null;
	}
	
	public void Fresh() throws IOException {
		Document doc = Jsoup.connect(url_head+this.code).get();		
		this.price_t = doc.getElementsByAttributeValue("class", "Fw(b) Fz(36px) Mb(-4px) D(ib)").text();
		this.netChange = String.format("%.2f",(Double.parseDouble(Stock.getPureNumber(this.price_t)) / Double.parseDouble(Stock.getPureNumber(this.price_y)) - 1)*100);
		doc = null;
	}
	//////////////////////////////////////////////////////
	
	//////////////GETTER AND SETTER///////////////////////
	
	//////////////////////////////////////////////////////
	
}
	
