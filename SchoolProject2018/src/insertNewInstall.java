//FETCH AVANT TOUS CHANGEMENTS SOUS PEINE DE MORT!

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

import accessBD.AccessBDGen;
import accessBD.TableModelGen;

public class insertNewInstall extends JPanel{
//elements: idInstall, date install,type install, commentaires, durée install, ref procedure install,validation,date valid prevue, code soft(FK), matricule(FK),codeOS(FK)

	private JComboBox comboSoft,comboMatri,comboOS,comboType,comboValid;
	private JTextField textDate,textCommentaire,textDuree,textRef,textDatePrevoir;
	private String[] type = {"Type:","standart","custom"};
	private String[] valid = {"State:","planified","work in progress","finished"};
	
	public insertNewInstall(Connection connect) {
//generale
		setBounds(0,0,500,400);
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
		comboSoft.addItem("Software:");
		comboMatri.addItem("Network Responsable:");
		comboOS.addItem("OS:");
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
	}
	public void addInstall(Connection connect) {
		
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
}
