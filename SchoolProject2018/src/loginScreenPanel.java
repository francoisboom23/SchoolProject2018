//FETCH AVANT TOUS CHANGEMENTS SOUS PEINE DE MORT!

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import accessBD.*;

public class loginScreenPanel extends JPanel {
	private JTextField login;
	private JPasswordField password;
	private JLabel loginLabel, passwordLabel,space;
	private JButton loginbtn;
	private String loginStr,passwordStr;
	private loginScreen parent;
	private loginScreenPanel ici;
	private Connection connect;
	
	public loginScreenPanel(loginScreen parent) {
		//setBounds(850,450,300,140);
		//setLayout(new GridBagLayout());
		setLayout(new GridLayout(3, 3,5,5));
		//init
		login = new JTextField();
		password = new JPasswordField();
		loginbtn = new JButton("login");
		space= new JLabel("");
		loginLabel = new JLabel("username:");
		loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
		passwordLabel = new JLabel("password:");
		passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.parent=parent;
		ici=this;
		
		add(loginLabel);
		add(login);
		add(passwordLabel);
		add(password);
		add(space);
		add(loginbtn);
		
//		//gridbaglayout conf
//		GridBagConstraints c = new GridBagConstraints();
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 1;
//		c.insets = new Insets(5, 5, 0, 0);
//		c.gridy=1;
//		c.gridx=1;
//		//add
//		this.add(loginLabel,c);
//		c.gridy++;
//		this.add(passwordLabel,c);
//		c.gridx++;
//		c.gridy--;
//		this.add(login,c);
//		c.gridy++;
//		this.add(password,c);
//		c.gridy++;
//		this.add(loginbtn,c);
//		//button listener
		
		Butlistener a = new Butlistener();
		loginbtn.addActionListener(a);
		
		setVisible(true);
	}
	public class Butlistener implements ActionListener{
		public void actionPerformed( ActionEvent a){
			if(a.getSource()==loginbtn) {
				System.out.println(login.getText());
				if(login.getText().equals("root") && password.getText().equals("Tigrou007")) {
					loginStr=login.getText();
					passwordStr=password.getText();
					Home f1 = new Home(ici);
					parent.dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "not ok");
				}
			}
		}
	}
	public String getLogin() {
		return login.getText();
	}
	public String getPassword() {
		return password.getText();
	}
}
