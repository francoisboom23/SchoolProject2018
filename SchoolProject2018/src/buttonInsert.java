//FETCH AVANT TOUS CHANGEMENTS SOUS PEINE DE MORT!

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class buttonInsert extends JPanel{
	public static final Windows Windows = null;
	private JButton validate;
	private insertNewInstall pan;
	private Connection connect;
	
	public buttonInsert(insertNewInstall pan,Connection connect) {
//generale
		setBounds(150,400,200,50);
		setLayout(new GridLayout(1,1,1,1));
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
				JOptionPane.showMessageDialog(null,"successfully added");
				pan.cleanTextField();
			}
		}
	}
}
