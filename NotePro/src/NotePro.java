import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileFilter;

public class NotePro extends JFrame {
	String file = "";
	static JTextArea editeur = new JTextArea();
	Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

	public NotePro() {
		this.setTitle("Note Pro");
		this.setSize(400, 440);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// changer l'icon par defaut de JFram
		this.setIconImage(getToolkit().getImage("images/icon.png"));

		this.setMenu();
		this.setToolBar();
		this.setEditeur();
		this.setVisible(true);
	}

	// Initialisation des menus
	private void setMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menuFichier = new JMenu("Fichier");
		JMenuItem menuItemNouveau = new JMenuItem("Nouveau");
		JMenuItem menuItemOuvrir = new JMenuItem("Ouvrir...");
		JMenuItem menuItemEnregistre = new JMenuItem("Enregistre");
		JMenuItem menuItemEnregistreSous = new JMenuItem("Enregistre Sous");
		JMenuItem menuItemQuitter = new JMenuItem("Quitter");

		JMenu menuEdition = new JMenu("Edition");
		JMenuItem menuItemCopier = new JMenuItem("Copier");
		JMenuItem menuItemCouper = new JMenuItem("Couper");
		JMenuItem menuItemColler = new JMenuItem("Coller");

		JMenu menuFormat = new JMenu("Format");
		JMenuItem menuItemPolice = new JMenuItem("Police");

		JMenu menuAide = new JMenu("Aide");
		JMenuItem menuItemAPropos = new JMenuItem("A propos du Note Pro");

		this.setJMenuBar(menuBar);

		// Menu Fichier

		menuBar.add(menuFichier);
		menuFichier.setMnemonic('F');
		menuFichier.add(menuItemNouveau);
		menuItemNouveau.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				KeyEvent.CTRL_MASK));
		menuFichier.add(menuItemOuvrir);
		menuItemOuvrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				KeyEvent.CTRL_MASK));
		menuFichier.add(menuItemEnregistre);
		menuItemEnregistre.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				KeyEvent.CTRL_MASK));
		menuFichier.add(menuItemEnregistreSous);
		menuFichier.addSeparator();
		menuFichier.add(menuItemQuitter);
		menuItemQuitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
				KeyEvent.CTRL_MASK));

		// Menu Edition

		menuBar.add(menuEdition);
		menuEdition.setMnemonic('E');
		menuEdition.add(menuItemCopier);
		menuItemCopier.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				KeyEvent.CTRL_MASK));
		menuEdition.add(menuItemCouper);
		menuItemCouper.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				KeyEvent.CTRL_MASK));
		menuEdition.add(menuItemColler);
		menuItemColler.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
				KeyEvent.CTRL_MASK));

		// Menu Format

		menuBar.add(menuFormat);
		menuFormat.setMnemonic('F');
		menuFormat.add(menuItemPolice);

		// Menu Aide

		menuBar.add(menuAide);
		menuAide.setMnemonic('A');
		menuAide.add(menuItemAPropos);

		// Click Menu Ouvrir
		// --------------------------------------------------------------------//
		menuItemOuvrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String chemin = "";
				JFileChooser dialogue = new JFileChooser();
				dialogue.showOpenDialog(null);

				if (dialogue.getSelectedFile() != null) {
					chemin = dialogue.getSelectedFile().toString();
					editeur.setText(Fichier.ouvrir(chemin));
					file = chemin;
					setTitle("Note Pro [" + file + "]");
				}
			}
		});
		// Click Menu Ouitter
		// --------------------------------------------------------------------//
		menuItemQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		// Click Menu Nouveau
		// --------------------------------------------------------------------//
		menuItemNouveau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nouveau();
			}
		});
		// Click Menu A propos
		// --------------------------------------------------------------------//
		menuItemAPropos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Apropos a = new Apropos();

			}
		});
		// Click Menu Police
		// --------------------------------------------------------------------//
		menuItemPolice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Police p = new Police();

			}
		});
		// Click Menu Enregistre Sous
		// --------------------------------------------------------------------//
		menuItemEnregistreSous.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				enregistre();
			}
		});
		// Click Menu Enregistre
		// --------------------------------------------------------------------//
		menuItemEnregistre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (file.equals("")) {
					enregistre();
				} else {
					Fichier.enregistre(file, editeur.getText());
				}

			}
		});
		// Click Menu Coller
		// --------------------------------------------------------------------//
		menuItemColler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					editeur.insert(clipboard.getData(DataFlavor.stringFlavor)
							.toString(), editeur.getCaretPosition());
				} catch (UnsupportedFlavorException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		// Click Menu Copier
		// --------------------------------------------------------------------//
		menuItemCopier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Clipboard clipboard = Toolkit.getDefaultToolkit()
						.getSystemClipboard();
				StringSelection data = new StringSelection(editeur
						.getSelectedText());
				clipboard.setContents(data, data);
			}
		});
		// Click Menu Couper
		// --------------------------------------------------------------------//
		menuItemCouper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Clipboard clipboard = Toolkit.getDefaultToolkit()
						.getSystemClipboard();
				StringSelection data = new StringSelection(editeur
						.getSelectedText());
				clipboard.setContents(data, data);
				editeur.replaceSelection("");
			}
		});
	}

	// Initialisation de la barre d'outils
	private void setToolBar() {
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		JButton barNouveau = new JButton(new ImageIcon("images/file_new.png"));
		JButton barEnregistre = new JButton(new ImageIcon("images/save.png"));
		toolBar.add(barNouveau);
		toolBar.add(barEnregistre);
		toolBar.addSeparator();
		this.getContentPane().add(toolBar, BorderLayout.NORTH);

		// Click Bar d'outil Nouveau
		// --------------------------------------------------------------------//
		barNouveau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nouveau();
			}
		});
		// Click Bar d'outil Enregistre
		// --------------------------------------------------------------------//
		barEnregistre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (file.equals("")) {
					enregistre();
				} else {
					Fichier.enregistre(file, editeur.getText());
				}
			}
		});
		// --------------------------------------------------------------------//

	}

	// Initialisation de l'editeur
	private void setEditeur() {
		JScrollPane scroll = new JScrollPane(editeur);
		this.getContentPane().add(scroll, BorderLayout.CENTER);

	}

	private void enregistre() {
		JFileChooser c = new JFileChooser();
		int rVal = c.showSaveDialog(c);
		if (rVal == JFileChooser.APPROVE_OPTION) {
			String chemin = c.getCurrentDirectory().toString() + "/"
					+ c.getSelectedFile().getName() + ".txt";
			Fichier.enregistre(chemin, editeur.getText());
			file = chemin;
			setTitle("Note Pro [" + file + "]");
		}
	}

	private void nouveau() {
		if (!editeur.getText().equals("")) {
			int rep = JOptionPane.showConfirmDialog(null,
					"voulez-vous enregistre les modification ?!");
			switch (rep) {
			case 0:

				if (file.equals("")) {
					enregistre();
				} else {
					Fichier.enregistre(file, editeur.getText());
				}

				editeur.setText("");
				file = "";
				setTitle("Note Pro");
				break;
			case 1:
				editeur.setText("");
				file = "";
				setTitle("Note Pro");
				break;
			}
		}
	}

}
