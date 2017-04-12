package edu.buffalo.cse116;

/**
 * Class which implements a proper {@code Queue} (including using the CORRECT method names) using a set of singly linked
 * nodes. Besides in the naming convention, this does not include the methods that are inappropriate for a Queue to
 * have,
 *
 * @author Matthew Hertz
 * @param <E> Type of data stored within this Queue.
 */
public class ProperQueue<E> {
  /**
   * The first node in the linked list (or null if the list is empty). This is the end of the list where we remove
   * elements from.
   */
  private Entry<E> head;

  /**
   * The first node in the linked list (or null if the list is empty). This is the end of the list where we add elements
   * to include them on the queue.
   */
  private Entry<E> tail;

  /**
   * The number of elements currently in the queue.
   */
  private int size;

  /** Create a new (empty) instance of this class. */
  public ProperQueue() {
    head = null;
    tail = null;
    size = 0;
  }

  /**
   * Adds an item to the end of the queue. Traditionally, this is the only
   * method available to add data to a Queue.
   *
   * @param item Element to be added to the end of the queue.
   * @return Element added to the Queue (e.g., {@code item}).
   */
  public E enqueue(E item) {
	  Entry<E> e = new Entry<E>();
	  e.setElement(item);
	  if(head==null){
		  head = e;
	  }
	  else{
		  tail.setNext(e);
	  }
	  tail = e;
	  size++;
	  return item;
  }

  /**
   * Removes and returns the element at the front of this queue. Traditionally,
   * this is the only method available to remove data from the Queue.
   *
   * @return Element that was removed from the front of the Queue.
   * @throws EmptyQueueException Thrown when the Queue does not have any
   *           elements to remove.
   */
  public E dequeue() {
	  if(head!=null){
		  Object o = head.getElement();
		  if(head.getNext()!=null){
			  head = head.getNext();
		  }
		  else{
			  head = null;
			  tail =null;
		  }
		  size--;
		  return (E) o;
	  }
	  throw new EmptyQueueException();
  }

  /**
   * Like {@link #dequeue()}, this returns the element at the front of the
   * queue, but unlike {@link #dequeue} this method DOES NOT remove it from the
   * queue.
   *
   * @return Element that is at the front of the queue.
   * @throws EmptyQueueException Thrown when the Queuedoes not have any elements
   *           to remove.
   */
  public E peek() {
	  if(head!=null){
		  return head.getElement();
	  }
	  throw new EmptyQueueException();
  }

  /**
   * Returns the number of elements in this Queue.
   *
   * @return Items available in the Queue.
   */
  public int size() {
    return size;
  }

  /**
   * Returns whether there are any elements in this Queue.
   *
   * @return True if the Queue does not have any elements; false otherwise.
   */
  public boolean isEmpty() {
    return size == 0;
  }
}
