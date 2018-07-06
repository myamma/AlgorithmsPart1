import edu.princeton.cs.algs4.StdOut;
//import edu.princeton.cs.algs4.StdIn;
//import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	
	private int dequeSize = 0;
	private Node head;
	private Node tail;
	
	private class Node {
		Item item; 
		Node next; 
		Node prev;
		
		//constructor of Node inner class
		private Node(Item item) {
			this.item = item;
		}
	}
	
	// constructor of an empty deque
	public Deque() {                          
		head = new Node(null);
		tail = new Node(null);
		head.next = tail;
		head.prev = null;
		tail.prev = head;
		tail.next = null;
	}
	
	// is the deque empty?
	public boolean isEmpty() {return (dequeSize == 0);}
	
	// return the number of items on the deque
	public int size() {return dequeSize;}
	
	// add the item to the front
	public void addFirst(Item item) {         
		if (item == null) {throw new IllegalArgumentException("item cannot be null");}
		Node temp = new Node(item);
		temp.next = head.next;
		temp.prev = head;
		head.next.prev = temp;
		head.next = temp;
		dequeSize++;
	}
	
	// add the item to the end
	public void addLast(Item item) {          
		if (item == null) {throw new IllegalArgumentException("item cannot be null");}
		Node temp = new Node(item);
		temp.next = tail;
		temp.prev = tail.prev;
		tail.prev.next = temp;
		tail.prev = temp;
		dequeSize++;
	}
	
	// remove and return the item from the front
	public Item removeFirst() {               
		if (dequeSize == 0) {throw new NoSuchElementException("deque is empty");}
		Node tobeRemove;
		tobeRemove = head.next;
		tobeRemove.next.prev = head;
		head.next = tobeRemove.next;
		dequeSize--;
		
		return tobeRemove.item;
	}
	
	// remove and return the item from the end
	public Item removeLast() {  
		if (dequeSize == 0) {throw new NoSuchElementException("deque is empty");}
		Node tobeRemove;
		tobeRemove = tail.prev;
		tobeRemove.prev.next = tail;
		tail.prev = tobeRemove.prev;
		dequeSize--;
	
		return tobeRemove.item;
	}
	
	// return an iterator over items in order from front to end
	public Iterator<Item> iterator() {return new dequeIterator();}
	
	private class dequeIterator implements Iterator<Item> {
		private Node current = head;

		public boolean hasNext() {
			// TODO Auto-generated method stub
			return (current.next!=tail);
		}

		public Item next() {
			// TODO Auto-generated method stub
			if (!hasNext()) {throw new NoSuchElementException("deque is empty");}
			
			//swapping the next two lines of code will result in first sentinel node NULL
			//being printed while the last real element being ignored.
			current = current.next;
			Item item = current.item;
			return item;
		}
		
		public void remove() {
			throw new UnsupportedOperationException("remove not supported");
		}
		
	}
	
	// unit testing (optional)
	public static void main(String[] args) {  
		
		Deque<Integer> testDeque = new Deque<Integer>();
//		try{testDeque.removeFirst();} 
//		catch(NoSuchElementException e) {StdOut.println("First empty");}
//		try{testDeque.removeLast();} 
//		catch(NoSuchElementException e) {StdOut.println("Last empty");}
		StdOut.println("size: " + testDeque.dequeSize);
		testDeque.addFirst(8);
		testDeque.addFirst(9);
		testDeque.addLast(7);
		testDeque.addLast(6);
		testDeque.addLast(5);
		testDeque.removeFirst();
		testDeque.removeLast();
		StdOut.println("size: " + testDeque.dequeSize);
		
		for (Integer i:testDeque) {
			StdOut.println(i);
			
		}
		
		
	}
}