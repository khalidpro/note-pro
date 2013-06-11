import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JTextArea;


public class Apropos extends JFrame{

	public Apropos()
	{
		String str="\n Note Pro\n"+
					"Projet IHM avec Swing\n"+
					"réalisé par : TAHRI Khalid\n"+
					":)";
		this.setTitle("[A propos de Note Pro]");
		this.setSize(260, 220);
		this.setLocationRelativeTo(null);
		JTextArea txt=new JTextArea(str);
		this.getContentPane().add(txt);
		txt.setBackground(this.getBackground());
		this.setVisible(true);
	}

}
