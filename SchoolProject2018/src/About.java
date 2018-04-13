//FETCH AVANT TOUS CHANGEMENTS SOUS PEINE DE MORT!

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class About extends JFrame{
	private JLabel author;
	private JButton quit;
	private Windows parent;
	
	public About(Windows parent) {
//general
		super("about this program");
		this.parent=parent;
		setBounds(850,450,300,200);
		setResizable(false);
		setLayout(new GridLayout(2, 1,5,5));

//content
		author = new JLabel("<html><body><p align=\"center\">Version 1.0<br>Authors:<br>Curé Vincent<br>Devilez François</p></body></html>");
		author.setHorizontalAlignment(SwingConstants.CENTER);
		add(author);
	
		quit = new JButton("close");
		add(quit);
		
		Butlistener k = new Butlistener();
		quit.addActionListener(k);
		setVisible(true);
		
		

	}
	private class Butlistener implements ActionListener{
		public void actionPerformed( ActionEvent k){
			About.this.dispose();
		}
	}
}

