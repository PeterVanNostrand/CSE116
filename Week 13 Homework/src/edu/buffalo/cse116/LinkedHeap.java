package edu.buffalo.cse116;

import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * This uses a heap to implement
 *
 * @author Lewis and Chase
 * @author Matthew Hertz
 * @version 4.0
 * @param <T>
 *            Type of (Comparable) element to be stored in this priority queue.
 */
public class LinkedHeap<T> extends BinaryTree<T> {
	/** Stores the last node that we added to this structure. */
	private BTNode<T> lastNode;

	/** Comparator used to organize and order the elements in the heap. */
	private Comparator<T> comparator;

	/** Number of nodes/elements within this binary tree. */
	private int size;

	/**
	 * Creates a new (empty) heap which will uses the specified Comparator to
	 * order its elements.
	 *
	 * @param comp
	 *            Comparator that this heap will use to order its elements.
	 */
	public LinkedHeap(Comparator<T> comp) {
		comparator = comp;
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
	 * Adds the specified element to this heap in the appropriate position
	 * according to its key value.
	 *
	 * @param obj
	 *            the element to be added to the heap
	 * @return Since this method will always succeed, it only returns true.
	 */
	@Override
	public boolean add(T obj) {
		BTNode<T> node = new BTNode<>();
		node.setElement(obj);

		if (getRootNode() == null) {
			setRootNode(node);
		} else {
			BTNode<T> nextParent = getNextParentAdd();
			if (nextParent.getLeft() == null) {
				nextParent.setLeft(node);
			} else {
				nextParent.setRight(node);
			}
			node.setParent(nextParent);
		}
		lastNode = node;
		size += 1;
		siftUpComparator(node, obj);
		return true;
	}

	/**
	 * Returns the node that will be the parent of the new node
	 *
	 * @return the node that will be the parent of the new node
	 */
	private BTNode<T> getNextParentAdd() {
		BTNode<T> result = lastNode;

		while ((result != getRootNode()) && (result.getParent().getLeft() != result)) {
			result = result.getParent();
		}

		if (result != getRootNode()) {
			if (result.getParent().getRight() == null) {
				result = result.getParent();
			} else {
				result = result.getParent().getRight();
				while (result.getLeft() != null) {
					result = result.getLeft();
				}
			}
		} else {
			while (result.getLeft() != null) {
				result = result.getLeft();
			}
		}

		return result;
	}

	/**
	 * Reorders this heap after creating the space for an element in the tree.
	 *
	 * @param node
	 *            BTNode in our linked tree that needs to be filled
	 * @param elem
	 *            Element that we are adding to the tree
	 */
	private void siftUpComparator(BTNode<T> node, T elem) {
		while (node != null && node.getParent() != null) {
			if (comparator.compare(node.getParent().getElement(), node.getElement()) > 0) {
				T temp = node.getParent().getElement();
				node.getParent().setElement(node.getElement());
				node.setElement(temp);
			}
			node = node.getParent();
		}
	}

	/**
	 * Remove the element with the lowest value in this heap and returns a
	 * reference to it. Throws an EmptyCollectionException if the heap is empty.
	 *
	 * @return the element with the lowest value in this heap
	 */
	public T remove() {
		if (isEmpty()) {
			throw new NoSuchElementException("Cannot call remove on an empty LinkedHeap");
		}

		BTNode<T> rootNode = getRootNode();
		T minElement = rootNode.getElement();

		if (size() == 1) {
			setRootNode(null);
			lastNode = null;
		} else {
			BTNode<T> nextLast = getNewLastNode();
			if (lastNode.getParent().getLeft() == lastNode) {
				lastNode.getParent().setLeft(null);
			} else {
				lastNode.getParent().setRight(null);
			}
			T shuffleElem = lastNode.getElement();
			rootNode.setElement(shuffleElem);
			lastNode = nextLast;
			siftDownComparator(rootNode, shuffleElem);
		}
		size -= 1;
		return minElement;
	}

	/**
	 * Reorders this heap after unlinking the node that is actually removed.
	 *
	 * @param node
	 *            BTNode in our linked tree from which our down shifting need to
	 *            start
	 * @param elem
	 *            Element that was in the (now removed) node to be shifted into
	 *            the tree.
	 */
	private void siftDownComparator(BTNode<T> node, T elem) {
		while (node!=null && (node.getLeft() != null || node.getLeft() != null)) {
			if (node.getLeft() != null && node.getRight() == null) {
				siftDownSwap(node, node.getLeft());
				node = node.getLeft();
			}
			else if (node.getLeft() == null && node.getRight() != null) {
				siftDownSwap(node, node.getRight());
				node = node.getRight();
			} else if(node.getLeft()!=null && node.getRight()!=null) {
				if (comparator.compare(node.getRight().getElement(), node.getLeft().getElement()) < 0) {
					siftDownSwap(node, node.getRight());
					node = node.getRight();
				}
				else{
					siftDownSwap(node, node.getLeft());
					node = node.getLeft();
				}
			}
			
		}
	}

	private void siftDownSwap(BTNode<T> parent, BTNode<T> child) {
		if(comparator.compare(parent.getElement(), child.getElement())>0){
			T temp = child.getElement();
			child.setElement(parent.getElement());
			parent.setElement(temp);
		}
	}

	/**
	 * Returns the node that will be the new last node after a remove.
	 *
	 * @return the node that willbe the new last node after a remove
	 */
	private BTNode<T> getNewLastNode() {
		BTNode<T> result = lastNode;

		while ((result != getRootNode()) && (result.getParent().getLeft() == result)) {
			result = result.getParent();
		}

		if (result != getRootNode()) {
			result = result.getParent().getLeft();
		}

		while (result.getRight() != null) {
			result = result.getRight();
		}

		return result;
	}

	/**
	 * Returns the element with the lowest value in this heap. Throws an
	 * EmptyCollectionException if the heap is empty.
	 *
	 * @return the element with the lowest value in this heap
	 */
	public T element() {
		if (isEmpty()) {
			throw new NoSuchElementException("Cannot call remove on an empty LinkedHeap");
		}

		BTNode<T> rootNode = getRootNode();
		T minElement = rootNode.getElement();
		return minElement;
	}
}
