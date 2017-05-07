package edu.buffalo.cse116;

import java.util.Comparator;

/**
 * This class defines a single public method which performs one be used to sorts
 * an array using insertion-sort.
 *
 * @author Matthew Hertz
 * @param <E>
 *            Type of data which instances of this class will be sorting.
 */
public class InsertionSort<E> {

	/** Comparator instance we will be using to sort our data. */
	private Comparator<E> comp;

	/**
	 * Creates a new instance of this class which will be used to sort members
	 * of an array when there are only a few items to sort.
	 *
	 * @param theComp
	 *            Comparator instance we will be using for our sorting.
	 */
	public InsertionSort(Comparator<E> theComp) {
		comp = theComp;
	}

	/**
	 * Begins to order the smallest {@code insertionSteps} values in the array
	 * using insertion sort. While it is unusual to terminate midway through the
	 * sorting process, this is useful when an instructor needs to write unit
	 * tests which verify students correctly implemented insertion sort (and is
	 * nearly the same as writing the entire method!).
	 *
	 * @param arrayToSort
	 *            Array whose contents are to be sorted.
	 * @param insertionSteps
	 *            Specifies how many passes through the array we should take
	 *            before stopping the sort.
	 */
	public void insertionSort(E[] arrayToSort, int insertionSteps) {
		// arrayToSort[0] to arrayToSort[i-1] is sorted region; arrayToSort[i] to arrayToSort[arrayToSort.length-1] is unsorted
		for (int i = 1; i < insertionSteps; i++) {
			// Move element from arrayToSort[i] into proper place in the sorted region
			for (int k = i; k > 0 && (comp.compare(arrayToSort[k - 1], arrayToSort[k])>0); k--) {
				// Swap arrayToSort[k][k-1] and arrayToSort[k][k]
				E temp = arrayToSort[k];
				arrayToSort[k] = arrayToSort[k - 1];
				arrayToSort[k - 1] = temp;
			}
		}
	}
}
