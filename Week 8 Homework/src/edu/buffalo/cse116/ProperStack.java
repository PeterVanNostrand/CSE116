package edu.buffalo.cse116;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

/**
 * Class which implements a proper {@code Stack} -- e.g., unlike Java this class eliminates methods that are not
 * appropriate for a Stack. Because performance is also important, this implementation uses an ArrayList as its backing
 * store.
 *
 * @author Matthew Hertz
 * @param <E> Type of data stored within this Stack.
 */
public class ProperStack<E> {
  /** The backing store in which all the elements are stored. */
  private List<E> store;

  /** Create a new (empty) instance of this class. */
  public ProperStack() {
    store = new ArrayList<>();
  }

  /**
   * Pushes an item onto the top of this stack. Traditionally, this is the only
   * method available to add data to a Stack.
   *
   * @param item Element to be added to the top of the stack.
   * @return Element added to the Stack (e.g., {@code item}).
   */
  public E push(E item) {
	  store.add(item);
	  return item;
  }

  /**
   * Removes and returns the element at the top of this stack. Traditionally,
   * this is the only method available to remove data from the Stack.
   *
   * @return Element that was removed from the top of the stack.
   * @throws EmptyStackException Thrown when the Stack does not have any
   *           elements to remove.
   */
  public E pop() {
	  if(store.size()>0){
		 return store.remove(store.size()-1); 
	  }
	  else{
		  throw new EmptyStackException();
	  }
  }

  /**
   * Like {@link #pop()}, this returns the element at the top of the stack, but
   * unlike {@link #pop} this method DOES NOT remove it from the stack.
   *
   * @return Element that is at the top of the stack.
   * @throws EmptyStackException Thrown when the Stack does not have any
   *           elements to remove.
   */
  public E peek() {
	 if(store.size()>0){
		 return store.get(store.size()-1);
	 }
	 else{
		 throw new EmptyStackException();
	 }
  }

  /**
   * Returns the number of elements in this Stack.
   *
   * @return Items available in the Stack.
   */
  public int size() {
    return store.size();
  }

  /**
   * Returns whether there are any elements in this Stack.
   *
   * @return True if the Stack does not have any elements; false otherwise.
   */
  public boolean isEmpty() {
    return store.isEmpty();
  }

  /**
   * Removes all the elements from the Stack.
   */
  public void clear() {
    store.clear();
  }
}
