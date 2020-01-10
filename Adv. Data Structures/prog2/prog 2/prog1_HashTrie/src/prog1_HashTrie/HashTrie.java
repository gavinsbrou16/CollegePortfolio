package prog1_HashTrie;

import java.util.*;

class HashTrie {
	
	
	

	
	/** Function to add a string to hash trie **/
	public void add(String str) {
		Hashtable<Character, HashEntryNode> root = new Hashtable<>();


		for (int i = 0; i < str.length(); i++) {
			if (!root.containsKey(str.charAt(i)))
				root.put(str.charAt(i), null);
			else {
				root.put(str.charAt(i), new Hashtable<Character, HashEntryNode>());
				root = root.get(str.charAt(i));
			}
		}
		/** end of string **/
		root.put('\0', new Hashtable<Character, HashTable>(1000));
	}

	/** Function to check if hash trie contains a given word **/
	public boolean contains(String str) {
		HashMap<Character, HashMap> currentNode = root;
		for (int i = 0; i < str.length(); i++) {
			if (currentNode.containsKey(str.charAt(i)))
				currentNode = currentNode.get(str.charAt(i));
			else
				return false;
		}
		return currentNode.containsKey('\0') ? true : false;
	}

}
