import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.swing.*;

import accessBD.AccessBDGen;
import accessBD.TableModelGen;

public class DelInstall extends JPanel {
	private JLabel providerLabel,indication;
	private JButton listInstall,delButton; //addDelButton
	private JComboBox listProvider;
	private Windows parent;
	private String sqlRequest;
	
	public DelInstall (Connection connect, Windows win) {
		
		// general
		parent=win;
		
		// controls
		
		providerLabel = new JLabel ("Provider :");
		providerLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		indication = new JLabel("(select item to delete in table)");
		indication.setHorizontalAlignment(SwingConstants.CENTER);
		listProvider = new JComboBox ();
		listInstall = new JButton ("List");
		delButton = new JButton ("Delete Install"); // Ajout boutton
		add(providerLabel);
		add(listProvider);
		add(listInstall);
		add(delButton);
		
		// Listener
		
		Butlistener a = new Butlistener();
		listInstall.addActionListener(a);
		delButton.addActionListener(a);
		
		// SQL Database
		fillCombobox(connect);
		
	}
		//fill combobox
		private void fillCombobox(Connection connect) {
			try {
				PreparedStatement prepStat = connect.prepareStatement("SELECT Designation FROM Fournisseur;");
				TableModelGen table2 = AccessBDGen.creerTableModel(prepStat);
				for(int i=0; i <= table2.getRowCount()-1; i++) {
					listProvider.addItem(table2.getValueAt(i, 0));
					}
				}
			catch(SQLException e) {
				JOptionPane.showMessageDialog(null,e.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
			}		
		}
	//listener refresh button
		private class Butlistener implements ActionListener{
			private int indiceLigneSelection,index ;
			private tableModel f2;
			
			public void actionPerformed( ActionEvent a){
				if(a.getSource()==delButton) {
					JOptionPane confirmation = new JOptionPane();
					if(f2.getTable2()==0) {
						JOptionPane.showMessageDialog(null,"no valid selection");
					}
					else {
						System.out.println("autre");
						int input =JOptionPane.showConfirmDialog(null," Are you sure to delete this line ?");
						if(input == 0) {
							try {
								sqlRequest="DELETE FROM Installation WHERE IdInstallation = ?;";
								PreparedStatement prepstat = parent.getConnect().prepareStatement(sqlRequest);
								prepstat.setInt(1,f2.getTable2());
								int nbUpdatedLines = prepstat.executeUpdate(); 
								getDelItem(f2.getTable2());
								refresh();

							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							System.out.println(sqlRequest);
							}
					}
				}
				if(a.getSource()==listInstall){
					if((String)listProvider.getSelectedItem()=="") {
						sqlRequest="";
						JOptionPane.showMessageDialog(null, "<html><body><p align=\\\"center\\\">invalid selection</p></body></html>", "error", JOptionPane.ERROR_MESSAGE);
					}
					else {
						refresh();
					}
				}
			}
			public void refresh() {
				sqlRequest = "SELECT inst.IdInstallation, inst.DateInstallation, inst.CodeSoftware, inst.Matricule FROM Installation inst"+" JOIN Software soft"+" ON inst.CodeSoftware = soft.CodeSoftware "+"JOIN Fournisseur fourn "+"ON soft.CodeFourn = fourn.CodeFourn "+"WHERE Designation LIKE'"+(String)listProvider.getSelectedItem()+"';";
				System.out.println(sqlRequest);
				
				f2 = new tableModel(parent.getConnect(), sqlRequest);
				DelInstall listInstallByDesignation= new DelInstall (parent.getConnect(),parent.getWin());
				listInstallByDesignation.SetBox((String)listProvider.getSelectedItem());
				
				parent.getCont().removeAll();
				parent.getCont().setLayout(new BorderLayout());
				parent.getCont().add(listInstallByDesignation,BorderLayout.NORTH);
				parent.getCont().add(f2,BorderLayout.CENTER);
				parent.getCont().add(indication,BorderLayout.SOUTH);
				parent.getCont().repaint();
				parent.getCont().setVisible(true);
				parent.validate();
			}
		}
		
		
		public void SetBox(String selection){
			listProvider.setSelectedItem(selection);
		}
		public void getDelItem(int item) {
			System.out.println(item);
		}
	}
	
	


