package edu.buffalo.cse116;

import java.util.AbstractList;

/**
 * This defines the basic functionality of an array-based List. Students will
 * complete several methods in this class to show they understand how data can
 * be added and removed from an array-based list.
 *
 * @author Matthew Hertz
 * @param <E>
 *            Type of data held in this collection
 */
public class ArrayList<E> extends AbstractList<E> {

	/** Default size of the array created when the class is instantiated. */
	private final static int DEFAULT_INITIAL_CAPACITY = 12;

	/**
	 * First index in the array at which elements cannot be found. Only indices
	 * LESS THAN this value are valid.
	 */
	private int _size;

	/** Array in which the elements are stored. */
	private E[] _store;

	/**
	 * Creates an empty list using the default capacity.
	 */
	public ArrayList() {
		reset(DEFAULT_INITIAL_CAPACITY);
	}

	/**
	 * Creates an empty list using the specified capacity.
	 *
	 * @param initialCapacity
	 *            the integer value of the size of the array list
	 */
	public ArrayList(int initialCapacity) {
		reset(initialCapacity);
	}

	/**
	 * This method, which is only used within the ArrayList class, returns the
	 * instance to its initial state. This call should allocate a new backing
	 * store with the specified capacity and update any instance variables
	 * needed to reflect that the ArrayMultiSet does not contain any elements.
	 *
	 * @param newCapacity
	 *            When allocating a new (empty) backing store, this specifies
	 *            the space it should reserve for later
	 */
	@SuppressWarnings("unchecked")
	private void reset(int newCapacity) {
		_size = 0;
		_store = (E[]) new Object[newCapacity];
	}

	/**
	 * Replaces the element at the specified index in this list.
	 *
	 * @param index
	 *            List location whose element should be replaced.
	 * @param elem
	 *            Element to be stored at the given index
	 * @return Element previously stored at the specified index in this list
	 */
	@Override
	public E set(int index, E elem) {
		if (index < _size - 1) {
			Object o = _store[index];
			_store[index] = elem;
			return (E) o;
		}
		throw new IndexOutOfBoundsException();
	}

	/**
	 * Removes the element found at the given index. This is done while
	 * maintaining the class invariant, by shifting elements in the list to
	 * eliminate any "hole" that this removal could create. Once this is done,
	 * we can assign null to the location that had been at the end of the
	 * ArrayList and update any instance variables to insure class invariant
	 * remain true.<br />
	 * <b>Precondition (e.g., this method SHOULD ASSUME this is true) </b>:
	 * {@code i} is a valid index within {@code _store} AND {@code _size} is not
	 * 0.
	 *
	 * @param index
	 *            Index of the element to remove from the ArrayList.
	 * @return True is always returned since this method is called only when the
	 *         removal is successful.
	 */
	private boolean removeAtIndex(int index) {
		if (index < _size - 1) {
			for (int i = index; i < _size - 1; i++) {
				_store[i] = _store[i + 1];
			}
		}
		_store[_size - 1] = null;
		_size--;
		return true;
	}

	/**
	 * Removes the element in the list at the given index, if that index is a
	 * valid one for the ArrayList. The method returns the element that this
	 * method has removed from the ArrayList.Since the order of elements in a
	 * List IS guaranteed, we need to make changes relative to the use in an
	 * ArrayMultiSet.
	 *
	 * @param index
	 *            Index of the element to be removed from the List.
	 * @return Element removed from the List.
	 */
	@Override
	public E remove(int index) {
		E retVal = get(index);
		removeAtIndex(index);
		return retVal;
	}

	/**
	 * Removes a single instance of the given object, if one can be found in the
	 * ArrayMultiSet. The method returns {@code true} if a match was found (and
	 * removed) and {@code false} if no match was found. Since the order of
	 * elements in a MultiSet is not guaranteed, we use this to simplify how we
	 * remove an item.
	 *
	 * @param obj
	 *            Object (or null) which we want to remove
	 * @return {@code true} if {@code obj} was found and an instance removed;
	 *         {@code false} if a match could not be found.
	 */
	@Override
	public boolean remove(Object obj) {
		int indexFound = findFirstIndex(obj);
		if (indexFound != -1) {
			return removeAtIndex(indexFound);
		}
		return false;
	}

	/**
	 * Returns the first backing store index where {@code obj} is found in the
	 * ArrayMultiSet. If {@code obj} is not an element in the ArrayList, -1 is
	 * returned.
	 *
	 * @param obj
	 *            Object (or null) for which we return the first index at which
	 *            it is found in a valid location in _store
	 * @return Index in _store at which the item is found or -1 if it is not in
	 *         the ArrayList.
	 */
	private int findFirstIndex(Object obj) {
		// Only scan through _size, since those are the only indices in _store
		// at which elements are found
		for (int i = 0; i < _size; i++) {
			// Need to use the == operator to match when we are searching for
			// null;
			// once we know obj is not null, we need to use the .equals() method
			// to be certain we match objects
			if ((obj == _store[i]) || ((obj != null) && obj.equals(_store[i]))) {
				return i;
			}
			// No else clause possible, since the match could be at a higher
			// index!
		}
		// Checked all VALID indices, so we now know nothing matches. In this
		// case, we return -1
		return -1;
	}

	/**
	 * Update the list so that it includes all of the elements from before the
	 * call AND the given element.
	 *
	 * @param e
	 *            Item to be added to this collection.
	 */
	@Override
	public void add(int index, E e) {
		if (storeIsFull()) {
			_store = growBackingStore();
		}
		addElement(index, e);
	}

	/**
	 * Checks if the backing store has an element in every available entry.
	 *
	 * @return True when a new, larger, backing store is needed before an
	 *         element can be added. False if we could add an element currently.
	 */
	private boolean storeIsFull() {
		boolean retVal = _size == _store.length;
		return retVal;
	}

	/**
	 * Allocates a new, larger, array and copies over all of the existing
	 * elements into it. This returns the larger array so that it can be used as
	 * the backing store.
	 *
	 * @return Array with all of the ArrayList's elements AND additional space
	 *         in which elements could be added.
	 */
	@SuppressWarnings("unchecked")
	private E[] growBackingStore() {
		// For maximal efficiency (and best practice), we double the size of the
		// array each time it grows.
		E[] retVal = (E[]) new Object[_store.length * 2];
		// Copy all of the elements into the new array
		for (int i = 0; i < _store.length; i++) {
			retVal[i] = _store[i];
		}
		// An easier, more efficient way (but less useful for teaching) to
		// allocate and copy the elements coding is:
		// _store = Arrays.copyOf(_store, _store.length * 2);
		return retVal;
	}

	/**
	 * After shifting elements to make space in the array, this adds the element
	 * to the backing store and updates the instance variables to maintain the
	 * class invariant. Returns if this addition was successful (this should
	 * always return true).
	 *
	 * @param index
	 *            Index at which the new element should be added.
	 * @param elem
	 *            Element to be added to the backing store
	 * @return True since this will always successfully adds the element.
	 */
	private boolean addElement(int index, E elem) {
		// Shift any elements to make space for the new one.
		for (int i = _size; i > index; i--) {
			_store[i] = _store[i - 1];
		}
		// Add the element to the store
		_store[_size] = elem;
		// Finally, we can increase _size, since this change will no longer
		// violate any class invariants.
		_size += 1;
		return true;
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
		if (index >= _size) {
			throw new IndexOutOfBoundsException();
		}
		return _store[index];
	}

	/**
	 * Returns true if this list is empty and false otherwise.
	 *
	 * @return true if the list is empty, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return _size == 0;
	}

	/**
	 * Returns the number of elements currently in this list.
	 *
	 * @return the number of elements in the list
	 */
	@Override
	public int size() {
		return _size;
	}
}
