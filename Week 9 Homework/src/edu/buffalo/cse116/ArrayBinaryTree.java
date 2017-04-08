package edu.buffalo.cse116;

import java.util.AbstractSet;
import java.util.Iterator;

/**
 * Implementation of an abstract set using an array-based binary tree. This is used to help teach binary trees and will
 * have more details explained in future lectures.
 *
 * @author William J. Collins
 * @author Matthew Hertz
 * @param <E> Data type (which must be Comparable) of the elements in this tree.
 */
public class ArrayBinaryTree<E extends Comparable<E>> extends AbstractSet<E> {
  /** Index where the root node can be found. */
  private static final int ROOT = 0;

  /** Array used to store the elements in the binary tree. */
  private E[] store;

  /** Number of elements within the tree. */
  private int size;

  /**
   * Initializes this ArrayBinaryTree object to be empty. This creates the array in which items will be stored.
   */
  @SuppressWarnings("unchecked")
  public ArrayBinaryTree() {
    store = (E[]) new Comparable[31];
    size = 0;
  }

  /**
   * Checks if the binary tree contains an element at the given index. This requires checking both that the array is
   * large enough (to avoid triggering an exception) AND (when the array is large enough) that the array has a non-null
   * value at that index.
   *
   * @param idx Index to be checked out.
   * @return True if there is an element at the given index; false otherwise.
   */
  private boolean nodeExists(int idx) {
	  return idx<size && store[idx]!=null;
  }

  /**
   * Given an index, returns the element in that node's left child. If the left child node does not exist, null should
   * be returned. It is important that this NOT trigger an index out of bounds exception.
   *
   * @param idx Index of the node for which we want the left child.
   * @return Value of the node's left child or null if no left child exists.
   */
  private E leftChild(int idx) {
	  int childIdx = 2*idx +1;
	  if(nodeExists(childIdx)){
		  return store[childIdx];
	  }
	  else{
		  throw new IndexOutOfBoundsException();
	  }
  }

  /**
   * Given an index, returns the value of that node's parent. If the node is the root (and so has no parent), null
   * should be returned. It is important that this NOT trigger an index out of bounds exception.
   *
   * @param idx Index of the node for which we want the parent.
   * @return Value of the node's parent or null if no parent exists.
   */
  private E parent(int idx) {
	  int parentIdx = (idx-1)/2;
	  if(nodeExists(parentIdx)){
		  return store[parentIdx];
	  }
	  else{
		  throw new IndexOutOfBoundsException();
	  }
  }

  /**
   * Returns the size of this ArrayBinaryTree object.
   *
   * @return the size of this ArrayBinaryTree object.
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * Returns an iterator that will return the elements in this ArrayBinaryTree, but without any specific ordering.
   *
   * @return Iterator positioned at the smallest element in this ArrayBinaryTree object.
   */
  @Override
  public Iterator<E> iterator() {
    // Skipped for now.
    throw new UnsupportedOperationException();
  }

  /**
   * Determines if there is at least one element in this ArrayBinaryTree object that equals a specified element.
   *
   * @param obj - the element sought in this ArrayBinaryTree object.
   * @return true - if there is an element in this ArrayBinaryTree object that equals obj; otherwise, return false.
   * @throws ClassCastException - if obj cannot be compared to the elements in this ArrayBinaryTree object.
   * @throws NullPointerException - if obj is null.
   */
  @Override
  public boolean contains(Object obj) {
    int index = getIndex(obj);
    return index != -1;
  }

  /**
   * Finds the index at which a specified element exist or -1 if the object is not in the tree.
   *
   * @param obj Element whose Entry is sought.
   * @return Element in the treeEntry object in the ArrayBinaryTree that holds obj; if no Entry exists, returns null.
   */
  protected int getIndex(Object obj) {
    // Handle the special case of obj being null -- we can return null since we
    // KNOW this not to be in the tree. By definition, we are to throw this exception.
    if (obj == null) {
      throw new NullPointerException();
    }
    // Keep searching while there might be a node at the current index within the tree
    for (int idx = ROOT; idx < store.length; idx++ ) {
      // If we have fallen off the bottom of the tree, obj cannot be found
      // within it.
      if (store[idx] == null) {
        return -1;
      }
      E curElement = store[idx];
      @SuppressWarnings("unchecked")
      int comp = curElement.compareTo((E) obj);
      if (comp == 0) {
        return idx;
      }
    }
    // We made it through the tree and did not find the element; return that it was not found.
    return -1;
  }
}
