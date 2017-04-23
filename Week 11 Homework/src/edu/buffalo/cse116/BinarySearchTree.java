package edu.buffalo.cse116;

import java.util.AbstractSet;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BinarySearchTree<E extends Comparable<E>> extends AbstractSet<E> {
	/**
	 * Data version count which can be used to help our fail-fast implementation
	 * of the iterator.
	 */
	protected int modCount;

	protected Entry<E> root;

	protected int size;

	/**
	 * Initializes this BinarySearchTree object to be empty, to contain only
	 * elements of type E, to be ordered by the Comparable interface, and to
	 * contain no duplicate elements.
	 */
	public BinarySearchTree() {
		root = null;
		size = 0;
		modCount = 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof BinarySearchTree)) {
			return false;
		}
		return equals(root, ((BinarySearchTree<? extends E>) obj).root);
	} // method 1-parameter equals

	public boolean equals(Entry<E> p, Entry<? extends E> q) {
		if ((p == null) || (q == null)) {
			return p == q;
		}
		if (!p.element.equals(q.element)) {
			return false;
		}
		if (equals(p.left, q.left) && equals(p.right, q.right)) {
			return true;
		}
		return false;
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
		return new TreeIterator();
	}

	/**
	 * Determines if there is at least one element in this BinarySearchTree
	 * object that equals a specified element. The worstTime(n) is O(n) and
	 * averageTime(n) is O(log n).
	 *
	 * @param obj
	 *            - the element sought in this BinarySearchTree object.
	 * @return true - if there is an element in this BinarySearchTree object
	 *         that equals obj; otherwise, return false.
	 * @throws ClassCastException
	 *             - if obj cannot be compared to the elements in this
	 *             BinarySearchTree object.
	 * @throws NullPointerException
	 *             - if obj is null.
	 */
	@Override
	public boolean contains(Object obj) {
		return getEntry(obj) != null;
	}

	/**
	 * Ensures that this BinarySearchTree object contains a specified element.
	 * The worstTime(n) is O(n) and averageTime(n) is O(log n).
	 *
	 * @param element
	 *            - the element whose presence is ensured in this
	 *            BinarySearchTree object.
	 * @return true - if this BinarySearchTree object changed as a result of
	 *         this method call (that is, if element was actually inserted);
	 *         otherwise, return false.
	 * @throws ClassCastException
	 *             - if element cannot be compared to the elements already in
	 *             this BinarySearchTree object.
	 * @throws NullPointerException
	 *             - if element is null.
	 */
	@Override
	public boolean add(E element) {
		if (root == null) {
			if (element == null) {
				throw new NullPointerException();
			}
			root = new Entry<>(element, null);
			size++;
			modCount++;
			return true;
		} else {
			Entry<E> temp = root;

			int comp;

			while (true) {
				comp = element.compareTo(temp.element);
				if (comp == 0) {
					return false;
				}
				if (comp < 0) {
					if (temp.left != null) {
						temp = temp.left;
					} else {
						temp.left = new Entry<>(element, temp);
						size++;
						modCount++;
						return true;
					} // temp.left == null
				} else if (temp.right != null) {
					temp = temp.right;
				} else {
					temp.right = new Entry<>(element, temp);
					size++;
					modCount++;
					return true;
				} // temp.right == null
			} // while
		} // root not null
	} // method add

	/**
	 * Ensures that this BinarySearchTree object does not contain a specified
	 * element. The worstTime(n) is O(n) and averageTime(n) is O(log n).
	 *
	 * @param obj
	 *            - the object whose absence is ensured in this BinarySearchTree
	 *            object.
	 * @return true - if this BinarySearchTree object changed as a result of
	 *         this method call (that is, if obj was actually removed);
	 *         otherwise, return false.
	 * @throws ClassCastException
	 *             - if obj cannot be compared to the elements already in this
	 *             BinarySearchTree object.
	 * @throws NullPointerException
	 *             - if obj is null.
	 */
	@Override
	public boolean remove(Object obj) {
		Entry<E> e = getEntry(obj);
		if (e == null) {
			return false;
		}
		deleteEntry(e);
		modCount++;
		return true;
	}

	/**
	 * Finds the Entry object that houses a specified element, if there is such
	 * an Entry. The worstTime(n) is O(n), and averageTime(n) is O(log n).
	 *
	 * @param obj
	 *            - the element whose Entry is sought.
	 * @return the Entry object that houses obj - if there is such an Entry;
	 *         otherwise, return null.
	 * @throws ClassCastException
	 *             - if obj is not comparable to the elements already in this
	 *             BinarySearchTree object.
	 * @throws NullPointerException
	 *             - if obj is null.
	 */
	@SuppressWarnings("unchecked")
	protected Entry<E> getEntry(Object obj) {
		int comp;

		if (obj == null) {
			throw new NullPointerException();
		}
		if (!(obj instanceof Comparable)) {
			return null;
		}
		Comparable<E> compObj = (Comparable<E>) obj;
		Entry<E> e = root;
		while (e != null) {
			comp = compObj.compareTo(e.element);
			if (comp == 0) {
				return e;
			} else if (comp < 0) {
				e = e.left;
			} else {
				e = e.right;
			}
		} // while
		return null;
	}

	/**
	 * Deletes the element in a specified Entry object from this
	 * BinarySearchTree.
	 *
	 * @param p
	 *            the Entry object whose element is to be deleted from this
	 *            BinarySearchTree object.
	 * @return the Entry object that was actually deleted from this
	 *         BinarySearchTree object.
	 */
	protected Entry<E> deleteEntry(Entry<E> p) {
		size--;
		modCount++;

		// If p has two children, replace p's element with p's successor's
		// element, then make p reference that successor.
		if ((p.left != null) && (p.right != null)) {
			Entry<E> s = successor(p);
			p.element = s.element;
			p = s;
		} // p had two children

		// At this point, p has either no children or one child.

		Entry<E> replacement;

		if (p.left != null) {
			replacement = p.left;
		} else {
			replacement = p.right;
		}

		// If p has at least one child, link replacement to p.parent.
		if (replacement != null) {
			replacement.parent = p.parent;
			if (p.parent == null) {
				root = replacement;
			} else if (p == p.parent.left) {
				p.parent.left = replacement;
			} else {
				p.parent.right = replacement;
			}
		} // p has at least one child
		else if (p.parent == null) {
			root = null;
		} else {
			if (p == p.parent.left) {
				p.parent.left = null;
			} else {
				p.parent.right = null;
			}
		} // p has a parent but no children
		return p;
	} // method deleteEntry

	/**
	 * Finds the successor of a specified Entry object in this BinarySearchTree.
	 * The worstTime(n) is O(n) and averageTime(n) is constant.
	 *
	 * @param e
	 *            - the Entry object whose successor is to be found.
	 * @return the successor of e, if e has a successor; otherwise, return null.
	 */
	protected Entry<E> successor(Entry<E> e) {
		if (e == null) {
			return null;
		} else if (e.right != null) {
			// successor is leftmost Entry in right subtree of e
			Entry<E> p = e.right;
			while (p.left != null) {
				p = p.left;
			}
			return p;

		} // e has a right child
		else {

			// go up the tree to the left as far as possible, then go up
			// to the right.
			Entry<E> p = e.parent;
			Entry<E> ch = e;
			while ((p != null) && (ch == p.right)) {
				ch = p;
				p = p.parent;
			} // while
			return p;
		} // e has no right child
	} // method successor

	protected class TreeIterator implements Iterator<E> {

		protected int expectedModCount;

		protected Entry<E> lastReturned = null, next;

		/**
		 * Positions this TreeIterator to the smallest element, according to the
		 * Comparable interface, in the BinarySearchTree object. The
		 * worstTime(n) is O(n) and averageTime(n) is O(log n).
		 */
		protected TreeIterator() {
			expectedModCount = modCount;
			next = root;
			if (next != null) {
				while (next.left != null) {
					next = next.left;
				}
			}
		}

		/**
		 * Determines if there are still some elements, in the BinarySearchTree
		 * object this TreeIterator object is iterating over, that have not been
		 * accessed by this TreeIterator object.
		 *
		 * @return true - if there are still some elements that have not been
		 *         accessed by this TreeIterator object; otherwise, return
		 *         false.
		 */
		@Override
		public boolean hasNext() {
			if (expectedModCount == modCount) {
				return next != null;
			} else {
				throw new ConcurrentModificationException();
			}
		} // method hasNext

		/**
		 * Returns the element in the Entry this TreeIterator object was
		 * positioned at before this call, and advances this TreeIterator
		 * object. The worstTime(n) is O(n) and averageTime(n) is constant.
		 *
		 * @return the element this TreeIterator object was positioned at before
		 *         this call.
		 * @throws NoSuchElementException
		 *             - if this TreeIterator object was not positioned at an
		 *             Entry before this call.
		 */
		@Override
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			lastReturned = next;
			next = successor(next);
			return lastReturned.element;
		} // method next

		/**
		 * Removes the element returned by the most recent call to this
		 * TreeIterator object's next() method. The worstTime(n) is O(n) and
		 * averageTime(n) is constant.
		 *
		 * @throws IllegalStateException
		 *             - if this TreeIterator's next() method was not called
		 *             before this call, or if this TreeIterator's remove()
		 *             method was called between the call to the next() method
		 *             and this call.
		 */
		@Override
		public void remove() {
			if (lastReturned == null) {
				throw new IllegalStateException();
			}
			if (expectedModCount != modCount) {
				throw new ConcurrentModificationException();
			}

			if ((lastReturned.left != null) && (lastReturned.right != null)) {
				next = lastReturned;
			}
			deleteEntry(lastReturned);

			expectedModCount = modCount;

			lastReturned = null;
		} // method remove

	} // class TreeIterator

	protected static class Entry<E> {
		protected E element;

		protected Entry<E> left = null, right = null, parent;

		/**
		 * Initializes this Entry object. This default constructor is defined
		 * for the sake of subclasses of the BinarySearchTree class.
		 */
		public Entry() {
		}

		/**
		 * Initializes this Entry object from element and parent.
		 */
		public Entry(E element, Entry<E> parent) {
			this.element = element;
			this.parent = parent;
		} // constructor

	} // class Entry

} // class BinarySearchTree
