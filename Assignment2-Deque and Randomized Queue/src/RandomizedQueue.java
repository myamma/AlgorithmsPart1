import edu.princeton.cs.algs4.StdOut;
//import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
	
	private Item[] RQ;
	private int idx; //next unfilled position in queue
	
	// construct an empty randomized queue
	public RandomizedQueue() {
		RQ = (Item[]) new Object[1];
	}
	
	// is the randomized queue empty?
	public boolean isEmpty() {return idx == 0;}                 
	
	// return the number of items on the randomized queue
	public int size() {return idx;}
	
	// add the item
	public void enqueue(Item item) {
		if (item == null) {throw new IllegalArgumentException("item cannot be null");}
		if (idx == RQ.length - 1) {resize(true);}
		RQ[idx] = item;
		idx++;
	}
	
	// remove and return a random item
	public Item dequeue() {
		if (idx == 0) {throw new NoSuchElementException("queue is empty");}
		int n = StdRandom.uniform(idx); //n ranges from 0 to idx - 1, idx is the next unfilled position
		Item temp = RQ[n];
		RQ[n] = RQ[idx - 1];
		RQ[idx - 1] = null;
		idx--;
		if (idx >= 0 && idx <= RQ.length/4) {resize(false);}
		return temp;
	}
	
	private void resize(boolean scaleUp) {
		Item[] temp;
		
		if (scaleUp) {
			temp = (Item[]) new Object[RQ.length * 2];
		} else {
			temp = (Item[]) new Object[RQ.length / 2];
		}
		
		for (int i = 0; i < idx; i++) {
			temp[i] = RQ[i];
		}
		RQ = temp;
	}
	
	 // return a random item (but do not remove it)
	public Item sample() {
		if (idx == 0) {throw new NoSuchElementException("queue is empty");}
		int n = StdRandom.uniform(idx); //n ranges from 0 to idx - 1, idx is the next unfilled position
		return RQ[n];
	}
	
	// return an independent iterator over items in random order
	public Iterator<Item> iterator() {return new RQIterator();}
	
	private class RQIterator implements Iterator<Item> {
		
		Item[] temp = (Item[]) new Object[idx]; //change RQ.length to idx to avoid printing NULL
		int n = 0;
		public RQIterator() {
			for (int i = 0; i < idx; i++) { //change RQ.length to idx to avoid printing NULL
				temp[i] = RQ[i];
			}
			StdRandom.shuffle(temp);
		}
		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return n < idx;
		}

		@Override
		public Item next() {
			// TODO Auto-generated method stub
			if (!hasNext()) {throw new NoSuchElementException("queue is empty");}
			n++;
			return temp[n - 1];
		}
		
		public void remove() {
			throw new UnsupportedOperationException("remove not supported");
		}

	}
	
	// unit testing (optional)
	public static void main(String[] args) {
		RandomizedQueue<Integer> testRQ = new RandomizedQueue<>();
//		StdOut.println("size is  " + testRQ.size() + " next idx is " + testRQ.idx);
		testRQ.enqueue(1);
//		StdOut.println("size is  " + testRQ.size() + " next idx is " + testRQ.idx);
		testRQ.enqueue(2);
//		StdOut.println("size is  " + testRQ.size() + " next idx is " + testRQ.idx);
		testRQ.enqueue(3);
//		StdOut.println("size is  " + testRQ.size() + " next idx is " + testRQ.idx);
		testRQ.enqueue(4);
//		StdOut.println("size is  " + testRQ.size() + " next idx is " + testRQ.idx);
		testRQ.enqueue(5);
//		StdOut.println("size is  " + testRQ.size() + " next idx is " + testRQ.idx);
		StdOut.println("deque " + testRQ.dequeue());
		StdOut.println("deque " + testRQ.dequeue());

		for (Integer i:testRQ) {
			StdOut.println(i);
		}
		
		testRQ.enqueue(null);
		
	}
}