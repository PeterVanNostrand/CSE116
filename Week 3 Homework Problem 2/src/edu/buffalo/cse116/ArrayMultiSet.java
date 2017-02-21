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

	/**
	 * Update the multiset so that it includes all of the elements from before
	 * the call AND the given element.
	 *
	 * @param e
	 *            Item to be added to this collection.
	 * @return True when the element was successfully added; false otherwise.
	 *         This always returns true.
	 */
	@Override
	public boolean add(E e) {
		if (storeIsFull()) {
			_store = growBackingStore();
		}
		boolean retVal = addElement(e);
		return retVal;
	}

	/**
	 * Checks if the backing store has an element in every available entry.
	 *
	 * @return True when a new, larger, backing store is needed before an
	 *         element can be added. False if we could add an element currently.
	 */
	private boolean storeIsFull() {
		return _size == _store.length;
	}

	/**
	 * Allocates a new, larger, array and copies over all of the existing
	 * elements into it. This returns the larger array so that it can be used as
	 * the backing store.
	 *
	 * @return Array with all of the ArrayMultiSet's elements AND additional
	 *         space in which elements could be added.
	 */
	@SuppressWarnings("unchecked")
	private E[] growBackingStore() {
		E[] newStore = (E[]) (new Object[_store.length * 2]);
		for (int i = 0; i < _store.length; i++) {
			newStore[i] = _store[i];
		}
		return newStore;
	}

	/**
	 * Adds the element to the next available entry in the backing store and
	 * updates the instance variables to maintain the class invariant. Returns
	 * if this addition was successful (this should always return true).
	 *
	 * @param elem
	 *            Element to be added to the backing store
	 * @return True since this will always successfully adds the element.
	 */
	private boolean addElement(E elem) {
		if(!storeIsFull()){
			_store[_size] = elem;
			_size+=1;
		}
		return true;
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
	 * @return {@code true} if {@code obj} was found and an instance removed;
	 *         {@code false} if a match could not be found.
	 */
	@Override
	public boolean contains(Object obj) {
		// Skipped on behalf of Friday's homework.
		throw new UnsupportedOperationException();
	}

	/**
	 * Removes a single instance of the given object, if one can be found in the
	 * multibag. The method returns {@code true} if a match was found (and
	 * removed) and {@code false} if no match was found. Normally, this uses
	 * {@link Object#equals} to check if there is a match, but uses {@code ==}
	 * when {@code obj} is {@code null}.
	 *
	 * @param obj
	 *            Object (or null) which we want to remove
	 * @return {@code true} if {@code obj} was found and an instance removed;
	 *         {@code false} if a match could not be found.
	 */
	@Override
	public boolean remove(Object obj) {
		// Skipped on behalf of Friday's homework.
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