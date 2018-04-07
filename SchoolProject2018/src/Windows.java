import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.sql.*;
import accessBD.*;

//OS, software, responsable réseau

public class Windows extends JFrame{
	private Container cont;
	private JMenuBar menuBar;
	private JMenu file, list, insert, delete, help;
	private JMenuItem welcome,quit,InsertNewInstall,listInstall,deleteInstall,listPreInstall,listSoftSection,wiki,about;
	private String nomBD="dbinstallations";
	private String username="root";
	private String password="Tigrou007";
	private Connection connect;
	
	public Windows(){
//general
		super("IESN Calculator Student Pro DVD Architect 2048");
		cont = getContentPane();
		setBounds(710,290,500,500);
		setResizable(false);
		setLayout(null);
		
		try {
			connect = AccessBDGen.connecter(nomBD, username, password);
			System.out.println("connecté");
		}
		catch(SQLException e) {
			System.out.println("non connecté");
		}
		
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
		wiki = new JMenuItem("wiki");
		about = new JMenuItem("about this program");
		//add
		file.add(welcome);
		file.addSeparator();
		file.add(quit);
		list.add(listInstall);
		list.add(listPreInstall);
		list.add(listSoftSection);
		insert.add(InsertNewInstall);
		delete.add(deleteInstall);
		help.add(wiki);
		help.addSeparator();
		help.add(about);
		//listener
		welcome.addActionListener(menuLi);
		quit.addActionListener(menuLi);
		wiki.addActionListener(menuLi);
		about.addActionListener(menuLi);
		listInstall.addActionListener(menuLi);
		
//close program
		WindowClose w = new WindowClose();
		this.addWindowListener(w);
		
		setVisible(true);
	}
	
//close program function
	class WindowClose extends WindowAdapter {
		public void windowClosing(WindowEvent w) {
			System.exit(0);
		}
	};
	
//listener menu
	private class menuListener implements ActionListener{
		public void actionPerformed (ActionEvent e) {
			if(e.getSource() == quit) {
				System.exit(0);
			}
			if(e.getSource() == wiki){
				try {//demander a madame
					Desktop desktop = Desktop.getDesktop();
					desktop.browse(new URI("http://www.google.com"));
					} 
				catch (Exception exceptWeb) {
					exceptWeb.printStackTrace();
					}
			}
			if(e.getSource() == about){
				About f1 = new About();
			}
			if(e.getSource() == welcome){
				Windows.this.dispose();
				Home f2 = new Home();
			}
			if(e.getSource() == listInstall){
				//change le panel par un autre
				cont.removeAll();
				ListInstallTable listinstalltable = new ListInstallTable ();
				cont.add(listinstalltable);
				cont.repaint();
				Windows.this.setVisible(true);
			}
		}
	}
}
