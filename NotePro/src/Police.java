import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
 
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

	JLabel lbl = new JLabel("Style de police");

	String font = "Arial";
	int style = 0;
	int size = 12;
	Font f = new Font(font, style, size);

	public Police() {

		this.setTitle("Police");
		this.setSize(300, 300);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setIconImage(getToolkit().getImage("icon.png"));

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
		lbl.setFont(f);
		panApercu.add(lbl);
		this.getContentPane().add(panApercu, BorderLayout.CENTER);

		// Bouton
		JPanel panBt = new JPanel();
		panBt.setBorder(BorderFactory.createTitledBorder(""));
		panBt.add(btAnnuler);
		panBt.add(btOK);
		this.getContentPane().add(panBt, BorderLayout.SOUTH);

		// EVENTS
		listeSizes.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				size = Integer.parseInt(listeSizes.getSelectedValue()
						.toString());
				f = new Font(font, style, size);
				lbl.setFont(f);
			}
		});

		listeFonts.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				font = listeFonts.getSelectedValue().toString();
				f = new Font(font, style, size);
				lbl.setFont(f);
			}
		});
		listeStyles.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int i = listeStyles.getSelectedIndex();
				switch (i) {
				case 0:
					style = 0;
					break;
				case 1:
					style = Font.ITALIC;
					break;
				case 2:
					style = Font.BOLD;
					break;
				case 3:
					style = Font.BOLD | Font.ITALIC;
					break;
				}
				f = new Font(font, style, size);
				lbl.setFont(f);
			}
		});
		
		btAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				
			}
		});
		btOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NotePro.editeur.setFont(f);
				setVisible(false);				
			}
		});
		
		this.setVisible(true);
	}
}
