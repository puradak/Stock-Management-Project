package menu;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.awt.Window.Type;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.JList;

import data.LocalStock;
import data.Stock;
import exceptions.NotPositiveNumberExeption;
import functions.ToolFunction;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.ScrollPaneConstants;


public class test {
	private String imageURL = "https://ssl.pstatic.net/imgfinance/chart/item/area/day/051910.png";
	private ToolFunction tool = ToolFunction.getToolFunctionObject();
	private ArrayList<Stock> localStocks = new ArrayList<>();
	private ArrayList<Stock> foreignStocks = new ArrayList<>();
	private ArrayList<String> localNameList = new ArrayList<>();
	private ArrayList<String> foreignNameList = new ArrayList<>();
	ArrayList<String> nameList;
	JFrame removeFrame = new JFrame();
	JTextField tF_input = new JTextField();
	Stock stock;
	JLabel lb_narr = new JLabel("변경할 주식을 선택하고, 확인 버튼을 눌러주세요.");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test window = new test();
					window.removeFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public test() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		removeFrame.setAlwaysOnTop(true);
		removeFrame.setTitle("Remove Stock");
		removeFrame.setType(Type.UTILITY);
		removeFrame.setBounds(100, 100, 550, 400);

		removeFrame.getContentPane().setBackground(new Color(255, 255, 255));
		removeFrame.getContentPane().setLayout(null);
		
		JLabel introduce = new JLabel("변경할 주식을 선택하고, 확인 버튼을 눌러주세요.");
		introduce.setBackground(new Color(255, 255, 255));
		introduce.setHorizontalAlignment(SwingConstants.CENTER);
		introduce.setFont(new Font("굴림", Font.PLAIN, 12));
		introduce.setBounds(12, 10, 510, 15);
		removeFrame.getContentPane().add(introduce);
		
		JButton btn_select1 = new JButton("선택");
		btn_select1.setPreferredSize(new Dimension(60, 60));
		btn_select1.setBounds(40, 328, 164, 29);
		removeFrame.getContentPane().add(btn_select1);
		
		JButton btn_select2 = new JButton("선택");
		btn_select2.setPreferredSize(new Dimension(60, 60));
		btn_select2.setBounds(239, 328, 164, 29);
		removeFrame.getContentPane().add(btn_select2);
		
		JButton btn_cancle = new JButton("취소");
		btn_cancle.setPreferredSize(new Dimension(60, 60));
		btn_cancle.setBounds(435, 236, 60, 60);
		removeFrame.getContentPane().add(btn_cancle);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(10, 55, 512, 130);
		removeFrame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lb_name = new JLabel("주식명");
		lb_name.setBounds(new Rectangle(0, 0, 0, 20));
		lb_name.setFont(new Font("굴림", Font.PLAIN, 15));
		lb_name.setHorizontalAlignment(SwingConstants.CENTER);
		lb_name.setBounds(5, 10, 42, 20);
		panel.add(lb_name);
		
		JLabel lb_asset = new JLabel("보유주");
		lb_asset.setBounds(new Rectangle(0, 0, 0, 20));
		lb_asset.setHorizontalAlignment(SwingConstants.CENTER);
		lb_asset.setFont(new Font("굴림", Font.PLAIN, 15));
		lb_asset.setBounds(5, 40, 42, 20);
		panel.add(lb_asset);
		
		JLabel lb_desc = new JLabel("설명");
		lb_desc.setBounds(new Rectangle(0, 0, 0, 20));
		lb_desc.setHorizontalAlignment(SwingConstants.CENTER);
		lb_desc.setFont(new Font("굴림", Font.PLAIN, 15));
		lb_desc.setBounds(5, 70, 42, 20);
		panel.add(lb_desc);
		
		JLabel name = new JLabel("주식명");
		name.setHorizontalAlignment(SwingConstants.LEFT);
		name.setFont(new Font("굴림", Font.PLAIN, 15));
		name.setBounds(new Rectangle(0, 0, 0, 20));
		name.setBounds(59, 10, 417, 20);
		panel.add(name);
		
		JLabel asset = new JLabel("보유주");
		asset.setHorizontalAlignment(SwingConstants.LEFT);
		asset.setFont(new Font("굴림", Font.PLAIN, 15));
		asset.setBounds(new Rectangle(0, 0, 0, 20));
		asset.setBounds(59, 40, 417, 20);
		panel.add(asset);
		
		JTextPane desc = new JTextPane();
		desc.setText("description");
		desc.setBounds(57, 67, 419, 60);
		panel.add(desc);
		
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(40, 204, 164, 114);
		removeFrame.getContentPane().add(scrollPane2);
		
		JList<String> localList = new JList<>();
		scrollPane2.setViewportView(localList);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(239, 204, 164, 114);
		removeFrame.getContentPane().add(scrollPane_1);
		
		JList<String> foreignList = new JList<>();
		scrollPane_1.setViewportView(foreignList);
		
		
		
	}
}
