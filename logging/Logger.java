package logging;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class Logger {
	private static int count = 0;
	private String[] menu = {"0.Exit","1.Show_All", "2.Add Stock", "3.Delete Stock", "4.Edit Stock", "5.Search Stock", "6.Statistics"};
	
	public String getLogMessage(int number) {
		Date date = new Date();
		count ++;
		return ""+count+"번째 행동 : "+menu[number]+date;
	}
	
	public void saveLog(int number) {
		try {
			FileOutputStream out= new FileOutputStream("log.txt");
			out.write(getLogMessage(number).getBytes());
			out.close();
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException occurs.");
			return;
		} catch (IOException e) {
			System.out.println("IOException occurs.");
			return;
		}
	}
	
	
}
