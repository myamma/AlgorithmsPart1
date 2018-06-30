import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

//figure out the relation between grid and vec

public class Percolation {
	   
	private WeightedQuickUnionUF vec; //using n^2 by 1 vector to represent grid
//	private WeightedQuickUnionUF vec_backwash; // solving backwash exceeds memory requirement
	private int[][] grid;
	private int gridSize = 0;
	private int numOpenSites = 0;
	private int vecSize = 0;
	
	// create n-by-n grid, with all sites blocked
	public Percolation(int n) { 
		if (n<=0) { throw new IllegalArgumentException("grid size needs to be larger than 0");}
		gridSize = n;
		vecSize = (int) Math.pow(n,2) + 2;
		//extra two element for virtual top and bottom
		//n^2 is top, n^2 + 1 is bottom
		vec = new WeightedQuickUnionUF(vecSize); 
//		vec_backwash = new WeightedQuickUnionUF(vecSize);
		//StdOut.print(vec.connected(0, 1));
		grid = new int[n][n];
	}               

	
	private void checkRowColRange (int row, int col) {
		if (row < 1 || row > gridSize || col < 1 || col > gridSize) {
			throw new IllegalArgumentException();
		}
	}
	
	// open site (row, col) if it is not open already
	public void open(int row, int col) {
		checkRowColRange(row, col);
		
		if (grid[row - 1][col - 1] != 1) {
			grid[row - 1][col - 1] = 1;
			numOpenSites++;
		}
		
		if ((row - 1 > 0) && isOpen(row - 1, col)) {connectU(row, col);}
		if ((row + 1 <= gridSize) && isOpen(row + 1, col)) {connectD(row, col);}
		if ((col - 1 > 0) && isOpen(row, col - 1)) {connectL(row, col);}
		if ((col + 1 <= gridSize) && isOpen(row, col + 1)) {connectR(row, col);}
		if (row == gridSize) {connectBot(row, col);}
		if (row == 1) {connectTop(row, col);}

	}   
	
	private void connectU (int row, int col) {
		//connect to element above if not already connected
		if (!vec.connected((row-1)*gridSize + col - 1, (row-2)*gridSize + col - 1)) {
			vec.union((row-1)*gridSize + col - 1, (row-2)*gridSize + col - 1);
//			vec_backwash.union((row-1)*gridSize + col - 1, (row-2)*gridSize + col - 1);
		}
	}
	
	private void connectD (int row, int col) {
		//connect to element below if not already connected
		if (!vec.connected((row-1)*gridSize + col - 1, row*gridSize + col - 1)) {
			vec.union((row-1)*gridSize + col - 1, row*gridSize + col - 1);
//			vec_backwash.union((row-1)*gridSize + col - 1, row*gridSize + col - 1);
		}
	}
	
	private void connectL (int row, int col) {
		//connect to element to the left if not already connected
		if (!vec.connected((row-1)*gridSize + col - 1, (row-1)*gridSize + col - 2)) {
			vec.union((row-1)*gridSize + col - 1, (row-1)*gridSize + col - 2);
//			vec_backwash.union((row-1)*gridSize + col - 1, (row-1)*gridSize + col - 2);
		}
	}
	
	private void connectR (int row, int col) {
		//connect to element to the right if not already connected
		if (!vec.connected((row-1)*gridSize + col - 1, (row-1)*gridSize + col)) {
			vec.union((row-1)*gridSize + col - 1, (row-1)*gridSize + col);
//			vec_backwash.union((row-1)*gridSize + col - 1, (row-1)*gridSize + col);
		}
	}
	
	private void connectBot (int row, int col) {
		if ((!vec.connected((row-1)*gridSize + col - 1, gridSize*gridSize + 1))){
			vec.union((row-1)*gridSize + col - 1, gridSize*gridSize + 1);
		}
	}
	
	private void connectTop (int row, int col) {
		if ((!vec.connected((row-1)*gridSize + col - 1, gridSize*gridSize))){
			vec.union((row-1)*gridSize + col - 1, gridSize*gridSize);
//			vec_backwash.union((row-1)*gridSize + col - 1, gridSize*gridSize);
		}
	}
	
	// is site (row, col) open?
	public boolean isOpen(int row, int col) {
		checkRowColRange(row, col);
		return grid[row - 1][col - 1] == 1;
	}  

	// is site (row, col) full?
	public boolean isFull(int row, int col) {
		checkRowColRange(row, col);
		return vec.connected(((row - 1)*gridSize) + col - 1, gridSize*gridSize);
//		return (vec.connected(((row - 1)*gridSize) + col - 1, gridSize*gridSize)) &&
//				(vec_backwash.connected(((row - 1)*gridSize) + col - 1, gridSize*gridSize));
	}  

	public int numberOfOpenSites() // number of open sites
	{return numOpenSites;}      

	// does the system percolate?
	public boolean percolates() {
		return vec.connected(gridSize*gridSize, gridSize*gridSize + 1);
	}            

    private void print2D()
    {
        // Loop through all rows
        for (int i = 0; i < grid.length; i++) {
 
            // Loop through all elements of current row
            for (int j = 0; j < grid[i].length; j++) {
            	StdOut.print(grid[i][j] + " ");
            }
            StdOut.println();
        }
        	
    }
	
	// test client (optional)
	public static void main(String[] args) {
		int n = 6;
		Percolation game = new Percolation(n);
		
		//this code still has backwash problem (update: backwash should be adjusted now)
		//this would be so much easier if I can print out content of vec, try copying UF to a new file
		
		game.open(1,1); 
		game.open(2,1); 
		game.open(3,1); 
		game.open(4,1);
		game.open(4,1);
//		game.open(2,2); 
//		game.open(2,3);
//		game.open(3,3);
//		game.open(4,3);
//		game.print2D();
//
//		for(int i = 0; i<=n*n+1; i++) {
//			for(int j = 0; j<=n*n+1; j++) {
//				if(game.vec.connected(i, j) && (i!=j)) {
//					StdOut.printf("%d are connected to %d\n", i, j);
//				}
//			}
//		}
//		StdOut.println(game.percolates());
		StdOut.println(game.numberOfOpenSites());
		
		StdOut.println(game.isFull(6, 6) + " " + game.isOpen(6, 6));
		
	}

	   
}
