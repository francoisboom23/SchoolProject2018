//FETCH AVANT TOUS CHANGEMENTS SOUS PEINE DE MORT!

import java.awt.BorderLayout;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

import accessBD.AccessBDGen;
import accessBD.TableModelGen;

public class PanelPreInstalledSoft extends JPanel{
	
	private String sqlRequest;
	private ListPreInstalledSoft controls;
	private TablePreInstalledSoft table;
	private static String sql;
	
	public PanelPreInstalledSoft(Connection connect) {
//general
		this.setBounds(10,10,480,430);
		setLayout(new BorderLayout());
//panel
		ListPreInstalledSoft controls = new ListPreInstalledSoft (connect);
		TablePreInstalledSoft table = new TablePreInstalledSoft (connect, controls.getSQL());
		//table.setVisible(false);
		add(controls,BorderLayout.NORTH);
		add(table,BorderLayout.CENTER);	
	}
}
