/* Gavin Broussard
 * 3102 section 2
 * programming assignment #2
 * Kary heap
 */
import java.io.*;

public class Main {

	public static void main(String[] args) {
		
		
		long startTime = System.nanoTime();
		
		KaryHeap heap = new KaryHeap(50000,4);
		
		File file = new File("C:\\Users\\brous\\Desktop\\karyHeap-input02.txt"); 

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		  String st; 
		  try {
			while ((st = br.readLine()) != null)
			{
				int x = Integer.parseInt(st);
				if (x == 0)
				{
					System.out.println("Removed: " + heap.delete(0));
				}
				else
					heap.insert(x);
		    }
				
		} catch (IOException e) {
			e.printStackTrace();
		} 

		  long endTime = System.nanoTime();
		  
		  long duration = endTime - startTime;
		  
		  System.out.println("Micro Seconds: " + duration/1000);
		  
		  
		  
	}
}
