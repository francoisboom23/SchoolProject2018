import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
//OS, software, responsable réseau
public class Windows extends JFrame{
	private Container cont;
	private JMenuBar menuBar;
	private JMenu file, list, insert, delete, help;
	private JMenuItem home,quit,InsertNewInstall,listInstall,deleteInstall,listPreInstall,listSoftSection,wiki,about;
	
	public Windows(){
		//generale
		super("Super Calculator 2030");
		cont = getContentPane();
		setBounds(710,290,500,500);
		setResizable(false);
		cont.setLayout(null);
		
		//menubar
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		file = new JMenu("file");
		list = new JMenu("list");
		insert = new JMenu("insert");
		delete = new JMenu("delete");
		help = new JMenu("help");
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
		
		//menuItem
		home = new JMenuItem("home");
		quit = new JMenuItem("quit");
		InsertNewInstall = new JMenuItem("Insert a new install");
		listInstall = new JMenuItem("list all installed software");
		deleteInstall = new JMenuItem("delete an install");
		listPreInstall = new JMenuItem("list pre-installed software");
		listSoftSection = new JMenuItem("list installed software for a specific section");
		wiki = new JMenuItem("wiki");
		about = new JMenuItem("about");
		
		file.add(home);
		file.add(quit);
		list.add(listInstall);
		list.add(listPreInstall);
		list.add(listSoftSection);
		insert.add(InsertNewInstall);
		delete.add(deleteInstall);
		help.add(wiki);
		help.add(about);
		
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
}
