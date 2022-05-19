package managements;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;

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
			result = ""+count+"��° �ൿ : "+menu[Integer.parseInt(code)]+" "+date+"\n";
		} catch ( NumberFormatException e ) {
			result = ""+count+"��° �ൿ : ���� �޴����� "+code+"�Է�"+"\n";
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
	
	public void saveLog(String code, boolean isValid) {
		try {
			FileOutputStream out= new FileOutputStream("MenuLog.txt", true);
			if(isValid) out.write(getLogMessage(code).getBytes());
			else {
				count++;
				out.write((""+count+"��° �ൿ : "+"WrongInput : ShowMenu\n").getBytes());
			}
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
