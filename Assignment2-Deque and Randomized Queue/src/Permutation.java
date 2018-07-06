import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class Permutation {
	public static void main(String[] args) {

		int numToPrint;
		RandomizedQueue<String> RQ = new RandomizedQueue<> ();
		
		while (!StdIn.isEmpty()) {
//			StdOut.println(StdIn.readString());
			RQ.enqueue(StdIn.readString());
		}
		
		numToPrint = Integer.parseInt(args[0]); 
//		StdOut.println(numToPrint);
		
		while (numToPrint > 0) {
			StdOut.println(RQ.dequeue());
			numToPrint--;
		}
	}
}
