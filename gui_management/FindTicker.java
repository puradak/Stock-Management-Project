package gui_management;

import java.awt.Color;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import data.LocalStock;
import interfaces.BasicGUI;

public class FindTicker extends JFrame implements BasicGUI{
	private static final long serialVersionUID = 7061175061874041996L;
	ArrayList<String> nameList;
	
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
		
		JLabel lb_ticker = new JLabel("코드 : ");
		lb_ticker.setBounds(12, 15, 47, 15);
		panel_1.add(lb_ticker);
		
		JTextField ticker = new JTextField();
		ticker.setEditable(false);
		ticker.setDragEnabled(true);
		ticker.setBorder(null);
		ticker.setText(" ");
		ticker.setBounds(59, 12, 116, 21);
		panel_1.add(ticker);
		ticker.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 270, 270, 91);
		findFrame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JButton find = new JButton("검색");
		find.setBounds(30, 50, 100, 32);
		panel_2.add(find);
		
		JButton get = new JButton("코드");
		get.setBounds(138, 50, 100, 32);
		panel_2.add(get);
		
		JLabel lb_narr = new JLabel("검색어를 입력하세요.");
		lb_narr.setHorizontalAlignment(SwingConstants.CENTER);
		lb_narr.setBounds(12, 10, 246, 15);
		panel_2.add(lb_narr);
		
		JLabel lb_input = new JLabel("입력 : ");
		lb_input.setBounds(45, 30, 39, 15);
		panel_2.add(lb_input);
		
		JTextField input = new JTextField();
		input.setText("(검색어 입력)");
		input.setBounds(85, 27, 116, 21);
		panel_2.add(input);
		input.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(12, 9, 270, 197);
		findFrame.getContentPane().add(scrollPane);
		
		DefaultListModel<String> stockModel = new DefaultListModel<>();
		try {
			if(input.getText().isEmpty()) return;			
		} catch (NullPointerException e) {}
				
		JList<String> stockList = new JList<>(stockModel);
		stockList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.add(stockList);
		scrollPane.setViewportView(stockList);
		find.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					nameList = LocalStock.getListOfCode(input.getText().toLowerCase());
					for(String str : nameList) {
						stockModel.addElement(str);
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (NullPointerException e1) {
					lb_narr.setText("관련된 주식이 존재하지 않습니다.");
					input.setText("");
				}
			}
			
		});
		
		get.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					ticker.setText(LocalStock.findCodeByName(stockList.getSelectedValue()));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		findFrame.setVisible(true);
	}

}
