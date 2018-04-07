import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
//OS, software, responsable réseau
public class Windows extends JFrame{
	private Container cont;
	private JMenuBar menuBar;
	private JMenu home, list, insert, delete, help;
	private JMenuItem InsertNewInstall, menuItem2, wiki, about;
	
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
		home = new JMenu("home");
		list = new JMenu("list");
		insert = new JMenu("insert");
		delete = new JMenu("delete");
		help = new JMenu("help");
		home.setMnemonic('H');
		list.setMnemonic('L');
		insert.setMnemonic('I');
		delete.setMnemonic('D');
		help.setMnemonic('H');
		menuBar.add(home);
		menuBar.add(list);
		menuBar.add(insert);
		menuBar.add(delete);
		menuBar.add(help);
		
		//close program
		WindowClose w = new WindowClose();
		this.addWindowListener(w);
		
		setVisible(true);
	}
	
	//close program
	class WindowClose extends WindowAdapter {
		public void windowClosing(WindowEvent w) {
			System.exit(0);
		}
	};
}
