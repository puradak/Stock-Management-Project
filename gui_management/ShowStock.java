package gui_management;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
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
import functions.ToolFunction;
import interfaces.BasicGUI;
import log_management.LoadManager;

public class ShowStock extends JFrame implements BasicGUI{
	private static final long serialVersionUID = -2920264366783929183L;
	private ToolFunction tool = new ToolFunction();
	private Stock stock;
	
	private JFrame f_showStock = new JFrame();
	private JPanel p_list = new JPanel();
	private JPanel p_property = new JPanel();
	private JPanel p_chartImage = new JPanel();
	private JPanel p_desc = new JPanel();
	private JLabel lb_desc = new JLabel("");
	private JLabel[] lb_list = { new JLabel("국내 주식"),new JLabel("국외 주식") };
	private JLabel[] labels = {
			new JLabel(""),	// name
			new JLabel(""),	// price_t
			new JLabel(""),	// price_y
			new JLabel(""),	// change
			new JLabel(""),	// asset
			new JLabel(""),	// wealth
	};
	private JLabel[] lb_labels = {
			new JLabel("주식명"),	// name
			new JLabel("현재가"),	// price_t
			new JLabel("전일가"),	// price_y
			new JLabel("변화율"),	// change
			new JLabel("보유주"),	// asset
			new JLabel("총재산"),	// wealth
	};

	private JButton[] btn_search = {
			new JButton("검색하기"),
			new JButton("검색하기")
	};
	private String[] url_head = {
			"https://ssl.pstatic.net/imgfinance/chart/item/area/week/",
			"https://ssl.pstatic.net/imgfinance/chart/mobile/world/item/candle/month/"
	};
	private String[] url_tail = {
			".png",
			".O_end.png"
	};
	private double[] scales = { 0.85, 0.7 };
	private int[] widths = {700,658};
	private int[] heights = {298,408};
	private String[] current = {"원", "$"}; 
	private String[] type = {"local","foreign"};
	private LoadManager loader = LoadManager.getLoadManagerObject();	
	private ArrayList<JList<String>> lists = new ArrayList<>();
	private ArrayList<DefaultListModel<String>> models = new ArrayList<>();
	private ArrayList<ArrayList<Stock>> stockLists = new ArrayList<>();
	private JScrollPane[] scPanes = { new JScrollPane(), new JScrollPane() };
	
	public ShowStock() {
		f_showStock.setTitle("Show Stock");
		f_showStock.setAlwaysOnTop(true);
		f_showStock.setType(Type.UTILITY);
		f_showStock.setSize(new Dimension(800, 600));
		f_showStock.setResizable(false);
		f_showStock.getContentPane().setBackground(Color.WHITE);
		f_showStock.getContentPane().setLayout(null);
		
		p_list.setBackground(new Color(240, 248, 255));
		p_list.setBounds(12, 363, 770, 198);
		p_list.setLayout(null);
		f_showStock.getContentPane().add(p_list);
		
		p_property.setBackground(new Color(240, 248, 255));
		p_property.setBounds(12, 10, 190, 343);
		p_property.setLayout(null);
		f_showStock.getContentPane().add(p_property);
		
		p_chartImage.setBackground(Color.WHITE);
		p_chartImage.setBounds(214, 10, 568, 303);
		f_showStock.getContentPane().add(p_chartImage);
		
		p_desc.setBackground(new Color(240, 248, 255));
		p_desc.setBounds(214, 313, 568, 40);
		p_desc.setLayout(null);
		p_desc.add(lb_desc);
		f_showStock.getContentPane().add(p_desc);
		
		for(int i=0; i<6; i++) {
			labels[i].setBounds(65, 33+50*i, 100, 20);
			lb_labels[i].setBounds(10, 35+50*i, 55, 20);
			lb_labels[i].setFont(new Font("굴림", Font.PLAIN, 14));
			lb_labels[i].setFont(new Font("굴림", Font.PLAIN, 16));
			p_property.add(labels[i]);
			p_property.add(lb_labels[i]);
		}
		lb_desc.setHorizontalAlignment(SwingConstants.CENTER);
		lb_desc.setBounds(0, 0, 568, 40);

		for(int i=0; i<2; i++) {
			lb_list[i].setBounds(120+230*i, 10, 170, 15);
			scPanes[i].setBounds(120+230*i, 33, 170, 88);
			btn_search[i].setBounds(120+230*i, 128, 170, 60);
			lb_list[i].setHorizontalAlignment(SwingConstants.CENTER);
			p_list.add(lb_list[i]);
			
			stockLists.add(loader.getList(i));
			models.add(new DefaultListModel<String>());
			for(Stock stock : stockLists.get(i)) {
				models.get(i).addElement(stock.getName());
			}
			
			lists.add(new JList<String>(models.get(i)));
			scPanes[i].setViewportView(lists.get(i));
			lists.get(i).setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			btn_search[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			p_list.add(scPanes[i]);
			p_list.add(btn_search[i]);
			
			int index = i;
			btn_search[i].addMouseListener(new MouseAdapter() {
		         public void mouseClicked(MouseEvent e) {
					stock = tool.getElementByName(lists.get(index).getSelectedValue());
					try {
						labels[0].setText(stock.getName());
						labels[1].setText(stock.getPrice_t()+current[index]);
						labels[2].setText(stock.getPrice_y()+current[index]);
						labels[3].setText(stock.getNetChange()+"%");
						labels[4].setText(""+stock.getAsset()+"주");
						labels[5].setText(tool.getTotalAsset(stock, type[index], "today")+current[index]);
						lb_desc.setText(stock.getDescription());
					} catch ( NullPointerException e1 ) {}
					try {
						String imageURL = url_head[index]+stock.getCode().toUpperCase()+url_tail[index];
						URL url = new URL(imageURL);
						ReadableByteChannel channel = Channels.newChannel(url.openStream());
						FileOutputStream stream = new FileOutputStream("chart.png");
						stream.getChannel().transferFrom(channel, 0, Long.MAX_VALUE);
						stream.close();
						
						ImageIcon originalImage = new ImageIcon("chart.png");
						Image tempChartImage = originalImage.getImage();
						Image chartImage = tempChartImage.getScaledInstance((int)(widths[index]*scales[index]), (int)(heights[index]*scales[index]), Image.SCALE_SMOOTH);
						ImageIcon chartIcon = new ImageIcon(chartImage);
						JLabel ImageLabel = new JLabel(chartIcon);
						ImageLabel.setLocation(0,0);
						ImageLabel.setSize(570,350);
						p_chartImage.removeAll();
						p_chartImage.add(ImageLabel);
					} 
					catch (MalformedURLException e1) {} 
					catch (IOException e1) {}
					catch (NullPointerException e1) {}
				}
			});
		}
	}
	public void printGUI() { 
		JButton btn_cancle = new JButton("취소");
		btn_cancle.setBounds(660, 72, 60, 60);
		p_list.add(btn_cancle);
		btn_cancle.setPreferredSize(new Dimension(60, 60));
		btn_cancle.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				f_showStock.setVisible(false);
			}
		});
		JButton btn_reset = new JButton("초기화");
		btn_reset.setMargin(new Insets(0, 0, 0, 0));
		btn_reset.setBounds(580, 72, 60, 60);
		p_list.add(btn_reset);
		btn_reset.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				for(JLabel label : labels) {
					label.setText("");
				}
				p_chartImage.removeAll();
			}
		});
		f_showStock.setVisible(true);
	}
}
