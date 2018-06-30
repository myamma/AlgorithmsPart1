import edu.princeton.cs.algs4.*;

public class Percolation1 {
	   
	int[][] grid;
	int numOpenSites = 0;
	
	// create n-by-n grid, with all sites blocked
	public Percolation1(int n) { 
		grid = new int[n][n];
	}               

	// open site (row, col) if it is not open already
	public void open(int row, int col) {
		grid[row - 1][col - 1] = 1;
		numOpenSites++;
	}   

	// is site (row, col) open?
	public boolean isOpen(int row, int col) {
		return grid[row - 1][col - 1] == 1;
	}  

	// is site (row, col) full?
	public boolean isFull(int row, int col) {
		if ((row == 1) && (isOpen(row, col) == true)) {
			return true;
		}
		
		isFull(row-1,col); //up
		isFull(row+1,col); //down
		isFull(row, col-1); //left
		isFull(row,col+1); //right
		
		return false;
	}  

	public int numberOfOpenSites() // number of open sites
	{return numOpenSites;}      

	// does the system percolate?
	public boolean percolates() {
		for(int i = 0; i <= grid[0].length - 1; i++) { 
			if (isFull(grid.length - 1,i)) {
				return true;
			}
		}
		return false;
	}            

	// test client (optional)
	public static void main(String[] args) {
		Percolation1 game = new Percolation1(5);
		game.open(1,1);
		game.open(2,1);
		game.open(3,1);
		game.open(4,1);
		game.open(5,1);
		
		
	}

	   
}
