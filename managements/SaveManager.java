package managements;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;

import data.Stock;

public class SaveManager extends Printer{
	private static int count = 0;
	private String[] menu = {"0.Exit","1.Show_All", "2.Add Stock", "3.Delete Stock", "4.Edit Stock", "5.Search Stock", "6.Statistics"};
	private ToolFunction tool = ToolFunction.getToolFunctionObject();
	
	public SaveManager() {}
	
	private String getLogMessage(String code) {
		Date date = new Date();
		String result = "";
		try {
			count ++;
			result = ""+count+"번째 행동 : "+menu[Integer.parseInt(code)]+date;
		} catch ( InputMismatchException e) {
			result = ""+count+"번째 행동 : 메인 메뉴에서 "+code+"입력";
		}
		return result;
	}
	
	public void saveLog(String code, boolean isValid) {
		try {
			FileOutputStream out= new FileOutputStream("MenuLog.txt", true);
			if(isValid) out.write(getLogMessage(code).getBytes());
			else out.write("WrongInput : ShowMenu".getBytes());
			out.close();
		} catch (FileNotFoundException e) {
			printOf("예외가 발생하였습니다.","Cancle");
			return;
		} catch (IOException e) {
			printOf("예외가 발생하였습니다.","Cancle");
			return;
		}
	}
	
	public void saveObject() {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("ObjectSave.ser"));
			for(int i = 0; i<2; i++) {
				out.writeObject(tool.getList(i));
			}
			out.close();
		} catch (IOException e) {
			printOf("예외가 발생하였습니다.","Cancle");
			return;
		}
		
	}
	
	public void FreshMenuLog() {
		try {
			FileOutputStream out = new FileOutputStream("MenuLog.txt");
			out.write("".getBytes());
			out.close();
		} catch (FileNotFoundException e) {
			printOf("예외가 발생하였습니다.","Cancle");
			return;
		} catch ( IOException e ) {
			printOf("예외가 발생하였습니다.","Cancle");
			return;
		}
	}
	public void FreshObjectSave() {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("ObjectSave.ser"));
			out.writeObject(null);
			out.close();
			
		} catch (IOException e) {
			printOf("예외가 발생하였습니다.","Cancle");
			return;
		}
	}
	
}
