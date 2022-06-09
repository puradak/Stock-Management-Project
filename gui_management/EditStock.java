package gui_management;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.LineBorder;
import data.Stock;
import interfaces.BasicGUI;
import log_management.LoadManager;

public class EditStock extends JFrame implements BasicGUI {
	private static final long serialVersionUID = 634635532486231804L;
	private LoadManager loader = LoadManager.getLoadManagerObject();
	private JFrame editFrame = new JFrame();
	private JTextField tF_input = new JTextField();
	private JLabel lb_narr = new JLabel("변경할 주식을 선택하고, 확인 버튼을 눌러주세요.");
	private Stock stock = null;
	private int saveAsset = 0;
	private int sqFlag = 0;
	
	private ArrayList<DefaultListModel<String>> models = new ArrayList<>(); 
	private ArrayList<JList<String>> lists= new ArrayList<>();
	private JScrollPane[] scPane = {
			new JScrollPane(),	// local
			new JScrollPane()	// foreign
			};

	private JButton[] btn_selects = {
			new JButton("선택하기"),	//local
			new JButton("선택하기")	//foreign
	};
	
	public EditStock() {
		editFrame.setAlwaysOnTop(true); 
		editFrame.setType(Type.UTILITY);
		editFrame.setTitle("Edit Stock");
		editFrame.setBounds(100, 100, 450, 330);
		editFrame.getContentPane().setBackground(Color.WHITE);
		editFrame.getContentPane().setLayout(null);
		
		lb_narr.setHorizontalAlignment(SwingConstants.CENTER);
		lb_narr.setFont(new Font("굴림", Font.PLAIN, 12));
		lb_narr.setBounds(12, 10, 410, 15);
		editFrame.getContentPane().add(lb_narr);
		
		tF_input.setBounds(67, 167, 330, 21);
		tF_input.setColumns(10);
		editFrame.getContentPane().add(tF_input);
		
		for(int i=0; i<2; i++) {
			models.add(new DefaultListModel<>()); // local
			lists.add(new JList<String>(models.get(i)));
		}
		scPane[0].setBounds(32, 30, 182, 100);
		scPane[1].setBounds(215, 30, 182, 100);
		btn_selects[0].setBounds(38, 135, 168, 30);
		btn_selects[1].setBounds(223, 135, 168, 30);
	}

	public void printGUI() {
		JLabel lb_input = new JLabel("입력 :");
		lb_input.setBounds(32, 170, 57, 15);
		editFrame.getContentPane().add(lb_input);
		
		JLabel lb_changes = new JLabel("");
		lb_changes.setBorder(new LineBorder(new Color(0, 0, 0)));
		lb_changes.setOpaque(true);
		lb_changes.setBackground(new Color(240, 248, 255));
		lb_changes.setBounds(32, 190, 365, 28);
		editFrame.getContentPane().add(lb_changes);

		for(int i=0; i<2; i++) {
			editFrame.getContentPane().add(scPane[i]);
			
			for(Stock stock : loader.getList(i)) {
				models.get(i).addElement(stock.getName());
			}
			scPane[i].setViewportView(lists.get(i));
			
			JButton button = btn_selects[i];
			int index = i;
			editFrame.getContentPane().add(button);
			button.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if(sqFlag != 0) return;
					saveAsset = 0;
					for(Stock s : loader.getList(index)) {
						try{
							if(lists.get(index).getSelectedValue().equals(s.getName())) {
							stock = s;
							saveAsset = s.getAsset();
							}
						} catch ( NullPointerException e1 ) {
						}
					}
					lb_narr.setText("변경할 보유 주식 수를 입력하세요. 현재 "+saveAsset+"주를 보유중입니다.");
					tF_input.setText("");
				}
			});
		}
		JButton btn_ok = new JButton("변경");
		btn_ok.setBounds(150, 225, 60, 60);
		editFrame.getContentPane().add(btn_ok);
		btn_ok.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(sqFlag == 0) {
					try {
						if(Integer.parseInt(tF_input.getText()) <= 0 ) throw new NumberFormatException();
						stock.setAsset(Integer.parseInt(tF_input.getText()));
						lb_narr.setText("변경할 주식 설명을 입력하세요. 취소를 누르면 변경하지 않습니다.");
						lb_changes.setText("[변경사항] "+saveAsset+"주 → "+tF_input.getText()+"주");
						tF_input.setText("");
						sqFlag++;
					} catch (NumberFormatException e1) {
						lb_narr.setText("잘못 입력하셨습니다. 변경할 주식 수를 입력하세요.(현재 : "+saveAsset+"주");
					}
				}
				else {
					String saveDesc = stock.getDescription();
					stock.setDescription(tF_input.getText());
					sqFlag = 0;
					lb_narr.setText("변경되었습니다.");
					lb_changes.setText("[변경사항] "+saveDesc+" → "+tF_input.getText());
					tF_input.setText("");
				}
			}
		});
		JButton btn_cancle = new JButton("취소");
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
