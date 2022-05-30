package gui_management;

import java.awt.*;
import java.awt.Window.Type;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.LineBorder;

import data.Stock;
import functions.ToolFunction;
import interfaces.BasicGUI;

public class EditStock extends JFrame implements BasicGUI{
	private static final long serialVersionUID = 634635532486231804L;
	private ToolFunction tool = ToolFunction.getToolFunctionObject();
	JFrame editFrame = new JFrame();
	JList<Stock> foreignList = new JList<Stock>();
	JTextField tF_input = new JTextField();
	JLabel lb_narr = new JLabel("변경할 주식을 선택하고, 확인 버튼을 눌러주세요.");
	public void printGUI() {
		
		editFrame.setAlwaysOnTop(true);
		editFrame.setType(Type.UTILITY);
		editFrame.setTitle("Edit Stock");
		editFrame.setBounds(100, 100, 450, 300);
		
		editFrame.getContentPane().setBackground(Color.WHITE);
		editFrame.getContentPane().setLayout(null);
		
		lb_narr.setHorizontalAlignment(SwingConstants.CENTER);
		lb_narr.setFont(new Font("굴림", Font.PLAIN, 12));
		lb_narr.setBounds(12, 10, 410, 15);
		editFrame.getContentPane().add(lb_narr);
		
		JButton btn_ok = new JButton("확인");
		btn_ok.setBounds(143, 191, 60, 60);
		editFrame.getContentPane().add(btn_ok);
		btn_ok.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				
			}
		});
		
		JButton btn_cancle = new JButton("취소");
		btn_cancle.setBounds(215, 191, 60, 60);
		editFrame.getContentPane().add(btn_cancle);
		btn_cancle.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				editFrame.setVisible(false);
			}
		});
		
		
		ArrayList<String> localNames = new ArrayList<>();
		DefaultListModel<String> localModel = new DefaultListModel<String>();
		ArrayList<Stock> stocks = tool.getList(0);
		for(int i=0; i<stocks.size(); i++) {
			localNames.add(stocks.get(i).getName());
		}
		for(String str : localNames) {
			localModel.addElement(str);
			System.out.println(str);
		}
		JList<String> localList = new JList<String>(localModel);
		localList.setBorder(new LineBorder(new Color(0, 0, 0)));
		localList.setBounds(32, 61, 182, 96);
		editFrame.getContentPane().add(localList);
		

		foreignList.setBorder(new LineBorder(new Color(0, 0, 0)));
		foreignList.setBounds(215, 61, 182, 96);
		editFrame.getContentPane().add(foreignList);
		
		tF_input.setText("1234");
		tF_input.setBounds(117, 30, 196, 21);
		editFrame.getContentPane().add(tF_input);
		tF_input.setColumns(10);
		
		JLabel lb_input = new JLabel("입력 :");
		lb_input.setBounds(83, 33, 57, 15);
		editFrame.getContentPane().add(lb_input);
		
		JLabel lb_changes = new JLabel("New label");
		lb_changes.setBorder(new LineBorder(new Color(0, 0, 0)));
		lb_changes.setOpaque(true);
		lb_changes.setBackground(new Color(240, 248, 255));
		lb_changes.setBounds(32, 160, 365, 28);
		editFrame.getContentPane().add(lb_changes);
		
		editFrame.setVisible(true);
	}

}
