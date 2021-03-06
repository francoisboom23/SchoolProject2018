//FETCH AVANT TOUS CHANGEMENTS SOUS PEINE DE MORT!

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.sql.*;

//OS, software, responsable réseau

public class Windows extends JFrame{
	private Container cont;
	private JMenuBar menuBar;
	private JMenu file, list, insert, delete, help;
	private JMenuItem welcome,quit,InsertNewInstall,listInstall,deleteInstall,listPreInstall,listSoftSection,wiki,about,contact,listTable;
	private Connection connect;
	private Desktop desktop = Desktop.getDesktop();
	private windowsImage i;
	private loginScreenPanel home;
	
	public Windows(loginScreenPanel parent){
//general
		super("IESN Calculator Student Pro DVD Architect 2048");
		cont = getContentPane();
		setBounds(710,290,500,500);
		setResizable(false);
		this.home=parent;
		
//connection SQL
		connect=home.getConnect();
		
//menubar
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		file = new JMenu("file");
		list = new JMenu("list");
		insert = new JMenu("insert");
		delete = new JMenu("delete");
		help = new JMenu("help");
//add
		file.setMnemonic('F');
		list.setMnemonic('L');
		insert.setMnemonic('I');
		delete.setMnemonic('D');
		help.setMnemonic('H');
		menuBar.add(file);
		menuBar.add(list);
		menuBar.add(insert);
		menuBar.add(delete);
		menuBar.add(help);
//listener
		menuListener menuLi = new menuListener();
		
//menuItem
		welcome = new JMenuItem("welcome");
		quit = new JMenuItem("quit");
		InsertNewInstall = new JMenuItem("Insert a new install");
		listInstall = new JMenuItem("list all installed software");
		deleteInstall = new JMenuItem("delete an install");
		listPreInstall = new JMenuItem("list pre-installed software");
		listSoftSection = new JMenuItem("list installed software for a specific section");
		listTable = new JMenuItem ("list table");
		wiki = new JMenuItem("wiki");
		about = new JMenuItem("about this program");
		contact = new JMenuItem("contact");
		//add
		file.add(welcome);
		file.addSeparator();
		file.add(quit);
		list.add(listInstall);
		list.add(listPreInstall);
		list.add(listSoftSection);
		list.add(listTable);
		insert.add(InsertNewInstall);
		delete.add(deleteInstall);
		help.add(wiki);
		help.add(contact);
		help.addSeparator();
		help.add(about);
		//listener
		welcome.addActionListener(menuLi);
		quit.addActionListener(menuLi);
		wiki.addActionListener(menuLi);
		about.addActionListener(menuLi);
		listInstall.addActionListener(menuLi);
		listPreInstall.addActionListener(menuLi);
		listSoftSection.addActionListener(menuLi);
		listTable.addActionListener(menuLi);
		InsertNewInstall.addActionListener(menuLi);
		contact.addActionListener(menuLi);
		deleteInstall.addActionListener(menuLi);
		
//close program & SQL connection
		WindowClose w = new WindowClose();
		this.addWindowListener(w);
//		
		i = new windowsImage();
		add(i);
		
		setVisible(true);
	}
	
//close program
	class WindowClose extends WindowAdapter {
		public void windowClosing(WindowEvent w) {
			try {
				connect.close();
			}
			catch(SQLException e) { }
			System.exit(0);
		}
	};
//listener menu
	private class menuListener implements ActionListener{
		public void actionPerformed (ActionEvent e) {
			if(e.getSource() == quit) {
				System.exit(0);
			}
			if(e.getSource()==contact) {
				try {//demander a madame
					desktop.mail();
					} 
				catch (Exception d) {
					d.printStackTrace();
					}
			}
			if(e.getSource() == wiki){
				try {
					desktop.browse(new URI("http://www.google.com"));
					} 
				catch (Exception o) {
					o.printStackTrace();
					}
			}
			if(e.getSource() == about){
				About f1 = new About();
			}
			if(e.getSource() == welcome){
				Windows.this.dispose();
				Home f2 = new Home(home);
			}
			if(e.getSource() == listInstall){
				cont.removeAll();
				ListInstallTable listinstalltable = new ListInstallTable (connect);
				cont.add(listinstalltable);
				cont.repaint();
				Windows.this.setVisible(true);
			}
			if(e.getSource() == listPreInstall){
				cont.removeAll();
				ListPreInstalledSoft listPreInstalledType= new ListPreInstalledSoft (connect,Windows.this);
				cont.add(listPreInstalledType);
				cont.repaint();
				Windows.this.setVisible(true);
			}
			if(e.getSource() == listSoftSection) {
				cont.removeAll();
				ListPreInstalledSoftBySection listPreInstalledSection = new ListPreInstalledSoftBySection (connect,Windows.this);
				cont.add(listPreInstalledSection);
				cont.repaint();
				Windows.this.setVisible(true);
			}
			if(e.getSource() == listTable) {
				cont.removeAll();
				ListTable listTable = new ListTable (connect,Windows.this);
				cont.add(listTable);
				cont.repaint();
				Windows.this.setVisible(true);
				
			}
			if(e.getSource() == InsertNewInstall) {
				cont.removeAll();
				insertNewInstall insertNew = new insertNewInstall (connect);
				cont.add(insertNew);
				cont.repaint();
				Windows.this.setVisible(true);
			}
			if(e.getSource()== deleteInstall) {
				cont.removeAll();
				DelInstall delinstall = new DelInstall (connect, Windows.this);
				cont.add(delinstall);
				cont.repaint();
				Windows.this.setVisible(true);
			}
		}
	}
	public Container getCont(){
		return cont;
	}
	public Connection getConnect() {
		return connect;
	}
	public Windows getWin() {
		return this;
	}
}
