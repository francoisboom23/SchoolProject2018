//FETCH AVANT TOUS CHANGEMENTS SOUS PEINE DE MORT!

import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

import accessBD.AccessBDGen;
import accessBD.TableModelGen;

public class ListPreInstalledSoft extends JPanel{
	
	private JLabel PreInstalledSoftLabel;
	private JComboBox combox;
	private JButton refresh;
	private String sqlRequest;
	
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
//listener refresh button
	private class Butlistener implements ActionListener{
		public void actionPerformed( ActionEvent a){
			if(a.getSource()==refresh){
				sqlRequest = "select Nom from Software soft join SoftwarePreinstalle softpr on soft.CodeSoftware = softpre.CodeSoftware join TypePC pc on softpre.IdTypePC = pc.IdTypePC where pc.Description like '"+(String)combox.getSelectedItem()+"';";
				System.out.println(sqlRequest);
			}
		}
	}
	public String getSQL() {
		return sqlRequest;
	}
}