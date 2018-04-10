//FETCH AVANT TOUS CHANGEMENTS SOUS PEINE DE MORT!

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

import accessBD.AccessBDGen;
import accessBD.TableModelGen;

public class insertNewInstall extends JPanel{
//elements: idInstall, date install,type install, commentaires, durée install, ref procedure install,validation,date valid prevue, code soft(FK), matricule(FK),codeOS(FK)
	//private JLabel dateInstall,commentaire,dureeInstall,refProc,codeSoft,Matricule,codeOS;
	private JComboBox comboSoft,comboMatri,comboOS,comboType,comboValid;
	private JTextField textDate,textCommentaire,textDuree,textRef,textDatePrevoir;
	private String[] type = {"Type:","standart","custom"};
	private String[] valid = {"State:","planified","work in progress","finished"};
	
	public insertNewInstall(Connection connect) {
//generale
		setBounds(0,0,500,500);
		setLayout(new GridLayout(12,1,1,1));
//content	
//		dateInstall = new JLabel("install date:");
//		commentaire = new JLabel("commentary:");
//		dureeInstall = new JLabel("install time:");
//		refProc = new JLabel("procedure reference:");
//		codeSoft = new JLabel("software:");
//		Matricule = new JLabel("matricule:");
//		codeOS = new JLabel("OS:");
		comboSoft = new JComboBox();
		comboMatri = new JComboBox();
		comboOS = new JComboBox();
		comboType = new JComboBox(type);
		comboValid = new JComboBox(valid);
		textDate = new JTextField("installation date:");
		textCommentaire = new JTextField("commentaire:");
		textDuree = new JTextField("installation time:");
		textRef = new JTextField("installation reference:");
		textDatePrevoir = new JTextField("to do:");
		textDatePrevoir.setEnabled(false);
		comboSoft.addItem("Software:");
		comboMatri.addItem("Network Responsable:");
		comboOS.addItem("OS:");
		fillCombo(connect);
		
//		add(dateInstall);
//		add(commentaire);
//		add(dureeInstall);
//		add(refProc);
//		add(codeSoft);
//		add(Matricule);
//		add(codeOS);
		add(comboSoft);
		add(comboMatri);
		add(comboOS);
		add(comboType);
		add(comboValid);
		add(textDate);
		add(textCommentaire);
		add(textDuree);
		add(textRef);
		add(textDatePrevoir);
		
		setVisible(true);
	}
//fill all combobox
	private void fillCombo(Connection connect) {
		try {
			PreparedStatement prepStatSoft = connect.prepareStatement("SELECT Nom FROM dbinstallations.Software;");
			PreparedStatement prepStatMatri = connect.prepareStatement("SELECT NomPrenom FROM dbinstallations.ResponsableReseaux;");
			PreparedStatement prepStatOS = connect.prepareStatement("SELECT Libelle FROM dbinstallations.OS;");
			TableModelGen table1 = AccessBDGen.creerTableModel(prepStatSoft);
			TableModelGen table2 = AccessBDGen.creerTableModel(prepStatMatri);
			TableModelGen table3 = AccessBDGen.creerTableModel(prepStatOS);
			for(int i=0; i <= table2.getRowCount()-1; i++) {
				comboSoft.addItem(table1.getValueAt(i, 0));
				}
			for(int i=0; i <= table2.getRowCount()-1; i++) {
				comboMatri.addItem(table2.getValueAt(i, 0));
				}
			
			for(int i=0; i <= table3.getRowCount()-1; i++) {
				comboOS.addItem(table3.getValueAt(i, 0));
				}
		}
		catch(SQLException e) {	}
	}
}
