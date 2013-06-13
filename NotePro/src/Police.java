import java.awt.BorderLayout;
import java.awt.ScrollPane;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Police extends JFrame {

	String[] fonts = { "Arial", "Courier New", "Georgia", "Comic Sans MS",
			"Times New Roman", "Consolas" };
	JList listeFonts = new JList(fonts);

	String[] styles = { "Normal", "Italique", "Gras", "Italique gras" };
	JList listeStyles = new JList(styles);

	String[] sizes = { "8", "10", "12", "14", "16", "18", "20", "24" };
	JList listeSizes = new JList(sizes);

	JButton btOK = new JButton("OK");
	JButton btAnnuler = new JButton("Annuler");

	public Police() {

		this.setTitle("Police");
		this.setSize(300, 300);
		this.setLocationRelativeTo(null);

		// listes
		JPanel pantop = new JPanel();
		pantop.setBorder(BorderFactory.createTitledBorder("Stlyle du font:"));
		pantop.add(new JScrollPane(listeFonts));
		pantop.add(new JScrollPane(listeStyles));
		pantop.add(new JScrollPane(listeSizes));

		listeFonts.setVisibleRowCount(4);
		listeStyles.setVisibleRowCount(4);
		listeSizes.setVisibleRowCount(4);
		this.getContentPane().add(pantop, BorderLayout.NORTH);

		// Bouton
		JPanel panApercu = new JPanel();
		panApercu.setBorder(BorderFactory.createTitledBorder("Aperçu :"));
		this.getContentPane().add(panApercu, BorderLayout.CENTER);

		// Bouton
		JPanel panBt = new JPanel();
		panBt.setBorder(BorderFactory.createTitledBorder(""));
		panBt.add(btAnnuler);
		panBt.add(btOK);
		this.getContentPane().add(panBt, BorderLayout.SOUTH);

		this.setVisible(true);
	}

}
