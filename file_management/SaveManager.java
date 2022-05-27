package file_management;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import functions.Printer;
import functions.ToolFunction;

public class SaveManager extends Printer{
	private static int count = 0;
	private String[] menu = {"0.Exit","1.Show_All", "2.Add Stock", "3.Delete Stock", "4.Edit Stock", "5.Search Stock", "6.Statistics", "7.Open Window"};
	private ToolFunction tool = ToolFunction.getToolFunctionObject();
	private SimpleDateFormat type = new SimpleDateFormat("yy-MM-dd hh-mm-ss");
	public SaveManager() {}
		
	private String getLogMessage(String code) {
		String result = "";
		try {
			count ++;
			result = count+"��° �ൿ : "+type.format(new Date())+"  "+menu[Integer.parseInt(code)]+"\n";
		} catch ( NumberFormatException e ) {
			result = count+"��° �ൿ : "+type.format(new Date())+"  "+"���� �޴����� ������ �ƴ� �� �Է�"+"\n";
		} catch ( ArrayIndexOutOfBoundsException e ) {
			result = count+"��° �ൿ : "+type.format(new Date())+"  "+"���� �޴����� 0���� 6 �̿��� ���� �Է�"+"\n";
		}
		return result;
	}
	
	private void saveCheck() {
		try {
			FileOutputStream out = new FileOutputStream("check.txt");
			out.write("o".getBytes());
			out.close();
		} catch (IOException e) {
			printOf("���ܰ� �߻��Ͽ����ϴ�.","Cancle");
			return;
		}
	}
	
	public void saveLog(String code) {
		try {
			FileOutputStream out= new FileOutputStream("MenuLog.txt", true);
			out.write(getLogMessage(code).getBytes());
			out.close();
		} catch (FileNotFoundException e) {
			printOf("���ܰ� �߻��Ͽ����ϴ�.","Cancle");
			return;
		} catch (IOException e) {
			printOf("���ܰ� �߻��Ͽ����ϴ�.","Cancle");
			return;
		}
	}
	
	public boolean saveObject() {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("ObjectSave.txt"));
			for(int i = 0; i<2; i++) {
				out.writeObject(tool.getList(i));
			}
			out.close();
			saveCheck();
		} catch (IOException e) {
			printOf("���ܰ� �߻��Ͽ����ϴ�.","Cancle");
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
}