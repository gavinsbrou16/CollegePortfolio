package prog1;

/**
 * @author mishawnlolis, mlolis1@lsu.edu, LSU ID: 89-127-3262
 */


/**
 * Trie Tree Class
 * @author mishawnlolis
 * @CoAuthor GavinBroussard, LSU ID: 89-589-0468
 *
 */
public class Trie {
	
/**
 * Takes First Child and Character c
 * Search for correct Node by following Right sibling 
 * Searches till Node with C as its leading character
 * @return findChild
 */
	public static Node findChild(Node p, String lead) {
		
		Node q = p.firstChild;

		while (q != null) {
			if (q.leadChar == lead) {
				return q;
			}
			else {
				q = q.rightSibling;
			}
		}
		return null;
	}
	
	Node root;
	
/** 
 * This method inserts the word into the tree
 * @param p
 * @param s
 */
	public static void insert (Node p, String s) {
		
			int i = 0;
			
			while ((i<p.label.length()) && (i<s.length()) && (p.label.substring(i) == s.substring(i))) {
				i++;
			}	
				
			s = s.substring(i, s.length()); //Possibly Empty
			
			if ((s.length() == 0) && (p.label.length() == i)) {
				p.isWord = true;
			}
			else if (i == p.label.length()) {
				Node q = findChild(p,s.substring(0));
				if (q == null) {
					//System.out.println("test----------------------");
					Node r = new Node();
					r.label = s;
					r.leadChar = s.substring(0);
					r.rightSibling = p.firstChild;
					r.isWord = true;
					
					p.firstChild = r;
				}
				else {
					insert(q,s);
				}
			}
			else {
				String s1 = p.label.substring(i,p.label.length());
				String s2 = p.label.substring(0,i-1);
				
				Node q1 = new Node();
				q1.label = s1;
				q1.leadChar = s1.substring(0);
				
				Node q2 = new Node();
				q2.label = s2;
				q2.leadChar = s2.substring(0);
				
				q1.firstChild=p.firstChild;
				q1.isWord = p.isWord;
				
				if (s.length() != 0) {
					p.isWord = false;
				}
				else {
					p.isWord = true;
				}
				
				p.firstChild = q2;
				p.label = s2;
				q2.rightSibling = q1;
			}

	}

/**
 * Find node which contains prefic, then do this call this.
 * This method Prints the Prefix
 * @param p
 * @param prefix
 */
	public static void preOrder (Node p, String prefix) {
		//Making sure P != null
		if (p == null) {
			return;
		}
		
		//Concatenation
		prefix = p.label;
		
		//If it is word, print it
		if (p.isWord) {
			System.out.println(prefix);	
		}
		
		Node q = p.firstChild;
		
		//Recursive call 
		while (q!=null) {
			preOrder(q,prefix);
			q = q.rightSibling;
			
		}
	}
}

