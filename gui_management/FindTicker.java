package gui_management;

import java.awt.Color;
import java.awt.Window.Type;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import interfaces.BasicGUI;

public class FindTicker extends JFrame implements BasicGUI{
	private static final long serialVersionUID = 7061175061874041996L;

	public void printGUI() {
		JFrame findFrame = new JFrame();
		findFrame.setAlwaysOnTop(true);
		findFrame.setTitle("Find Ticker");
		findFrame.setType(Type.UTILITY);
		findFrame.setBounds(100, 100, 310, 412);
		findFrame.setResizable(false);
		
		findFrame.getContentPane().setBackground(new Color(255, 255, 255));
		findFrame.getContentPane().setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 216, 270, 44);
		findFrame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lb_ticker = new JLabel("Ticker : ");
		lb_ticker.setBounds(12, 15, 47, 15);
		panel_1.add(lb_ticker);
		
		JTextField ticker = new JTextField();
		ticker.setEditable(false);
		ticker.setDragEnabled(true);
		ticker.setBorder(null);
		ticker.setText("123");
		ticker.setBounds(59, 12, 116, 21);
		panel_1.add(ticker);
		ticker.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 270, 270, 91);
		findFrame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JButton find = new JButton("FInd");
		find.setBounds(85, 50, 100, 32);
		panel_2.add(find);
		
		JLabel lb_narr = new JLabel("Type the stock name");
		lb_narr.setHorizontalAlignment(SwingConstants.CENTER);
		lb_narr.setBounds(12, 10, 246, 15);
		panel_2.add(lb_narr);
		
		JLabel lb_input = new JLabel("input : ");
		lb_input.setBounds(45, 30, 39, 15);
		panel_2.add(lb_input);
		
		JTextField input = new JTextField();
		input.setText("123");
		input.setBounds(85, 27, 116, 21);
		panel_2.add(input);
		input.setColumns(10);
		
		findFrame.setVisible(true);
	}

}
