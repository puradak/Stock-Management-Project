package managements;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import data.Stock;

public class LoadManager {
	private LoadManager() {
		if(!LoadStocks()) {
			try {
				byte[] code = new byte[2];
				FileInputStream in = new FileInputStream("check.txt");
				String check = in.read(c);
			}
			System.out.println("�� �� ���� ���ܰ� �߻��߽��ϴ�. ���α׷��� �����մϴ�.");
			System.exit(-1);
		}
	}
	
	private static LoadManager loadManager = new LoadManager();
	
	public static LoadManager getLoadManagerObject() {
		return loadManager; 
	}
	ArrayList<Stock> LocalStockList = new ArrayList<Stock>();
	ArrayList<Stock> ForeignStockList = new ArrayList<Stock>();
	
	@SuppressWarnings("unchecked")
	private boolean LoadStocks() {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("ObjectSave.ser"));
			this.LocalStockList = (ArrayList<Stock>)in.readObject();
			this.ForeignStockList = (ArrayList<Stock>)in.readObject();
			in.close();
		}catch ( IOException e ) {
			System.out.println("���ܰ� �߻��Ͽ����ϴ�.");
			return false;
		}catch ( ClassNotFoundException e ) {
			System.out.println("���ܰ� �߻��Ͽ����ϴ�.");
			return false;
		}
		return true;
	}
	
	public ArrayList<Stock> LoadList(String type){
		if(type.equals("local")) return this.LocalStockList;
		else return this.ForeignStockList;
	}
}
