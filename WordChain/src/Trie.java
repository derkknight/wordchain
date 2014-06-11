public class Trie {
	Node root;

	public Trie()
	{
		root = new Node(' ');
	}
	
	public void insertWord(String word)
	{
		char[] letters = word.toCharArray();
		Node current_node = root;
		
		
		for (int i = 0; i < word.length(); i++ )
		{
			if (current_node.links[letters[i]-97] == null)
			{
				current_node.links[letters[i]-97] = new Node(letters[i]);
			}
			current_node = current_node.links[letters[i]-97];
			//System.out.println(current_node.letter);

			if (i == word.length()-1) current_node.isWord = true;
		}
	}
	
	public boolean search(String word)
	{
		char[] letters = word.toCharArray();
		Node current_node = root;
		for (int i = 0; i < word.length(); i++ )
		{
			if (current_node.links[letters[i]-97] == null) return false;
			current_node = current_node.links[letters[i]-97];
			//System.out.println(current_node.letter);
		}
		if (!current_node.isWord && current_node != null) return false;
		return true;
	}

	/*
	public static void main(String[] args) {
		Trie hello = new Trie();
		hello.insertWord("welcome");
		boolean foo = hello.search("wel");
		System.out.println(foo);

	}
		*/
}
