package prog2;

import java.util.Map;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * This is the Tree class
 * @author mishawnlolis, LSU ID: 89-127-3262
 * @CoAuthor GavinBroussard, LSU ID: 89-589-0468
 *
 */
public class hTrie {
	

/** Build Tree method, It builds the tree
 * It also prints everything
 * @param text
 * @throws FileNotFoundException 
 */
	public static void buildTree (String text) throws FileNotFoundException {
		//Builds Hash map		
		Map<Character, Integer> hMap = new HashMap<>();
		
		//Adds content to map
		for(int i = 0; i < text.length(); i++) {
			if (!hMap.containsKey(text.charAt(i))) {
				hMap.put(text.charAt(i), 0);
			}
			hMap.put(text.charAt(i), hMap.get(text.charAt(i))+1);
		}
		
		//Builds Priority Queue
		PriorityQueue<hNode> queue = new PriorityQueue<>((l,r) -> l.fr - r.fr);
		
		//Makes node for every item
		for (Map.Entry<Character, Integer> entry : hMap.entrySet()) {
			queue.add(new hNode(entry.getKey(), entry.getValue()));
		}
		
		//Loop that repeats until there's more than one node
		while (queue.size() != 1) {
			//Removes Highest Priority
			hNode left = queue.poll();
			hNode right = queue.poll();
//________________________________________________________________________________________________	--Frequencies Maybe go here. Maybe			
			//Makes new Node, Freq equals sum of two nodes
			int sum = left.fr + right.fr;
			//Adds Node to queue
			queue.add(new hNode ('\0', sum, left, right));
		}
		
		//Stores root
		hNode root = queue.peek();
		
		//Stores tree and map
		Map<Character, String> hCode = new HashMap<>();
		encodeIt(root, "", hCode);
//________________________________________________________________________________________________	
		//Gets Output File
		PrintWriter output = new PrintWriter("out.txt"); 
		
		//Prints Code
		output.println("Huffman Code");
		for (Map.Entry<Character, String> entry : hCode.entrySet()) {
			output.println(entry.getKey() + " -- " + entry.getValue());
		}
			output.println("----------------------------------");
		//prints char and freq
			output.println("Frequencies");
			printFreq(root,output);	
			output.println("----------------------------------");
		
		
		
		//Prints Original String
			output.println("Original Code was: \n" + text);
		
		//Prints Encoded String
		StringBuilder theString = new StringBuilder();
		for (int i = 0; i < text.length(); i++) {
			theString.append(hCode.get(text.charAt(i)));
		}
		
		output.println("----------------------------------");
		
		output.println("Encoded String:  \n" + theString);
		
		output.println("----------------------------------");
		
		//Prints Decoded String
		int index = -1;
		output.println("Decoded String:");
		while (index < theString.length() - 2) {
			index = decodeIt(root, index, theString, output);
		}
		output.println("\n----------------------------------");
		
		output.close();
	}

/**
 * Decode Method, Puts the code back into string
 * @param root
 * @param index
 * @param sb
 * @return
 */
	public static int decodeIt (hNode root, int index, StringBuilder sb, PrintWriter output) {
		//Null, Finished with method
		if (root == null) {
			return index;
		}
		//Found, Returns index
		if (root.right == null && root.left == null) {
			output.print(root.c);
			return index;
		}
		index++; //Increases Index
		
		//If 0, go left (Recurrsive)
		if (sb.charAt(index) == '0') {
			index = decodeIt(root.left, index, sb, output);
		}
		//Else, go right
		else {
			index = decodeIt(root.right, index, sb, output);
		}
		return index;
	}
		
/**
 * Encode Method, Puts the string into code
 * @param root
 * @param text
 * @param huffmanCode
 */
	public static void encodeIt (hNode root, String text, Map <Character, String> huffmanCode) {
		//Null, Done with method
		if (root == null) {
			return; 
		}
		//Found, put root and text
		if ((root.left == null) && (root.right == null)){
			huffmanCode.put(root.c, text);
		}
		//Making Code, Recurssive 
		encodeIt(root.left, text + "0", huffmanCode);
		encodeIt(root.right, text + "1", huffmanCode);
	}
	/*
	 * method to print Frequencies 
	 * @param output
	 * @param root
	 */
	public static void printFreq (hNode root, PrintWriter output)
	{
		if (root == null)
			output.println("Root is null");
		if (root.left == null && root.right == null)
			output.println(root.c + " -- " + root.fr + " times ");
		if (root.left != null)
			printFreq(root.left,output);
		if (root.right != null)
			printFreq(root.right,output);

		
				
	}
 
}