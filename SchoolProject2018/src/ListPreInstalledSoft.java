//FETCH AVANT TOUS CHANGEMENTS SOUS PEINE DE MORT!

import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

import accessBD.AccessBDGen;
import accessBD.TableModelGen;

public class ListPreInstalledSoft extends JPanel {
	
	private JLabel PreInstalledSoftLabel;
	private JComboBox combox;
	private JButton refresh;
	private String sqlRequest="select DISTINCT Nom from Software soft join SoftwarePreinstalle softpr on soft.CodeSoftware = softpr.CodeSoftware join TypePC pc on softpr.IdTypePC = pc.IdTypePC;";
	private JTable tableau2;
	
	public ListPreInstalledSoft(Connection connect) {
//generale
		setBounds(0,0,500,500);
//controls
		PreInstalledSoftLabel = new JLabel("PC type:");
		PreInstalledSoftLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		combox = new JComboBox();
		refresh = new JButton("refresh");
		add(PreInstalledSoftLabel);
		add(combox);
		add(refresh);
//listener controls
		Butlistener a = new Butlistener();
		refresh.addActionListener(a);
//SQL database
		fillCombobox(connect);
		DisplayList(connect);
}
//fill combobox
	private void fillCombobox(Connection connect) {
		try {
			PreparedStatement prepStat = connect.prepareStatement("SELECT Description FROM dbinstallations.TypePC;");
			TableModelGen table2 = AccessBDGen.creerTableModel(prepStat);

			for(int i=0; i <= table2.getRowCount()-1; i++) {
				combox.addItem(table2.getValueAt(i, 0));
				}
			}
		catch(SQLException e) {	}		
	}
//display table
	void DisplayList(Connection connect){
		setBounds(0,0,500,500);
		try {
			PreparedStatement prepStat = connect.prepareStatement(sqlRequest);
			TableModelGen table = AccessBDGen.creerTableModel(prepStat);
			tableau2 = new JTable(table);
		 	tableau2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		 	JScrollPane scroll = new JScrollPane (tableau2) ;
		 	this.add(scroll);
		 	tableau2.setVisible(false);
			}	
		catch(SQLException e) {	}
	}
	//listener refresh button
		private class Butlistener implements ActionListener{
			public void actionPerformed( ActionEvent a){
				if(a.getSource()==refresh){
					sqlRequest = "select Nom from Software soft join SoftwarePreinstalle softpr on soft.CodeSoftware = softpr.CodeSoftware join TypePC pc on softpr.IdTypePC = pc.IdTypePC where pc.Description like '"+(String)combox.getSelectedItem()+"';";
					System.out.println(sqlRequest);
					tableau2.setVisible(true);			
				}
			}
		}
}