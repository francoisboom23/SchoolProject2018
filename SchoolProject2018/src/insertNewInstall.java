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
	
	private JLabel softwareLabel, netLabel, osLabel,typeLabel,stateLabel,installDateLabel,commentaireLabel,dureeLabel,obligatoire,refLabel,datePlanifiedLabel;
	private JComboBox comboSoft,comboMatri,comboOS,comboType,comboValid;
	private JTextField textCommentaire,textRef;
	private JSpinner duree;
	private SpinnerNumberModel modelSpinner;
	private String[] type = {"Type:","standard","custom"};
	private String[] valid = {"State:","planified","working on","finished"};
	private dateCombo datePanel,datePlanifiedPanel;
	private String dataRecu;
	
	private static Connection connect;
	
	public insertNewInstall(Connection connect) {
//generale
		setBounds(0,0,400,400);
		setLayout(new GridLayout(11,2,0,0));
//initialization	
		softwareLabel = new JLabel("Software*");
		netLabel = new JLabel("Network responsable*");
		osLabel = new JLabel("Operating system*");
		typeLabel = new JLabel("Install type*");
		stateLabel = new JLabel("State*");
		installDateLabel = new JLabel("Installation date: (YYYY/MM/DD)*");
		commentaireLabel = new JLabel("Commantary");
		dureeLabel = new JLabel("Installation duration: (in minutes)*");
		obligatoire = new JLabel("(*mandatory field)");
		refLabel = new JLabel("Installation reference");
		datePlanifiedLabel = new JLabel("Date planified: (YYYY/MM/DD)*");
		datePlanifiedLabel.setVisible(false);
		
		comboSoft = new JComboBox();
		comboMatri = new JComboBox();
		comboOS = new JComboBox();
		comboType = new JComboBox(type);
		comboValid = new JComboBox(valid);
		fillCombo(connect);
		
		textCommentaire = new JTextField("commentaire:");
		textRef = new JTextField("installation reference:");
		
		modelSpinner = new SpinnerNumberModel(0,0,999,1);
		duree = new JSpinner(modelSpinner);
		duree.setEditor(new JSpinner.DefaultEditor(duree));
		
		buttonInsert but = new buttonInsert(this,connect);
		datePanel = new dateCombo();
		datePlanifiedPanel = new dateCombo();
		datePlanifiedPanel.setVisible(false);
		datePanel.print();
		
//tooltips	
		textCommentaire.setToolTipText("commentary");
		duree.setToolTipText("installation duration in minute");
		textRef.setToolTipText("installation reference");	

//add
		add(softwareLabel);
		add(comboSoft);
		add(netLabel);
		add(comboMatri);
		add(osLabel);
		add(comboOS);
		add(typeLabel);
		add(comboType);
		add(stateLabel);
		add(comboValid);
		add(installDateLabel);
		add(datePanel);
		add(commentaireLabel);
		add(textCommentaire);
		add(dureeLabel);
		add(duree);
		add(refLabel);
		add(textRef);
		add(datePlanifiedLabel);
		add(datePlanifiedPanel);
		add(obligatoire);
		add(but);
//listener
		mouse m = new mouse();
		comboboxListener a = new comboboxListener();
		textCommentaire.addMouseListener(m);
		textRef.addMouseListener(m);
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
		catch(SQLException e) {
			JOptionPane.showMessageDialog(null,e.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
		}
	}
//clean all jtextfield	
	public void cleanTextField() {
		textCommentaire.setText("commentaire:");
		duree.setValue(0);
		textRef.setText("installation reference:");
	}
//add in DB	
	public void addInstall(Connection connect){

		try {
			//String SqlInstruction="INSERT INTO Installation (IdInstallation, DateInstallation, TypeInstallation, Commentaires, DureeInstallation, RefProcedureInstallation, Validation, DateValidation, CodeSoftware, Matricule,CodeOS) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		String SqlInstruction="INSERT INTO Installation VALUES (?,?,?,?,?,?,?,?,?,?,?)";

			PreparedStatement myPrepStat = connect.prepareStatement(SqlInstruction);
			
			//IDTABLEINSTALL//
			myPrepStat.setInt(1,idCount(connect)+1);

			
			//COLONNE DATE//
			myPrepStat.setDate(2, new java.sql.Date(datePanel.getDate().getTime()));		
			
			//COLONNE TYPEINSTALL//
			if(!comboType.getSelectedItem().equals("Types:")) {
				if(comboType.getSelectedItem().equals("standard")) {
					myPrepStat.setBoolean(3, true);
				}
				else{
				myPrepStat.setBoolean(3, false);
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Installation type not set","error" , ERROR);
			}
			
			//COLONNE COMMENTAIRE//
			
			if(!textCommentaire.getText().equals("")) {
				myPrepStat.setString(4,textCommentaire.getText());
			}
			else {
				myPrepStat.setNull(4,Types.VARCHAR);
			}
			
			//COLONNE DUREINSTALLE//
			if(!duree.getValue().equals(0)) {
				myPrepStat.setInt(5, (int)duree.getValue());
			}
			else {
				JOptionPane.showMessageDialog(null,"No Installation Time value selected !","error" , ERROR);
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
				myPrepStat.setString(7, (String) comboValid.getSelectedItem());
				myPrepStat.setDate(8, new java.sql.Date(datePlanifiedPanel.getDate().getTime()));
			}
			if(comboValid.getSelectedItem().equals("working on")) {
				myPrepStat.setString(7, (String) comboValid.getSelectedItem());
				myPrepStat.setNull(8, Types.DATE);
			}
			if(comboValid.getSelectedItem().equals("finished")) {
				myPrepStat.setString(7, (String) comboValid.getSelectedItem());
				myPrepStat.setNull(8, Types.DATE);
			}
			
			//COLONNECODESOFTWARE//
			if(comboSoft.getSelectedItem().equals("Software:")) {
				JOptionPane.showMessageDialog(null,"No software responsable selected !","error" , ERROR);
			}
			else {
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
			}
			
			
			//COLONNEADMINRESEAU//
			if(comboMatri.getSelectedItem().equals("Network Responsable:")) {
				JOptionPane.showMessageDialog(null,"No network responsable selected !","error" , ERROR);
			}
			else {
				if(comboMatri.getSelectedItem().equals("Alexandre Baligant")){
					myPrepStat.setString(10, "AlBa");
				}
				if(comboMatri.getSelectedItem().equals("André Van Kerrebroeck")){
					myPrepStat.setString(10, "AVK");
				}
				if(comboMatri.getSelectedItem().equals("Marvin Gobin")){
					myPrepStat.setString(10, "MarGob");
				}
			}
			
			//COLONNETABLEOS//
			if(comboOS.getSelectedItem().equals("OS:")) {
				JOptionPane.showMessageDialog(null,"No OS selected !","error" , ERROR);
			}
			else {
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
			}
		int nbUpdatesLines = myPrepStat.executeUpdate();
		JOptionPane.showMessageDialog(null, "succesfully added !");
		cleanTextField();
		}
			
		 catch (Exception e) {
			 System.out.println("error");
		 }
		
		
	}
//mouse listener
	private class mouse implements MouseListener,MouseMotionListener{
		public void mouseClicked(MouseEvent e) {
			if(e.getSource()==textCommentaire) {
				textCommentaire.setText("");
			}
			if(e.getSource()==textRef) {
				textRef.setText("");
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
					datePlanifiedLabel.setVisible(true);
					datePlanifiedPanel.setVisible(true);
				}
				else {
					datePlanifiedLabel.setVisible(false);
					datePlanifiedPanel.setVisible(false);
				}
			}
		}
	}
//counter id	
	public int idCount (Connection connect) {
		int count=0;
		try {
			PreparedStatement prepStatSoft = connect.prepareStatement("SELECT * FROM dbinstallations.Installation;");
			TableModelGen table1 = AccessBDGen.creerTableModel(prepStatSoft);
			count=table1.getRowCount();
		}	 
		catch (SQLException e) {
			System.out.println("pas ok");
			JOptionPane.showMessageDialog(null, e.getMessage());
			}
		return count;
	}
}
