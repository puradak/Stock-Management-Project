package menu;

import gui_management.MainMenu;
import log_management.LoadManager;

public class MenuManagement { 	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		LoadManager loader = LoadManager.getLoadManagerObject();
		MainMenu starter = new MainMenu();
		starter.printGUI();
	}
}
