package gui_management;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

import data.Stock;
import file_management.LoadManager;
import functions.ToolFunction;
import interfaces.BasicGUI;

public class AddStock extends JFrame implements BasicGUI{
	private static final long serialVersionUID = 5227932774846019655L;
	LoadManager loader = LoadManager.getLoadManagerObject();
	ArrayList<Stock> localStockList = loader.LoadList("local");
	ArrayList<Stock> foreignStockList = loader.LoadList("foreign");
	private ToolFunction tool = ToolFunction.getToolFunctionObject();
	private JFrame f;
	private JLabel lb_narr = new JLabel("�ֽĸ�");;
	private JPanel p_logoImage;
	private JLabel name = new JLabel(""); 
	private JLabel price_t = new JLabel(""); 
	private JLabel price_y = new JLabel(""); 
	private JLabel change = new JLabel(""); 
	private JLabel isOpen = new JLabel(""); 
	private JTextField tF_input = new JTextField("");
	private int sqFlag = 0;
	private Stock stock;
	
	public void printGUI() {
		f = new JFrame();
		f.getContentPane().setBackground(Color.WHITE);
		f.getContentPane().setLayout(null);
		
		lb_narr = new JLabel("������ �ֽ��� �ֽ��ڵ带 �Է��ϰ�, Ȯ�� ��ư�� ��������.");
		lb_narr.setHorizontalTextPosition(SwingConstants.CENTER);
		lb_narr.setHorizontalAlignment(SwingConstants.CENTER);
		lb_narr.setBounds(0, 10, 432, 15);
		f.getContentPane().add(lb_narr);
		
		p_logoImage = new JPanel();
		p_logoImage.setBounds(12, 47, 200, 138);
		f.getContentPane().add(p_logoImage);
		
		JLabel lb_name = new JLabel("�ֽĸ�");
		lb_name.setFont(new Font("����", Font.PLAIN, 15));
		lb_name.setBounds(223, 53, 42, 18);
		f.getContentPane().add(lb_name);
		
		JLabel lb_price_t = new JLabel("���簡");
		lb_price_t.setFont(new Font("����", Font.PLAIN, 15));
		lb_price_t.setBounds(224, 80, 42, 18);
		f.getContentPane().add(lb_price_t);
		
		JLabel lb_price_y = new JLabel("���ϰ�");
		lb_price_y.setFont(new Font("����", Font.PLAIN, 15));
		lb_price_y.setBounds(224, 108, 42, 18);
		f.getContentPane().add(lb_price_y);
		
		JLabel lb_change = new JLabel("��ȭ��");
		lb_change.setFont(new Font("����", Font.PLAIN, 15));
		lb_change.setBounds(224, 136, 42, 18);
		f.getContentPane().add(lb_change);
		
		JLabel lb_isOpen = new JLabel("���忩��");
		lb_isOpen.setFont(new Font("����", Font.PLAIN, 15));
		lb_isOpen.setBounds(224, 164, 56, 18);
		f.getContentPane().add(lb_isOpen);
		
		JLabel lb_input = new JLabel("�Է� : ");
		lb_input.setBounds(112, 27, 36, 15);
		f.getContentPane().add(lb_input);
		
		tF_input = new JTextField();
		tF_input.setHorizontalAlignment(SwingConstants.LEFT);
		tF_input.setBounds(150, 26, 116, 16);
		f.getContentPane().add(tF_input);
		tF_input.setColumns(10);
		
		name.setFont(new Font("����", Font.PLAIN, 15));
		name.setBounds(289, 53, 144, 18);
		f.getContentPane().add(name);
		
		price_t = new JLabel("���簡");
		price_t.setFont(new Font("����", Font.PLAIN, 15));
		price_t.setBounds(289, 80, 143, 18);
		f.getContentPane().add(price_t);
		
		price_y = new JLabel("���ϰ�");
		price_y.setFont(new Font("����", Font.PLAIN, 15));
		price_y.setBounds(289, 108, 143, 18);
		f.getContentPane().add(price_y);
		
		change = new JLabel("��ȭ��");
		change.setFont(new Font("����", Font.PLAIN, 15));
		change.setBounds(289, 136, 143, 18);
		f.getContentPane().add(change);
		
		isOpen = new JLabel("���忩��");
		isOpen.setFont(new Font("����", Font.PLAIN, 15));
		isOpen.setBounds(289, 164, 143, 18);
		f.getContentPane().add(isOpen);
		
		JButton btn_ok = new JButton("Ȯ��");
		btn_ok.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_ok.setPreferredSize(new Dimension(60, 60));
		btn_ok.setBounds(152, 201, 60, 60);
		f.getContentPane().add(btn_ok);
		btn_ok.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				if(tool.isExistStock(tF_input.getText()))	{
					lb_narr.setText("�̹� ����Ʈ�� ����� �ֽ��Դϴ�. �ٽ� �Է��ϼ���.");
					tF_input.setText("");
					return;
				}
				while(sqFlag == 0) {
					if(!addStock_check(tF_input.getText())) {
						lb_narr.setText("�߸��� �ֽ��ڵ��Դϴ�. �ٽ� �Է��ϼ���.");
						tF_input.setText("");
						return;
					}
					else {
						sqFlag = 1;
						return;
					}
				}
				while(sqFlag == 1) {
					if(addStock_asset(tF_input.getText())) {
						sqFlag++;
						return;
					}
				}
				while(sqFlag == 2) {
					if(addStock_desc(tF_input.getText())) {
						sqFlag++;
						return;
					}
				}
				return;
			}
		});
		
		JButton btn_cancle = new JButton("���");
		btn_cancle.setPreferredSize(new Dimension(60, 60));
		btn_cancle.setBounds(224, 201, 60, 60);
		f.getContentPane().add(btn_cancle);
		btn_cancle.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				f.setVisible(false);
			}
		});
		
		f.setType(Type.UTILITY);
		f.setTitle("Add Stock");
		f.setResizable(false);
		f.setBackground(Color.WHITE);
		f.setBounds(100, 100, 450, 300);
		f.setVisible(true);
	}

	
	public boolean addStock_check(String code) {
		try {
			this.stock = Stock.createStock(code);
			if(!stock.getExist()) {
				stock = null;
				return false;
			}
			else {
				setName(stock.getName());
				setPrice_t(stock.getPrice_t());
				setPrice_y(stock.getPrice_y());
				setChange(stock.getNetChange());
				if(tool.isOpen(stock)) setIsOpen("����");
				else setIsOpen("����");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		tF_input.setText("");
		lb_narr.setText("���� ���� ������ �Է��ϼ���.");
		return true;
	}
	
	public boolean addStock_asset(String Asset) {
		if(Asset.equals("")) {
			lb_narr.setText("���� ���� ������ �Է��ϼ���.");
			return false;
		}
		
		if(stock.getType().equals("local")) localStockList.add(stock);
		if(stock.getType().equals("foreign")) foreignStockList.add(stock);
		
		try {
			int asset = Integer.parseInt(Asset);
			if(asset <= 0) throw new NumberFormatException();
			stock.setAsset(asset);
		}catch( NumberFormatException e ) {
			setNarr("�߸� �Է��ϼ̽��ϴ�. �ڿ����� �Է��ϼ���");
			return false;
		}
		
		lb_narr.setText("�ֽ� ��� �Ϸ�! �ֽĿ� ���� ������ �Է��ϼ���.(���û���)");
		return true;
	}
	
	public boolean addStock_desc(String Desc) {
		if(Desc.isEmpty()) stock.setDescription("(�Է����� ����)");
		else stock.setDescription(Desc);
		System.out.println(stock.getName()+"��(��) ��ϵ�. ���� �� : "+stock.getAsset()+", ���� : "+stock.getDescription());
		return true;
	}
	
	public void setNarr(String narr) {
		this.lb_narr.setText(narr);
		return;
	}
	
	public void setName(String name) {
		this.name.setText(name);
		return;
	}
	
	public void setPrice_t(String price_t) {
		this.price_t.setText(price_t);
		return;
	}
	
	public void setPrice_y(String price_y) {
		this.price_y.setText(price_y);
		return;
	}
	public void setChange(String Change) {
		this.change.setText(Change);
		return;
	}
	public void setIsOpen(String isOpen) {
		this.isOpen.setText(isOpen);
		return;
	}
	
}
