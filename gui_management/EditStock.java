package gui_management;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.LineBorder;

import data.Stock;
import file_management.LoadManager;
import interfaces.BasicGUI;

public class EditStock extends JFrame implements BasicGUI {
	private static final long serialVersionUID = 634635532486231804L;
	private LoadManager loader = LoadManager.getLoadManagerObject();
	private JFrame editFrame = new JFrame();
	private JTextField tF_input = new JTextField();
	private JLabel lb_narr = new JLabel("������ �ֽ��� �����ϰ�, Ȯ�� ��ư�� �����ּ���.");
	private Stock stock = null;
	private int saveAsset= 0;
	private int sqFlag = 0;
	
	public void printGUI() {
		editFrame.setAlwaysOnTop(true); 
		editFrame.setType(Type.UTILITY);
		editFrame.setTitle("Edit Stock");
		editFrame.setBounds(100, 100, 450, 330);
		
		editFrame.getContentPane().setBackground(Color.WHITE);
		editFrame.getContentPane().setLayout(null);
		
		lb_narr.setHorizontalAlignment(SwingConstants.CENTER);
		lb_narr.setFont(new Font("����", Font.PLAIN, 12));
		lb_narr.setBounds(12, 10, 410, 15);
		editFrame.getContentPane().add(lb_narr);
		
		tF_input.setBounds(67, 167, 330, 21);
		tF_input.setColumns(10);
		editFrame.getContentPane().add(tF_input);
		
		JLabel lb_input = new JLabel("�Է� :");
		lb_input.setBounds(32, 170, 57, 15);
		editFrame.getContentPane().add(lb_input);
		
		JLabel lb_changes = new JLabel("");
		lb_changes.setBorder(new LineBorder(new Color(0, 0, 0)));
		lb_changes.setOpaque(true);
		lb_changes.setBackground(new Color(240, 248, 255));
		lb_changes.setBounds(32, 190, 365, 28);
		editFrame.getContentPane().add(lb_changes);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 30, 182, 100);
		editFrame.getContentPane().add(scrollPane);
		
		DefaultListModel<String> localModel = new DefaultListModel<>();
		for(Stock stock : loader.getList(0)) {
			localModel.addElement(stock.getName());
		}
		JList<String> localList = new JList<>(localModel);
		scrollPane.setViewportView(localList);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(215, 30, 182, 100);
		editFrame.getContentPane().add(scrollPane_1);
		
		DefaultListModel<String> foreignModel = new DefaultListModel<>();
		for(Stock stock : loader.getList(1)) {
			foreignModel.addElement(stock.getName());
		}
		JList<String> foreignList = new JList<>(foreignModel);
		scrollPane_1.setViewportView(foreignList);
		
		JButton btn_select1 = new JButton("�����ϱ�");
		btn_select1.setBounds(38, 135, 168, 30);
		editFrame.getContentPane().add(btn_select1);
		btn_select1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(sqFlag != 0) return;
				int saveAsset= 0;
				for(Stock s : loader.getList(0)) {
					try{
						if(localList.getSelectedValue().equals(s.getName())) {
						stock = s;
						saveAsset = stock.getAsset();
						}
					} catch ( NullPointerException e1 ) {}
				}
				lb_narr.setText("������ ���� �ֽ� ���� �Է��ϼ���. ���� "+saveAsset+"�ָ� �������Դϴ�.");
			}
		});
		
		JButton btn_select2 = new JButton("�����ϱ�");
		btn_select2.setBounds(223, 135, 168, 30);
		editFrame.getContentPane().add(btn_select2);
		btn_select2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(sqFlag != 0) return;
				for(Stock s : loader.getList(1)) {
					if(foreignList.getSelectedValue().equals(s.getName())) {
						stock = s;
						saveAsset = stock.getAsset();
					}
				}
				lb_narr.setText("������ ���� �ֽ� ���� �Է��ϼ���. ���� "+saveAsset+"�ָ� �������Դϴ�.");
				tF_input.setText("");
			}
		});
		
		JButton btn_ok = new JButton("����");
		btn_ok.setBounds(150, 225, 60, 60);
		editFrame.getContentPane().add(btn_ok);
		btn_ok.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(sqFlag == 0) {
					try {
						stock.setAsset(Integer.parseInt(tF_input.getText()));
						lb_narr.setText("������ �ֽ� ������ �Է��ϼ���. ��Ҹ� ������ �������� �ʽ��ϴ�.");
						lb_changes.setText("[�������] "+saveAsset+"�� �� "+tF_input.getText()+"��");
						tF_input.setText("");
						sqFlag++;
					} catch (NumberFormatException e1) {
						lb_narr.setText("�߸� �Է��ϼ̽��ϴ�. ������ �ֽ� ���� �Է��ϼ���.(���� : "+saveAsset+"��");
					}
				}
				else {
					String saveDesc = stock.getDescription();
					stock.setDescription(tF_input.getText());
					sqFlag = 0;
					lb_narr.setText("����Ǿ����ϴ�.");
					lb_changes.setText("[�������] "+saveDesc+" �� "+tF_input.getText());
					tF_input.setText("");
					
				}
			}
		});
		
		JButton btn_cancle = new JButton("���");
		btn_cancle.setBounds(220, 225, 60, 60);
		editFrame.getContentPane().add(btn_cancle);
		btn_cancle.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				editFrame.setVisible(false);
			}
		});
		
		editFrame.setVisible(true);
	}

}
