package gui_management;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.*;
import data.Stock;
import exceptions.IntegerNotInRangeException;
import functions.ToolFunction;
import interfaces.BasicGUI;
import log_management.LoadManager;

public class AddStock extends JFrame implements BasicGUI{
	private static final long serialVersionUID = 5227932774846019655L;
	private LoadManager loader = LoadManager.getLoadManagerObject();
	private ToolFunction tool = new ToolFunction();
	private JFrame f;
	private JLabel lb_narr = new JLabel("������ �ֽ��� �ֽ��ڵ带 �Է��ϰ�, Ȯ�� ��ư�� ��������.");
	private JLabel lb_input = new JLabel("�Է� : ");
	private JLabel[] labels = {
			new JLabel("(�ֽĸ�)"),		// name
			new JLabel("(���簡)"),		// price_t
			new JLabel("(���ϰ�)"),		// price_y
			new JLabel("(��ȭ��)"),		// change
			new JLabel("(���忩��)"),	// isOpen
	};
	private JLabel[] lb_labels = {
			new JLabel("�ֽĸ�"),		// lb_name
			new JLabel("���簡"),		// lb_price_t
			new JLabel("���ϰ�"),		// lb_price_y
			new JLabel("��ȭ��"),		// lb_change
			new JLabel("�� ��"),		// lb_isOpen
	};
	private JTextField tF_input = new JTextField("");
	private Stock stock;
	private int sqFlag = 0;
	
	public void printGUI() { 
		f = new JFrame();
		f.getContentPane().setBackground(Color.WHITE);
		f.getContentPane().setLayout(null);
		
		lb_narr.setHorizontalTextPosition(SwingConstants.CENTER);
		lb_narr.setHorizontalAlignment(SwingConstants.CENTER);
		lb_narr.setBounds(0, 10, 432, 15);
		f.getContentPane().add(lb_narr);
		
		lb_input.setBounds(112, 27, 36, 20);
		f.getContentPane().add(lb_input);
		
		tF_input.setFont(new Font("����", Font.PLAIN, 18));
		tF_input.setHorizontalAlignment(SwingConstants.LEFT);
		tF_input.setBounds(150, 26, 133, 21);
		tF_input.setColumns(10);
		f.getContentPane().add(tF_input);
		
		JButton btn_ok = new JButton("Ȯ��");
		btn_ok.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
					}
					else sqFlag++;
					return;
				}
				while(sqFlag == 1) {
					if(addStock_asset(tF_input.getText())) sqFlag++;
					tF_input.setText("");
					return;
				}
				while(sqFlag == 2) {
					if(addStock_desc(tF_input.getText())) check();
				}
				return;
			}
		});
		JButton btn_cancle = new JButton("���");
		btn_cancle.setPreferredSize(new Dimension(60, 60));
		btn_cancle.setBounds(223, 201, 60, 60);
		f.getContentPane().add(btn_cancle);
		btn_cancle.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				f.setVisible(false);
			}
		});
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 248, 255));
		panel.setBounds(12, 52, 422, 140);
		f.getContentPane().add(panel);
		panel.setLayout(null);
		
		for(int i=0; i<5; i++) {
			labels[i].setBounds(186, 5+28*i, 144, 18);
			lb_labels[i].setBounds(100, 6+28*i, 42, 18);
			labels[i].setFont(new Font("����", Font.PLAIN, 15));
			lb_labels[i].setFont(new Font("����", Font.PLAIN, 15));
			panel.add(labels[i]);
			panel.add(lb_labels[i]);
		}
		f.setType(Type.UTILITY);
		f.setTitle("Add Stock");
		f.setResizable(false);
		f.setBackground(Color.WHITE);
		f.setBounds(100, 100, 450, 300);
		f.setVisible(true);
	}
	
	private void check() {
		JFrame checkFrame = new JFrame();
		checkFrame.setTitle("Add Stock");
		checkFrame.setType(Type.UTILITY);
		checkFrame.setBounds(100, 100, 400, 200);
		checkFrame.getContentPane().setLayout(null);
		
		JButton btn_ok = new JButton("Ȯ��");
		btn_ok.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_ok.setPreferredSize(new Dimension(60, 60));
		btn_ok.setBounds(164, 91, 60, 60);
		checkFrame.getContentPane().add(btn_ok);
		btn_ok.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				tF_input.setText("");
				checkFrame.setVisible(false);
			}
		});
		JLabel lb_sentence = new JLabel("�߰��� �Ϸ�Ǿ����ϴ�.");
		lb_sentence.setHorizontalAlignment(SwingConstants.CENTER);
		lb_sentence.setFont(new Font("����", Font.PLAIN, 15));
		lb_sentence.setBounds(12, 39, 360, 20);
		checkFrame.getContentPane().add(lb_sentence);
		
		lb_narr.setText("������ �ֽ��� �ֽ��ڵ带 �Է��ϰ�, Ȯ�� ��ư�� ��������.");
		checkFrame.setVisible(true);
		
		reset();
		sqFlag = 0;
	}
	private void reset() {
		for(int i=0; i<5; i++) {
			setText(i, "");
		}
	}
	private boolean addStock_check(String code) {
		try {
			this.stock = Stock.createStock(code);
			if(!stock.getExist()) {
				stock = null;
				return false;
			}
			else {
				setText(0,stock.getName());
				setText(1,stock.getPrice_t());
				setText(2,stock.getPrice_y());
				setText(3,stock.getNetChange());
				if(tool.isOpen(stock)) setText(4,"����");
				else setText(4,"����");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		tF_input.setText("");
		lb_narr.setText("���� ���� ������ �Է��ϼ���.");
		return true;
	}
	private boolean addStock_asset(String Asset) {
		if(Asset.equals("")) {
			lb_narr.setText("���� ���� ������ �Է��ϼ���.");
			return false;
		}
		try {
			int asset = Integer.parseInt(Asset);
			if(asset <= 0) throw new NumberFormatException();
			stock.setAsset(asset);
		}catch( NumberFormatException e ) {
			lb_narr.setText("�߸� �Է��ϼ̽��ϴ�. �ڿ����� �Է��ϼ���");
			return false;
		}
		if(stock.getType().equals("local")) loader.getList(0).add(stock);
		if(stock.getType().equals("foreign")) loader.getList(1).add(stock);

		lb_narr.setText("�ֽ� ��� �Ϸ�! �ֽĿ� ���� ������ �Է��ϼ���.(���û���)");
		return true;
	}
	private boolean addStock_desc(String Desc) {
		if(Desc.isEmpty()) stock.setDescription("(�Է����� ����)");
		else stock.setDescription(Desc);
		return true;
	}
	public void setText(int number, String value) {
		try{
			if(number < 0 || number > 4) throw new IntegerNotInRangeException(0,4);
			labels[number].setText(value);
		} catch ( IntegerNotInRangeException e ) {
			e.printStackTrace();
		}
	}
}
