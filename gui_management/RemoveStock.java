package gui_management;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Window.Type;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import interfaces.BasicGUI;

public class RemoveStock extends JFrame implements BasicGUI{
	private static final long serialVersionUID = -7973139397981674187L;

	public void printGUI() {
		JFrame removeFrame = new JFrame();
		removeFrame.getContentPane().setBackground(new Color(255, 255, 255));
		removeFrame.setType(Type.UTILITY);
		removeFrame.setTitle("Remove Stock");
		removeFrame.setAlwaysOnTop(true);
		removeFrame.setBounds(100, 100, 450, 300);
		removeFrame.getContentPane().setLayout(null);
		
		JLabel introduce = new JLabel("������ �ֽ��� �ֽ��ڵ带 �Է��ϰ�, Ȯ�� ��ư�� �����ּ���.");
		introduce.setBackground(new Color(255, 255, 255));
		introduce.setHorizontalAlignment(SwingConstants.CENTER);
		introduce.setFont(new Font("����", Font.PLAIN, 12));
		introduce.setBounds(12, 10, 410, 15);
		removeFrame.getContentPane().add(introduce);
		
		JButton btnNewButton = new JButton("\uD655\uC778");
		btnNewButton.setPreferredSize(new Dimension(60, 60));
		btnNewButton.setBounds(143, 191, 60, 60);
		removeFrame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\uCDE8\uC18C");
		btnNewButton_1.setPreferredSize(new Dimension(60, 60));
		btnNewButton_1.setBounds(215, 191, 60, 60);
		removeFrame.getContentPane().add(btnNewButton_1);
		
		JList localLIst = new JList();
		localLIst.setModel(new AbstractListModel() {
			String[] values = new String[] {"apple", "orange", "grape"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		localLIst.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		localLIst.setBounds(180, 35, 120, 150);
		removeFrame.getContentPane().add(localLIst);
		
		JList foreignList = new JList();
		foreignList.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		foreignList.setBounds(305, 35, 120, 150);
		removeFrame.getContentPane().add(foreignList);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(10, 35, 165, 150);
		removeFrame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lb_name = new JLabel("�ֽĸ�");
		lb_name.setBounds(new Rectangle(0, 0, 0, 20));
		lb_name.setFont(new Font("����", Font.PLAIN, 15));
		lb_name.setHorizontalAlignment(SwingConstants.CENTER);
		lb_name.setBounds(5, 10, 42, 20);
		panel.add(lb_name);
		
		JLabel lb_asset = new JLabel("������");
		lb_asset.setBounds(new Rectangle(0, 0, 0, 20));
		lb_asset.setHorizontalAlignment(SwingConstants.CENTER);
		lb_asset.setFont(new Font("����", Font.PLAIN, 15));
		lb_asset.setBounds(5, 40, 42, 20);
		panel.add(lb_asset);
		
		JLabel lb_desc = new JLabel("\uC124 \uBA85");
		lb_desc.setBounds(new Rectangle(0, 0, 0, 20));
		lb_desc.setHorizontalAlignment(SwingConstants.CENTER);
		lb_desc.setFont(new Font("����", Font.PLAIN, 15));
		lb_desc.setBounds(5, 70, 42, 20);
		panel.add(lb_desc);
		
		JLabel name = new JLabel("\uC8FC\uC2DD\uBA85");
		name.setHorizontalAlignment(SwingConstants.LEFT);
		name.setFont(new Font("����", Font.PLAIN, 15));
		name.setBounds(new Rectangle(0, 0, 0, 20));
		name.setBounds(59, 10, 94, 20);
		panel.add(name);
		
		JLabel asset = new JLabel("\uC8FC\uC2DD\uBA85");
		asset.setHorizontalAlignment(SwingConstants.LEFT);
		asset.setFont(new Font("����", Font.PLAIN, 15));
		asset.setBounds(new Rectangle(0, 0, 0, 20));
		asset.setBounds(59, 40, 94, 20);
		panel.add(asset);
		
		JTextPane desc = new JTextPane();
		desc.setText("description\u3131\u3134\u3137");
		desc.setBounds(57, 67, 105, 75);
		panel.add(desc);
		
		removeFrame.setVisible(true);
	}

}
