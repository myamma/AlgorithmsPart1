import edu.princeton.cs.algs4.*;
import java.util.Random;
import java.util.ArrayList;

public class PercolationStats {
		
	double[] results;
	int row;
	int col;
	int num_trial = 0;
	int[] blocked;
	int nextR; int nextC;
	ArrayList<Integer> A;
	int ele;
	
	public PercolationStats(int n, int trials) {   // perform trials independent experiments on an n-by-n grid
		if (n<=0 || trials<=0) {throw new IllegalArgumentException("");}
		results = new double[trials];
		num_trial = trials;
		for(int i = 0; i < trials; i++) {
			Percolation game = new Percolation(n);
//			A = new ArrayList<Integer>(n*n);
//			for(int k =0; k<n*n; k++) {
//				A.set(k, (Integer) k);
//			}
			while (!game.percolates() ) {
//				ele = A.get(StdRandom.uniform(0, n*n - 1));
//				A.remove(ele);
//				row = ele/n + 1;
//				col = ele - (row-1)*n + 1;
				
				row = StdRandom.uniform(1, n+1);
				col = StdRandom.uniform(1, n+1);
				if (!game.isOpen(row, col)) {
					game.open(row, col); 
					
					//StdOut.printf("%d %d\n",row, col);
				}
//				nextOpen(game);
//				game.open(row, col); StdOut.printf("%d %d\n",row, col);
				
			}
			results[i] = (double)game.numOpenSites/(double)(n*n);
			//StdOut.printf("%d %f\n", game.numOpenSites, results[i]);
		}
		
	}
	
//	public void nextOpen(Percolation game) {
//		blocked 
//		
//	}

	public double mean() {                         // sample mean of percolation threshold
		return StdStats.mean(results);
	}
	
	public double stddev() {                       // sample standard deviation of percolation threshold
		return StdStats.stddev(results);
	}

	public double confidenceLo() {                // low  endpoint of 95% confidence interval
		return mean() - ((1.96 * stddev()) / Math.sqrt(num_trial));
	}

	public double confidenceHi() {                 // high endpoint of 95% confidence interval
		return mean() + ((1.96 * stddev()) / Math.sqrt(num_trial));
	}

	public static void main(String[] args) {       // test client (described below)
		PercolationStats game = new PercolationStats(4, 5);
		StdOut.println(game.mean());
	}
}