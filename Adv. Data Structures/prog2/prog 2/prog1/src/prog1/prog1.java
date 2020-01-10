package prog1;

import java.io.PrintWriter;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


/**
 * @author mishawnlolis, mlolis1@lsu.edu, LSU ID: 89-127-3262
 * @CoAuthor GavinBroussard, LSU ID: 89-589-0468
 */

public class prog1 {
	public static void main(String[] args) throws FileNotFoundException {
		//inputScanner
		System.out.println("Importing Words List");
		File file = new File("/Users/Gavin/Desktop/word.txt");
		Scanner in = new Scanner (file);
		int count = 0;
		
		Node root = new Node(); 
		root.label = "";
		root.isWord = false;
		
		//Doing Something with scanner
		while (in.hasNext()) {
			String word = in.next();
			System.out.println(word);
			Trie.insert(root, word);
			
			System.out.println(count);
			count++;
		}
		
		System.out.println("Would you like to Search the trie tree? <Y/N>");
		Scanner input = new Scanner(System.in);
		String choice = input.nextLine();
		boolean done = false;
		
		while (!done) {
			if (choice.equalsIgnoreCase("Y")) {
				System.out.println("What is the prefix you'd like to search for?");
				String pref = input.nextLine();
				
				Trie.preOrder(root, pref);
				done = true;
			}
		}
		System.out.println("Thank you.");
		System.out.println("Remember to change Word list at end");
	
	}
			
}