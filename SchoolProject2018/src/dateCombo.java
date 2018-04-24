//FETCH AVANT TOUS CHANGEMENTS SOUS PEINE DE MORT!

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

public class dateCombo  extends JPanel{
	private String[] dayList = {"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
	private String[] monthListText = {"January","February","March","April","May","June","July","August","September","October","November","December"};
	private String[] monthList = {"01","02","03","04","05","06","07","08","09","10","11","12"};
	private String[] yearList = {"2000","2001","2002","2003","2004","2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017","2018"};
	private JComboBox day,month,year;
	private java.util.Date dateJava;
	
	public dateCombo() {
		//setBounds(150,400,200,50);
		setLayout(new GridLayout(1,3,1,1));
		day = new JComboBox(dayList);
		month = new JComboBox(monthListText);
		year = new JComboBox(yearList);
		
		add(year);
		add(month);
		add(day);
		setVisible(true);
	}
	
	public Date getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dateText = (String)year.getSelectedItem()+0+(month.getSelectedIndex()+1)+(String)day.getSelectedItem();
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
