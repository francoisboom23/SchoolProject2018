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
	private String SqlInstruction;
	private Windows parent;

	private JTable tableau2;
	
	public ListPreInstalledSoftBySection (Connection connect, Windows win) {
		
		setBounds(0,0,500,500);
		parent=win;
		
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
			

			private class ItemList implements ItemListener{
				

				public void itemStateChanged(ItemEvent e){
					if(e.getSource()==AfficherList){
						if(e.getStateChange()==ItemEvent.SELECTED){
							SqlInstruction="SELECT Nom FROM Software Soft JOIN UtilisationSoftware Uti ON Soft.CodeSoftware = Uti.CodeSoftware JOIN AnneeEtude Annee on Uti.IdAnneeEtude = Annee.IdAnneeEtude JOIN Section Sect ON Annee.CodeSection = Sect.CodeSection WHERE Sect.Libelle LIKE '"+(String)Combox.getSelectedItem()+"';";
							System.out.println(SqlInstruction);
							TableInstalledSoft f2 = new TableInstalledSoft(parent.getConnect(), SqlInstruction);
							ListPreInstalledSoftBySection listPreInstalledType= new ListPreInstalledSoftBySection (parent.getConnect(),parent.getWin());
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

}
			
			
		

			
			
			

	
