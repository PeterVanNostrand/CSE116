package edu.buffalo.cse116;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class which implements the concept of a multiset -- an unorganized collection
 * which does not limited the number of times an instance can be added.
 *
 * @author Carl Alphonce
 * @author Matthew Hertz
 * @param <E> Type of data contained with the instance.
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

  /**
   * Create a new empty multiset.
   */
  public ArrayMultiSet() {
    _modCount = 0;
    clear();
  }

  /**
   * Remove all of the elements within the instance and invalidate any current
   * iterators.
   */
  @SuppressWarnings("unchecked")
  @Override
  public void clear() {
    _store = (E[]) (new Object[16]);
    _size = 0;
    // maintains the class invariant
  }

  /**
   * Update the multiset so that it includes all of the elements from before the
   * call AND the given element.
   *
   * @param e Item to be added to this collection.
   */
  @SuppressWarnings("unchecked")
  @Override
  public boolean add(E e) {
    // Check if we do not have enough space in the underlying array to store the
    // new element
    if (_size == _store.length) {
      // We do not have space, so create a new larger space (doubling the size
      // is the most time efficient)
      E[] newStore = (E[]) new Object[_store.length * 2];
      // Copy all of the references into the new array
      for (int i = 0; i < _store.length; i++ ) {
        newStore[i] = _store[i];
      }
      _store = newStore;
      // An easier, more efficient way of coding this (but less useful for
      // teaching) would instead be:
      // _store = Arrays.copyOf(_store, _store.length * 2);

    }
    // Add the element to the store
    _store[_size] = e;
    // Finally, we can increase _size, since this change will no longer violate
    // any class invariants.
    _size += 1;
    return true;
  }

  /**
   * Return true if at least one element in the multiset is equal to the given
   * object. When {@code obj} is null, it must use the {@code ==} operator to
   * perform these checks, but when {@code obj} is not null, the
   * {@link Object#equals} method is used.
   *
   * @param obj Object (or null) for which we will search
   * @return {@code true} if {@code obj} was found;
   *         {@code false} if a match could not be found.
   */
  @Override
  public boolean contains(Object obj) {
    // Only scan through _size, since those are the only "real" entries for the
    // multibag.
    for (int i = 0; i < _size; i++ ) {
      // When obj is null, we need to use ==
      if ((obj == null) && (_store[i] == null)) {
        return true;
      }
      // Otherwise, we use .equals() to find a match
      else if ((obj != null) && obj.equals(_store[i])) {
        return true;
      }
      // No else clause, since the match could be at a higher index!
    }
    // Checked all VALID indices, so the result must be:
    return false;
  }

  @Override
  public int size() {
    return _size;
  }

  /**
   * Remove the element found at the given index. This method also acts to maintain the class
   * invariants.<br />
   * <b>Precondition</b>: {@code i} is a valid index within {@code _store}.
   *
   * @param i Index of the element to remove from the multibag.
   */
  private void removeAtIndex(int i) {
    // We do not need to check i, since we made that a precondition (assumption)
    // for this method.

    // Slide elements found in higher indices down to eliminate the "gap" that would otherwise exist.
    while (i < (_size - 1)) {
      _store[i] = _store[i+1];
      i += 1;
    }
    // Update which is the first unused index in the array
    _size -= 1;

    // Finally set the newly unused index to null thus avoiding a space leak
    _store[_size] = null;

  }

  /**
   * Removes a single instance of the given object, if one can be found in the
   * multibag. The method returns {@code true} if a match was found (and
   * removed) and {@code false} if no match was found. Normally, this uses
   * {@link Object#equals} to check if there is a match, but uses {@code ==}
   * when {@code obj} is {@code null}.
   *
   * @param obj Object (or null) which we want to remove
   * @return {@code true} if {@code obj} was found and an instance removed;
   *         {@code false} if a match could not be found.
   */
  @Override
  public boolean remove(Object obj) {
    for (int i = 0; i < _size; i++ ) {
      if ((obj == null) && (_store[i] == null)) {
        removeAtIndex(i);
        return true;
      } else if ((obj != null) && obj.equals(_store[i])) {
        removeAtIndex(i);
        return true;
      }
      // No else clause, since a match may still exist at a higher index!
    }
    // Checked all VALID indices, so the result must be:
    return false;
  }

  @Override
  public boolean isEmpty() {
    return _size == 0;
  }

  @Override
  public Iterator<E> iterator() {
    return new MultiSetIterator();
  }

  /**
   * Instances of this class are used as the iterators for a multiset. We must
   * keep this as an inner-class, because the multiset definition does not
   * include any methods to access its elements.
   *
   * @author Carl Alphonse
   * @author Matthew Hertz
   */
  private class MultiSetIterator implements Iterator<E> {

    /**
     * The index of the _store entry which will be returned by the next call to
     * next()
     */
    private int _cursor;

    /**
     * In keeping with the fail-fast convention, the iterator is only valid
     * while _modCount remains on this version.
     */
    private long _collectionVersion;

    /**
     * Create a new instance of this class that will go through the (valid)
     * entries in the store.
     */
    public MultiSetIterator() {
      _cursor = 0;
      _collectionVersion = _modCount;
    }

    public boolean hasNext() {
    	if(_cursor<_size){
    		return true;
    	}
    	return false;
    }

    public E next() {
    	if(hasNext()){
    		_cursor++;
    		return _store[_cursor-1];
    	}
    	else{
    		throw new NoSuchElementException();
    	}
    }

    public void remove() {
      throw new UnsupportedOperationException();
    }
  }

  /*
   * The remaining methods are part of the Collection<E> interface, but are
   * beyond what is necessary for CSE 116. Students who want a complete Multiset
   * implementation should investigate Google's "Guava" library.
   */

  @Override
  public Object[] toArray() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public <T> T[] toArray(T[] a) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean addAll(Collection<? extends E> c) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    // TODO Auto-generated method stub
    return false;
  }
}
