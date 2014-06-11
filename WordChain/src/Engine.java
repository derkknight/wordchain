import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;
/**
 * WordChain
 * @version 0.1.0
 * @author Derick Yung
 * A Java implementation of the Word Chain game. Uses a Trie
 * data structure to determine the validity of a user's
 * word. Uses another trie to check if the word has been
 * used before. Local game only at the moment.
 * 
 * DISCLAIMER:
 * The original word list is provided by Curlew Commons.
 * This list is edited by Derick Yung to omit
 * any words containing spaces.
 * 
 * The original word list can be obtained at:
 * http://www.curlewcommunications.co.uk/wordlist.html
 * 
 */
public class Engine {
	
	/**
	 * Check if the user input word is a valid word in the dictionary.
	 * Used when a required_char exists to validate the word (non-first
	 * turns).
	 * @param required_char compared to first letter of word
	 * @param word word to be validated
	 * @param trie trie of all valid words
	 * @return
	 */
	private static boolean isValidWord(char required_char, String word, Trie trie)
	{
		if (required_char == word.charAt(0))
		{
			if (trie.search(word)) return true;
			else return false;
		}
		else return false;
	}
	
	/**
	 * Check if the user input word is never used before.
	 * @param word word to be validated
	 * @param used_trie trie of used words
	 * @return
	 */
	private static boolean isNewWord(String word, Trie used_trie)
	{
		if (!used_trie.search(word)) return true;
		return false;
	}
	
	/**
	 * Check if word exists in dictionary.
	 * @param word word to be validated
	 * @param trie trie of all valid words
	 * @return
	 */
	private static boolean isValidWord(String word, Trie trie)
	{
		if (trie.search(word)) return true;
		return false;
	}
	
	/**
	 * Runs a game with two players locally. Takes turns between players
	 * in a while loop. First verifies if word is valid, and then
	 * verifies word is not used.
	 * @param trie holds all valid words
	 * @param used_trie holds all used words
	 */
	private static void localGame(Trie trie, Trie used_trie)
	{
		Scanner reader = new Scanner(System.in);
		char required_char = 0;
		int turn;
		if (trie.root.letter == ' ') turn = -1;
		else
		{
			turn = trie.root.letter;
		}
		while(turn != 0)
		{
			if (turn == 1)
			{
				System.out.println("Player 1's Turn:");
				String word = reader.next();
				if (isValidWord(required_char, word, trie))
				{
					if (isNewWord(word, used_trie))
					{
						required_char = word.charAt(word.length()-1);
						used_trie.insertWord(word);
						turn++;
					}
					else System.out.println("This word is already used!");
				}
				else System.out.println("This word is invalid.");

			}
			else if (turn == 2)
			{
				System.out.println("Player 2's Turn:");
				String word = reader.next();
				if (isValidWord(required_char, word, trie))
				{
					if (isNewWord(word, used_trie))
					{
						required_char = word.charAt(word.length()-1);
						used_trie.insertWord(word);
						turn--;
					}
					else System.out.println("This word is already used!");
				}
				else System.out.println("This word is invalid.");
			}
			else if (turn == -1)
			{
				//The very first turn. Special case because
				//there is no required_char assigned yet.
				System.out.println("Player 1's Turn:");
				String word = reader.next();
				if (isValidWord(word, trie))
				{
					if (isNewWord(word, used_trie))
					{
						required_char = word.charAt(word.length()-1);
						used_trie.insertWord(word);
						turn = 2;
					}
				}
				else System.out.println("This word is invalid.");
			}
		}
	}
	
	
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		char required_char;
		System.out.println("(S)ingle-Player or (T)wo-Player?");
		String mode = reader.next();
		
		if (mode.equals("S"))
		{
			System.out.println("Hello");
		}
		else if (mode.equals("T"))
		{
			String textPath = System.getProperty("user.dir") + "/src/brit-a-z.txt";
			Trie valid_words = Importer.readText(textPath);
			Trie game = new Trie();
			//valid_words = Importer.readText(textPath);
			localGame(valid_words, game);
		}
		else if (mode.equals("D"))
		{//Debug game
			String textPath = System.getProperty("user.dir") + "/src/brit-a-z.txt";
			Trie valid_words = new Trie();
			Trie game = new Trie();
			valid_words = Importer.readText(textPath);
		}

	}

	
}
