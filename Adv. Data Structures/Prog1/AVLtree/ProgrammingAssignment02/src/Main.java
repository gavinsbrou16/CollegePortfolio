/* Gavin Broussard
 * 3102 section 2
 * programming assignment #2
 * AVLtree
 */
public class Main {

	public static void main(String[] args) {
		
		long startTime = System.nanoTime();

		
		AvlTree tree = new AvlTree();
		
			//input
			tree.root = tree.insert(tree.root, 200);
			tree.root = tree.insert(tree.root, 100);
			tree.root = tree.insert(tree.root, 150);
			tree.root = tree.insert(tree.root, 120);
			tree.root = tree.insert(tree.root, 523);
			tree.root = tree.insert(tree.root, 400);
			tree.root = tree.insert(tree.root, 350);
			tree.root = tree.insert(tree.root, 944);
			tree.root = tree.insert(tree.root, 778);
			
			//we then want to find the successor of 400
			
			tree.print(tree.successor(tree.root, tree.getNode(tree.root, 400)));
			
			//we then want to print the parent of 523
			
			tree.printParent(tree.root, 523);
			
			tree.root = tree.insert(tree.root, 767);
			tree.root = tree.insert(tree.root, 677);
			tree.root = tree.insert(tree.root, 546);
			tree.root = tree.insert(tree.root, 321);
			
			// find the min
			//find the max
			
			System.out.println(tree.minValue(tree.root).key);
			System.out.println(tree.maxValue(tree.root).key);
			
			
			tree.root = tree.insert(tree.root, 429);
			tree.root = tree.insert(tree.root, 352);
			tree.root = tree.insert(tree.root, 295);
			tree.root = tree.insert(tree.root, 991);
			tree.root = tree.insert(tree.root, 833);
			tree.root = tree.insert(tree.root, 747);
			tree.root = tree.insert(tree.root, 568);
			tree.root = tree.insert(tree.root, 428);
			tree.root = tree.insert(tree.root, 42);
			tree.root = tree.insert(tree.root, 689);
			tree.root = tree.insert(tree.root, 520);
			tree.root = tree.insert(tree.root, 567);
			tree.root = tree.insert(tree.root, 639);
			tree.root = tree.insert(tree.root, 935);
			tree.root = tree.insert(tree.root, 873);
			tree.root = tree.insert(tree.root, 700);
			tree.root = tree.insert(tree.root, 690);
			tree.root = tree.insert(tree.root, 943);
			tree.root = tree.insert(tree.root, 779);
			tree.root = tree.insert(tree.root, 766);
			tree.root = tree.insert(tree.root, 678);
			tree.root = tree.insert(tree.root, 542);
			
			//find successor of 520
			
			System.out.println(tree.successor(tree.root, tree.getNode(tree.root, 520)));

			//print parent of 700
			
			tree.printParent(tree.root, 523);
			
			//find min
			//find max
			
			System.out.println(tree.minValue(tree.root).key);
			System.out.println(tree.maxValue(tree.root).key);
			
			//traverse inOrder
			
			System.out.println("InOrder traversal" + 
					" of constructed tree is : ");
			tree.inOrder(tree.root);
			
			//select 10
			System.out.println();
			System.out.println(tree.select(tree.root, 10).key);
			System.out.println(tree.select(tree.root, 11).key);

			//select 11
			//rank of 100
			//rank of 991
		
			
			  long endTime = System.nanoTime();
			  
			  long duration = endTime - startTime;
			  
			  System.out.println("Micro Seconds: " + duration/1000);
			
			System.out.println();
			
			
				
	}

}
