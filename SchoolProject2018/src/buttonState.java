//FETCH AVANT TOUS CHANGEMENTS SOUS PEINE DE MORT!

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class buttonState extends JPanel{
	
	private JRadioButton but1,but2,but3;
	private ButtonGroup group1;
	private int choice;
	private dateCombo date;
	
	public buttonState(dateCombo datePanel) {
		setLayout(new GridLayout(1,3,1,1));
		but1 = new JRadioButton("Planified",true);
		but2 = new JRadioButton("Working on",false);
		but3 = new JRadioButton("finished",false);
		add(but1);
		add(but2);
		add(but3);
		group1 = new ButtonGroup();
		group1.add(but1);
		group1.add(but2);
		group1.add(but3);
		
		listener g = new listener();
		but1.addItemListener(g);
		but2.addItemListener(g);
		but3.addItemListener(g);
		
		date = datePanel;
		date.setVisible(true);
		
		setVisible(true);
	}
	
	private class listener implements ItemListener{
		
		public void itemStateChanged(ItemEvent t) {
			if(t.getSource()==but1 && t.getStateChange()==ItemEvent.SELECTED) {
				choice=1;
				date.setVisible(true);
				System.out.println("1");
			}
			if(t.getSource()==but2 && t.getStateChange()==ItemEvent.SELECTED) {
				choice=2;
				date.setVisible(false);
				System.out.println("2");
			}
			if(t.getSource()==but3 && t.getStateChange()==ItemEvent.SELECTED) {
				choice=3;
				date.setVisible(false);
				System.out.println("3");
			}
		}
	}
	public int getBut() {
		return choice;
	}
}
