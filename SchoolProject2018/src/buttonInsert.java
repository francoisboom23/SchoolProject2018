//FETCH AVANT TOUS CHANGEMENTS SOUS PEINE DE MORT!

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class buttonInsert extends JPanel{
	private JButton validate, clean;
	private insertNewInstall pan;
	
	public buttonInsert(insertNewInstall pan) {
//generale
		setLayout(new GridLayout(1,2,1,1));
		this.pan=pan;
//initialization
		validate = new JButton("validate");
		clean = new JButton("clean textfield");
//add
		add(validate);
		add(clean);
//listener
		butListener l = new butListener();
		validate.addActionListener(l);
		clean.addActionListener(l);
		setVisible(true);
	}
	private class butListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==clean) {
				pan.cleanTextField();
			}
			if(e.getSource()==validate) {
				pan.cleanTextField();
			}
		}
	}
}
