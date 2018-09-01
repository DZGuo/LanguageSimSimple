package Sim;

public class Trie {
	TrieNode first = new TrieNode('^');
	
	public void addTrie(String str) {
		first.addString(str);
	}
	
	public boolean findString(String str) {
		return first.findString(str);
	}
}
