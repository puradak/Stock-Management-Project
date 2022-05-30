package gui_management;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import interfaces.BasicGUI;

public class About extends JFrame implements BasicGUI{
	private static final long serialVersionUID = 2312720291577326202L;

	public void printGUI() {
		JFrame frame = new JFrame();
		frame.setAlwaysOnTop(true);
		frame.setResizable(false);
		frame.setType(Type.UTILITY);
		frame.setBounds(100, 100, 450, 300);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Stock-Management-System");
		lblNewLabel.setFont(new Font("±¼¸²", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 10, 420, 44);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel myName = new JLabel("My name : Lee Jeong Hyun");
		myName.setBounds(12, 84, 158, 15);
		frame.getContentPane().add(myName);
		
		JLabel stNumber = new JLabel("Student Number : 2019011949 @ GNU");
		stNumber.setBounds(12, 109, 214, 15);
		frame.getContentPane().add(stNumber);
		
		JLabel lb_gitURL = new JLabel("My GIt URL :");
		lb_gitURL.setBounds(12, 134, 72, 15);
		frame.getContentPane().add(lb_gitURL);
		
		JTextField gitURL = new JTextField();
		gitURL.setDisabledTextColor(new Color(0, 0, 255));
		gitURL.setEditable(false);
		gitURL.setDragEnabled(true);
		gitURL.setBorder(null);
		gitURL.setBackground(new Color(245, 245, 245));
		gitURL.setText("https://github.com/puradak/Stock-Management-Project.git");
		gitURL.setBounds(90, 130, 340, 21);
		frame.getContentPane().add(gitURL);
		gitURL.setColumns(10);
		
		JLabel lb_LibraryURL = new JLabel("Jsoup Library : ");
		lb_LibraryURL.setBounds(12, 159, 89, 15);
		frame.getContentPane().add(lb_LibraryURL);
		
		JTextField LibraryURL = new JTextField();
		LibraryURL.setText("https://jsoup.org/");
		LibraryURL.setEditable(false);
		LibraryURL.setDragEnabled(true);
		LibraryURL.setDisabledTextColor(new Color(0, 0, 255));
		LibraryURL.setColumns(10);
		LibraryURL.setBorder(null);
		LibraryURL.setBackground(new Color(245, 245, 245));
		LibraryURL.setBounds(102, 156, 120, 21);
		frame.getContentPane().add(LibraryURL);
		
		frame.setVisible(true);
	}

}
