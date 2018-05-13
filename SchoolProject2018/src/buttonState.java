//FETCH AVANT TOUS CHANGEMENTS SOUS PEINE DE MORT!

import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

public class buttonState extends JPanel{
	
	private JRadioButton but1,but2,but3;
	private ButtonGroup group1;
	private String choice="finished";
	private dateCombo date;
	private JLabel texte;
	
	public buttonState(dateCombo datePanel, JLabel datePlanifiedLabel) {
		setLayout(new GridLayout(1,3,1,1));
		but1 = new JRadioButton("Planified",false);
		but2 = new JRadioButton("Working on",false);
		but3 = new JRadioButton("finished",true);
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
		texte=datePlanifiedLabel;
		
		setVisible(true);
	}
	
	private class listener implements ItemListener{
		
		public void itemStateChanged(ItemEvent t) {
			if(t.getSource()==but1 && t.getStateChange()==ItemEvent.SELECTED) {
				choice=but1.getText();;
				date.setVisible(true);
				texte.setVisible(true);;
			}
			if(t.getSource()==but2 && t.getStateChange()==ItemEvent.SELECTED) {
				choice=but2.getText();
				date.setVisible(false);
				texte.setVisible(false);
			}
			if(t.getSource()==but3 && t.getStateChange()==ItemEvent.SELECTED) {
				choice=but3.getText();
				date.setVisible(false);
				texte.setVisible(false);
			}
		}
	}
	public String getBut() {
		return choice;
	}
}
