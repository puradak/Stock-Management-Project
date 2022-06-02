package gui_management;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import data.Stock;
import interfaces.BasicGUI;
import log_management.LoadManager;

public class RemoveStock extends JFrame implements BasicGUI{
	private static final long serialVersionUID = -7973139397981674187L;
	LoadManager loader = LoadManager.getLoadManagerObject();
	private Stock target = null;
	
	public void printGUI() {
		JFrame removeFrame = new JFrame();
		removeFrame.setType(Type.UTILITY);
		removeFrame.setResizable(false);
		removeFrame.setAlwaysOnTop(true);
		removeFrame.getContentPane().setBackground(Color.WHITE);
		removeFrame.setBounds(100, 100, 550, 350);
		removeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		removeFrame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 248, 255));
		panel.setBounds(12, 10, 222, 228);
		removeFrame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lb_name = new JLabel("주식명");
		lb_name.setBounds(12, 10, 66, 24);
		panel.add(lb_name);
		
		JLabel lb_asset = new JLabel("보유주");
		lb_asset.setBounds(12, 60, 66, 24);
		panel.add(lb_asset);
		
		JLabel lb_price_t = new JLabel("현재가");
		lb_price_t.setBounds(12, 110, 66, 24);
		panel.add(lb_price_t);
		
		JLabel lb_desc = new JLabel("설명");
		lb_desc.setBounds(12, 160, 66, 24);
		panel.add(lb_desc);
		
		JLabel name = new JLabel("");
		name.setBounds(90, 10, 120, 24);
		panel.add(name);
		
		JLabel asset = new JLabel("");
		asset.setBounds(90, 60, 120, 24);
		panel.add(asset);
		
		JLabel price_t = new JLabel("");
		price_t.setBounds(90, 110, 120, 24);
		panel.add(price_t);
		
		JTextArea desc = new JTextArea();
		desc.setOpaque(false);
		desc.setLineWrap(true);
		desc.setEditable(false);
		desc.setDragEnabled(true);
		desc.setBounds(90, 160, 120, 58);
		panel.add(desc);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(246, 10, 183, 109);
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
				price_t.setText(stock.getPrice_t());
				desc.setText(stock.getDescription());
			}
		};
		localList.addListSelectionListener(e1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(246, 129, 183, 109);
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
		ListSelectionListener e2 = new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				Stock stock = null;
				for(Stock s : foreignStocks) {
					if(s.getName().equals(foreignList.getSelectedValue())) {
						stock = s;
						break;
					}
				}
				if(stock == null) return;
				name.setText(stock.getName());
				asset.setText(""+stock.getAsset());
				price_t.setText(stock.getPrice_t());
				desc.setText(stock.getDescription());
			}
		};
		foreignList.addListSelectionListener(e2);
		
		JButton btn_local = new JButton("삭제");
		btn_local.setBounds(452, 31, 60, 60);
		removeFrame.getContentPane().add(btn_local);
		btn_local.addMouseListener(new MouseAdapter() {
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
		JButton btn_foreign = new JButton("삭제");
		btn_foreign.setBounds(452, 152, 60, 60);
		removeFrame.getContentPane().add(btn_foreign);
		btn_foreign.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				for(Stock stock : foreignStocks) {
					if(stock.getName().equals(foreignList.getSelectedValue())) {
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
						foreignStocks.remove(target);
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
		btn_cancle.setBounds(230, 245, 60, 60);
		removeFrame.getContentPane().add(btn_cancle);
		btn_cancle.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				removeFrame.setVisible(false);
			}
		});
		
		removeFrame.setVisible(true);
	}
	public void check(ArrayList<Stock> list, Stock target) {
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
				list.remove(target);
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
}
