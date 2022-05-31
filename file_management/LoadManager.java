package file_management;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import data.Stock;

public class LoadManager {
	
	private LoadManager() {
		LoadStocks();
	}
	
	private static LoadManager loadManager = new LoadManager();
	public static LoadManager getLoadManagerObject() {
		return loadManager; 
	}
	private ArrayList<Stock> LocalStockList = new ArrayList<Stock>();
	private ArrayList<Stock> ForeignStockList = new ArrayList<Stock>();
	
	public ArrayList<Stock> getList(int number){
		if(number == 0) return LocalStockList;
		else return ForeignStockList;
	}
	
	@SuppressWarnings("unchecked")
	private boolean LoadStocks() {
		try {
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
			return;
		} catch ( IOException e ) {
			return;
		}
	}
	
	public ArrayList<Stock> LoadList(String type){
		if(type.equals("local")) return this.LocalStockList;
		else return this.ForeignStockList;
	}
}
