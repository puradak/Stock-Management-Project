package gui_management;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Window.Type;

import javax.swing.*;

import interfaces.BasicGUI;

public class AddStock extends JFrame implements BasicGUI{
	private static final long serialVersionUID = 5227932774846019655L;

	private JFrame f;
	
	public void printGUI() {
		f = new JFrame();
		f.getContentPane().setBackground(Color.WHITE);
		f.getContentPane().setLayout(null);
		
		JLabel lb_narr = new JLabel("보유한 주식의 주식코드를 입력하고, 확인 버튼을 누르세요.");
		lb_narr.setHorizontalTextPosition(SwingConstants.CENTER);
		lb_narr.setHorizontalAlignment(SwingConstants.CENTER);
		lb_narr.setBounds(0, 10, 432, 15);
		f.getContentPane().add(lb_narr);
		
		JPanel p_logoImage = new JPanel();
		p_logoImage.setBounds(12, 47, 200, 138);
		f.getContentPane().add(p_logoImage);
		
		JLabel lb_name = new JLabel("주식명");
		lb_name.setFont(new Font("굴림", Font.PLAIN, 15));
		lb_name.setBounds(223, 53, 42, 18);
		f.getContentPane().add(lb_name);
		
		JLabel lb_price_t = new JLabel("현재가");
		lb_price_t.setFont(new Font("굴림", Font.PLAIN, 15));
		lb_price_t.setBounds(224, 80, 42, 18);
		f.getContentPane().add(lb_price_t);
		
		JLabel lb_price_y = new JLabel("전일가");
		lb_price_y.setFont(new Font("굴림", Font.PLAIN, 15));
		lb_price_y.setBounds(224, 108, 42, 18);
		f.getContentPane().add(lb_price_y);
		
		JLabel lb_change = new JLabel("변화율");
		lb_change.setFont(new Font("굴림", Font.PLAIN, 15));
		lb_change.setBounds(224, 136, 42, 18);
		f.getContentPane().add(lb_change);
		
		JLabel lb_isOpen = new JLabel("개장여부");
		lb_isOpen.setFont(new Font("굴림", Font.PLAIN, 15));
		lb_isOpen.setBounds(224, 164, 56, 18);
		f.getContentPane().add(lb_isOpen);
		
		JLabel lb_input = new JLabel("입력 : ");
		lb_input.setBounds(112, 27, 36, 15);
		f.getContentPane().add(lb_input);
		
		JTextField tF_input = new JTextField();
		tF_input.setHorizontalAlignment(SwingConstants.LEFT);
		tF_input.setBounds(150, 26, 116, 16);
		f.getContentPane().add(tF_input);
		tF_input.setColumns(10);
		
		JButton btnNewButton = new JButton("확인");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setPreferredSize(new Dimension(60, 60));
		btnNewButton.setBounds(152, 201, 60, 60);
		f.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("취소");
		btnNewButton_1.setPreferredSize(new Dimension(60, 60));
		btnNewButton_1.setBounds(224, 201, 60, 60);
		f.getContentPane().add(btnNewButton_1);
		
		JLabel lb_d_name = new JLabel("주식명");
		lb_d_name.setFont(new Font("굴림", Font.PLAIN, 15));
		lb_d_name.setBounds(289, 53, 144, 18);
		f.getContentPane().add(lb_d_name);
		
		JLabel lb_d_price_t = new JLabel("현재가");
		lb_d_price_t.setFont(new Font("굴림", Font.PLAIN, 15));
		lb_d_price_t.setBounds(289, 80, 143, 18);
		f.getContentPane().add(lb_d_price_t);
		
		JLabel lb_d_price_y = new JLabel("전일가");
		lb_d_price_y.setFont(new Font("굴림", Font.PLAIN, 15));
		lb_d_price_y.setBounds(289, 108, 143, 18);
		f.getContentPane().add(lb_d_price_y);
		
		JLabel lb_d_change = new JLabel("변화율");
		lb_d_change.setFont(new Font("굴림", Font.PLAIN, 15));
		lb_d_change.setBounds(289, 136, 143, 18);
		f.getContentPane().add(lb_d_change);
		
		JLabel lb_d_isOpen = new JLabel("개장여부");
		lb_d_isOpen.setFont(new Font("굴림", Font.PLAIN, 15));
		lb_d_isOpen.setBounds(289, 164, 143, 18);
		f.getContentPane().add(lb_d_isOpen);
		
		f.setType(Type.UTILITY);
		f.setTitle("Add Stock");
		f.setResizable(false);
		f.setBackground(Color.WHITE);
		f.setBounds(100, 100, 450, 300);
		f.setVisible(true);
	}

	
}
