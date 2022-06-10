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
	private LoadManager loader = LoadManager.getLoadManagerObject();
	private Stock target = null;
	private JFrame removeFrame = new JFrame("Remove Stock");
	private JPanel panel = new JPanel();
	private JScrollPane[] scPanes = { new JScrollPane(), new JScrollPane() };
	private ArrayList<DefaultListModel<String>> models = new ArrayList<>();
	private ArrayList<JList<String>> lists = new ArrayList<>();
	private JTextArea desc = new JTextArea();
	private JLabel[] labels = { new JLabel(), new JLabel(), new JLabel(), new JLabel("") };
	private JLabel[] lb_labels = { new JLabel("주식명"), new JLabel("보유주"), new JLabel("현재가"), new JLabel("설 명") };
	private JButton[] btn_remove = { new JButton("삭제"), new JButton("삭제") };
	public RemoveStock() {
		removeFrame.setType(Type.UTILITY);
		removeFrame.setResizable(false);
		removeFrame.setAlwaysOnTop(true);
		removeFrame.getContentPane().setBackground(Color.WHITE);
		removeFrame.setBounds(100, 100, 550, 350);
		removeFrame.getContentPane().setLayout(null);
		
		panel.setBackground(new Color(240, 248, 255));
		panel.setBounds(12, 10, 222, 228);
		panel.setLayout(null);
		removeFrame.getContentPane().add(panel);
		
		desc.setOpaque(false);
		desc.setLineWrap(true);
		desc.setEditable(false);
		desc.setDragEnabled(true);
		desc.setBounds(90, 160, 120, 58);
		panel.add(desc);
		
		for(int i=0; i<4;i++) {
			labels[i].setBounds(90, 10+50*i, 120, 24);
			lb_labels[i].setBounds(12, 10+50*i, 66, 24);
			panel.add(labels[i]);
			panel.add(lb_labels[i]);
		}
		for(int i=0; i<2; i++) {
			btn_remove[i].setBounds(452, 31+121*i, 60, 60);
			scPanes[i].setBounds(246, 10+119*i, 183, 109);
			removeFrame.getContentPane().add(scPanes[i]);
			removeFrame.getContentPane().add(btn_remove[i]);
			int index = i;
			btn_remove[i].addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					for(Stock stock : loader.getList(index)) {
						if(stock.getName().equals(lists.get(index).getSelectedValue())) {
							target = stock;
							break;
						}
					}
					if(target == null) return;
					warning(loader.getList(index));
				}
			});
			models.add(new DefaultListModel<String>());
			for(Stock stock : loader.getList(i)) {
				models.get(i).addElement(stock.getName());
			}
			lists.add(new JList<String>(models.get(i)));
			lists.get(i).setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			lists.get(i).setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			scPanes[i].setViewportView(lists.get(i));
			
			ListSelectionListener e1 = new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e1) {
					Stock stock = null;
					for(Stock s : loader.getList(index)) {
						if(s.getName().equals(lists.get(index).getSelectedValue())) {
							stock = s;
							break;
						}
					}
					if(stock == null) return;
					labels[0].setText(stock.getName());
					labels[1].setText(""+stock.getAsset());
					labels[2].setText(stock.getPrice_t());
					desc.setText(stock.getDescription());
				}
			};
			lists.get(i).addListSelectionListener(e1);
		}
		removeFrame.setVisible(true);
	}
	
	public void printGUI() {		
		JButton btn_cancle = new JButton("취소");
		btn_cancle.setBounds(230, 245, 60, 60);
		removeFrame.getContentPane().add(btn_cancle);
		btn_cancle.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				removeFrame.setVisible(false);
			}
		});
	}
	public boolean done() {
		JFrame removed = new JFrame();
		removed.setType(Type.UTILITY);
		removed.setAlwaysOnTop(true);
		removed.setBounds(100, 100, 271, 221);
		removed.getContentPane().setLayout(null);
		
		JLabel lb_narr = new JLabel("삭제되었습니다.");
		lb_narr.setHorizontalAlignment(SwingConstants.CENTER);
		lb_narr.setBounds(12, 42, 231, 30);
		removed.getContentPane().add(lb_narr);
		
		JButton btn_done = new JButton("확인");
		btn_done.setBounds(83, 82, 90, 50);
		removed.getContentPane().add(btn_done);
		btn_done.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				removed.setVisible(false);
			}
		});
		removed.setVisible(true);
		return true;
	}
	public void warning(ArrayList<Stock> stocks) {
		JFrame warningFrame = new JFrame();
		warningFrame.setAlwaysOnTop(true);
		warningFrame.setTitle("RemoveStock");
		warningFrame.setType(Type.UTILITY);
		warningFrame.setBounds(100, 100, 400, 200);
		warningFrame.getContentPane().setLayout(null);
		
		JButton btn_ok = new JButton("삭제");
		btn_ok.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_ok.setBounds(120, 90, 60, 60);
		warningFrame.getContentPane().add(btn_ok);
		btn_ok.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				stocks.remove(target);
				if(done()) warningFrame.setVisible(false);
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