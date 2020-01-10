package prog2;

import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * This is the Node class
 * @author mishawnlolis, LSU ID: 89-127-3262
 * @CoAuthor GavinBroussard, LSU ID: 89-589-0468
 *
 */
public class hNode {
	//Char and How Much of each
	char c;
	int fr;
	//Setting to null
	hNode left = null;
	hNode right = null;
	
	hNode (char c, int fr){
		this.c = c;
		this.fr = fr;
	}
/**
 * Constructor
 * @param c
 * @param fr
 * @param left
 * @param right
 */
	public hNode (char c, int fr, hNode left, hNode right) {
		this.right = right;
		this.left = left;
		this.fr = fr;
		this.c = c;
	}

}