package edu.buffalo.cse116;

import java.util.AbstractList;

/**
 * This defines a few basic methods in a singly linked-based List. This is
 * missing several methods needed for it to actually work (but will appear in a
 * second lecture *hint* *hint*), but has enough code for students to
 * demonstrate they understand how to traverse through a simple linked list.
 *
 * @author Matthew Hertz
 * @param <E>
 *            Type of data held in this collection
 */
public class SingleLinkedList<E> extends AbstractList<E> {

	/**
	 * Reference to the first node in our linked list. This will be null if the
	 * list is empty.
	 */
	private Entry head;

	/**
	 * Creates an empty list.
	 */
	public SingleLinkedList() {
		reset();
	}

	/**
	 * This method, which is only used within the SinglyLinkedList class,
	 * returns the instance to its initial state. This call will reset the head
	 * to be null, which also empties the linked list.
	 */
	private void reset() {
		head = null;
	}

	/**
	 * Returns the element at the specified index in this list.
	 *
	 * @param index
	 *            List location whose element should be returned.
	 * @return Element at the specified index in this list
	 */
	@Override
	public E get(int index) {
		Entry e = head;
		int cursor = 0;
		if(index<0 || index>=size()){
			throw new IndexOutOfBoundsException();
		}
		while(cursor < index){
			e = e.next;
			cursor++;
		}
		return e.element;
	}

	/**
	 * Returns the number of elements currently in this list.
	 *
	 * @return the number of elements in the list
	 */
	@Override
	public int size() {
		Entry e = head;
		int size = 0;
		while(e!=null){
			e = e.next;
			size++;
		}
		return size;
	}

	/**
	 * Class which defines the Entry instances ("nodes") in this
	 * single-linked-based list. Note that this class does not specify a generic
	 * type, because it MUST be identical to the element type of the main class.
	 *
	 * @author Matthew Hertz
	 */
	private class Entry {
		/** Element stored with the current entry. */
		private E element;

		/**
		 * Reference to the next entry in our linked list or null if this is the
		 * final link.
		 */
		private Entry next;

		/** Create a new, blank Entry. */
		public Entry() {
			element = null;
			next = null;
		}
	}
}
