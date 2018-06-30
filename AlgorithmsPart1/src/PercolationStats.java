import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


public class PercolationStats {
		
	private double[] results;
	private int row;
	private int col;
	private int num_trial = 0;
	
	public PercolationStats(int n, int trials) {   // perform trials independent experiments on an n-by-n grid
		if (n<=0 || trials<=0) {throw new IllegalArgumentException("");}
		results = new double[trials];
		num_trial = trials;
		for(int i = 0; i < trials; i++) {
			Percolation game = new Percolation(n);
			while (!game.percolates() ) {
				
				row = StdRandom.uniform(1, n+1);
				col = StdRandom.uniform(1, n+1);
				if (!game.isOpen(row, col)) {
					game.open(row, col); 					
					//StdOut.printf("%d %d\n",row, col);
				}
			}
			results[i] = (double)game.numberOfOpenSites()/(double)(n*n);
			//StdOut.printf("%d %f\n", game.numOpenSites, results[i]);
		}
		
	}

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