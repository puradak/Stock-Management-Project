package file_management;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import data.Stock;
import functions.Printer;

public class LoadManager extends Printer {
	
	private LoadManager() {
		LoadStocks();
	}
	
	private static LoadManager loadManager = new LoadManager();
	public static LoadManager getLoadManagerObject() {
		return loadManager; 
	}
	private ArrayList<Stock> LocalStockList = new ArrayList<Stock>();
	private ArrayList<Stock> ForeignStockList = new ArrayList<Stock>();
	
	@SuppressWarnings("unchecked")
	private boolean LoadStocks() {
		try {
			System.out.println("����� �ֽ� ����Ʈ�� �ҷ��ɴϴ�.");
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("ObjectSave.txt"));
			this.LocalStockList = (ArrayList<Stock>)in.readObject();
			this.ForeignStockList = (ArrayList<Stock>)in.readObject();
			in.close();
		}catch ( IOException e ) {
			return false;
		}catch ( ClassNotFoundException e ) {
			return false;
		}
		return true;
	}
	
	public void cleanMenuLog() {
		try {
			FileOutputStream out = new FileOutputStream("MenuLog.txt");
			out.write("".getBytes());
			out.close();
		} catch (FileNotFoundException e) {
			printOf("���ܰ� �߻��Ͽ����ϴ�.","Cancle");
			return;
		} catch ( IOException e ) {
			printOf("���ܰ� �߻��Ͽ����ϴ�.","Cancle");
			return;
		}
	}
	
	public ArrayList<Stock> LoadList(String type){
		if(type.equals("local")) return this.LocalStockList;
		else return this.ForeignStockList;
	}
}
