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
	private String[] dayList = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
	private String[] monthList = {"January:","February","March","April:","May","June","July:","August","September","October:","November","December"};
	private String[] yearList = {"2000","2001","2002","2003","2004","2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017","2018"};
	private JComboBox day,month,year;
	
	public dateCombo() {
		//setBounds(150,400,200,50);
		setLayout(new GridLayout(1,3,1,1));
		day = new JComboBox(dayList);
		month = new JComboBox(monthList);
		year = new JComboBox(yearList);
		
		add(year);
		add(month);
		add(day);
		setVisible(true);
	}
	public Date getDate() {
		String dateText = year.getSelectedItem()+"/"+month.getSelectedItem()+"/"+(String)day.getSelectedItem();
		Date date=new Date(2000,10,10);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd");
		try {
			date = sdf.parse(dateText);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		return date;
	}
}
