package menu;

import file_management.LoadManager;
import gui_management.MainMenu;

public class MenuManagement { 	
	public static void main(String[] args) {
		LoadManager loader = LoadManager.getLoadManagerObject();
		
		MainMenu starter = new MainMenu();
		starter.printGUI();
	}
}
