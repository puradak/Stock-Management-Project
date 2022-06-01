package gui_management;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import data.News;
import interfaces.BasicGUI;

public class NewsPage extends JFrame implements BasicGUI {
	private static final long serialVersionUID = -6201371114321005865L;
	private String Title;
	private String[] Body;
	public NewsPage(News news) {
		this.Title = news.getTitle();
		this.Body = news.getNews();
	}
	public void printGUI() {
		JFrame articleFrame = new JFrame();
		articleFrame.setTitle("ariticle");
		articleFrame.setType(Type.UTILITY);
		articleFrame.setAlwaysOnTop(true);
		articleFrame.setBounds(100, 100, 630, 650);
		articleFrame.getContentPane().setBackground(Color.WHITE);
		articleFrame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 10, 590, 65);
		articleFrame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel title = new JLabel(Title);
		title.setOpaque(true);
		title.setBackground(new Color(240, 248, 255));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setHorizontalTextPosition(SwingConstants.LEFT);
		title.setFont(new Font("±¼¸²", Font.BOLD, 13));
		title.setBounds(0, 0, 590, 65);
		panel.add(title);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 95, 590, 455);
		articleFrame.getContentPane().add(scrollPane);
		
		String News = new String();
		for(String str : this.Body) {
			News += str;
		}
		News = News.replaceAll("\n", System.getProperty("line.separator"));
		JTextArea body = new JTextArea(News);
		body.setFont(new Font("±¼¸²", Font.PLAIN, 14));
		body.setDragEnabled(true);
		body.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		body.setOpaque(true);
		body.setEditable(false);
		body.setMargin(new Insets(10, 5, 10, 10));
		body.setLineWrap(true);
		body.setWrapStyleWord(true);
		body.setBackground(new Color(255, 255, 255));
		scrollPane.setViewportView(body);
		
		
		articleFrame.setVisible(true);
	}

}
