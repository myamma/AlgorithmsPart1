import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
	
	private LineSegment[] lineSegs;
	private LineSegment[] lineSegsFinal;
	private Point[] twoEnds = new Point[2];
	private Point a, b, c, d;
	private int n;
	
	// finds all line segments containing 4 points
	public BruteCollinearPoints(Point[] points) {
		
		checkValidity(points);
		lineSegs = new LineSegment[points.length - 3];
		for (int i1 = 0; i1 < points.length - 3; i1++) {
			a = points[i1];
			for (int i2 = i1 + 1; i2 < points.length - 2; i2++) {
				b = points[i2];
				for (int i3 = i2 + 1; i3 < points.length - 1; i3++) {
					c = points[i3];
					for (int i4 = i3 + 1; i4 < points.length; i4++) {
						d = points[i4];
						if ((a.slopeTo(b) == b.slopeTo(c)) && (b.slopeTo(c) == c.slopeTo(d))) {
							twoEnds = findEnds(a,b,c,d);
//							StdOut.println();
//							StdOut.println("a-b: " + a.slopeTo(b) + " b-c: " + b.slopeTo(c) + " c-d: " + c.slopeTo(d));
//							StdOut.println("a: " + a + " ,b: " + b + " ,c: " + c + " ,d: " + d);
//							StdOut.println(twoEnds[0] + " " + twoEnds[1]);
							lineSegs[n] = new LineSegment(twoEnds[0],twoEnds[1]);
//							StdOut.println(lineSegs[n]);
							n++;
						}
					}
				}
			}
		}
		lineSegsFinal = resize(lineSegs, n);
	}
	
	private void checkValidity(Point[] points) {
		if (points == null) {throw new IllegalArgumentException("points array can't be null");}
//		if (points.length < 4) {throw new IllegalArgumentException("array size cannot be less than 4");}
		
		for (Point p : points) {
            if (p == null) {
                throw new java.lang.IllegalArgumentException("contain null points");
            }
}
		
		for (int i = 0; i < points.length; i++) {
//			StdOut.println(points[i]);
//			if(points[i] == null) {throw new IllegalArgumentException("can't have null points in array");}
			for (int j = 0; j < points.length; j++) {
//				StdOut.println(points[i] + " " + points[j] + " " + points[i].compareTo(points[j]));
				if (points[i].compareTo(points[j]) == 0 && (i!=j)) {
					throw new java.lang.IllegalArgumentException("duplicate points");
			
				}
			}
		}
	}
	
	private LineSegment[] resize(LineSegment[] prev, int n) {
		LineSegment[] after = new LineSegment[n];
		for (int i = 0; i < n; i++) {
			after[i] = prev[i];
		}
		return after;
	}
	
	private Point[] findEnds(Point a, Point b, Point c, Point d) {
		Point[] twoEnds = new Point[2];
		Point min = a;
		Point max = a;
		
		if (min.compareTo(b) == 1) {min = b;}
		if (min.compareTo(c) == 1) {min = c;}
		if (min.compareTo(d) == 1) {min = d;}
		
		if (max.compareTo(b) == -1) {max = b;}
		if (max.compareTo(c) == -1) {max = c;}
		if (max.compareTo(d) == -1) {max = d;}
		
		twoEnds[0] = min;
		twoEnds[1] = max;
		
		return twoEnds;
	}
	
	// the number of line segments
	public int numberOfSegments() {return lineSegsFinal.length;}
	
	// the line segments
	public LineSegment[] segments() {return lineSegsFinal;}
	
	public static void main(String[] args) {
		
		Point[] points = new Point[2];
		points[0] = new Point(23979, 1849);
		points[1] = new Point(29990, 25441);
		

		
		BruteCollinearPoints collinear = new BruteCollinearPoints(points);
		
	}
}