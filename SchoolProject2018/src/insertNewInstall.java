//FETCH AVANT TOUS CHANGEMENTS SOUS PEINE DE MORT!

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.swing.*;


import accessBD.AccessBDGen;
import accessBD.TableModelGen;

public class insertNewInstall extends JPanel{
//elements: idInstall, date install,type install, commentaires, durée install, ref procedure install,validation,date valid prevue, code soft(FK), matricule(FK),codeOS(FK)

	private JComboBox comboSoft,comboMatri,comboOS,comboType,comboValid;
	private JTextField textDate,textCommentaire,textDuree,textRef,textDatePrevoir;
	private String[] type = {"Type:","standard","custom"};
	private String[] valid = {"State:","planified","work in progress","finished"};
	private static Connection connect;
	
	public insertNewInstall(Connection connect) {
//generale
		setBounds(150,0,200,380);
		setLayout(new GridLayout(10,1,1,1));
//initialization		
		comboSoft = new JComboBox();
		comboMatri = new JComboBox();
		comboOS = new JComboBox();
		comboType = new JComboBox(type);
		comboValid = new JComboBox(valid);
		textDate = new JTextField("installation date:");
		textCommentaire = new JTextField("commentaire:");
		textDuree = new JTextField("installation duration:");
		textRef = new JTextField("installation reference:");
		textDatePrevoir = new JTextField("date planified:");
		textDatePrevoir.setEnabled(false);
//		comboSoft.addItem("Software:");
//		comboMatri.addItem("Network Responsable:");
//		comboOS.addItem("OS:");
		fillCombo(connect);
//tooltips
		textDate.setToolTipText("installation date");
		textCommentaire.setToolTipText("commentary");
		textDuree.setToolTipText("installation duration");
		textRef.setToolTipText("installation reference");
		textDatePrevoir.setToolTipText("date planified");
//add
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
//listener
		mouse m = new mouse();
		comboboxListener a = new comboboxListener();
		textDate.addMouseListener(m);
		textCommentaire.addMouseListener(m);
		textDuree.addMouseListener(m);
		textRef.addMouseListener(m);
		textDatePrevoir.addMouseListener(m);
		comboSoft.addActionListener(a);
		comboMatri.addActionListener(a);
		comboOS.addActionListener(a);
		comboType.addActionListener(a);
		comboValid.addActionListener(a);
		
		setVisible(true);
	}
//fill all combobox
	public void fillCombo(Connection connect) {
		comboSoft.removeAllItems();
		comboMatri.removeAllItems();
		comboOS.removeAllItems();
		comboSoft.addItem("Software:");
		comboMatri.addItem("Network Responsable:");
		comboOS.addItem("OS:");
		try {
			PreparedStatement prepStatSoft = connect.prepareStatement("SELECT Nom FROM dbinstallations.Software;");
			PreparedStatement prepStatMatri = connect.prepareStatement("SELECT NomPrenom FROM dbinstallations.ResponsableReseaux;");
			PreparedStatement prepStatOS = connect.prepareStatement("SELECT Libelle FROM dbinstallations.OS;");
			TableModelGen table1 = AccessBDGen.creerTableModel(prepStatSoft);
			TableModelGen table2 = AccessBDGen.creerTableModel(prepStatMatri);
			TableModelGen table3 = AccessBDGen.creerTableModel(prepStatOS);
			for(int i=0; i <= table1.getRowCount()-1; i++) {
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
//clean all jtextfield	
	public void cleanTextField() {
		textDate.setText("");
		textCommentaire.setText("");
		textDuree.setText("");
		textRef.setText("");
		if(comboValid.getSelectedItem()=="planified") {
			textDatePrevoir.setText("");
		}
		else {
			textDatePrevoir.setText("date planified:");
		}
		System.out.println(comboValid.getSelectedItem());
	}
//add in DB	
	public void addInstall(Connection connect) {

		try {
			//String SqlInstruction="INSERT INTO Installation (IdInstallation, DateInstallation, TypeInstallation, Commentaires, DureeInstallation, RefProcedureInstallation, Validation, DateValidation, CodeSoftware, Matricule,CodeOS) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		String SqlInstruction="INSERT INTO Installation VALUES (?,?,?,?,?,?,?,?,?,?,?)";

			PreparedStatement myPrepStat = connect.prepareStatement(SqlInstruction);
			
			//IDTABLEINSTALL//
			myPrepStat.setInt(1,idCount(connect)+1);

			
			//COLONNE DATE//	
			if(!textDate.getText().equals("")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd");
			java.util.Date date = sdf.parse(textDate.getText());
			myPrepStat.setDate(2, new java.sql.Date(date.getTime()));
			System.out.println(date);
			}
			
			//COLONNE TYPEINSTALL//
			if(comboType.getSelectedItem().equals("standard")) {
				myPrepStat.setBoolean(3, true);
			}
			else
			{
				myPrepStat.setBoolean(3, false);
			}
			
			//COLONNE COMMENTAIRE//
			
			if(!textCommentaire.getText().equals("")) {
				myPrepStat.setString(4,textCommentaire.getText());
			}
			else {
				myPrepStat.setNull(4,Types.VARCHAR);
			}
			
			//COLONNE DUREINSTALLE//
			
			if(!textDuree.getText().equals("")) {
				int i =Integer.parseInt(textDuree.getText());
				myPrepStat.setInt(5, i);

			}
			
			//COLONNE REFPRO //
			
			if (!textRef.getText().equals("")) {
				myPrepStat.setString(6, textRef.getText());
			}
			else {
				myPrepStat.setNull(6, Types.VARCHAR);
			}
			
			// COLONNE VALIDATION +dateprevoir //
			
			if(comboValid.getSelectedItem().equals("planified")) {
				System.out.println(comboValid.getSelectedItem());
				myPrepStat.setString(7, (String) comboValid.getSelectedItem());
				if(!textDatePrevoir.getText().equals("")) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd");
					java.util.Date date = sdf.parse(textDatePrevoir.getText());
					myPrepStat.setDate(8, new java.sql.Date(date.getTime()));	
				}
			}
			if(comboValid.getSelectedItem().equals("work in progress")) {
				System.out.println(comboValid.getSelectedItem());
				myPrepStat.setString(7, (String) comboValid.getSelectedItem());
				myPrepStat.setNull(8, Types.VARCHAR);
			}
			if(comboValid.getSelectedItem().equals("finished")) {
				System.out.println(comboValid.getSelectedItem());
				myPrepStat.setString(7, (String) comboValid.getSelectedItem());
				myPrepStat.setNull(8, Types.VARCHAR);
			}
			
			//COLONNECODESOFTWARE//
			if(comboSoft.getSelectedItem().equals("Bob50")) {
				myPrepStat.setString(9, "Bob001");
			}
			if(comboSoft.getSelectedItem().equals("NetBeans")) {
				myPrepStat.setString(9, "NB02");
			}
			if(comboSoft.getSelectedItem().equals("Office 2013")) {
				myPrepStat.setString(9, "Of13");
			}
			if(comboSoft.getSelectedItem().equals("Oracle 11g")) {
				myPrepStat.setString(9, "Or11");
			}
			if(comboSoft.getSelectedItem().equals("Visual Studio")) {
				myPrepStat.setString(9, "Vs12");
			}
			
			
			//COLONNEADMINRESEAU//
			if(comboMatri.getSelectedItem().equals("Alexandre Baligant")){
				myPrepStat.setString(10, "AlBa");
			}
			if(comboMatri.getSelectedItem().equals("André Van Kerrebroeck")){
				myPrepStat.setString(10, "AVK");
			}
			if(comboMatri.getSelectedItem().equals("Marvin Gobin")){
				myPrepStat.setString(10, "MarGob");
			}
			
			//COLONNETABLEOS//
			if(comboOS.getSelectedItem().equals("Fedora 2012")) {
				myPrepStat.setString(11, "Fedora");
			}
			if(comboOS.getSelectedItem().equals("Linux Mint")) {
				myPrepStat.setString(11, "Mint");
			}
			if(comboOS.getSelectedItem().equals("Red Hat 8 Linux EN")) {
				myPrepStat.setString(11, "RedHat8");
			}
			if(comboOS.getSelectedItem().equals("Ubuntu 2012")) {
				myPrepStat.setString(11, "Ubuntu");
			}
			if(comboOS.getSelectedItem().equals("Windows 7 Professional English")) {
				myPrepStat.setString(11, "W7ProfEn");
			}
			if(comboOS.getSelectedItem().equals("Windows 10 Professional English")) {
				myPrepStat.setString(11, "W8ProfEn");
			}
			if(comboOS.getSelectedItem().equals("Windows 8 Prof Français")) {
				myPrepStat.setString(11, "W8ProfFr");
			}
			
			
		
		int nbUpdatesLines = myPrepStat.executeUpdate();
		
		System.out.println("ok");
		System.out.println(nbUpdatesLines);
		}
			
		 catch (SQLException | ParseException e) {
			 System.out.println(e.getMessage());
		}
		
		
	}
//mouse listener
	private class mouse implements MouseListener,MouseMotionListener{
		public void mouseClicked(MouseEvent e) {
			if(e.getSource()==textDate){
				textDate.setText("");
			}
			if(e.getSource()==textCommentaire) {
				textCommentaire.setText("");
			}
			if(e.getSource()==textDuree) {
				textDuree.setText("");
			}
			if(e.getSource()==textRef) {
				textRef.setText("");
			}
			if(e.getSource()==textDatePrevoir && comboValid.getSelectedItem()=="planified") {
				textDatePrevoir.setText("");
			}
		}
		public void mouseDragged(MouseEvent e) {
			
		}
		public void mouseMoved(MouseEvent e) {
		
		}
		public void mouseEntered(MouseEvent e) {
			
		}
		public void mouseExited(MouseEvent e) {
			
		}
		public void mousePressed(MouseEvent e) {
			
		}
		public void mouseReleased(MouseEvent e) {
			
		}
	}
//combobox listener	
	private class comboboxListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==comboSoft){
				comboSoft.removeItem("Software:");
			}
			if(e.getSource()==comboMatri) {
				comboMatri.removeItem("Network Responsable:");
			}
			if(e.getSource()==comboOS) {
				comboOS.removeItem("OS:");
			}
			if(e.getSource()==comboType) {
				comboType.removeItem("Type:");
			}
			if(e.getSource()==comboValid) {
				comboValid.removeItem("State:");
				if(comboValid.getSelectedItem()=="planified") {
					textDatePrevoir.setEnabled(true);
				}
				else {
					textDatePrevoir.setEnabled(false);
					textDatePrevoir.setText("date planified:");
				}
			}
		}
	}
//counter id	
	public static int idCount (Connection connect) {
		int count=0;
		try {
			PreparedStatement prepStatSoft = connect.prepareStatement("SELECT * FROM dbinstallations.Installation;");
			TableModelGen table1 = AccessBDGen.creerTableModel(prepStatSoft);
			count=table1.getRowCount();
		} 
		catch (SQLException e) {}
		return count;
	}
}
