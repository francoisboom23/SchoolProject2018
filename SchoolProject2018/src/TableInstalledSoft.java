//FETCH AVANT TOUS CHANGEMENTS SOUS PEINE DE MORT!

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

import accessBD.AccessBDGen;
import accessBD.TableModelGen;

public class TableInstalledSoft extends JPanel{
	private JLabel vincent;
	
	TableInstalledSoft(Connection connect,String sqlRequest){
		setBounds(0,0,500,500);	
		try {
			PreparedStatement prepStat = connect.prepareStatement(sqlRequest);
			TableModelGen table = AccessBDGen.creerTableModel(prepStat);
		 	JTable table2 = new JTable(table);
		 	//selectionner ligne dans jtable
		 	table2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		 	//
		 	table2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		 	table2.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		 	JScrollPane scroll = new JScrollPane (table2) ;
		 	scroll.setPreferredSize(new Dimension(400, 200));//270,200
		 	this.add(scroll);
			}
		catch(SQLException e) {	}
//		add(vincent =new JLabel("vincent"));
		setVisible(true);
		}
}
