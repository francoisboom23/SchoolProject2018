//FETCH AVANT TOUS CHANGEMENTS SOUS PEINE DE MORT!

import java.awt.Container;
import javax.swing.*;

public class loginScreen extends JFrame{
	
	private Container cont;
	
	public loginScreen() {
		cont = getContentPane();
		loginScreenPanel f2 = new loginScreenPanel(this);
		add(f2);
		
		setBounds(850,450,300,140);
		setTitle("login screen");
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
