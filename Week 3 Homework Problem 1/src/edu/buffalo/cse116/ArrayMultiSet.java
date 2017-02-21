package edu.buffalo.cse116;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Class which implements the concept of a multiset -- an unorganized collection in which elements can appear multiple
 * times.
 *
 * @author Carl Alphonce
 * @author Matthew Hertz
 * @param <E> Type of data contained with the instance.
 */
public class ArrayMultiSet<E> implements Collection<E> {
  /** Array in which the elements in this multiset are stored. */

  private E[] _store;
  /**
   * Array indices below this amount contain the active elements in this collection.
   */
  private int _size;

  /**
   * Modification counter used to preserve the fail-fast nature of its iterators.
   */
  private long _modCount;

  /** Constant value used as the initial size for the backing store when no other size is provided. */
  private static final int DEFAULT_INITIAL_CAPACITY= 12;

  /**
   * Create a new empty multiset using the default initial capacity.
   */
  public ArrayMultiSet() {
    reset(DEFAULT_INITIAL_CAPACITY);
  }

  /**
   * This method, which is only used within the ArrayMultiSet code, returns the instance to its initial state. This call
   * should allocate a new backing store with the specified capacity and update any instance variables needed to reflect
   * that the ArrayMultiSet does not contain any elements.
   *
   * @param newCapacity When allocating a new (empty) backing store, this specifies the space it should reserve for
   *          later
   */
  @SuppressWarnings("unchecked")
  private void reset(int newCapacity) {
	  _store = (E[]) (new Object[newCapacity]);
	  _size = 0;
  }

  /**
   * Create a new empty multiset and, while the size of the multiset will still be 0, allocate a backing store large
   * enough that it COULD hold up to the specified capacity without resizing.
   *
   * @param initialCapacity Amount of space to reserve for future additions to the multiset.
   */
  public ArrayMultiSet(int initialCapacity) {
    reset(DEFAULT_INITIAL_CAPACITY);
  }

  /**
   * Remove all of the elements within the instance and invalidate any current iterators.
   */
  @Override
  public void clear() {
    reset(DEFAULT_INITIAL_CAPACITY);
  }


  /**
   * Returns a newly allocated ArrayList containing the elements in the ArrayMultiset. The ArrayList should have the
   * same _size_ as the ArrayMultiSet and should not include any padding or extra spaces.
   *
   * @return Newly allocated ArrayList whose entries are set to the elements in the Multiset.
   */
  public ArrayList<E> toList() {
	  ArrayList<E> list = new ArrayList<E>();
	  for(int i=0; i<_size; i++){
		  list.add(_store[i]);
	  }
	  return list;
  }

  @Override
  public boolean isEmpty() {
    return _size == 0;
  }

  @Override
  public int size() {
    return _size;
  }

  /**
   * Update the multiset so that it includes all of the elements from before the call AND the given element.
   *
   * @param e Item to be added to this collection.
   */
  @Override
  public boolean add(E e) {
    // To be discussed on Wednesday!
    throw new UnsupportedOperationException();
  }


  /**
   * Return true if at least one element in the multiset is equal to the given object. When {@code obj} is null, it must
   * use the {@code ==} operator to perform these checks, but when {@code obj} is not null, the {@link Object#equals}
   * method is used.
   *
   * @param obj Object (or null) for which we will search
   * @return {@code true} if {@code obj} was found and an instance removed; {@code false} if a match could not be found.
   */
  @Override
  public boolean contains(Object obj) {
    // To be discussed on Friday!
    throw new UnsupportedOperationException();
  }


  /**
   * Removes a single instance of the given object, if one can be found in the multibag. The method returns {@code true}
   * if a match was found (and removed) and {@code false} if no match was found. Normally, this uses
   * {@link Object#equals} to check if there is a match, but uses {@code ==} when {@code obj} is {@code null}.
   *
   * @param obj Object (or null) which we want to remove
   * @return {@code true} if {@code obj} was found and an instance removed; {@code false} if a match could not be found.
   */
  @Override
  public boolean remove(Object obj) {
    // To be discussed Friday!
    throw new UnsupportedOperationException();
  }

  /*
   * The remaining methods are part of the Collection<E> interface, but are beyond what is necessary for CSE 116.
   * Students who want a complete Multiset implementation should investigate Google's "Guava" library.
   */
  @Override
  public Iterator<E> iterator() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object[] toArray() {
    throw new UnsupportedOperationException();
  }

  @Override
  public <T> T[] toArray(T[] a) {
    throw new UnsupportedOperationException();
  }

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