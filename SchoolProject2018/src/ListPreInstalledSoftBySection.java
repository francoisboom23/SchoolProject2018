//FETCH AVANT TOUS CHANGEMENTS SOUS PEINE DE MORT!

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

import accessBD.AccessBDGen;
import accessBD.TableModelGen;

public class ListPreInstalledSoftBySection extends JPanel{
	
	private JLabel PreInstalledSoftBySection;
	private JComboBox Combox;
	private JButton refresh;
	private String SqlInstruction;
	private Windows parent;
	
	public ListPreInstalledSoftBySection (Connection connect, Windows win) {
		
		setBounds(0,0,500,500);
		parent=win;
		
		PreInstalledSoftBySection = new JLabel ("Section :");
		PreInstalledSoftBySection.setHorizontalAlignment(SwingConstants.RIGHT);
		Combox = new JComboBox();
		Combox.addItem("");
		refresh= new JButton ("refresh");
		this.add(PreInstalledSoftBySection);
		this.add(Combox);
		this.add(refresh);
		
		Butlistener a = new Butlistener();
		refresh.addActionListener(a);
//SQL database
		fillCombobox(connect);
		}
//fill combobox
	private void fillCombobox(Connection connect) {
		try {
			PreparedStatement prepStat = connect.prepareStatement("SELECT Libelle FROM Section;");
			TableModelGen table2 = AccessBDGen.creerTableModel(prepStat);
			for(int i=0; i <= table2.getRowCount()-1; i++) {
				Combox.addItem(table2.getValueAt(i, 0));
			}
		}
		catch(SQLException e) {	}		
	}
//listener refresh button
	private class Butlistener implements ActionListener{
		Connection connect;
		public void actionPerformed( ActionEvent a){
			if(a.getSource()==refresh){
				if((String)Combox.getSelectedItem()=="") {
					SqlInstruction="";
					JOptionPane.showMessageDialog(null, "<html><body><p align=\\\"center\\\">invalid selection</p></body></html>", "error", JOptionPane.ERROR_MESSAGE);
				}
				else {
					SqlInstruction="SELECT DISTINCT Nom FROM Software Soft JOIN UtilisationSoftware Uti ON Soft.CodeSoftware = Uti.CodeSoftware JOIN AnneeEtude Annee on Uti.IdAnneeEtude = Annee.IdAnneeEtude JOIN Section Sect ON Annee.CodeSection = Sect.CodeSection WHERE Sect.Libelle LIKE '"+(String)Combox.getSelectedItem()+"';";
					System.out.println(SqlInstruction);
				}
				tableModel f2 = new tableModel(parent.getConnect(), SqlInstruction);
				ListPreInstalledSoftBySection listPreInstalledSection= new ListPreInstalledSoftBySection (parent.getConnect(),parent.getWin());
				listPreInstalledSection.SetBox((String)Combox.getSelectedItem());
			
				parent.getCont().removeAll();
				parent.getCont().setLayout(new BorderLayout());
				parent.getCont().add(listPreInstalledSection,BorderLayout.NORTH);
				parent.getCont().add(f2,BorderLayout.CENTER);
				parent.getCont().repaint();
				parent.getCont().setVisible(true);
				parent.validate();
				}
			} 	
		}
	//set combobox default selection same as selected refresh
		public void SetBox(String selection){
			Combox.setSelectedItem(selection);
		}
}