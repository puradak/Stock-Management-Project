package log_management;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SaveManager{
	private static int count = 0;
	private String[] menu = {"0.Exit","1.Show_All", "2.Add Stock", "3.Delete Stock", "4.Edit Stock", "5.Search Stock", "6.Statistics", "7.About",/*8*/"Refresh News",/*9*/"Watch News - ",/*10*/"System On"};
	private LoadManager loader = LoadManager.getLoadManagerObject();
	private SimpleDateFormat type = new SimpleDateFormat("yy-MM-dd hh-mm-ss");
	public SaveManager() {
		cleanMenuLog();
	}
		
	private String getLogMessage( int code ) {
		count ++;
		return count+"번째 행동 : "+type.format(new Date())+"  "+menu[code]+"\n";
	}
	private String getLogMessage( int code , String title ) {
		count ++;
		return count+"번째 행동 : "+type.format(new Date())+"  "+menu[code]+title+"\n";
	}
	
	private void saveCheck() {
		try {
			FileOutputStream out = new FileOutputStream("check.txt");
			out.write("o".getBytes());
			out.close();
		} catch (IOException e) {
			return;
		}
	}
	
	public void saveLog( int code ) {
		try {
			FileOutputStream out= new FileOutputStream( "MenuLog.txt", true );
			out.write(getLogMessage(code).getBytes());
			out.close();
		} catch ( FileNotFoundException e ) {
			return;
		} catch ( IOException e ) {
			return;
		}
	}
	
	public void saveLog( int code, String title ) {
		try {
			FileOutputStream out= new FileOutputStream( "MenuLog.txt", true );
			out.write(getLogMessage(code, title).getBytes());
			out.close();
		} catch ( FileNotFoundException e ) {
			return;
		} catch ( IOException e ) {
			return;
		}
	}
	public boolean saveObject() {
		try {
			ObjectOutputStream out = new ObjectOutputStream( new FileOutputStream( "ObjectSave.txt" ) );
			for( int i = 0; i<2; i++ ) {
				out.writeObject(loader.getList(i));
			}
			out.close();
			saveCheck();
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	
	public void cleanMenuLog() {
		try {
			FileOutputStream out = new FileOutputStream("MenuLog.txt");
			out.write("".getBytes());
			out.close();
		} catch ( FileNotFoundException e ) {
			return;
		} catch ( IOException e ) {
			return;
		}
	}
}
