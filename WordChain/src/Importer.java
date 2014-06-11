import java.util.List;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 
 * @author Derick Yung
 * Handles importing a word list text file.
 */
public class Importer {

	/**
	 * Read the list of words in a text file. Returns a trie.
	 * @param path text file's directory path
	 * @return
	 */
	
	public static Trie readText(String path)
	{
		Trie trie = new Trie();
		BufferedReader br = null;
		try {
			String line;
			br = new BufferedReader(new FileReader(path));
			while ((line = br.readLine()) != null)
			{
				
				if (line.matches("(.*)['ιθ-](.*)"))
				{//Do nothing
					;
				}
				else{
					//System.out.println(line);
					trie.insertWord(line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) br.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
		return trie;
	}
}
