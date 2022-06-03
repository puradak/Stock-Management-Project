package gui_management;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import data.GUIs;
import data.News;
import interfaces.BasicGUI;
import log_management.SaveManager;

public class MainMenu extends JFrame implements BasicGUI{
	private static final long serialVersionUID = -7295373837441585468L;
	private SaveManager saver = new SaveManager();
	private String[] URL = new String[5];
	private int index = 0;
	private JButton[] btn = { 
			new JButton("EXIT"), 			//0
			new JButton("Show Stocks"), 	//1
			new JButton("Add Stocks"), 		//2
			new JButton("Remove Stocks"), 	//3
			new JButton("Edit Stocks"), 	//4
			new JButton("Find Ticker"), 	//5
			new JButton("Statistics"),		//6
			new JButton("About"),			//7
			new JButton("Refresh")			//8
			};

	private JButton[] head = {
			new JButton("news head 1"), 
			new JButton("news head 2"), 
			new JButton("news head 3"), 
			new JButton("news head 4"), 
			new JButton("news head 5")
			};
	
	public MainMenu() {
		setNews();
		saver.cleanMenuLog();
		saver.saveLog(10);
	}
	
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
		
		for(int i=0; i<5; i++) {
			head[i].setFont(new Font("±¼¸²", Font.PLAIN, 20));
			head[i].setBounds(12, 10+40*i, 623, 31);
			head[i].setFocusable(false);
			head[i].setContentAreaFilled(false);
			head[i].setBorder(null);
			head[i].setOpaque(false);
			head[i].setHorizontalAlignment(SwingConstants.LEFT);
			head[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			p_news.add(head[i]);
		}
		
		head[0].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				News news = new News(0);
				saver.saveLog(9, news.getTitle());
				NewsPage page = new NewsPage(news);
				page.printGUI();
			}
		});
		head[1].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				News news = new News(1);
				saver.saveLog(9, news.getTitle());
				NewsPage page = new NewsPage(news);
				page.printGUI();
			}
		});
		head[2].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				News news = new News(2);
				saver.saveLog(9, news.getTitle());
				NewsPage page = new NewsPage(news);
				page.printGUI();
			}
		});
		head[3].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				News news = new News(3);
				saver.saveLog(9, news.getTitle());
				NewsPage page = new NewsPage(news);
				page.printGUI();
			}
		});
		head[4].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				News news = new News(4);
				saver.saveLog(9, news.getTitle());
				NewsPage page = new NewsPage(news);
				page.printGUI();
			}
		});
		JPanel p_button = new JPanel();
		p_button.setBounds(12, 311, 647, 154);
		frame.getContentPane().add(p_button);
		p_button.setLayout(null);
		
		for(index=0; index < 8; index++) {
			btn[index].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			p_button.add(btn[index]);
		}
		frame.add(btn[8]);
		btn[0].setBounds(483, 75, 145, 55);
		btn[1].setBounds(12, 75, 145, 55);
		btn[2].setBounds(12, 10, 145, 55);
		btn[3].setBounds(169, 10, 145, 55);
		btn[4].setBounds(326, 10, 145, 55);
		btn[5].setBounds(483, 10, 145, 55);
		btn[6].setBounds(169, 75, 145, 55);
		btn[7].setBounds(326, 75, 145, 55);
		btn[8].setBounds(275, 278, 100, 30);
		
		btn[0].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				openGUI(0);
			}
		});
		btn[1].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				openGUI(1);
			}
		});
		btn[2].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				openGUI(2);
			}
		});
		btn[3].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				openGUI(3);
			}
		});
		btn[4].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				openGUI(4);
			}
		});
		btn[5].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				openGUI(5);
			}
		});
		btn[6].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				openGUI(6);
			}
		});
		btn[7].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				openGUI(7);
			}
		});
		btn[8].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				openGUI(8);
			}
		});
		frame.setBounds(100, 100, 687, 532);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	private void openGUI(int index) {
		saver.saveLog(index);
		if (index > 0 && index < 8) new GUIs().printGUI(index);
		if (index == 0) {
			saver.saveObject();
			System.exit(0);
		}
		if (index == 8) setNews();
	}
	private void setNews() {
		String baseURL = "https://finance.naver.com/";
		String[] news = new String[5];
		try {
			Document doc = Jsoup.connect(baseURL).get();
			Elements newsSection = doc.getElementsByAttributeValue("class", "h_strategy");
			for(int i=0; i<5; i++) {
				URL[i] = baseURL+newsSection.next().select("a").get(i).attr("href");
				news[i] = newsSection.next().select("a").get(i).text();
				head[i].setText(news[i]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}