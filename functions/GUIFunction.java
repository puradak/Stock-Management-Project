package functions;

import java.io.IOException;
import java.util.ArrayList;
import data.Stock;
import file_management.LoadManager;
import gui_management.AddStock;

public class GUIFunction {
	
	ToolFunction tool = ToolFunction.getToolFunctionObject();
	LoadManager loader = LoadManager.getLoadManagerObject();
	AddStock frame = new AddStock();
	ArrayList<Stock> localStockList = loader.LoadList("local");
	ArrayList<Stock> foreignStockList = loader.LoadList("foreign");
	
	
	
}
