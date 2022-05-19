package managements;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import data.Stock;

public class LoadManager extends Printer {
	
	private boolean isArrayEmpty;
	
	private LoadManager() {
		if(!LoadStocks()) {
			try {
				byte[] code = new byte[2];
				FileInputStream in = new FileInputStream("check.txt");
				in.read(code);
				in.close();
				if(!code.equals("o".getBytes())) throw new FileNotFoundException();
			}catch ( FileNotFoundException e ) {
				this.isArrayEmpty = true;
			}catch ( IOException e ) {
				System.out.println("�� �� ���� ���ܰ� �߻��߽��ϴ�. ���α׷��� �����մϴ�.");
				System.exit(-1);
			}
			this.isArrayEmpty = false;
		}
	}
	
	private static LoadManager loadManager = new LoadManager();
	public static LoadManager getLoadManagerObject() {
		return loadManager; 
	}
	private ArrayList<Stock> LocalStockList = new ArrayList<Stock>();
	private ArrayList<Stock> ForeignStockList = new ArrayList<Stock>();
	
	@SuppressWarnings("unchecked")
	private boolean LoadStocks() {
		if(this.isArrayEmpty) return true;
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("ObjectSave.txt"));
			this.LocalStockList = (ArrayList<Stock>)in.readObject();
			this.ForeignStockList = (ArrayList<Stock>)in.readObject();
			in.close();
		}catch ( IOException e ) {
			System.out.println("���ܰ� �߻��Ͽ����ϴ�. : LoadStocks IOException");
			System.out.println(e.getStackTrace());
			return false;
		}catch ( ClassNotFoundException e ) {
			System.out.println("���ܰ� �߻��Ͽ����ϴ�. : LoadStocks ClassNotFoundException");
			e.getStackTrace();
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
