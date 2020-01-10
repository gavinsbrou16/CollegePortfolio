/* Gavin Broussard
 * 3102 section 2
 * programming assignment #2
 * Kary heap
 */
import java.util.Arrays;
import java.util.NoSuchElementException;

public class KaryHeap {

	private int[] heap;
	private int size;
	private int k;
	
	public KaryHeap(int maxSize, int k)
	{
		this.size = 0;
		this.k = k;
		heap = new int[maxSize + 1];
		Arrays.fill(heap, -1);
	}
	
	public Boolean isEmpty()
	{
		return size == 0;
		
	}
	
	public Boolean isFull()
	{
		return size == heap.length;
	}
	
	public void clear() {
		
		size = 0;
	}
	
	private int parent(int pos)
	{
		return (pos-1)/k;
	}
	
	private int child(int pos, int d)
	{
		return k * pos + d;
	}
	
	public void insert(int x)
	{
		if(isFull())
		{
			
		}
		heap[size++] = x;
		heapUp(size - 1);
	}
	
	public int findMin()
	{
		if(isEmpty())
		{
			throw new NoSuchElementException("heap is empty!");
		}
		return heap[0];
	}
	
	public int delete(int pos)
	{
		if(isEmpty())
		{
			throw new NoSuchElementException("heap is empty!");
		}
		int key = heap[pos];
		heap[pos] = heap[size - 1];
		size--;
		heapDown(pos);
		return key;
		
	}
	
	private void heapUp(int childPos)
	{
		int temp = heap[childPos];
		while (childPos > 0 && temp < heap[parent(childPos)])
		{
			heap[childPos] = heap[parent(childPos)];
			childPos = parent(childPos);
		}
		heap[childPos] = temp;
	}
	
	private void heapDown(int pos)
	{
		int child;
		int temp = heap[pos];
		while(child(pos, 1) < size)
		{
			child = minChild(pos);
			if (heap[child] < temp)
				heap[pos] = heap[child];
			else
				break;
			pos = child;
		}
		heap[pos] = temp;
	}
	
	private int minChild(int pos)
	{
		int keyChild = child(pos, 1);
		int x = 2;
		int ind = child(pos, x);
		while (x <= k && ind < size)
		{
			if (heap[ind] < heap[keyChild])
				keyChild = ind;
			ind = child(pos, x++);
		}
		return keyChild;
	}
	public void print()
	{
		System.out.print("\nMinKary Heap : ");
		for (int i = 0; i < size; i++)
			System.out.print(heap[i] +" ");
		System.out.println();
	}

	
	
	
	
	
	
}
