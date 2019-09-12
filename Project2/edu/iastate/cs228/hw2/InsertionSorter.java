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
 * This class implements insertion sort.
 *
 */

public class InsertionSorter extends AbstractSorter {
	// Other private instance variables if you need ...
	{
		algorithm = "insertion sort";
		outputFileName = "insert.txt";
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
	public InsertionSorter(Point[] pts) {
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
	public InsertionSorter(String inputFileName) throws InputMismatchException, FileNotFoundException {
		super(inputFileName);
	}

	/**
	 * Perform insertion sort on the array points[] of the parent class
	 * AbstractSorter.
	 * 
	 * @param order
	 *            1 by x-coordinate 2 by polar angle
	 */
	@Override
	public void sort(int order) {
		setComparator(order);

		long currentTime = System.nanoTime();

		for (int i = 1; i < points.length; i++)
			for (int j = i; j > 0; j--)
				if (pointComparator.compare(points[j], points[j - 1]) < 0)
					swap(j, j - 1);

		sortingTime = System.nanoTime() - currentTime;
	}
}
