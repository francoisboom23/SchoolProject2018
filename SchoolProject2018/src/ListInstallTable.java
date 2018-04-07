import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import accessBD.AccessBDGen;
import accessBD.TableModelGen;

public class ListInstallTable extends JPanel{
	static final private String nomBD="dbinstallations";
	static final private String username="root";
	static final private String password="Tigrou007";
	private Connection connect;
	
	ListInstallTable(){
		try {
			Connection connect = AccessBDGen.connecter(nomBD, username, password);
			String sqlInstruction = "select * from Installation;";
			PreparedStatement myPrep = connect.prepareStatement(sqlInstruction);
			TableModelGen interventionModel = AccessBDGen.creerTableModel(myPrep);
		 	JTable interventionTable = new JTable(interventionModel);
		 	interventionTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		 	JScrollPane defilant = new JScrollPane (interventionTable) ;
		 	this.add(defilant);
		 	
		 	connect.close();
			}
		catch(SQLException e) {	}
	}
}
