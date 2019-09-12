package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;

/**
 *  
 * @author Zhanghao Wen
 *
 */

/**
 * 
 * This class implements selection sort.
 *
 */

public class SelectionSorter extends AbstractSorter {
	// Other private instance variables if you need ...
	{
		algorithm = "selection sort";
		outputFileName = "select.txt";
	}

	/**
	 * The two constructors below invoke their corresponding superclass
	 * constructors. They also set the instance variables algorithm and
	 * outputFileName in the superclass.
	 */

	/**
	 * Constructor takes an array of points.
	 * 
	 * @param pts
	 */
	public SelectionSorter(Point[] pts) {
		super(pts);
	}

	/**
	 * Constructor reads points from a file.
	 * 
	 * @param inputFileName
	 *            name of the input file
	 * @throws FileNotFoundException
	 * @throws InputMismatchException
	 */
	public SelectionSorter(String inputFileName) throws InputMismatchException, FileNotFoundException {
		super(inputFileName);
	}

	/**
	 * Apply selection sort on the array points[] of the parent class
	 * AbstractSorter.
	 *
	 * @param order
	 *            1 by x-coordinate 2 by polar angle
	 *
	 */
	@Override
	public void sort(int order) {
		setComparator(order);
		long currentTime = System.nanoTime();
		for (int i = 0; i < points.length; i++) {
			int min = i;
			for (int j = i + 1; j < points.length; j++)
				if (pointComparator.compare(points[j], points[min]) < 0)
					min = j;
			swap(i, min);
		}
		sortingTime = System.nanoTime() - currentTime;
	}
}
