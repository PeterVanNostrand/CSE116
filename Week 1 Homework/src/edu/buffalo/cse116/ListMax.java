package edu.buffalo.cse116;

import java.util.ArrayList;

/**
 * Instances of this class are built around an ArrayList, but add a method which
 * returns the largest value currently in that List. This is not the most useful
 * of classes, but its simplicity is perfect for learning JUnit testing.
 *
 * @author Matthew Hertz
 */
public class ListMax {

	/** ArrayList instance whose largest value will be calculated. */
	private ArrayList<Double> theList;

	/**
	 * Create a new instance of this class and have it built around the
	 * specified list.
	 *
	 * @param list
	 *            List whose maximum element this instance will calculate.
	 */
	public ListMax(ArrayList<Double> list) {
		theList = list;
	}

	/**
	 * Find and return the largest element in the list passed to the
	 * constructor.
	 *
	 * @return Largest element currently stored in the list.
	 */
	public double getLargest() {
		double retVal;
		// The code is below is intentionally buggy -- students will need to fix
		// this.
		retVal = theList.get(0);
		for (int i = 1; i < theList.size(); i++) {
			Double currentElem = theList.get(i);
			if ((currentElem != null) && (retVal < currentElem)) {
				retVal = currentElem;
			}
		}
		return retVal;
	}
}