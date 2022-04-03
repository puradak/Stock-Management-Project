package Data;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Stock {
	
	private String name;
	private String code;
	private String price;
	private String netChange_sign;
	private String netChange_value;
	private String description;
	private int asset;
	private boolean isExist;
	
	public Stock(String code) throws IOException {
		this.code = code;
		this.isExist = true;

		String url = "https://finance.naver.com/item/main.nhn?code="+this.code;
		Document doc = Jsoup.connect(url).get();		
		if(doc.getElementsByTag("head").select("title").text().equals(": 네이버 금융")||doc.getElementsByTag("head").select("title").text().equals("네이버 :: 세상의 모든 지식, 네이버")) {
			this.isExist = false;
			return;
		}
		this.name = doc.getElementsByAttribute("href").select("a").get(17).text();
		this.price = doc.getElementsByAttributeValue("class", "no_today").select("span").get(0).text();
		this.netChange_sign = doc.getElementsByAttributeValue("class", "no_exday").select("span").get(1).text();
		this.netChange_value = doc.getElementsByAttributeValue("class", "no_exday").select("span").get(11).text();
	}

	public String getName() {
		return this.name;
	}
	
	public boolean getExist() {
		return this.isExist;
	}
	
	public String getCode() {
		return this.code;
	}

	public String getNetChange() {
		String str = "";
		str += this.netChange_sign+" ";
		str += this.netChange_sign.equals("하락")?"-":"+";
		str += this.netChange_value;
		
		return str;
	}
	
	public int getNetChangeValue() {
		return this.netChange_sign.equals("하락") ? (-1)*Integer.parseInt(this.netChange_value) : Integer.parseInt(this.netChange_value);
	}
	
	public void setDescription(String desc) {
		this.description = desc;
	}
	
	public String getDescription() {
		return this.description;
	}

	public String getPrice() {
		return this.price;
	}
	
	public int getAsset() {
		return this.asset;
	}
	
	public void setAsset(int asset) {
		this.asset = asset;
	}
	
	public void Fresh() throws IOException {
		String url = "https://finance.naver.com/item/main.nhn?code="+this.code;
		Document doc = Jsoup.connect(url).get();		
		this.price = doc.getElementsByAttributeValue("class", "no_today").select("span").get(0).text();
		this.netChange_sign = doc.getElementsByAttributeValue("class", "no_exday").select("span").get(1).text();
		this.netChange_value = doc.getElementsByAttributeValue("class", "no_exday").select("span").get(11).text();
	}
}