package gui_management;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import interfaces.BasicGUI;

public class MainMenu extends JFrame implements BasicGUI{
	private static final long serialVersionUID = -7295373837441585468L;
	
	public void printGUI() {
		JFrame frame = new JFrame("Main Menu");
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setSize(new Dimension(650, 550));
		frame.getContentPane().setSize(new Dimension(650, 550));
		frame.getContentPane().setLayout(null);
		
		JLabel greeting = new JLabel("Stock-Management-System");
		greeting.setBounds(0, 0, 671, 53);
		greeting.setHorizontalAlignment(SwingConstants.CENTER);
		greeting.setFont(new Font("±¼¸²", Font.PLAIN, 45));
		frame.getContentPane().add(greeting);
		
		JPanel p_news = new JPanel();
		p_news.setBounds(12, 63, 647, 213);
		frame.getContentPane().add(p_news);
		p_news.setLayout(null);
		
		JLabel lb_nh1 = new JLabel("news head 1");
		lb_nh1.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		lb_nh1.setBounds(12, 10, 117, 31);
		p_news.add(lb_nh1);
		
		JLabel lb_nb1 = new JLabel("news body 1");
		lb_nb1.setBorder(null);
		lb_nb1.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		lb_nb1.setBounds(133, 10, 502, 31);
		p_news.add(lb_nb1);
		
		JLabel lb_nh2 = new JLabel("news head 2");
		lb_nh2.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		lb_nh2.setBounds(12, 51, 117, 31);
		p_news.add(lb_nh2);
		
		JLabel lb_nb2 = new JLabel("news body 2");
		lb_nb2.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		lb_nb2.setBorder(null);
		lb_nb2.setBounds(133, 51, 502, 31);
		p_news.add(lb_nb2);
		
		JLabel lb_nh3 = new JLabel("news head 3");
		lb_nh3.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		lb_nh3.setBounds(12, 92, 117, 31);
		p_news.add(lb_nh3);
		
		JLabel lb_nb3 = new JLabel("news body 3");
		lb_nb3.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		lb_nb3.setBorder(null);
		lb_nb3.setBounds(133, 92, 502, 31);
		p_news.add(lb_nb3);
		
		JLabel lb_nh4 = new JLabel("news head 4");
		lb_nh4.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		lb_nh4.setBounds(12, 133, 117, 31);
		p_news.add(lb_nh4);
		
		JLabel lb_nb4 = new JLabel("news body 4");
		lb_nb4.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		lb_nb4.setBorder(null);
		lb_nb4.setBounds(133, 133, 502, 31);
		p_news.add(lb_nb4);
		
		JLabel lb_nh5 = new JLabel("news head 5");
		lb_nh5.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		lb_nh5.setBounds(12, 174, 117, 31);
		p_news.add(lb_nh5);
		
		JLabel lb_nb5 = new JLabel("news body 5");
		lb_nb5.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		lb_nb5.setBorder(null);
		lb_nb5.setBounds(133, 174, 502, 31);
		p_news.add(lb_nb5);
		
		JPanel p_button = new JPanel();
		p_button.setBounds(12, 311, 647, 154);
		frame.getContentPane().add(p_button);
		p_button.setLayout(null);
		
		JButton btn2 = new JButton("Add Stocks");
		btn2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn2.setBounds(12, 10, 145, 55);
		p_button.add(btn2);
		btn2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				new AddStock().printGUI();
			}
		});
		
		JButton btn1 = new JButton("Show Stocks");
		btn1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn1.setBounds(12, 75, 145, 55);
		p_button.add(btn1);
		btn1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				new ShowStock().printGUI();
			}
		});
		
		JButton btn4 = new JButton("Edit Stock");
		btn4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn4.setBounds(326, 10, 145, 55);
		p_button.add(btn4);
		btn4.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				new EditStock().printGUI();
			}
		});

		JButton btn5 = new JButton("Find Ticker");
		btn5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn5.setBounds(483, 10, 145, 55);
		p_button.add(btn5);
		btn5.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				new FindTicker().printGUI();
			}
		});
		JButton btn3 = new JButton("Remove Stocks");
		btn3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn3.setBounds(169, 10, 145, 55);
		p_button.add(btn3);
		btn3.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				new RemoveStock().printGUI();
			}
		});
		JButton btn6 = new JButton("Statistics");
		btn6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn6.setBounds(169, 75, 145, 55);
		p_button.add(btn6);
		btn6.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				new Statistics().printGUI();
			}
		});
		JButton btn8 = new JButton("About");
		btn8.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn8.setBounds(326, 75, 145, 55);
		p_button.add(btn8);
		btn8.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				new About().printGUI();
			}
		});
		JButton btn0 = new JButton("EXIT");
		btn0.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn0.setBounds(483, 75, 145, 55);
		p_button.add(btn0);
		btn0.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				System.exit(0);
			}
		});
		frame.setBounds(100, 100, 687, 532);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
