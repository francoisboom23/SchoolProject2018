//FETCH AVANT TOUS CHANGEMENTS SOUS PEINE DE MORT!

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
	private loginScreen parent;
	private loginScreenPanel ici;
	private Connection connect;
	static final private String nomBD="dbinstallations";
	
	public loginScreenPanel(loginScreen parent) {
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
		
		Butlistener a = new Butlistener();
		loginbtn.addActionListener(a);
		
		setVisible(true);
	}
	public class Butlistener implements ActionListener{
		public void actionPerformed( ActionEvent a){
			if(a.getSource()==loginbtn) {
				try {
					connect = AccessBDGen.connecter(nomBD, login.getText(), password.getText());
					Home f1 = new Home(ici);
					parent.dispose();
				}
				catch(SQLException e) {
					JOptionPane.showMessageDialog(null,e.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	public Connection getConnect() {
		return connect;
	}
}
