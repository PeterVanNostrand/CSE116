package edu.buffalo.cse116;

import java.util.AbstractSet;
import java.util.Iterator;

import javax.swing.plaf.synth.SynthSeparatorUI;

public class BinaryTree<E> extends AbstractSet<E> {

  protected Entry<E> root;

  protected int size;

  /**
   * Initializes this BinarySearchTree object to be empty, to contain only
   * elements of type E, to be ordered by the Comparable interface, and to
   * contain no duplicate elements.
   */
  public BinaryTree() {
    root = null;
    size = 0;
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

  // Specify where we would need to do the processing in each method to generate the ordering specified.
  // THESE METHODS ARE LISTED IN ALPHABETICAL ORDER AND MAY OR MAY NOT REFLECT THE REQUIRED LOCATION VALUE
  public void inOrderPrint() {
	  inOrderPrint(root);
  }
  
  private void inOrderPrint(Entry<E> ent){
	  if(ent!=null){
		  inOrderPrint(ent.getLeft());
		  System.out.print(ent.getElement());
		  inOrderPrint(ent.getRight());
	  }
  }

  public void postOrderPrint() {
    postOrderPrint(root);
  }
  
  private void postOrderPrint(Entry<E> ent){
	  if(ent!=null){
		  postOrderPrint(ent.getLeft());
		  postOrderPrint(ent.getRight());
		  System.out.print(ent.getElement());
	  }
  }

  public void preOrderPrint() {
    preOrderPrint(root);
  }
  
  private void preOrderPrint(Entry<E> ent){
	  if(ent!=null){
		  System.out.print(ent.getElement());
		  preOrderPrint(ent.getLeft());
		  preOrderPrint(ent.getRight());
	  }
  }

  /**
   * Iterator method will be implemented for a future
   *
   * @return an iterator positioned at the smallest element in this
   *         BinarySearchTree object.
   */
  @Override
  public Iterator<E> iterator() {
    throw new UnsupportedOperationException("Not implemented yet!");
  }


} // class BinarySearchTree
