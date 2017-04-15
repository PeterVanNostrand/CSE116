package edu.buffalo.cse116;

import java.util.AbstractCollection;
import java.util.Iterator;

/**
 * Instances of this class represent a vanilla binary tree (e.g., and not a binary search tree).
 *
 * @author Matthew Hertz
 * @param <E> Type of data stored in each node of this tree. Since this is not a BST, E can be anything.
 */
public class BinaryTree<E> extends AbstractCollection<E> {

  /** Root node of this binary tree */
  private Entry<E> root;

  /** Number of nodes/elements within this binary tree. */
  private int size;

  /**
   * Initializes this BinarySearchTree object to be empty, to contain only
   * elements of type E, to be ordered by the Comparable interface, and to
   * contain no duplicate elements.
   */
  public BinaryTree() {
    root = null;
    size = 0;
  }

  public String visit(TreeVisitor<E> visitor) {
	  return root.apply(visitor, "");
  }

  /**
   * Returns the size of this BinarySearchTree object.
   *
   * @return the size of this BinarySearchTree object.
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * Iterator method that has, at last, been implemented.
   *
   * @return an iterator positioned at the smallest element in this
   *         BinarySearchTree object.
   */
  @Override
  public Iterator<E> iterator() {
    // Skipped until next week
    throw new UnsupportedOperationException();
  }

  /**
   * Determines if there is at least one element in this binary tree object that equals a specified element.
   *
   * @param obj - the element sought in this binary tree
   * @return true if the object is an element in the binary tree; false othrwise.
   */
  @Override
  public boolean contains(Object obj) {
    return getEntry(obj) != null;
  }


  /**
   * Finds the BTNode object that houses a specified element, if there is such an
   * BTNode.
   *
   * @param obj - the element whose BTNode is sought.
   * @return the BTNode object that houses obj - if there is such an BTNode;
   *         otherwise, return null.
   */
  protected Entry<E> getEntry(Object obj) {
    return getEntry(root, obj);
  }

  /**
   * Finds the BTNode object that houses a specified element in the subtree rooted at subtreeRoot, if there is such an
   * BTNode.
   *
   * @param obj - the element whose BTNode is sought.
   * @return the BTNode object that houses obj - if there is such an BTNode in the subtree at subtreeRoot;
   *         otherwise, return null.
   */
  protected Entry<E> getEntry(Entry<E> subtreeRoot, Object obj) {
    if (subtreeRoot == null) {
      return null;
    } else if ((subtreeRoot.getElement() == obj) ||
        ((subtreeRoot.getElement() != null) && subtreeRoot.getElement().equals(obj))) {
      return subtreeRoot;
    }
    if (subtreeRoot.getLeft() != null) {
      Entry<E> retVal = getEntry(subtreeRoot.getLeft(), obj);
      if (retVal != null) {
        return retVal;
      }
    }
    if (subtreeRoot.getRight() != null) {
      Entry<E> retVal = getEntry(subtreeRoot.getRight(), obj);
      if (retVal != null) {
        return retVal;
      }
    }
    return null;
  }

  /**
   * Finds the successor of a specified Entry object in this BinarySearchTree.
   * The worstTime(n) is O(n) and averageTime(n) is constant.
   *
   * @param e - the Entry object whose successor is to be found.
   * @return the successor of e, if e has a successor; otherwise, return null.
   */
  protected Entry<E> successor(Entry<E> e) {
    if (e == null) {
      return null;
    } else if (e.getRight() != null) {
      // successor is leftmost Entry in right subtree of e
      Entry<E> p = e.getRight();
      while (p.getLeft() != null) {
        p = p.getLeft();
      }
      return p;

    } // e has a right child
    else {

      // go up the tree to the left as far as possible, then go up
      // to the right.
      Entry<E> p = e.getParent();
      Entry<E> ch = e;
      while ((p != null) && (ch == p.getRight())) {
        ch = p;
        p = p.getParent();
      } // while
      return p;
    } // e has no right child
  } // method successor
}
