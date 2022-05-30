package gui_management;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import interfaces.BasicGUI;

public class Statistics extends JFrame implements BasicGUI{
	private static final long serialVersionUID = -5443809365075419678L;

	public void printGUI() {
		JFrame statFrame = new JFrame();
		statFrame.setAlwaysOnTop(true);
		statFrame.setBackground(new Color(255, 255, 255));
		statFrame.getContentPane().setBackground(new Color(255, 255, 255));
		statFrame.getContentPane().setLayout(null);
		
		JPanel p_local = new JPanel();
		p_local.setAlignmentX(0.0f);
		p_local.setBackground(new Color(255, 255, 255));
		p_local.setBounds(-5, 0, 220, 271);
		statFrame.getContentPane().add(p_local);
		p_local.setLayout(null);
		
		JLabel lb_local = new JLabel("Local");
		lb_local.setHorizontalAlignment(SwingConstants.CENTER);
		lb_local.setBounds(12, 10, 196, 15);
		p_local.add(lb_local);
		
		JTextPane locals = new JTextPane();
		locals.setBorder(new LineBorder(new Color(0, 0, 0)));
		locals.setBounds(12, 35, 196, 163);
		p_local.add(locals);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(12, 201, 196, 60);
		p_local.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lb_totalAsset_l = new JLabel("total asset");
		lb_totalAsset_l.setBounds(5, 10, 57, 15);
		panel_2.add(lb_totalAsset_l);
		
		JLabel totalAsset_l = new JLabel("total asset");
		totalAsset_l.setBounds(66, 10, 125, 15);
		panel_2.add(totalAsset_l);
		
		JLabel lb_netChange_l = new JLabel("percent");
		lb_netChange_l.setBounds(5, 35, 57, 15);
		panel_2.add(lb_netChange_l);
		
		JLabel netChange_l = new JLabel("percentage");
		netChange_l.setBounds(66, 35, 125, 15);
		panel_2.add(netChange_l);
		
		JPanel p_foreign = new JPanel();
		p_foreign.setAlignmentX(0.0f);
		p_foreign.setBackground(new Color(255, 255, 255));
		p_foreign.setBounds(219, 0, 220, 271);
		statFrame.getContentPane().add(p_foreign);
		p_foreign.setLayout(null);
		
		JLabel lb_Foreign = new JLabel("Foreign");
		lb_Foreign.setHorizontalAlignment(SwingConstants.CENTER);
		lb_Foreign.setBounds(12, 10, 196, 15);
		p_foreign.add(lb_Foreign);
		
		JTextPane foreigns = new JTextPane();
		foreigns.setBorder(new LineBorder(new Color(0, 0, 0)));
		foreigns.setBounds(12, 35, 196, 163);
		p_foreign.add(foreigns);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2_1.setBackground(Color.WHITE);
		panel_2_1.setBounds(12, 201, 196, 60);
		p_foreign.add(panel_2_1);
		
		JLabel lb_totalAsset_f = new JLabel("total asset");
		lb_totalAsset_f.setBounds(5, 10, 57, 15);
		panel_2_1.add(lb_totalAsset_f);
		
		JLabel totalAsset_f = new JLabel("total asset");
		totalAsset_f.setBounds(66, 10, 125, 15);
		panel_2_1.add(totalAsset_f);
		
		JLabel lb_netChange_f = new JLabel("percent");
		lb_netChange_f.setBounds(5, 35, 57, 15);
		panel_2_1.add(lb_netChange_f);
		
		JLabel netChange_f = new JLabel("percentage");
		netChange_f.setBounds(66, 35, 125, 15);
		panel_2_1.add(netChange_f);
		statFrame.setResizable(false);
		statFrame.setTitle("Statistics");
		statFrame.setType(Type.UTILITY);
		statFrame.setBounds(100, 100, 450, 300);
		statFrame.setVisible(true);
	}

}
