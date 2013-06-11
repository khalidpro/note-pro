import java.awt.BorderLayout;
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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileFilter;




public class NotePro extends JFrame{

	JTextArea editeur=new JTextArea();	
	
	public NotePro()
	{
		this.setTitle("Note Pro");
		this.setSize(400,440);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setMenu();
		this.setToolBar();
		this.setEditeur();
		this.setVisible(true);
		
	}
	// Initialisation des menus  
	private void setMenu()
	{		
		
		JMenuBar menuBar=new JMenuBar();
		JMenu menuFichier=new JMenu("Fichier");
		JMenuItem menuItemNouveau =new JMenuItem("Nouveau");
		JMenuItem menuItemOuvrir=new JMenuItem("Ouvrir...");
		JMenuItem menuItemEnregistre =new JMenuItem("Enregistre");
		JMenuItem menuItemEnregistreSous =new JMenuItem("Enregistre Sous");
		JMenuItem menuItemQuitter =new JMenuItem("Quitter");
		
		JMenu menuEdition=new JMenu("Edition");
		JMenuItem menuItemCopier =new JMenuItem("Copier");
		JMenuItem menuItemCouper =new JMenuItem("Couper");
		JMenuItem menuItemColler =new JMenuItem("Coller");
		
		JMenu menuAide=new JMenu("Aide");
		JMenuItem menuItemAPropos =new JMenuItem("A propos du Note Pro");
		
		this.setJMenuBar(menuBar);
		
		// Menu Fichier
		
		menuBar.add(menuFichier);
		menuFichier.setMnemonic('F');
		menuFichier.add(menuItemNouveau);	
		menuItemNouveau.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,KeyEvent.CTRL_MASK));
		menuFichier.add(menuItemOuvrir);
		menuItemOuvrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,KeyEvent.CTRL_MASK));
		menuFichier.add(menuItemEnregistre);
		menuItemEnregistre.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,KeyEvent.CTRL_MASK));
		menuFichier.add(menuItemEnregistreSous);
		menuFichier.addSeparator();
		menuFichier.add(menuItemQuitter);
		menuItemQuitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,KeyEvent.CTRL_MASK));
		
		// Menu Edition
		
		menuBar.add(menuEdition);
		menuEdition.setMnemonic('E');
		menuEdition.add(menuItemCopier);
		menuItemCopier.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,KeyEvent.CTRL_MASK));
		menuEdition.add(menuItemCouper);
		menuItemCouper.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,KeyEvent.CTRL_MASK));
		menuEdition.add(menuItemColler);
		menuItemColler.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,KeyEvent.CTRL_MASK));
		
		// Menu Aide
		
		menuBar.add(menuAide);
		menuAide.setMnemonic('A');
		menuAide.add(menuItemAPropos);
	
		//--------------------------------------------------------------------//
		menuItemOuvrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ouvrirFichier();
			}
		});
		//--------------------------------------------------------------------//
		menuItemQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();				
			}
		});
		//--------------------------------------------------------------------//
		//--------------------------------------------------------------------//
		//--------------------------------------------------------------------//
		
		
		
	}
	 // Initialisation de la barre d'outils 
	private void setToolBar()
	{
		JToolBar toolBar=new JToolBar();
		JButton barNouveau = new JButton(new ImageIcon("images/file_new.png"));
		JButton barEnregistre = new JButton(new ImageIcon("images/save.png"));
		toolBar.add(barNouveau);
		toolBar.add(barEnregistre);
		toolBar.addSeparator();
		
		this.getContentPane().add(toolBar , BorderLayout.NORTH);
		
	} 
	// Initialisation de l'editeur
	private void setEditeur()
	{
		this.getContentPane().add(editeur, BorderLayout.CENTER);
		
	}
	
	private void enregistreFichier(String chemin )
	{
		try {

			FileWriter fw = new FileWriter(chemin, true);
			BufferedWriter output = new BufferedWriter(fw);
			output.write(editeur.getText());
			output.flush();
			output.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
	}
	
	private void ouvrirFichier()
	{
		String chemin = "";
		JFileChooser dialogue = new JFileChooser();
		dialogue.showOpenDialog(null);
		chemin = dialogue.getSelectedFile().toString();
		
		FileInputStream fis;
		FileChannel fc;

		try {

			fis = new FileInputStream(new File(chemin));
			fc = fis.getChannel();
			int size = (int) fc.size();
			ByteBuffer bBuff = ByteBuffer.allocate(size);
			fc.read(bBuff);
			bBuff.flip();
			String str = new String(bBuff.array(), "UTF-8");
			editeur.setText(str);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}



