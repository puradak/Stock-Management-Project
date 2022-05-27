package gui_management;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.List;

import javax.swing.*;

import interfaces.BasicGUI;

public class ShowStock extends JFrame implements BasicGUI{
	private static final long serialVersionUID = -2920264366783929183L;
	
	public void printGUI() {
			JFrame f_showStock = new JFrame();
			f_showStock.setTitle("Show Stock");
			f_showStock.setType(Type.UTILITY);
			f_showStock.getContentPane().setBackground(Color.WHITE);
			f_showStock.getContentPane().setLayout(null);
			f_showStock.setSize(new Dimension(650, 521));
			f_showStock.setResizable(false);
			
			JPanel p_property = new JPanel();
			p_property.setBackground(new Color(240, 248, 255));
			p_property.setBounds(12, 141, 190, 167);
			f_showStock.getContentPane().add(p_property);
			p_property.setLayout(null);
			
			JLabel lb_name = new JLabel("name");
			lb_name.setBounds(28, 10, 55, 20);
			lb_name.setFont(new Font("±¼¸²", Font.PLAIN, 16));
			lb_name.setPreferredSize(new Dimension(90, 20));
			p_property.add(lb_name);
			
			JLabel name = new JLabel("New label");
			name.setBounds(90, 10, 100, 20);
			name.setFont(new Font("±¼¸²", Font.PLAIN, 16));
			name.setPreferredSize(new Dimension(90, 20));
			p_property.add(name);
			
			JLabel lb_price_t = new JLabel("price_t");
			lb_price_t.setBounds(28, 35, 55, 20);
			lb_price_t.setFont(new Font("±¼¸²", Font.PLAIN, 16));
			lb_price_t.setPreferredSize(new Dimension(90, 20));
			p_property.add(lb_price_t);
			
			JLabel price_t = new JLabel("New label");
			price_t.setBounds(90, 35, 100, 20);
			price_t.setFont(new Font("±¼¸²", Font.PLAIN, 16));
			price_t.setPreferredSize(new Dimension(90, 20));
			p_property.add(price_t);
			
			JLabel lb_price_y = new JLabel("price_y");
			lb_price_y.setBounds(28, 60, 55, 20);
			lb_price_y.setFont(new Font("±¼¸²", Font.PLAIN, 16));
			lb_price_y.setPreferredSize(new Dimension(90, 20));
			p_property.add(lb_price_y);
			
			JLabel price_y = new JLabel("New label");
			price_y.setBounds(90, 60, 100, 20);
			price_y.setFont(new Font("±¼¸²", Font.PLAIN, 16));
			price_y.setPreferredSize(new Dimension(90, 20));
			p_property.add(price_y);
			
			JLabel lb_change = new JLabel("change");
			lb_change.setBounds(28, 85, 55, 20);
			lb_change.setFont(new Font("±¼¸²", Font.PLAIN, 16));
			lb_change.setPreferredSize(new Dimension(90, 20));
			p_property.add(lb_change);
			
			JLabel change = new JLabel("New label");
			change.setBounds(90, 85, 90, 20);
			change.setFont(new Font("±¼¸²", Font.PLAIN, 16));
			change.setPreferredSize(new Dimension(90, 20));
			p_property.add(change);
			
			JLabel lb_asset = new JLabel("asset");
			lb_asset.setBounds(28, 110, 55, 20);
			lb_asset.setFont(new Font("±¼¸²", Font.PLAIN, 16));
			lb_asset.setPreferredSize(new Dimension(90, 20));
			p_property.add(lb_asset);
			
			JLabel asset = new JLabel("New label");
			asset.setBounds(90, 110, 100, 20);
			asset.setFont(new Font("±¼¸²", Font.PLAIN, 16));
			asset.setPreferredSize(new Dimension(90, 20));
			p_property.add(asset);
			
			JLabel lb_wealth = new JLabel("wealth");
			lb_wealth.setBounds(28, 135, 55, 20);
			lb_wealth.setFont(new Font("±¼¸²", Font.PLAIN, 16));
			lb_wealth.setPreferredSize(new Dimension(90, 20));
			p_property.add(lb_wealth);
			
			JLabel wealth = new JLabel("New label");
			wealth.setBounds(90, 135, 100, 20);
			wealth.setFont(new Font("±¼¸²", Font.PLAIN, 16));
			wealth.setPreferredSize(new Dimension(90, 20));
			p_property.add(wealth);
			
			JPanel p_chartImage = new JPanel();
			p_chartImage.setBackground(Color.WHITE);
			p_chartImage.setForeground(Color.WHITE);
			p_chartImage.setBounds(214, 10, 415, 298);
			f_showStock.getContentPane().add(p_chartImage);
			
			JPanel p_logoImage = new JPanel();
			p_logoImage.setBackground(Color.WHITE);
			p_logoImage.setForeground(Color.WHITE);
			p_logoImage.setBounds(12, 10, 190, 121);
			f_showStock.getContentPane().add(p_logoImage);
			
			JPanel panel = new JPanel();
			panel.setBackground(new Color(240, 248, 255));
			panel.setBounds(125, 318, 400, 160);
			f_showStock.getContentPane().add(panel);
			panel.setLayout(null);
			
			List list = new List();
			list.setBounds(10, 33, 170, 88);
			list.setMultipleSelections(false);
			panel.add(list);
			
			List list_2 = new List();
			list_2.setMultipleSelections(false);
			list_2.setBounds(220, 33, 170, 88);
			panel.add(list_2);
			
			JLabel lb_local = new JLabel("±¹³» ÁÖ½Ä");
			lb_local.setHorizontalAlignment(SwingConstants.CENTER);
			lb_local.setBounds(10, 10, 170, 15);
			panel.add(lb_local);
			
			JLabel lo_foreign = new JLabel("±¹¿Ü ÁÖ½Ä");
			lo_foreign.setHorizontalAlignment(SwingConstants.CENTER);
			lo_foreign.setBounds(220, 10, 170, 15);
			panel.add(lo_foreign);
	}

}
