
public class Node {

	int key, height; // each Node will have a key and height int variable
    Node left, right, parent, min, max; // each Node also points to two left and right children nodes
    // bellow is just the constructor  
    Node(int d) { 
        key = d; 
        height = 1; // each node will have a height of at least 1
        left = right = parent = min = max = null;
    } 
}
