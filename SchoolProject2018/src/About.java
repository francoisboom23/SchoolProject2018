import javax.swing.*;

public class About extends JFrame{
	private JLabel author;
	
	public About(){
//general
		super("about this program");
		setBounds(850,450,250,120);
		setResizable(false);

//content
		author = new JLabel("<html><body><p align=\"center\">Version 1.0<br>Authors:<br>Curé Vincent<br>Devilez François</p></body></html>");
		author.setHorizontalAlignment(SwingConstants.CENTER);
		add(author);
		setVisible(true);
	}
}

