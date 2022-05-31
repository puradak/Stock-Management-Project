package gui_management;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import data.Stock;
import file_management.LoadManager;
import interfaces.BasicGUI;

public class RemoveStock extends JFrame implements BasicGUI{
	private static final long serialVersionUID = -7973139397981674187L;
	LoadManager loader = LoadManager.getLoadManagerObject();
	private Stock target = null;
	
	public void printGUI() {
		JFrame removeFrame = new JFrame();
		removeFrame.setAlwaysOnTop(true);
		removeFrame.setTitle("Remove Stock");
		removeFrame.setType(Type.UTILITY);
		removeFrame.setBounds(100, 100, 631, 300);

		removeFrame.getContentPane().setBackground(new Color(255, 255, 255));
		removeFrame.getContentPane().setLayout(null);
		
		JLabel introduce = new JLabel("변경할 주식의 주식코드를 입력하고, 확인 버튼을 눌러주세요.");
		introduce.setBackground(new Color(255, 255, 255));
		introduce.setHorizontalAlignment(SwingConstants.CENTER);
		introduce.setFont(new Font("굴림", Font.PLAIN, 12));
		introduce.setBounds(12, 20, 590, 15);
		removeFrame.getContentPane().add(introduce);
		
		
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(10, 55, 328, 130);
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
		
		JLabel name = new JLabel("");
		name.setHorizontalAlignment(SwingConstants.LEFT);
		name.setFont(new Font("굴림", Font.PLAIN, 15));
		name.setBounds(new Rectangle(0, 0, 0, 20));
		name.setBounds(59, 10, 257, 20);
		panel.add(name);
		
		JLabel asset = new JLabel(""); 
		asset.setHorizontalAlignment(SwingConstants.LEFT);
		asset.setFont(new Font("굴림", Font.PLAIN, 15));
		asset.setBounds(new Rectangle(0, 0, 0, 20));
		asset.setBounds(59, 40, 257, 20);
		panel.add(asset);
		
		JLabel desc = new JLabel();
		desc.setVerticalAlignment(SwingConstants.TOP);
		desc.setText("");
		desc.setBounds(57, 67, 259, 60);
		panel.add(desc);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(350, 55, 120, 130);
		removeFrame.getContentPane().add(scrollPane);
		
		ArrayList<Stock> localStocks = loader.getList(0);
		DefaultListModel<String> localModel = new DefaultListModel<>();
		for(Stock stock : localStocks) {
			localModel.addElement(stock.getName());
		}
		JList<String> localList = new JList<>(localModel);
		localList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		localList.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane.setViewportView(localList);
		ListSelectionListener e1 = new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				Stock stock = null;
				for(Stock s : localStocks) {
					if(s.getName().equals(localList.getSelectedValue())) {
						stock = s;
						break;
					}
				}
				if(stock == null) return;
				name.setText(stock.getName());
				asset.setText(""+stock.getAsset());
				desc.setText(stock.getDescription());
			}
		};
		localList.addListSelectionListener(e1);
		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(482, 55, 120, 130);
		removeFrame.getContentPane().add(scrollPane_1);
		
		ArrayList<Stock> foreignStocks = loader.getList(1);
		DefaultListModel<String> foreignModel = new DefaultListModel<>();
		for(Stock stock : foreignStocks) {
			foreignModel.addElement(stock.getName());
		}
		JList<String> foreignList = new JList<>(foreignModel);
		foreignList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		foreignList.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane_1.setViewportView(foreignList);
		
		JButton btn_ok1 = new JButton("선택");
		btn_ok1.setPreferredSize(new Dimension(60, 60));
		btn_ok1.setBounds(240, 191, 60, 60);
		removeFrame.getContentPane().add(btn_ok1);
		btn_ok1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				for(Stock stock : localStocks) {
					if(stock.getName().equals(localList.getSelectedValue())) {
						target = stock;
						break;
					}
				}
				if(target == null) return;
				JFrame warningFrame = new JFrame();
				warningFrame.setAlwaysOnTop(true);
				warningFrame.setTitle("RemoveStock");
				warningFrame.setType(Type.UTILITY);
				warningFrame.setBounds(100, 100, 400, 200);
				warningFrame.getContentPane().setLayout(null);
				
				JButton btn_ok = new JButton("삭제");
				btn_ok.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				btn_ok.setPreferredSize(new Dimension(60, 60));
				btn_ok.setBounds(120, 90, 60, 60);
				warningFrame.getContentPane().add(btn_ok);
				btn_ok.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						localStocks.remove(target);
						warningFrame.setVisible(false);
					}
				});

				JButton btn_no = new JButton("취소");
				btn_no.setPreferredSize(new Dimension(60, 60));
				btn_no.setBounds(210, 90, 60, 60);
				warningFrame.getContentPane().add(btn_no);
				btn_no.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						warningFrame.setVisible(false);
					}
				});
				
				JLabel lb_sentence = new JLabel("정말 이 주식을 삭제하시겠습니까?");
				lb_sentence.setHorizontalAlignment(SwingConstants.CENTER);
				lb_sentence.setFont(new Font("굴림", Font.PLAIN, 15));
				lb_sentence.setBounds(12, 39, 360, 20);
				warningFrame.getContentPane().add(lb_sentence);
				
				warningFrame.setVisible(true);
				
			}
		});
		
		JButton btn_cancle = new JButton("취소");
		btn_cancle.setPreferredSize(new Dimension(60, 60));
		btn_cancle.setBounds(310, 191, 60, 60);
		removeFrame.getContentPane().add(btn_cancle);
		btn_cancle.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				removeFrame.setVisible(false);
			}
		});
		
		removeFrame.setVisible(true);
	}
	public void valueChanged(ListSelectionEvent e) {
		
	}
}
