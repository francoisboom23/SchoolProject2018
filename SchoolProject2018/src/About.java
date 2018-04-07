//FETCH AVANT TOUS CHANGEMENTS SOUS PEINE DE MORT!

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class About extends JFrame{
	private JLabel author;
	
	public About() {
//general
		super("about this program");
		setBounds(850,450,300,200);
		setResizable(false);
		setLayout(new GridLayout(1,1,5,5));
//content
		author = new JLabel("<html><body><p align=\"center\">Version 1.0<br><br>Authors:<br>Curé Vincent<br>Devilez François</p></body></html>");
		author.setHorizontalAlignment(SwingConstants.CENTER);
		add(author);
		
		setVisible(true);
	}
}
