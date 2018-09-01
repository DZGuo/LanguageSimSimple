package Sim;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class Sim {
	static Trie t = new Trie();
	static HashMap hm = new HashMap();
	
	public static void simulate() {
		Scanner scan = null;
		String prevWord = ".";
		try {
			scan = new Scanner(new File("input.txt"));
		} catch(Exception e) {
			System.out.println("Read error");
		}
		if (scan == null) {
			System.out.println("file not found... exiting");
			System.exit(1);
		}
		while(scan.hasNext()) {
			String word = scan.next();
			String last = word.substring(word.length() - 1);
			if(last.matches("[^a-zA-Z0-9]")) {
				String alphaWord = word.substring(0, word.length() - 1);
				
				t.addTrie(alphaWord);
				if(hm.containsKey(prevWord)) {
					((ValueList) hm.get(prevWord)).addValue(alphaWord);
				} else {
					ValueList vlist = new ValueList();
					vlist.addValue(alphaWord);
					hm.put(prevWord, vlist);
				}
				prevWord = alphaWord;
				
				if(hm.containsKey(prevWord)) {
					((ValueList) hm.get(prevWord)).addValue(".");
				} else {
					ValueList vlist = new ValueList();
					vlist.addValue(".");
					hm.put(prevWord, vlist);
				}
				prevWord = last;
			} else {
				t.addTrie(word);
				if(hm.containsKey(prevWord)) {
					((ValueList) hm.get(prevWord)).addValue(word);
				} else {
					ValueList vlist = new ValueList();
					vlist.addValue(word);
					hm.put(prevWord, vlist);
				}
				prevWord = word;
			}
		}
		((ValueList) hm.get(prevWord)).addValue("$"); 
		((ValueList) hm.get(".")).addValue("$"); 	// add the possibility to end
		
		prevWord = ".";
		String curWord = ".";
		StringBuilder sb = new StringBuilder();
		while(curWord != "$") {
			if(curWord != ".") {
			sb.append(" ");
			}
			sb.append(curWord);
			prevWord = curWord;
			curWord = ((ValueList) hm.get(prevWord)).pickValuePercent();
			int count = 0;
			while(curWord == null || 
					((curWord == ".") && (prevWord == "."))) {
				curWord = ((ValueList) hm.get(prevWord)).pickValuePercent();
				++count;
				if(count > 100) {
					System.out.println("error... cannot continue");
					System.exit(1);
				}
			}
		}
		sb.delete(0, 2);
		System.out.println(sb);
	}
	
	public static void main(String args[]) {
		simulate();
	}
}
