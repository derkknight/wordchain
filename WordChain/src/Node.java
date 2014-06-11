
public class Node {
	
	char letter;
	Node[] links;
	boolean isWord;
	
	Node(char letter)
	{
		this.letter = letter;
		links = new Node[26];
	}
	
}
