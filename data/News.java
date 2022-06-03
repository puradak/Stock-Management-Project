package data;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class News {
	private String title;
	private String[] news;
	public News(int number){
		String baseURL = "https://finance.naver.com/";
		
		try {
			Document doc = Jsoup.connect(baseURL).get();
			Elements newsSection = doc.getElementsByAttributeValue("class", "h_strategy");
			String url = "https://finance.naver.com"+newsSection.next().select("a").get(number).attr("href");
			this.title = newsSection.next().select("a").get(number).text();
			Document newsPage = Jsoup.connect(url).get();
			Elements newsBody = newsPage.getElementsByAttributeValue("class", "articleCont");
			String author = newsBody.next().text();
			String[] temp;
			temp = newsBody.text().trim().split("\\. ");
			this.news = new String[temp.length+1];
			for(int i=0; i<temp.length; i++) {
				if(i == temp.length-1) break;
				this.news[i] = temp[i]+"."+"\n\n";
				this.news[i].replace("\n", System.getProperty("line.separator"));
			}

			this.news[temp.length-1] = author + System.getProperty("line.separator");
			this.news[temp.length] = ""
					+ ""
					+ "뉴스 출처 : "+url;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String getTitle() {
		return this.title;
	}
	public String[] getNews() {
		return this.news;
	}
}