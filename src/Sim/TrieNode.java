package Sim;

import java.util.ArrayList;

public class TrieNode {
	char ch;
	ArrayList<TrieNode> next;
	
	TrieNode(char c) {
		ch = c;
		next = new ArrayList<TrieNode>();
	}
	
	private TrieNode findChar(char c) {
		if(next != null) {
			for(TrieNode node: next) {
				if(node.ch == c) {
					return node;
				}
			}
		}
		return null;
	}
	
	private TrieNode addChar(char c) {
		TrieNode check = findChar(c);
		if(check == null) {
			TrieNode newNode = new TrieNode(c);
			next.add(newNode);
			return newNode;
		}
		return check;
	}
	
	public void addString(String str) {
		TrieNode curNode = this;
		int len = str.length();
		
		if(len > 0) {
			for(int i = 0; i < len; ++i) {
				curNode = curNode.addChar(str.charAt(i));
			}
		}
		curNode.addChar('$');
	}
	
	public boolean findString(String str) {
		TrieNode curNode = this;
		
		if(str.length() == 0) {
			if(curNode.findChar('$') != null) {
				return true;
			} else {
				return false;
			}
		} else {
			TrieNode nextNode = curNode.findChar(str.charAt(0));
			if(nextNode == null) {
				return false;
			} else {
				return nextNode.findString(str.substring(1,  str.length()));
			}
		}
	}
}
