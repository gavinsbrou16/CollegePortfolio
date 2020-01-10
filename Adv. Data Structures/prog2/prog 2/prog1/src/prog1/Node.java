package prog1;

/**
 * Class - Node
 * @author mishawnlolis
 * @CoAuthor GavinBroussard, LSU ID: 89-589-0468
 */

public class Node {
	
	Node[] children = new Node[26];
	
	String leadChar; //If Node is "Stock", leadChar is S 
	String label; //What Node stored
	Boolean isWord; //Indicates whether current Node is whole word or not
	
	Node rightSibling; //Sibling
	Node firstChild; //Child
}
