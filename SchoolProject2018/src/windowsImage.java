//FETCH AVANT TOUS CHANGEMENTS SOUS PEINE DE MORT!

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.sql.*;
import accessBD.*;

public class windowsImage extends JPanel {
	private JLabel image;
	public windowsImage() {
		setLayout(new FlowLayout());
		image = new JLabel(new ImageIcon("/home/francois/Documents/Programmation/git/SchoolProject2018/a.jpg"));
		add(image);
		setVisible(true);
	}
}
