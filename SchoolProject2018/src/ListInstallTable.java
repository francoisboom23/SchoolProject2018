import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

import accessBD.AccessBDGen;
import accessBD.TableModelGen;

public class ListInstallTable extends JPanel{
	
	private String sqlInstruction;
	
	ListInstallTable(Connection connect){
		setBounds(0,0,500,500);
		
		try {
			sqlInstruction = "select * from Installation;";
			PreparedStatement prepStat = connect.prepareStatement(sqlInstruction);
			TableModelGen table = AccessBDGen.creerTableModel(prepStat);
		 	JTable interventionTable = new JTable(table);
		 	interventionTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		 	JScrollPane defilant = new JScrollPane (interventionTable) ;
		 	this.add(defilant);
			}
		catch(SQLException e) {	}
	}
}
