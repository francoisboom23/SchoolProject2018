import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.swing.*;

import accessBD.AccessBDGen;
import accessBD.TableModelGen;

public class DelInstall extends JPanel {
	private JLabel providerLabel;
	private JComboBox listProvider;
	private static Connection connect;
	
	public DelInstall (Connection conect) {
		
		setBounds(10,10,480,440);
		setLayout(new GridLayout(2,2,1,1));
		providerLabel = new JLabel ("Provider :");
		

		
	}
	
	

}
