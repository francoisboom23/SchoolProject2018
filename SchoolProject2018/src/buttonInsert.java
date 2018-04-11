//FETCH AVANT TOUS CHANGEMENTS SOUS PEINE DE MORT!

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class buttonInsert extends JPanel{
	private JButton validate, clean;
	
	public buttonInsert() {
//generale
		setLayout(new GridLayout(1,2,1,1));
//initialization
		validate = new JButton("validate");
		clean = new JButton("clean textfield");
//add
		add(validate);
		add(clean);
//listeneter
		setVisible(true);
	}
}
