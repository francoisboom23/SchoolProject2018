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
		image = new JLabel(new ImageIcon("/home/francois/git/SchoolProject2018/SchoolProject2018/a2.jpg"));

		add(image);
		setVisible(true);
	}
}
