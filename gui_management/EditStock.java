package gui_management;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.*;

import interfaces.BasicGUI;

public class EditStock extends JFrame implements BasicGUI{
	private static final long serialVersionUID = 634635532486231804L;

	public void printGUI() {
		
		JFrame editFrame = new JFrame();
		editFrame.getContentPane().setBackground(Color.WHITE);
		editFrame.setType(Type.UTILITY);
		editFrame.setTitle("Edit Stock");
		editFrame.setPreferredSize(new Dimension(450, 300));
		editFrame.setBounds(100, 100, 450, 300);
		editFrame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(14, 60, 410, 120);
		editFrame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lb_changes = new JLabel("�������");
		lb_changes.setHorizontalAlignment(SwingConstants.CENTER);
		lb_changes.setFont(new Font("����", Font.PLAIN, 15));
		lb_changes.setBounds(175, 10, 60, 18);
		panel.add(lb_changes);
		
		JLabel assetChange = new JLabel("New label");
		assetChange.setFont(new Font("����", Font.PLAIN, 15));
		assetChange.setBounds(12, 50, 386, 18);
		panel.add(assetChange);
		
		JLabel lb_asset = new JLabel("���� �ֽ� ��");
		lb_asset.setFont(new Font("����", Font.PLAIN, 15));
		lb_asset.setBounds(12, 28, 80, 18);
		panel.add(lb_asset);
		
		JLabel lb_desc = new JLabel("�ֽ� ����");
		lb_desc.setFont(new Font("����", Font.PLAIN, 15));
		lb_desc.setBounds(12, 72, 80, 18);
		panel.add(lb_desc);
		
		JLabel descChange = new JLabel("New label");
		descChange.setFont(new Font("����", Font.PLAIN, 15));
		descChange.setBounds(12, 92, 386, 18);
		panel.add(descChange);
		
		JLabel introduce = new JLabel("������ �ֽ��� �ֽ��ڵ带 �Է��ϰ�, Ȯ�� ��ư�� �����ּ���.");
		introduce.setHorizontalAlignment(SwingConstants.CENTER);
		introduce.setFont(new Font("����", Font.PLAIN, 12));
		introduce.setBounds(12, 10, 410, 15);
		editFrame.getContentPane().add(introduce);
		
		JLabel input = new JLabel("�Է� : ");
		input.setBounds(110, 28, 36, 15);
		editFrame.getContentPane().add(input);
		
		JButton btnNewButton = new JButton("Ȯ��");
		btnNewButton.setPreferredSize(new Dimension(60, 60));
		btnNewButton.setMinimumSize(new Dimension(60, 60));
		btnNewButton.setMaximumSize(new Dimension(60, 60));
		btnNewButton.setMargin(new Insets(0, 0, 0, 0));
		btnNewButton.setBounds(143, 191, 60, 60);
		editFrame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("���");
		btnNewButton_1.setPreferredSize(new Dimension(60, 60));
		btnNewButton_1.setMinimumSize(new Dimension(60, 60));
		btnNewButton_1.setMaximumSize(new Dimension(60, 60));
		btnNewButton_1.setMargin(new Insets(0, 0, 0, 0));
		btnNewButton_1.setBounds(215, 191, 60, 60);
		editFrame.getContentPane().add(btnNewButton_1);
		
		editFrame.setVisible(true);
	}

}
