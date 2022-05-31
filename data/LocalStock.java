package data;
import java.io.IOException;
import java.io.Serializable;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import interfaces.Functionalities;

public class LocalStock extends Stock implements Functionalities, Serializable{
	private static final long serialVersionUID = -510433286210521767L;
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
		try {
			String url = url_head + code;
			Document doc = Jsoup.connect(url).get();

			this.isExist = true;
			this.name = doc.getElementsByAttribute("href").select("a").get(17).text();
			this.price_t = doc.getElementsByAttributeValue("class", "no_today").select("span").get(0).text();
			this.price_y = doc.getElementsByAttributeValue("class", "first").get(0).select("span").get(1).text();
			this.netChange = String.format("%.2f",(Double.parseDouble(Stock.getPureNumber(this.price_t)) / Double.parseDouble(Stock.getPureNumber(this.price_y)) -1)*100);
			doc = null;
		} catch (IndexOutOfBoundsException e) {
			this.isExist = false;
			return;
		}
	}
	
	public void Fresh() throws IOException {
		Document doc = Jsoup.connect(url).get();		
		this.price_t = doc.getElementsByAttributeValue("class", "no_today").select("span").get(0).text();
		this.netChange = String.format("%.2f",(Double.parseDouble(Stock.getPureNumber(this.price_t)) / Double.parseDouble(Stock.getPureNumber(this.price_y)) -1)*100);
		doc = null;
	}
	
	public static ArrayList<String> getListOfCode(String name) throws IOException {
		ArrayList<String> saveResult = new ArrayList<String>();
		
		for(int i=1; ;i++) {
			Document doc = Jsoup.connect("https://finance.naver.com/search/searchList.naver?query="+URLEncoder.encode(name, "EUC-KR")+"&page="+i).get();
			if(doc.getElementsByAttributeValue("class", "result_area").text().equals("‘"+name+"’검색결과 (총0건)")){
				break;
			}
			
			Elements e = doc.getElementsByAttributeValue("class", "tit");
			for(String str : e.eachText()) {
				saveResult.add(str);
			}
		}
		if (saveResult.isEmpty()) return null;
		return saveResult;
	}
	
	public static String findCodeByName(String name) throws IOException {
		String exactName = name;
		
		Document doc = Jsoup.connect("https://search.naver.com/search.naver?&query="+exactName).get();
		Elements e = doc.getElementsByAttributeValue("class", "t_nm");
		String result = e.text();
		return result;
	}
}