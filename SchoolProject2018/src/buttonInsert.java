//FETCH AVANT TOUS CHANGEMENTS SOUS PEINE DE MORT!

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class buttonInsert extends JPanel{
	private JButton validate;
	private insertNewInstall pan;
	private Connection connect;
	
	public buttonInsert(insertNewInstall pan,Connection connect) {
//generale
		setBounds(0,0,10,10);

		this.pan=pan;
		this.connect=connect;
//initialization
		validate = new JButton("validate");
//add
		add(validate);
//listener
		butListener l = new butListener();
		validate.addActionListener(l);
		setVisible(true);
	}
	private class butListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==validate) {
				pan.addInstall(connect);
			}
		}
	}
}
