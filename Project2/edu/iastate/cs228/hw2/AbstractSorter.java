package edu.iastate.cs228.hw2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *  
 * @author Zhanghao Wen
 *
 */

import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 
 * This abstract class is extended by SelectionSort, InsertionSort, MergeSort,
 * and QuickSort. It stores the input (later on the sorted) sequence and records
 * the employed sorting algorithm, the comparison method, and the time spent on
 * sorting.
 *
 */

public abstract class AbstractSorter {

	protected Point[] points; // Array of points operated on by a sorting algorithm.
								// The number of points is given by points.length.

	protected String algorithm = ""; // "selection sort", "insertion sort",
										// "merge sort", or "quick sort". Initialized by a subclass
										// constructor.
	protected boolean sortByAngle; // true if last sort was done by polar angle and false
									// if by x-coordinate

	protected String outputFileName; // "select.txt", "insert.txt", "merge.txt", or "quick.txt"

	protected long sortingTime; // execution time in nanoseconds.

	protected Comparator<Point> pointComparator; // comparator which compares polar angle if
													// sortByAngle == true and x-coordinate if
													// sortByAngle == false

	private Point lowestPoint; // lowest point in the array, or in case of a tie, the
								// leftmost of the lowest points. This point is used
								// as the reference point for polar angle based comparison.

	// Add other protected or private instance variables you may need.

	/**
	 * This constructor accepts an array of points as input. Copy the points into
	 * the array points[]. Sets the instance variable lowestPoint.
	 * 
	 * @param pts
	 *            input array of points
	 * @throws IllegalArgumentException
	 *             if pts == null or pts.length == 0.
	 */
	protected AbstractSorter(Point[] pts) throws IllegalArgumentException {
		if (pts == null)
			throw new IllegalArgumentException("points[] is null");
		if (pts.length < 1)
			throw new IllegalArgumentException("points[] length is less than 1");
		points = new Point[pts.length];
		// lowestPoint = pts[0];
		lowestPoint = new Point(0, Integer.MAX_VALUE);
		for (int i = 0; i < pts.length; i++) {
			points[i] = new Point(pts[i]);
			if (points[i].getY() < lowestPoint.getY()
					|| (points[i].getY() == lowestPoint.getY() && points[i].getX() < lowestPoint.getX()))
				lowestPoint = points[i];
		}
	}

	/**
	 * This constructor reads points from a file. Sets the instance variables
	 * lowestPoint and outputFileName.
	 * 
	 * @param inputFileName
	 * @throws FileNotFoundException
	 * @throws InputMismatchException
	 *             when the input file contains an odd number of integers
	 */
	protected AbstractSorter(String inputFileName) throws FileNotFoundException, InputMismatchException {
		Scanner scan = new Scanner(new File(inputFileName));
		ArrayList<Point> list = new ArrayList<Point>();
		lowestPoint = new Point(0, Integer.MAX_VALUE);
		while (scan.hasNextInt()) {
			int x = scan.nextInt();
			if (!scan.hasNextInt()) {
				scan.close();
				throw new InputMismatchException("odd number of integers");
			}

			int y = scan.nextInt();
			Point p = new Point(x, y);
			list.add(p);
			if (y < lowestPoint.getY() || (y == lowestPoint.getY() && x < lowestPoint.getX()))
				lowestPoint = p;
		}
		points = list.toArray(new Point[0]);
		scan.close();
	}

	/**
	 * Sorts the elements in points[].
	 * 
	 * a) in the non-decreasing order of x-coordinate if order == 1 b) in the
	 * non-decreasing order of polar angle w.r.t. lowestPoint if order == 2
	 * (lowestPoint will be at index 0 after sorting)
	 * 
	 * Sets the instance variable sortByAngle based on the value of order. Calls the
	 * method setComparator() to set the variable pointComparator and use it in
	 * sorting. Records the sorting time (in nanoseconds) using the
	 * System.nanoTime() method. (Assign the time to the variable sortingTime.)
	 * 
	 * @param order
	 *            1 by x-coordinate 2 by polar angle w.r.t lowestPoint
	 *
	 * @throws IllegalArgumentException
	 *             if order is less than 1 or greater than 2
	 */
	public abstract void sort(int order) throws IllegalArgumentException;

	/**
	 * Outputs performance statistics in the format:
	 * 
	 * <sorting algorithm> <size> <time>
	 * 
	 * For instance,
	 * 
	 * selection sort 1000 9200867
	 * 
	 * Use the spacing in the sample run in Section 2 of the assignment description.
	 */
	public String stats() {
		return algorithm + "\t" + points.length + "\t" + sortingTime;
	}

	/**
	 * Write points[] to a string. When printed, the points will appear in order of
	 * increasing index with every point occupying a separate line. The x and y
	 * coordinates of the point are displayed on the same line with exactly one
	 * blank space in between.
	 */
	@Override
	public String toString() {
		String str = "";
		for (int i = 0; i < points.length; i++)
			str += points[i].getX() + " " + points[i].getY() + "\n";
		return str;
	}

	/**
	 * 
	 * This method, called after sorting, writes point data into a file by
	 * outputFileName. It will be used for Mathematica plotting to verify the
	 * sorting result. The data format depends on sortByAngle. It is detailed in
	 * Section 4.1 of the assignment description assn2.pdf.
	 * 
	 * @throws FileNotFoundException
	 */
	public void writePointsToFile() throws FileNotFoundException {
		if (sortByAngle == false) {
			PrintWriter pw = new PrintWriter(outputFileName);
			pw.write(toString());
			pw.close();
		} else {
			PrintWriter pw = new PrintWriter(outputFileName);
			String string = points[0].getX() + " " + points[0].getY() + "\n";
			for (int i = 1; i < points.length; i++)
				string += points[i - 1].getX() + " " + points[i - 1].getY() + " " + points[0].getX() + " "
						+ points[0].getY() + " " + points[i - 1].getX() + " " + points[i - 1].getY() + "\n";

			pw.write(string);
			pw.close();

		}

	}

	/**
	 * Generates a comparator on the fly that compares by polar angle if sortByAngle
	 * == true and by x-coordinate if sortByAngle == false. Set the protected
	 * variable pointComparator to it. Need to create an object of the
	 * PolarAngleComparator class and call the compareTo() method in the Point
	 * class, respectively for the two possible values of sortByAngle.
	 * 
	 * @param order
	 */
	protected void setComparator(int order) {
		sortByAngle = order == 2;
		pointComparator = order == 1 ? new Comparator<Point>() {
			@Override
			public int compare(Point p, Point other) {
				return p.compareTo(other);
			}
		} : new PolarAngleComparator(lowestPoint);
	}

	/**
	 * Swap the two elements indexed at i and j respectively in the array points[].
	 * 
	 * @param i
	 * @param j
	 */
	protected void swap(int i, int j) {
		Point tmp = points[i];
		points[i] = points[j];
		points[j] = tmp;
	}
}
