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
	private JLabel lb_narr = new JLabel("보유한 주식의 주식코드를 입력하고, 확인 버튼을 누르세요.");
	private JLabel lb_input = new JLabel("입력 : ");
	private JLabel[] labels = {
			new JLabel("(주식명)"),		// name
			new JLabel("(현재가)"),		// price_t
			new JLabel("(전일가)"),		// price_y
			new JLabel("(변화율)"),		// change
			new JLabel("(개장여부)"),	// isOpen
	};
	private JLabel[] lb_labels = {
			new JLabel("주식명"),		// lb_name
			new JLabel("현재가"),		// lb_price_t
			new JLabel("전일가"),		// lb_price_y
			new JLabel("변화율"),		// lb_change
			new JLabel("개 폐"),		// lb_isOpen
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
		
		tF_input.setFont(new Font("굴림", Font.PLAIN, 18));
		tF_input.setHorizontalAlignment(SwingConstants.LEFT);
		tF_input.setBounds(150, 26, 133, 21);
		tF_input.setColumns(10);
		f.getContentPane().add(tF_input);
		
		JButton btn_ok = new JButton("확인");
		btn_ok.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_ok.setBounds(152, 201, 60, 60);
		f.getContentPane().add(btn_ok);
		btn_ok.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				if(tool.isExistStock(tF_input.getText()))	{
					lb_narr.setText("이미 리스트에 등록한 주식입니다. 다시 입력하세요.");
					tF_input.setText("");
					return;
				}
				while(sqFlag == 0) {
					if(!addStock_check(tF_input.getText())) {
						lb_narr.setText("잘못된 주식코드입니다. 다시 입력하세요.");
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
		JButton btn_cancle = new JButton("취소");
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
			labels[i].setFont(new Font("굴림", Font.PLAIN, 15));
			lb_labels[i].setFont(new Font("굴림", Font.PLAIN, 15));
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
		
		JButton btn_ok = new JButton("확인");
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
		JLabel lb_sentence = new JLabel("추가가 완료되었습니다.");
		lb_sentence.setHorizontalAlignment(SwingConstants.CENTER);
		lb_sentence.setFont(new Font("굴림", Font.PLAIN, 15));
		lb_sentence.setBounds(12, 39, 360, 20);
		checkFrame.getContentPane().add(lb_sentence);
		
		lb_narr.setText("보유한 주식의 주식코드를 입력하고, 확인 버튼을 누르세요.");
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
				if(tool.isOpen(stock)) setText(4,"개장");
				else setText(4,"폐장");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		tF_input.setText("");
		lb_narr.setText("보유 중인 수량을 입력하세요.");
		return true;
	}
	private boolean addStock_asset(String Asset) {
		if(Asset.equals("")) {
			lb_narr.setText("보유 중인 수량을 입력하세요.");
			return false;
		}
		try {
			int asset = Integer.parseInt(Asset);
			if(asset <= 0) throw new NumberFormatException();
			stock.setAsset(asset);
		}catch( NumberFormatException e ) {
			lb_narr.setText("잘못 입력하셨습니다. 자연수를 입력하세요");
			return false;
		}
		if(stock.getType().equals("local")) loader.getList(0).add(stock);
		if(stock.getType().equals("foreign")) loader.getList(1).add(stock);

		lb_narr.setText("주식 등록 완료! 주식에 대한 설명을 입력하세요.(선택사항)");
		return true;
	}
	private boolean addStock_desc(String Desc) {
		if(Desc.isEmpty()) stock.setDescription("(입력하지 않음)");
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
