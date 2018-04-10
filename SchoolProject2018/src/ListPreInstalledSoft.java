//FETCH AVANT TOUS CHANGEMENTS SOUS PEINE DE MORT!

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
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
	private Windows parent;
	
	public ListPreInstalledSoft(Connection connect,Windows win) {
//generale
		setBounds(0,0,500,500);
		parent=win;
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
					sqlRequest = "SELECT Nom FROM Software soft JOIN SoftwarePreinstalle softpr ON soft.CodeSoftware = softpr.CodeSoftware JOIN TypePC pc ON softpr.IdTypePC = pc.IdTypePC WHERE pc.Description LIKE '"+(String)combox.getSelectedItem()+"';";
					System.out.println(sqlRequest);
					TableInstalledSoft f2 = new TableInstalledSoft(parent.getConnect(), sqlRequest);
					ListPreInstalledSoft listPreInstalledType= new ListPreInstalledSoft (parent.getConnect(),parent.getWin());
					parent.getCont().removeAll();
					parent.getCont().setLayout(new BorderLayout());
					parent.getCont().add(listPreInstalledType,BorderLayout.NORTH);
					parent.getCont().add(f2,BorderLayout.CENTER);
					parent.getCont().repaint();
					parent.getCont().setVisible(true);
					System.out.println("pute");
				}
			}
		}
}