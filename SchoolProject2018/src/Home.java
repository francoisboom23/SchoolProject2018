import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Home extends JFrame{
	private JLabel welcomeText;
	
	public Home() {
		super("Welcome");
		setBounds(850,450,300,100);
		setResizable(false);
		
		welcomeText = new JLabel("IESN Calculator Student Pro DVD Architect 2048");
		welcomeText.setHorizontalAlignment(SwingConstants.CENTER);
		add(welcomeText);
		
//close program
		WindowClose w = new WindowClose();
		this.addWindowListener(w);
		
		setVisible(true);
	}
	
//close program function
	class WindowClose extends WindowAdapter {
		public void windowClosing(WindowEvent w) {
			Home.this.dispose();
			Windows f1 = new Windows();
		}
	};
}
