//FETCH AVANT TOUS CHANGEMENTS SOUS PEINE DE MORT!

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

import accessBD.AccessBDGen;
import accessBD.TableModelGen;

public class ListPreInstalledSoft extends JPanel {
	
	private JLabel PreInstalledSoftLabel;
	private JComboBox combox;
	private JButton refresh;
	private String sqlRequest;
	private Windows parent;
	
	public ListPreInstalledSoft(Connection connect,Windows win) {
//generale
		setBounds(0,0,500,500);
		parent=win;
//controls
		PreInstalledSoftLabel = new JLabel("PC type:");
		PreInstalledSoftLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		combox = new JComboBox();
		combox.addItem("");
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
		catch(SQLException e) {
			JOptionPane.showMessageDialog(null,e.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
		}		
	}
//listener refresh button
	private class Butlistener implements ActionListener{
		public void actionPerformed( ActionEvent a){
			if(a.getSource()==refresh){
				if((String)combox.getSelectedItem()=="") {
					sqlRequest="";
					JOptionPane.showMessageDialog(null, "<html><body><p align=\\\"center\\\">invalid selection</p></body></html>", "error", JOptionPane.ERROR_MESSAGE);
				}
				else {
					sqlRequest = "SELECT Nom FROM Software soft JOIN SoftwarePreinstalle softpr ON soft.CodeSoftware = softpr.CodeSoftware JOIN TypePC pc ON softpr.IdTypePC = pc.IdTypePC WHERE pc.Description LIKE '"+(String)combox.getSelectedItem()+"';";
					System.out.println(sqlRequest);
				}
				//System.out.println(sqlRequest);
				TableInstalledSoft f2 = new TableInstalledSoft(parent.getConnect(), sqlRequest);
				ListPreInstalledSoft listPreInstalledType= new ListPreInstalledSoft (parent.getConnect(),parent.getWin());
			//	listPreInstalledType.combox.setSelectedItem((String)combox.getSelectedItem());
				listPreInstalledType.SetBox((String)combox.getSelectedItem());

				parent.getCont().removeAll();
				parent.getCont().setLayout(new BorderLayout());
				parent.getCont().add(listPreInstalledType,BorderLayout.NORTH);
				parent.getCont().add(f2,BorderLayout.CENTER);
				parent.getCont().repaint();
				parent.getCont().setVisible(true);
				parent.validate();
			}
		}
	}
	//set combobox default selection same as selected refresh
	public void SetBox(String selection){
		combox.setSelectedItem(selection);
	}
}