/* Gavin Broussard
 * 3102 section 2
 * programming assignment #2
 * AVLtree
 */
public class AvlTree {
	
Node root;
	
	Node successor(Node root, Node node)
	{
		if (node.right != null)
		{
			return minValue(node.right);
		}
		Node parent = node.parent;
		while(parent != null && node == parent.right)
		{
			node = parent;
			parent = parent.right;
		}
		return parent;
	}
	
	int size()
	{
		return size(root);
	}
	
	int size(Node node)
	{
		if(node == null)
			return 0;
		else 
			return(size(node.left) + 1 + size(node.right));
	}

     Node select(Node x, int k) {
        if (x == null) return null;
        int t = size(x.left);
        if (t > k) return select(x.left, k);
        else if (t < k) return select(x.right, k - t - 1);
        else return x;
    }
	
	void printParent(Node node, int val)
	{
		Node temp = node;
		while(node != null)
		{
			int nodeVal = node.key;
			if(val < nodeVal)
			{
				temp = node;
				node = node.left;
				
			}
			
			else if (val > nodeVal)
			{
				temp = node;
				node = node.right;
			}
			else 
			{
				System.out.println(temp.key);
				break;
			}
		}
		
	}
	
	Node getNode(Node node, int val)
	{
		Node temp = node;
		while(node != null)
		{
			int nodeVal = node.key;
			if(val < nodeVal)
				node = node.left;
			else if (val > nodeVal)
			{
				node = node.right;
			}
			else 
			{
				temp = node;
				break;
			}
		}
		return temp;
		
	}
	
	
	Boolean search(Node node, int val)
	{
		Boolean found = false;
		
		while(node != null && !found)
		{
			int nodeVal = node.key;
			if(val < nodeVal)
				node = node.left;
			else if (val > nodeVal)
			{
				node = node.right;
			}
			else 
			{
				found = true;
				break;
			}
		}
		return found;
	}
	
	void print(Node node)
	{
		System.out.println(node.key);
		
	}
	
		
	
	Node maxValue(Node node)
	{
		Node current = node;
		
		while (current.right != null)
		{
			current = current.right;
		}
		return current;
	}
	
	Node minValue(Node node)
	{
		Node current = node;
		
		while (current.left != null)
		{
			current = current.left;
		}
		return current;
	}
	int height(Node N)
	{
		if (N == null)
			return 0;
		return N.height;
	}
	
	int max(int a, int b)
	{
		return (a > b) ? a : b;
	}
	
	Node rightRotate(Node y)
	{
		Node x = y.left;
		Node T2 = x.right;
		
		x.right = y;
		y.left = T2;
		
		y.height = max(height(y.left), height(y.right)) + 1;
		x.height = max(height(x.left), height(x.right)) + 1;
		
		return x;
	}
	
	Node leftRotate(Node x) 
	{
		Node y = x.right;
		Node T2 = y.left;
		
		y.left = x;
		x.right = T2;
		
		x.height = max(height(x.left), height(x.right)) + 1;
		y.height = max(height(y.left), height(y.right)) + 1;
		
		return y;
				
	}
	
	int getBalance(Node N)
	{
		if(N == null)
			return 0;
		return height(N.left) - height(N.right);
	}
	
	Node insert(Node node, int key) { 
  	  
        if (node == null) 
            return (new Node(key)); 
  
        if (key < node.key) 
            node.left = insert(node.left, key); 
        else if (key > node.key) 
            node.right = insert(node.right, key); 
        else // Duplicate keys not allowed 
            return node; 
  
        node.height = 1 + max(height(node.left), 
                              height(node.right)); 
  
        int balance = getBalance(node); 
  

        // Left Left Case 
        if (balance > 1 && key < node.left.key) 
            return rightRotate(node); 
  
        // Right Right Case 
        if (balance < -1 && key > node.right.key) 
            return leftRotate(node); 
  
        // Left Right Case 
        if (balance > 1 && key > node.left.key) { 
            node.left = leftRotate(node.left); 
            return rightRotate(node); 
        } 
  
        // Right Left Case 
        if (balance < -1 && key < node.right.key) { 
            node.right = rightRotate(node.right); 
            return leftRotate(node); 
        } 
  
      
        return node; 
    } 
	
	void preOrder(Node node)
	{
		if (node != null)
		{
			System.out.print(node.key + " ");
			preOrder(node.left);
			preOrder(node.right);
		}
	}
	
	void inOrder(Node node)
	{
		if(node != null)
		{
			inOrder(node.left);
			System.out.print(node.key + " ");
			inOrder(node.right);
		}
	}
	void postOrder(Node node)
	{
		if (node != null)
		{
			postOrder(node.left);
			postOrder(node.right);
			System.out.print(node.key + " ");
		}
	}

}
