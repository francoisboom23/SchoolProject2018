//FETCH AVANT TOUS CHANGEMENTS SOUS PEINE DE MORT!

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

import accessBD.AccessBDGen;
import accessBD.TableModelGen;

public class ListPreInstalledSoft extends JPanel{
	
	private JLabel PreInstalledSoftLabel;
	private JComboBox combox;
	private String[] table= {"lol","mdr","test"};
	
	public ListPreInstalledSoft(Connection connect) {
		setBounds(0,0,500,500);
		
		try {
			PreparedStatement prepStat = connect.prepareStatement("SELECT Description FROM dbinstallations.TypePC;");
			TableModelGen table2 = AccessBDGen.creerTableModel(prepStat);
			
			
//			while(String.valueOf(table2.getValueAt(1, 0))!=null) {
//				table[i]=String.valueOf(table2.getValueAt(i, 0));
//				combox.addItem(String.valueOf(table2.getValueAt(1, 0)));
//				System.out.println(String.valueOf(table2.getValueAt(i, 0)));
//				i++;
//			}
			for(int i=0; i <= table2.getRowCount()-1; i++) {
				System.out.println(String.valueOf(table2.getValueAt(i, 0)));
				combox.addItem(String.valueOf(table2.getValueAt(i, 0)));

			}
//			table[1]=String.valueOf(table2.getValueAt(0, 0));
//			PreInstalledSoftLabel = new JLabel(String.valueOf(table2.getValueAt(1, 0)));
//			PreInstalledSoftLabel = new JLabel(String.valueOf(table2.getRowCount())); -> 5
			}
		catch(SQLException e) {	}
		
		PreInstalledSoftLabel = new JLabel("Type de PC:");
		PreInstalledSoftLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		combox = new JComboBox(table);
		this.add(PreInstalledSoftLabel);
		this.add(combox);
	}
}
