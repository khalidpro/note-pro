import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import javax.swing.JFileChooser;


public class Fichier {
	
	public static void enregistre(String chemin, String text) {
		try {

			FileWriter fw = new FileWriter(chemin, true);
			BufferedWriter output = new BufferedWriter(fw);
			output.write(text);
			output.flush();
			output.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}

	
	public static String ouvrir(String chemin) {
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
			return str;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
}
