/******************************************************************************
 *  Compilation:  javac Point.java
 *  Execution:    java Point
 *  Dependencies: none
 *  
 *  An immutable data type for points in the plane.
 *  For use on Coursera, Algorithms Part I programming assignment.
 *
 ******************************************************************************/

import java.util.Comparator;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;

//public class Point {


public class Point implements Comparable<Point> {

    private final double x;     // x-coordinate of this point
    private final double y;     // y-coordinate of this point

    /**
     * Initializes a new point.
     *
     * @param  x the <em>x</em>-coordinate of the point
     * @param  y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param  that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
        /* YOUR CODE HERE */
    	double slope = 0.0;
    	if (this.y == that.y) {
    		if (this.x == that.x) {slope = Double.NEGATIVE_INFINITY;}
    		else {slope = +0.0;}
    	} else if (this.x == that.x) {
    		slope = Double.POSITIVE_INFINITY;
    	} else {
    		slope = (that.y - this.y)/(that.x - this.x);
    	}
    	
    	return slope;
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param  that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     *         point (x0 = x1 and y0 = y1);
     *         a negative integer if this point is less than the argument
     *         point; and a positive integer if this point is greater than the
     *         argument point
     */
    public int compareTo(Point that) {
        /* YOUR CODE HERE */
    	int result;
    	if (this.x == that.x && this.y == that.y) {
    		result = 0;
    	} else if (this.y < that.y || (this.y == that.y && this.x < that.x)) {
    		result = -1;
    	} else {
    		result = 1;
    	}
    	
    	return result;
    }

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
        /* YOUR CODE HERE */
    	return new slopeComparator(this); 
    	/*what happens if 'this' is not passed down, how does inner class refer 
    	 * to instantiated outter class?
    	 */
    } 
    
    private class slopeComparator implements Comparator<Point> {
    	
    	private final Point point;
    	
    	slopeComparator (Point point) {
    		this.point = point; 
    		/*above is how inner class refer to an instance of outter class*/
    	}
    	
    	@Override
    	public int compare(Point p1, Point p2) {
    		
    		int result;
    		double slope_p0_p1 = point.slopeTo(p1);
    		double slope_p0_p2 = point.slopeTo(p2);
    		
    		if (slope_p0_p1 < slope_p0_p2) {result = -1;}
    		else if (slope_p0_p1 > slope_p0_p2) {result = 1;}
    		else {result = 0;}
    		
    		return result;
    	}
    }


    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    	Point a = new Point(1, 2);
    	Point b = new Point(2, 4);
    	Point c = new Point(2, 5);
    	Comparator<Point> slopeOrder = a.slopeOrder();
    	
//    	StdOut.println(b.compareTo(a));
//    	StdOut.println("slope ab is " + a.slopeTo(b));
//    	StdOut.println("slope ac is " + a.slopeTo(c));
    	
    	
//    	Point[] points = new Point[3];
//    	points[0] = a;
//    	points[1] = b;
//    	points[2] = c;
//    	
//		for (Point i : points) {
//			StdOut.println(i);
//		}
    }
}

