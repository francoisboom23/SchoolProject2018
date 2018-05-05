import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Home extends JFrame{
	private JLabel welcomeText;
	private JButton enter;
	private loginScreenPanel parent;
	public Home(loginScreenPanel parent) {
		super("Welcome");
		setBounds(850,450,300,100);
		setResizable(false);
		setLayout(new GridLayout(2,1,5,5));
		this.parent=parent;
//content	
		//walcome
		welcomeText = new JLabel("IESN Calculator Student Pro DVD Architect 2048");
		welcomeText.setHorizontalAlignment(SwingConstants.CENTER);
		add(welcomeText);
		//enter
		enter = new JButton("enter");
		this.add(enter);
		Butlistener k = new Butlistener();
		enter.addActionListener(k);
		
//close program
		WindowClose w = new WindowClose();
		this.addWindowListener(w);
		
		setVisible(true);
	}
	
//close program function
	class WindowClose extends WindowAdapter {
		public void windowClosing(WindowEvent w) {
			Home.this.dispose();
			Windows f1 = new Windows(parent);
		}
	}
	private class Butlistener implements ActionListener{
		public void actionPerformed( ActionEvent k){
			Home.this.dispose();
			Windows f1 = new Windows(parent);
		}
	}
}
