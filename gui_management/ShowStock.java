package gui_management;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;

import javax.swing.*;

import data.Stock;
import file_management.LoadManager;
import functions.ToolFunction;
import interfaces.BasicGUI;

public class ShowStock extends JFrame implements BasicGUI{
	private static final long serialVersionUID = -2920264366783929183L;
	private ToolFunction tool = ToolFunction.getToolFunctionObject();
	LoadManager loader = LoadManager.getLoadManagerObject();
	private ArrayList<Stock> localStocks = new ArrayList<>();
	private ArrayList<Stock> foreignStocks = new ArrayList<>();
	private ArrayList<String> localNameList = new ArrayList<>();
	private ArrayList<String> foreignNameList = new ArrayList<>();
	private Stock stock;
	public void printGUI() { 
		JFrame f_showStock = new JFrame();
		f_showStock.setAlwaysOnTop(true);
		f_showStock.setTitle("Show Stock");
		f_showStock.setType(Type.UTILITY);
		f_showStock.setSize(new Dimension(800, 600));
		f_showStock.setResizable(false);

		f_showStock.getContentPane().setBackground(Color.WHITE);
		f_showStock.getContentPane().setLayout(null);
		
		JPanel p_property = new JPanel();
		p_property.setBackground(new Color(240, 248, 255));
		p_property.setBounds(12, 10, 190, 343);
		f_showStock.getContentPane().add(p_property);
		p_property.setLayout(null);
		
		JLabel lb_name = new JLabel("name");
		lb_name.setBounds(10, 35, 55, 20);
		lb_name.setFont(new Font("±¼¸²", Font.PLAIN, 16));
		lb_name.setPreferredSize(new Dimension(82, 20));
		p_property.add(lb_name);
		
		JLabel name = new JLabel("");
		name.setBounds(82, 35, 100, 20);
		name.setFont(new Font("±¼¸²", Font.PLAIN, 16));
		name.setPreferredSize(new Dimension(82, 20));
		p_property.add(name);
		
		JLabel lb_price_t = new JLabel("price_t");
		lb_price_t.setBounds(10, 85, 55, 20);
		lb_price_t.setFont(new Font("±¼¸²", Font.PLAIN, 16));
		lb_price_t.setPreferredSize(new Dimension(82, 20));
		p_property.add(lb_price_t);
		
		JLabel price_t = new JLabel("");
		price_t.setBounds(82, 85, 100, 20);
		price_t.setFont(new Font("±¼¸²", Font.PLAIN, 16));
		price_t.setPreferredSize(new Dimension(82, 20));
		p_property.add(price_t);
		
		JLabel lb_price_y = new JLabel("price_y");
		lb_price_y.setBounds(10, 135, 55, 20);
		lb_price_y.setFont(new Font("±¼¸²", Font.PLAIN, 16));
		lb_price_y.setPreferredSize(new Dimension(82, 20));
		p_property.add(lb_price_y);
		
		JLabel price_y = new JLabel("");
		price_y.setBounds(82, 135, 100, 20);
		price_y.setFont(new Font("±¼¸²", Font.PLAIN, 16));
		price_y.setPreferredSize(new Dimension(82, 20));
		p_property.add(price_y);
		
		JLabel lb_change = new JLabel("change");
		lb_change.setBounds(10, 185, 55, 20);
		lb_change.setFont(new Font("±¼¸²", Font.PLAIN, 16));
		lb_change.setPreferredSize(new Dimension(82, 20));
		p_property.add(lb_change);
		
		JLabel change = new JLabel("");
		change.setBounds(82, 185, 82, 20);
		change.setFont(new Font("±¼¸²", Font.PLAIN, 16));
		change.setPreferredSize(new Dimension(82, 20));
		p_property.add(change);
		
		JLabel lb_asset = new JLabel("asset");
		lb_asset.setBounds(10, 235, 55, 20);
		lb_asset.setFont(new Font("±¼¸²", Font.PLAIN, 16));
		lb_asset.setPreferredSize(new Dimension(82, 20));
		p_property.add(lb_asset);
		
		JLabel asset = new JLabel("");
		asset.setBounds(82, 235, 100, 20);
		asset.setFont(new Font("±¼¸²", Font.PLAIN, 16));
		asset.setPreferredSize(new Dimension(82, 20));
		p_property.add(asset);
		
		JLabel lb_wealth = new JLabel("wealth");
		lb_wealth.setBounds(10, 285, 55, 20);
		lb_wealth.setFont(new Font("±¼¸²", Font.PLAIN, 16));
		lb_wealth.setPreferredSize(new Dimension(82, 20));
		p_property.add(lb_wealth);
		
		JLabel wealth = new JLabel("");
		wealth.setBounds(82, 285, 100, 20);
		wealth.setFont(new Font("±¼¸²", Font.PLAIN, 16));
		wealth.setPreferredSize(new Dimension(82, 20));
		p_property.add(wealth);
		
		JPanel p_chartImage = new JPanel();
		p_chartImage.setBackground(Color.WHITE);
		p_chartImage.setForeground(Color.WHITE);
		p_chartImage.setBounds(214, 10, 568, 303);
		f_showStock.getContentPane().add(p_chartImage);
		
		JLabel lb_desc = new JLabel("");
		lb_desc.setHorizontalAlignment(SwingConstants.CENTER);
		lb_desc.setBounds(0, 0, 568, 40);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(240, 248, 255));
		panel_1.setBounds(214, 313, 568, 40);
		panel_1.setLayout(null);
		panel_1.add(lb_desc);
		f_showStock.getContentPane().add(panel_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 248, 255));
		panel.setBounds(12, 363, 770, 198);
		f_showStock.getContentPane().add(panel);
		panel.setLayout(null);
		
		localStocks = loader.getList(0);
		for(Stock stock : localStocks) {
			localNameList.add(stock.getName());
		}
		DefaultListModel<String> localModel = new DefaultListModel<>();
		for(String str : localNameList) {
			localModel.addElement(str);
		}
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(120, 33, 170, 88);
		panel.add(scrollPane);
		JList<String> localList = new JList<String>(localModel);
		scrollPane.setViewportView(localList);
		localList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		foreignStocks = loader.getList(1);
		for(Stock stock : foreignStocks) {
			foreignNameList.add(stock.getName());
		}
		DefaultListModel<String> foreignModel = new DefaultListModel<>();
		for(String str : foreignNameList) {
			foreignModel.addElement(str);
		}
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(350, 33, 170, 88);
		panel.add(scrollPane_1);
		JList<String> foreignList = new JList<>(foreignModel);
		scrollPane_1.setViewportView(foreignList);
		foreignList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JLabel lb_local = new JLabel("±¹³» ÁÖ½Ä");
		lb_local.setHorizontalAlignment(SwingConstants.CENTER);
		lb_local.setBounds(120, 10, 170, 15);
		panel.add(lb_local);
		
		JLabel lo_foreign = new JLabel("±¹¿Ü ÁÖ½Ä");
		lo_foreign.setHorizontalAlignment(SwingConstants.CENTER);
		lo_foreign.setBounds(350, 10, 170, 15);
		panel.add(lo_foreign);
		
		JButton btn_ok1 = new JButton("°Ë»öÇÏ±â");
		btn_ok1.setBounds(350, 128, 170, 60);
		panel.add(btn_ok1);
		btn_ok1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_ok1.setPreferredSize(new Dimension(60, 60));
		btn_ok1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stock = tool.getElementByName(foreignList.getSelectedValue());
				try {
					name.setText(stock.getName());
					price_t.setText(stock.getPrice_t()+"$");
					price_y.setText(stock.getPrice_y()+"$");
					change.setText(stock.getNetChange()+"%");
					asset.setText(""+stock.getAsset()+"ÁÖ");
					wealth.setText(tool.getTotalAsset(stock, "local", "today")+"$");
					lb_desc.setText(stock.getDescription());
				} catch ( NullPointerException e1 ) {
					
				}
				try {
					String imageURL = "https://ssl.pstatic.net/imgfinance/chart/mobile/world/item/candle/month/"+stock.getCode().toUpperCase()+".O_end.png";
					URL url = new URL(imageURL);
					ReadableByteChannel channel = Channels.newChannel(url.openStream());
					FileOutputStream stream = new FileOutputStream("chart.png");
					stream.getChannel().transferFrom(channel, 0, Long.MAX_VALUE);
					stream.close();
					
					double scale = 0.7;
					ImageIcon originalImage = new ImageIcon("chart.png");
					Image tempChartImage = originalImage.getImage();
					Image chartImage = tempChartImage.getScaledInstance((int)(658*scale), (int)(408*scale), Image.SCALE_SMOOTH);
					ImageIcon chartIcon = new ImageIcon(chartImage);
					JLabel ImageLabel = new JLabel(chartIcon);
					ImageLabel.setLocation(0,0);
					ImageLabel.setSize(570,350);
					p_chartImage.removeAll();
					p_chartImage.add(ImageLabel);
				} catch (MalformedURLException e1) {
				} catch (IOException e1) {
				} catch (NullPointerException e1) {
				}
			}
		});
		
		JButton btn_ok2 = new JButton("°Ë»öÇÏ±â");
		btn_ok2.setBounds(120, 128, 170, 60);
		panel.add(btn_ok2);
		btn_ok2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_ok2.setPreferredSize(new Dimension(60, 60));
		btn_ok2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					stock = tool.getElementByName(localList.getSelectedValue());
					name.setText(stock.getName());
					price_t.setText(stock.getPrice_t()+"¿ø");
					price_y.setText(stock.getPrice_y()+"¿ø");
					change.setText(stock.getNetChange()+"%");
					asset.setText(""+stock.getAsset()+"ÁÖ");
					wealth.setText(tool.getTotalAsset(stock, "local", "today")+"¿ø");
					lb_desc.setText(stock.getDescription());
				} catch ( NullPointerException e2 ) {
					
				}
				try {
					String imageURL = "https://ssl.pstatic.net/imgfinance/chart/item/area/week/"+stock.getCode()+".png";
					URL url = new URL(imageURL);
					ReadableByteChannel channel = Channels.newChannel(url.openStream());
					FileOutputStream stream = new FileOutputStream("chart.png");
					stream.getChannel().transferFrom(channel, 0, Long.MAX_VALUE);
					stream.close();
					
					double scale = 0.85;
					ImageIcon originalImage = new ImageIcon("chart.png");
					Image tempChartImage = originalImage.getImage();
					Image chartImage = tempChartImage.getScaledInstance((int)(700*scale), (int)(289*scale), Image.SCALE_SMOOTH);
					ImageIcon chartIcon = new ImageIcon(chartImage);
					JLabel ImageLabel = new JLabel(chartIcon);
					ImageLabel.setLocation(0,0);
					ImageLabel.setSize(570,350);
					p_chartImage.removeAll();
					p_chartImage.add(ImageLabel);
				} catch (MalformedURLException e1) {
				} catch (IOException e1) {
				} catch (NullPointerException e1) {
				}
			}
		});
		JButton btn_cancle = new JButton("Ãë¼Ò");
		btn_cancle.setBounds(660, 72, 60, 60);
		panel.add(btn_cancle);
		btn_cancle.setPreferredSize(new Dimension(60, 60));
		btn_cancle.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				f_showStock.setVisible(false);
			}
		});
		
		JButton btn_reset = new JButton("ÃÊ±âÈ­");
		btn_reset.setMargin(new Insets(0, 0, 0, 0));
		btn_reset.setPreferredSize(new Dimension(60, 60));
		btn_reset.setBounds(580, 72, 60, 60);
		panel.add(btn_reset);
		btn_reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				name.setText("");
				price_t.setText("");
				price_y.setText("");
				change.setText("");
				asset.setText("");
				wealth.setText("");
				lb_desc.setText("");
				p_chartImage.removeAll();
			}
		});
		f_showStock.setVisible(true);
	}

}
