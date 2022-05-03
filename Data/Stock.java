package Data;

import java.io.IOException;

public abstract class Stock {
	
	///////////////////////FIELDS/////////////////////////
	protected String name;
	protected String price_t;
	protected String price_y;
	protected String netChange;
	protected String type;
	protected String url;
	protected boolean isExist;
	//여기까지 자식 클래스에서 초기화 하는 필드
	protected String description;
	protected int asset;
	//여기까지 외부에서 초기화하는 필드
	private String code;
	
	//////////////////CONSTRUCTOR/////////////////////////
	Stock(String code, String url){
		this.code = code;
		this.url = url;
	}
	//////////////////////////////////////////////////////
	
	///////////////////METHODS////////////////////////////
	protected abstract void initialize() throws IOException;
	public abstract void Fresh() throws IOException;
	
	public static Stock createStock(String code) throws IOException {
		if(code.charAt(0) == '0') return new LocalStock(code);
		else return new ForeignStock(code);
	}
	
	public static String getPureNumber(String number) {
		String temp = "";
		String fraction = "";
		String result = "";
		int count = 0;
		
		for(int i=0; i<number.length(); i++) {
			if(number.charAt(i)==',') continue;
			else temp += number.charAt(i);
		}
		
		for(int i=0; i<temp.length(); i++) {
			if(i>count) {
				fraction += temp.charAt(i);
				continue;
			}
			if(number.charAt(i)=='.') count = i;
			else result += number.charAt(i);
		}

		return result+fraction;
	}
	
	//////////////////////////////////////////////////////
	
	//////////////GETTER AND SETTER///////////////////////
	public String getNetChange() {
		return this.netChange;
	}
	
	public String getName() {
		return this.name;
	}
	
	public boolean getExist() {
		return this.isExist;
	}
	
	public void setDescription(String desc) {
		this.description = desc;
	}
	
	public String getDescription() {
		return this.description;
	}

	public String getPrice_t() {
		return this.price_t;
	}
	
	public String getPrice_y() {
		return this.price_y;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public int getAsset() {
		return this.asset;
	}
	
	public void setAsset(int asset) {
		this.asset = asset;
	}
	
	public String getType() {
		return this.type;
	}
	//////////////////////////////////////////////////////
}