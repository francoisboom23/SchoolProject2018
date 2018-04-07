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
//general
		super("IESN Calculator Student Pro DVD Architect 2048");
		cont = getContentPane();
		setBounds(710,290,500,500);
		setResizable(false);
		setLayout(null);
		
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
		home = new JMenuItem("home");
		quit = new JMenuItem("quit");
		InsertNewInstall = new JMenuItem("Insert a new install");
		listInstall = new JMenuItem("list all installed software");
		deleteInstall = new JMenuItem("delete an install");
		listPreInstall = new JMenuItem("list pre-installed software");
		listSoftSection = new JMenuItem("list installed software for a specific section");
		wiki = new JMenuItem("wiki");
		about = new JMenuItem("about this program");
		//add
		file.add(home);
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
		quit.addActionListener(menuLi);
		about.addActionListener(menuLi);
		
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
				if(e.getSource() == about){
					About f2 = new About();
				}
			}
		}
}
