package edu.buffalo.cse116;

import java.util.AbstractSet;
import java.util.Iterator;

public class BinarySearchTree<E extends Comparable<E>> extends AbstractSet<E> {
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
		// To be discussed next week
		throw new UnsupportedOperationException();
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
						return true;
					} // temp.left == null
				} else if (temp.right != null) {
					temp = temp.right;
				} else {
					temp.right = new Entry<>(element, temp);
					size++;
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
			throw new ClassCastException();
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
	 * @param removeMe
	 *            the Entry object whose element is to be deleted from this
	 *            BinarySearchTree object.
	 * @return the Entry object that was actually deleted from this
	 *         BinarySearchTree object.
	 */
	protected Entry<E> deleteEntry(Entry<E> removeMe) {
		size--;

		// If p has two children, replace p's element with p's successor's
		// element, then make p reference that successor.
		if ((removeMe.left != null) && (removeMe.right != null)) {
			// Get the successor for this node
			Entry<E> tribute = successor(removeMe);
			// Move that node's element to the node which we originally wanted
			// to delete.
			removeMe.element = tribute.element;
			// No delete the easier to remove (promoted) node
			return deleteEntry(tribute);
		} else {
			// At this point, p has either no children or one child.
			Entry<E> replacement = selectReplacementChild(removeMe);

			if (replacement != null) {
				promoteChild(replacement);
			} else if (removeMe != root) {
				removeLeaf(removeMe);
			} else {
				root = null;
			}
			return removeMe;
		}
	} // method deleteEntry

	/**
	 * Remove the leaf from the binary search tree by nulling out it's parent's
	 * reference to it
	 *
	 * @param leaf
	 *            Leaf node that needs to be removed.
	 */
	private void removeLeaf(Entry<E> leaf) {
		// You should assume that leaf a leaf node
		if (leaf.parent.right != null && leaf.parent.right.equals(leaf)) {
			leaf.parent.right = null;
		} else {
			leaf.parent.left = null;
		}
	}

	/**
	 * When we are removing a node that has a child, we need the child to
	 * replace that node in the tree. This method will switch any links so that
	 * they are no longer to it's parent.
	 *
	 * @param replacement
	 *            The node that will be promoted to where it's parent is in the
	 *            tree.
	 */
	private void promoteChild(Entry<E> replacement) {
		// You should assume replacement is not root
		Entry<E> parentEntry = replacement.parent;
		Entry<E> grandDaddyEntry = parentEntry.parent;
		migrateChildren(parentEntry, replacement);
		if (grandDaddyEntry != null) {
			if (isRightChild(parentEntry, grandDaddyEntry)) {
				grandDaddyEntry.right = replacement;
			} else {
				grandDaddyEntry.left = replacement;
			}
		} else {
			root = replacement;
		}
		replacement.parent = grandDaddyEntry;
	}

	private void migrateChildren(Entry<E> parent, Entry<E> child) {
		if (parent.left != null && !parent.left.equals(child)) {
			child.left = parent.left;
		}
		if (parent.right != null && !parent.right.equals(child)) {
			child.right = parent.right;
		}
	}

	private boolean isRightChild(Entry<E> child, Entry<E> parent) {
		return parent.right.equals(child);
	}

	/**
	 * Once we know a node has either 0 or 1 children, we need to find the
	 * replacement node. If the node to be removed is a leaf, null should be
	 * returned. If the node has only one child, then that only child should be
	 * removed.
	 *
	 * @param removeMe
	 *            Node that we will remove from the tree
	 * @return null if {@code removeMe} has no children; {@code removeMe}'s only
	 *         child otherwise.
	 */
	private Entry<E> selectReplacementChild(Entry<E> removeMe) {
		if (removeMe.left != null) {
			return removeMe.left;
		} else if (removeMe.right != null) {
			return removeMe.right;
		} else {
			return null;
		}
	}

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
