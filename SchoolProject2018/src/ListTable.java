import java.awt.BorderLayout;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

import accessBD.AccessBDGen;
import accessBD.TableModelGen;

public class ListTable extends JPanel{

	private JLabel ListTableLabel;
	private JComboBox<String> combox;
	private JButton refresh;
	private String sqlRequest;
	private Windows parent;
	
	
	public ListTable (Connection connect,Windows win) {
		//generale
				setBounds(0,0,500,500);
				parent=win;
		//controls
				ListTableLabel = new JLabel("table :");
				ListTableLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				combox = new JComboBox<String>();
				refresh = new JButton("refresh");
				add(ListTableLabel);
				add(combox);
				add(refresh);
		//listener controls
				Butlistener a = new Butlistener();
				refresh.addActionListener(a);

		//SQL database
				fillCombobox(connect);
		}
		//fill combobox
			private void fillCombobox(Connection connect) {
				try {
					PreparedStatement prepStat = connect.prepareStatement("SHOW TABLES");
					TableModelGen table2 = AccessBDGen.creerTableModel(prepStat);
					for(int i=0; i <= table2.getRowCount()-1; i++) {
						combox.addItem((String) table2.getValueAt(i, 0));
						}
					}
				catch(SQLException e) {
					JOptionPane.showMessageDialog(null,e.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
				}
	}
			private class Butlistener implements ActionListener{
				public void actionPerformed( ActionEvent a){
					if(a.getSource()==refresh){
						sqlRequest = "SELECT * FROM "+(String)combox.getSelectedItem()+";";
						System.out.println(sqlRequest);
						tableModel f2 = new tableModel(parent.getConnect(), sqlRequest);
						ListTable listTable= new ListTable (parent.getConnect(),parent.getWin());
						listTable.SetBox((String)combox.getSelectedItem());

						parent.getCont().removeAll();
						parent.getCont().setLayout(new BorderLayout());
						parent.getCont().add(listTable,BorderLayout.NORTH);
						parent.getCont().add(f2,BorderLayout.CENTER);
						parent.getCont().repaint();
						parent.getCont().setVisible(true);
						parent.validate();
					}
				}
			}
			//set combobox default selection same as selected refresh
			public void SetBox(String selection){
				combox.setSelectedItem(selection);
			}
}
