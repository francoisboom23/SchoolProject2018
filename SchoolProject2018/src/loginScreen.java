//FETCH AVANT TOUS CHANGEMENTS SOUS PEINE DE MORT!

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class loginScreen extends JFrame{
	
	public loginScreen() {
		loginScreenPanel f2 = new loginScreenPanel(this);
		add(f2);
		
		setBounds(850,450,300,140);
		setTitle("login screen");
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
