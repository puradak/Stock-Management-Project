package gui_management;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import data.Stock;
import file_management.LoadManager;
import interfaces.BasicGUI;

public class Statistics extends JFrame implements BasicGUI{
	private static final long serialVersionUID = -5443809365075419678L;
	LoadManager loader = LoadManager.getLoadManagerObject();
	public void printGUI() {
		JFrame statFrame = new JFrame();
		statFrame.setAlwaysOnTop(true);
		statFrame.setBackground(new Color(255, 255, 255));
		statFrame.getContentPane().setBackground(new Color(255, 255, 255));
		statFrame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 248, 255));
		panel.setBounds(12, 211, 570, 80);
		statFrame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lb_local = new JLabel("국내주식");
		lb_local.setHorizontalAlignment(SwingConstants.CENTER);
		lb_local.setBounds(12, 10, 261, 20);
		panel.add(lb_local);
		
		JLabel local = new JLabel("New label");
		local.setBounds(12, 40, 261, 20);
		panel.add(local);
		
		JLabel lb_foreign = new JLabel("국외주식");
		lb_foreign.setHorizontalAlignment(SwingConstants.CENTER);
		lb_foreign.setBounds(297, 10, 261, 20);
		panel.add(lb_foreign);
		
		JLabel foreign = new JLabel("New label");
		foreign.setBounds(297, 43, 261, 20);
		panel.add(foreign);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.setBounds(12, 10, 275, 191);
		statFrame.getContentPane().add(scrollPane);
		
		ArrayList<Stock> localStocks = loader.getList(0);
		DefaultListModel<String> localModel = new DefaultListModel<>();
		double localWealth = 0;
		for(Stock stock : localStocks) {
			localModel.addElement(stock.getName()+"("+stock.getAsset()+"주)"+" : "+stock.getPrice_t()+" (원)");
			localWealth += stock.getAsset() * Double.parseDouble(Stock.getPureNumber(stock.getPrice_t()));
		}
		local.setText("총 보유 자산 : "+localWealth+" 원");
		JList<String> localList = new JList<>(localModel);
		localList.setFocusable(false);
		scrollPane.setViewportView(localList);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(299, 10, 275, 191);
		statFrame.getContentPane().add(scrollPane_1);
		
		ArrayList<Stock> foreignStocks = loader.getList(1);
		DefaultListModel<String> foreignModel = new DefaultListModel<>();
		double foreignWealth = 0;
		for(Stock stock : foreignStocks) {
			foreignModel.addElement(stock.getName()+"("+stock.getAsset()+"주)"+" : "+stock.getPrice_t()+" ($)");
			foreignWealth += stock.getAsset() * Double.parseDouble(Stock.getPureNumber(stock.getPrice_t()));
		}
		foreign.setText("총 보유 자산 : "+foreignWealth+" $");
		JList<String> foreignList = new JList<>(foreignModel);
		scrollPane_1.setViewportView(foreignList);
		
		
		statFrame.setResizable(false);
		statFrame.setTitle("Statistics");
		statFrame.setType(Type.UTILITY);
		statFrame.setBounds(100, 100, 605, 340);
		statFrame.setVisible(true);
	}

}
