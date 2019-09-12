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
 * This class implements the mergesort algorithm.
 *
 */

public class MergeSorter extends AbstractSorter {
	// Other private instance variables if you need ...
	{
		algorithm = "mergesort";
		outputFileName = "merge.txt";
	}

	/**
	 * The two constructors below invoke their corresponding superclass
	 * constructors. They also set the instance variables algorithm and
	 * outputFileName in the superclass.
	 */

	/**
	 * Constructor accepts an input array of points. in the array.
	 * 
	 * @param pts
	 *            input array of integers
	 */
	public MergeSorter(Point[] pts) {
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
	public MergeSorter(String inputFileName) throws InputMismatchException, FileNotFoundException {
		super(inputFileName);
	}

	/**
	 * Perform mergesort on the array points[] of the parent class AbstractSorter.
	 * 
	 * @param order
	 *            1 by x-coordinate 2 by polar angle
	 *
	 */
	@Override
	public void sort(int order) {
		setComparator(order);
		long currentTime = System.nanoTime();
		mergeSortRec(points);
		sortingTime = System.nanoTime() - currentTime;
	}

	/**
	 * This is a recursive method that carries out mergesort on an array pts[] of
	 * points. One way is to make copies of the two halves of pts[], recursively
	 * call mergeSort on them, and merge the two sorted subarrays into pts[].
	 * 
	 * @param pts
	 *            point array
	 */
	private void mergeSortRec(Point[] pts) {
		int a = 0, b = 0, c = 0;
		if (pts.length <= 1)
			return;

		Point[] pointArrL = new Point[pts.length / 2];
		Point[] PointArrR = new Point[pts.length - pointArrL.length];
		for (int i = 0; i < pointArrL.length; i++)
			pointArrL[i] = pts[i];
		for (int j = 0; j < PointArrR.length; j++)
			PointArrR[j] = pts[j + pointArrL.length];

		mergeSortRec(pointArrL);
		mergeSortRec(PointArrR);

		while (a < pointArrL.length && b < PointArrR.length) {
			if (pointComparator.compare(pointArrL[a], PointArrR[b]) < 0)
				pts[c++] = pointArrL[a++];
			else
				pts[c++] = PointArrR[b++];
		}
		while (a < pointArrL.length)
			pts[c++] = pointArrL[a++];
		while (b < PointArrR.length)
			pts[c++] = PointArrR[b++];
	}

	// Other private methods in case you need ...

}
