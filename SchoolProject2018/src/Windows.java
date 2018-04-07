import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Windows extends JFrame{
	private Container cont;
	private JMenuBar menuBar;
	private JMenu menu1, menu2, menu3;
	private JMenuItem menuItem1, menuItem2, menuItem3;
	
	public Windows(){
		//generale
		super("Super Calculator 2030");
		setBounds(710,290,500,500);
		setResizable(false);
		
		//menu
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuItem1 = new JMenu("application");
		menuItem2 = new JMenu("Etudiant");
		menuItem3 = new JMenu("Informations");
		menuItem1.setMnemonic('A');
		menuItem2.setMnemonic('E');
		menuItem3.setMnemonic('I');
		menuBar.add(menuItem1);
		menuBar.add(menuItem2);
		menuBar.add(menuItem3);
		
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
