package edu.buffalo.cse116;

import java.util.Collection;
import java.util.Iterator;

/**
 * Class which implements the concept of a multiset -- an unorganized collection
 * which does not limited the number of times an instance can be added.
 *
 * @author Carl Alphonce
 * @author Matthew Hertz
 * @param <E>
 *            Type of data contained with the instance.
 */
public class ArrayMultiSet<E> implements Collection<E> {
	/** Array in which the elements in this multiset are stored. */
	private E[] _store;

	/**
	 * Array indices below this amount contain the active elements in this
	 * collection.
	 */
	private int _size;
	/**
	 * Modification counter used to preserve the fail-fast nature of its
	 * iterators.
	 */
	private long _modCount;

	/*
	 * Class invariants: _store has all of the elements in the multi-set. All
	 * elements in _store are in indices < _size
	 */

	/**
	 * Constant value used as the initial size for the backing store when no
	 * other size is provided.
	 */
	private static final int DEFAULT_INITIAL_CAPACITY = 12;

	/**
	 * Create a new empty multiset using the default initial capacity.
	 */
	public ArrayMultiSet() {
		reset(DEFAULT_INITIAL_CAPACITY);
	}

	/**
	 * This method, which is only used within the ArrayMultiSet code, returns
	 * the instance to its initial state. This call should allocate a new
	 * backing store with the specified capacity and update any instance
	 * variables needed to reflect that the ArrayMultiSet does not contain any
	 * elements.
	 *
	 * @param newCapacity
	 *            When allocating a new (empty) backing store, this specifies
	 *            the space it should reserve for later
	 */
	@SuppressWarnings("unchecked")
	private void reset(int newCapacity) {
		// Skipped on behalf of Monday's homework
	}

	/**
	 * Create a new empty multiset and, while the size of the multiset will
	 * still be 0, allocate a backing store large enough that it COULD hold up
	 * to the specified capacity without resizing.
	 *
	 * @param initialCapacity
	 *            Amount of space to reserve for future additions to the
	 *            multiset.
	 */
	public ArrayMultiSet(int initialCapacity) {
		reset(DEFAULT_INITIAL_CAPACITY);
	}

	/**
	 * Remove all of the elements within the instance and invalidate any current
	 * iterators.
	 */
	@Override
	public void clear() {
		reset(DEFAULT_INITIAL_CAPACITY);
	}

	@Override
	public int size() {
		return _size;
	}

	@Override
	public boolean isEmpty() {
		return _size == 0;
	}

	/**
	 * Return true if at least one element in the multiset is equal to the given
	 * object. When {@code obj} is null, it must use the {@code ==} operator to
	 * perform these checks, but when {@code obj} is not null, the
	 * {@link Object#equals} method is used.
	 *
	 * @param obj
	 *            Object (or null) for which we will search
	 * @return {@code true} if {@code obj} was found; {@code false} if a match
	 *         could not be found.
	 */

	public boolean contains(Object obj) {
		int indexFound = findFirstIndex(obj);
		return indexFound != -1;
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
	 * Removes the element found at the given index. This is done while
	 * maintaining the class invariant, by first replacing the element being
	 * removed with the element at the largest index in the MultiSet. Once this
	 * is done, we can assign null to the element that has been moved and then
	 * update any instance variables to insure class invariant remain
	 * true.<br />
	 * <b>Precondition (e.g., this method SHOULD ASSUME this is true) </b>:
	 * {@code i} is a valid index within {@code _store} AND {@code _size} is not
	 * 0.
	 *
	 * @param indexFound
	 *            Index of the element to remove from the MultiSet.
	 * @return True is always returned since this method is called only when the
	 *         removal is successful.
	 */
	private boolean removeAtIndex(int indexFound) {
			if(indexFound > -1 && indexFound<_size){
				_store[indexFound] = _store[_size - 1];
				_store[_size - 1] = null;
				_size--;
				return true;
			}
		return false;
	}

	/**
	 * Returns the first backing store index where {@code obj} is found in the
	 * ArrayMultiSet. If {@code obj} is not an element in the ArrayMultiSet, -1
	 * is returned.
	 *
	 * @param obj
	 *            Object (or null) for which we return the first index at which
	 *            it is found in a valid location in _store
	 * @return Index in _store at which the item is found or -1 if it is not in
	 *         the ArrayMultiSet.
	 */
	private int findFirstIndex(Object obj) {
		if(obj == null){
			for (int i = 0; i < _size; i++) {
				if (_store[i] == null) {
					return i;
				}
			}
		}
		for (int i = 0; i < _size; i++) {
			if (_store[i].equals(obj)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public boolean add(E e) {
		// Skipped on behalf of Wednesday's homework.
		throw new UnsupportedOperationException();
	}

	@Override
	public Object[] toArray() {
		// Skipped on behalf of Monday's homework.
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// Skipped on behalf of Monday's homework.
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterator<E> iterator() {
		// To be discussed next week
		throw new UnsupportedOperationException();
	}

	/*
	 * The remaining methods are part of the Collection<E> interface, but are
	 * beyond what is necessary for CSE 116. Students who want a complete
	 * Multiset implementation should investigate Google's "Guava" library.
	 */
	@Override
	public boolean containsAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}
}