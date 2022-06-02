package data;

import gui_management.About;
import gui_management.AddStock;
import gui_management.EditStock;
import gui_management.FindTicker;
import gui_management.RemoveStock;
import gui_management.ShowStock;
import gui_management.Statistics;

public class GUIs {
	public void printGUI(int number) {
		switch(number) {
		case 1:
			new ShowStock().printGUI();
			break;
		case 2:
			new AddStock().printGUI();
			break;
		case 3:
			new RemoveStock().printGUI();
			break;
		case 4:
			new EditStock().printGUI();
			break;
		case 5:
			new FindTicker().printGUI();
			break;
		case 6:
			new Statistics().printGUI();
			break;
		case 7:
			new About().printGUI();
			break;
		default:
				break;
		}
	}
}
