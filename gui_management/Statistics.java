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
import interfaces.BasicGUI;
import log_management.LoadManager;

public class Statistics extends JFrame implements BasicGUI{
	private static final long serialVersionUID = -5443809365075419678L;
	private LoadManager loader = LoadManager.getLoadManagerObject();
	private JFrame statFrame = new JFrame();
	private JPanel panel = new JPanel();
	private JScrollPane[] scPanes = { new JScrollPane(), new JScrollPane() };
	private JLabel[] labels = { new JLabel(), new JLabel() };
	private JLabel[] lb_labels = { new JLabel("국내주식"), new JLabel("국외주식") };
	private String[] currency = {" 원", " $"};
	private ArrayList<JList<String>> lists = new ArrayList<>();
	private ArrayList<DefaultListModel<String>> models = new ArrayList<>();
	public Statistics() {
		statFrame.setAlwaysOnTop(true);
		statFrame.getContentPane().setBackground(new Color(255, 255, 255));
		statFrame.getContentPane().setLayout(null);
		
		panel.setBackground(new Color(240, 248, 255));
		panel.setBounds(12, 211, 570, 80);
		panel.setLayout(null);
		statFrame.getContentPane().add(panel);
		
		for(int i=0; i<2; i++) {
			 labels[i].setBounds(12+285*i, 40+3*i, 261, 20);
			 lb_labels[i].setBounds(12+285*i, 10, 261, 20);
			 labels[i].setHorizontalAlignment(SwingConstants.CENTER);
			 lb_labels[i].setHorizontalAlignment(SwingConstants.CENTER);
			 panel.add(labels[i]);
			 panel.add(lb_labels[i]);
			 
			 double Wealth = 0;
			 models.add(new DefaultListModel<String>());
			 for(Stock stock : loader.getList(i)) {
				 models.get(i).addElement(stock.getName()+"("+stock.getAsset()+"주)"+" : "+stock.getPrice_t()+currency[i]);
				 Wealth += stock.getAsset() * Double.parseDouble(Stock.getPureNumber(stock.getPrice_t()));
			 }
			 labels[i].setText("총 보유 자산 : "+Wealth+currency[i]);
			 lists.add(new JList<String>(models.get(i)));
			 lists.get(i).setFocusable(false);
			 
			 scPanes[i].setBounds(12+287*i, 10, 275, 191);
			 scPanes[i].setViewportView(lists.get(i));
			 statFrame.getContentPane().add(scPanes[i]);
		}
	}
	
	public void printGUI() {
		statFrame.setResizable(false);
		statFrame.setTitle("Statistics");
		statFrame.setType(Type.UTILITY);
		statFrame.setBounds(100, 100, 605, 340);
		statFrame.setVisible(true);
	}
}
