package edu.buffalo.cse116;

import java.util.ArrayList;

public class NthSmallest {

	/**
	 * This method returns the n-th smallest item in the array list WITHOUT
	 * having to sort the data. Doing this involves a recursive process,
	 * however. Recursion is not required, but really strongly recommended.
	 *
	 * @param al
	 *            The ArrayList of unordered data from which we will select the
	 *            nth largest
	 * @param sizeRanking
	 *            Ranking of the item to be selected. When {@code sizeRanking}
	 *            is 0, the method needs to return the smallest item in
	 *            {@code al}; if {@code sizeRanking} is {@code al.size() - 1},
	 *            the largest item is returned.
	 * @return The {@code sizeRanking}-th smallest item from the array list.
	 */
	public static int findItem(ArrayList<Integer> al, int sizeRanking) {
		// You can just assume that sizeRanking is between 0 and al.size()-1
		// (inclusive)
		return findItem(al, 0, al.size() - 1, sizeRanking);
	}

	/**
	 * Recursively find the n-th smallest item. The method will do this by only
	 * considering the items whose index is between the values of left and
	 * right, inclusive. Each recursion halves the number of elements under
	 * consideration.
	 *
	 * @param al
	 *            ArrayList of unordered data to be examined
	 * @param left
	 *            First index of data which is in consideration
	 * @param right
	 *            Last index of data which is in consideration
	 * @param sizeRanking
	 *            Ranking of the item to be selected. When {@code sizeRanking}
	 *            is 0, the method needs to return the smallest item in
	 *            {@code al}; if {@code sizeRanking} is {@code al.size() - 1},
	 *            the largest item is returned.
	 * @return The {@code sizeRanking}-th smallest item from the array list.
	 */
	private static int findItem(ArrayList<Integer> al, int left, int right, int sizeRanking) {
		if (left == right) {
			return al.get(left);
		}
		int pivotIndex = split(al, left, right, (left+right)/2);
		if(sizeRanking == pivotIndex){
			return al.get(sizeRanking);
		}
		else if(sizeRanking < pivotIndex){
			return findItem(al, left, pivotIndex-1, sizeRanking);
		}
		else{
			return findItem(al, pivotIndex+1, right, sizeRanking);
		}
	}

	/**
	 * This is a helper method which splits the array list into left and right
	 * parts. The left part of the array list will contain all of the values
	 * smaller than the pivot. The right part of the array list will contain all
	 * of the values larger than the pivot.
	 *
	 * @param al
	 *            ArrayList of data which will be partitioned
	 * @param left
	 *            Leftmost index to be included in the partition
	 * @param right
	 *            Rightmost index to be included in the partition
	 * @param pivotIdx
	 *            Index whose element will be our pivot -- all data will be
	 *            partitioned based on whether it is larger or smaller than this
	 *            value.
	 * @return The index at which the element originally at pivotIdx ends up.
	 */
	private static int split(ArrayList<Integer> al, int left, int right, int pivotIdx) {
		int pivot = al.get(pivotIdx);
		al.set(pivotIdx, al.get(right));
		al.set(right, pivot);
		int storeIdx = left;
		for (int i = left; i < right; i++) {
			if (al.get(i) < pivot) {
				int temp = al.get(storeIdx);
				al.set(storeIdx, al.get(i));
				al.set(i, temp);
				storeIdx += 1;
			}
		}
		al.set(right, al.get(storeIdx));
		al.set(storeIdx, pivot);
		return storeIdx;
	}
}
