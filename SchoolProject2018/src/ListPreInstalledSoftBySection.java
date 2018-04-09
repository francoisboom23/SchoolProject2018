import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

import accessBD.AccessBDGen;
import accessBD.TableModelGen;

public class ListPreInstalledSoftBySection extends JPanel{
	
	private JLabel PreInstalledSoftBySection;
	private JComboBox Combox;
	private JCheckBox AfficherList;
	private String Section;
	private String SqlRequest;
	private JTable tableau2;
	
	public ListPreInstalledSoftBySection (Connection connect) {
		
		setBounds(0,0,500,500);
		PreInstalledSoftBySection = new JLabel ("Section :");
		PreInstalledSoftBySection.setHorizontalAlignment(SwingConstants.RIGHT);
		Combox = new JComboBox();
		AfficherList= new JCheckBox ("Afficher la table :");
		this.add(PreInstalledSoftBySection);
		this.add(Combox);
		this.add(AfficherList);
		


		ItemList e = new ItemList();
		AfficherList.addItemListener(e);
		//SQL database
				fillCombobox(connect);
				AfficherList(connect);
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
			
			
			void AfficherList(Connection connect){

				setBounds(10,10,500,500);
				
				
				try {
					PreparedStatement prepStat = connect.prepareStatement("select Nom from software soft \r\n" + 
							"join utilisationsoftware uti \r\n" + 
							"on soft.CodeSoftware = uti.CodeSoftware\r\n" + 
							"join anneeetude annee\r\n" + 
							"on uti.IdAnneeEtude = annee.IdAnneeEtude\r\n" + 
							"join section sect\r\n" + 
							"on annee.CodeSection = sect.CodeSection\r\n" + 
							"where sect.Libelle like 'Informatique de gestion';");
					TableModelGen table = AccessBDGen.creerTableModel(prepStat);
				 	this.tableau2 = new JTable(table);
				 	tableau2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				 	JScrollPane scroll = new JScrollPane (tableau2) ;
				 	this.add(scroll);
				 	tableau2.setVisible(false);

					}
				
				
				catch(SQLException e) {	}
			}
				
				private class ItemList implements ItemListener{
					
				
					public void itemStateChanged(ItemEvent e){
						if(e.getSource()==AfficherList){
							
							if(e.getStateChange()==ItemEvent.SELECTED){
								tableau2.setVisible(true);
							}
								else{
									tableau2.setVisible(false);
								}
						}

			}
				}
}
			
			
		

			
			
			

	
