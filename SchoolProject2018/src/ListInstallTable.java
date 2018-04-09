//FETCH AVANT TOUS CHANGEMENTS SOUS PEINE DE MORT!

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

import accessBD.AccessBDGen;
import accessBD.TableModelGen;

public class ListInstallTable extends JPanel{
	
	ListInstallTable(Connection connect){
		setBounds(0,0,500,500);
		
		try {
			PreparedStatement prepStat = connect.prepareStatement("select * from Installation;");
			TableModelGen table = AccessBDGen.creerTableModel(prepStat);
		 	JTable table2 = new JTable(table);
		 	table2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		 	JScrollPane scroll = new JScrollPane (table2) ;
		 	this.add(scroll);
			}
		catch(SQLException e) {	}
		setVisible(true);
	}
}
