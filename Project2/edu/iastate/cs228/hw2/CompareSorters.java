package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class CompareSorters {
	/**
	 * Repeatedly take integer sequences either randomly generated or read from
	 * files. Perform the four sorting algorithms over each sequence of integers,
	 * comparing points by x-coordinate or by polar angle with respect to the lowest
	 * point.
	 * 
	 * @param args
	 * @throws FileNotFoundException
	 **/
	public static void main(String[] args) throws FileNotFoundException {
		// Conducts multiple sorting rounds. In each round, performs the following:
		//
		// a) If asked to sort random points, calls generateRandomPoints() to initialize
		// an array
		// of random points.
		// b) Reassigns to elements in the array sorters[] (declared below) the
		// references to the
		// four newly created objects of SelectionSort, InsertionSort, MergeSort and
		// QuickSort.
		// c) Based on the input point order, carries out the four sorting algorithms in
		// a for
		// loop that iterates over the array sorters[], to sort the randomly generated
		// points
		// or points from an input file.
		// d) Meanwhile, prints out the table of runtime statistics.
		//
		// A sample scenario is given in Section 2 of the assignment description.
		//
		System.out.println("Comparison of Four Sorting Algorithms\n");
		System.out.println("keys:  1 (random integers)  2 (file input)  3 (exit)");
		System.out.println("order: 1 (by x-coordinate)  2 (by polar angle)\n");
		Scanner scan = new Scanner(System.in);
		int num, order; // number of random points, order used in sorting
		AbstractSorter[] sorters = new AbstractSorter[4];

		// Within a sorting round, every sorter object write its output to the file
		// "select.txt", "insert.txt", "merge.txt", or "quick.txt" if it is an object of
		// SelectionSort, InsertionSort, MergeSort, or QuickSort, respectively.
		int times = 1;
		out: while (true) {
			System.out.print("Trial " + times + ": ");
			switch (scan.nextInt()) {
			case 1:
				System.out.print("Enter number of random points: ");
				num = scan.nextInt();
				System.out.print("Order used in sorting: ");
				order = scan.nextInt();
				System.out.println("\nalgorithm\tsize\ttime (ns)");
				System.out.println("----------------------------------\n");
				Point[] pts = generateRandomPoints(num, new Random());
				sorters = new AbstractSorter[] { new SelectionSorter(pts), new InsertionSorter(pts),
						new MergeSorter(pts), new QuickSorter(pts) };
				for (AbstractSorter sorter : sorters) {
					sorter.sort(order);
					sorter.writePointsToFile();
					System.out.println(sorter.stats());
				}
				System.out.println("----------------------------------\n");
				times++;
				break;
			case 2:
				System.out.println("Points from a file");
				System.out.print("File name: ");
				String fileName = scan.next();
				System.out.print("Order used in sorting: ");
				order = scan.nextInt();
				System.out.println("\nalgorithm\tsize\ttime (ns)");
				System.out.println("----------------------------------\n");
				sorters = new AbstractSorter[] { new SelectionSorter(fileName), new InsertionSorter(fileName),
						new MergeSorter(fileName), new QuickSorter(fileName) };
				for (AbstractSorter sorter : sorters) {
					sorter.sort(order);
					sorter.writePointsToFile();
					System.out.println(sorter.stats());
				}
				System.out.println("----------------------------------\n");
				times++;
				break;
			case 3:
			default:
				break out;
			}

		}
		scan.close();
		System.exit(0);
	}

	/**
	 * This method generates a given number of random points to initialize
	 * randomPoints[]. The coordinates of these points are pseudo-random numbers
	 * within the range [-50,50] × [-50,50]. Please refer to Section 3 of assignment
	 * description document on how such points can be generated.
	 * 
	 * Ought to be private. Made public for testing.
	 * 
	 * @param numPts
	 *            number of points
	 * @param rand
	 *            Random object to allow seeding of the random number generator
	 * @throws IllegalArgumentException
	 *             if numPts < 1
	 */
	public static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException {
		Point[] pts = new Point[numPts];
		for (int i = 0; i < numPts; i++)
			pts[i] = new Point(rand.nextInt(101) - 50, rand.nextInt(101) - 50);
		return pts;
	}
}
