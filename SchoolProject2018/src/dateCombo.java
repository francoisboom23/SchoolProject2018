//FETCH AVANT TOUS CHANGEMENTS SOUS PEINE DE MORT!

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

public class dateCombo  extends JPanel{
	private String[] monthListText = {"January","February","March","April","May","June","July","August","September","October","November","December"};
	private JComboBox<Integer> day,year;
	private JComboBox<String> month;
	private java.util.Date dateJava;
	
	public dateCombo() {
		setLayout(new GridLayout(1,3,1,1));
		day = new JComboBox<Integer>();
		month = new JComboBox<String>(monthListText);
		year = new JComboBox<Integer>();
		
		for(int a=2020;a>=1900;a--) {
			year.addItem(a);
		}
		for(int b=1;b<=31;b++) {
			day.addItem(b);
		}
		add(year);
		add(month);
		add(day);
		setVisible(true);
	}
	
	public Date getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dateText = year.getSelectedItem()+"0"+(month.getSelectedIndex()+1)+day.getSelectedItem();
		try {
			dateJava = sdf.parse(dateText);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateJava;
	}
	public void print() {
		String dateText = (String)year.getSelectedItem()+0+(month.getSelectedIndex()+1)+(String)day.getSelectedItem();
		System.out.println(dateText);
	}
}
