package edu.buffalo.cse116;

public class BSTEntry<E extends Comparable<E>> {
	/** Tree's element which is stored within this Node. */
	protected E element;

	/** Left child of the current Node. */
	protected BSTEntry<E> left;
	/** Right child of the current Node. */
	protected BSTEntry<E> right;
	/** Parent in the binary tree for the current Node. */
	protected BSTEntry<E> parent;

	/**
	 * Creates a new BSTNode object with the specified element and parent
	 * BSTNode.
	 *
	 * @param element
	 *            Data to be held in this BSTNode.
	 * @param parent
	 *            Parent for this entry within the binary tree.
	 */
	public BSTEntry(E element, BSTEntry<E> parent) {
		this.element = element;
		this.parent = parent;
	}

	/**
	 * Counts the number of nodes in this subtree. Note that, like many tree
	 * properties, this is defined recursively.
	 */
	public int subtreeSize() {
		// Setup default values for when there is no subtree on the left & right
		// (e.g., those children are null)
		int leftChildSize = 0;
		int rightChildSize = 0;

		// If we have a left subtree
		if (left != null) {
			// Calculate the subtreeSize recursively.
			leftChildSize = left.subtreeSize();
		}

		// Check if we have a right subtree
		if (right != null) {
			// Calculate the right's subtreeSize recursively.
			rightChildSize = right.subtreeSize();
		}

		// The total size of this subtree is 1 (for the current node) plus the
		// results from each child.
		return leftChildSize + rightChildSize + 1;
	}

	/**
	 * Returns the number of nodes in this subtree whose elements are GREATER
	 * than the current node's. Using the properties of a BST, we can determine
	 * this count WITHOUT comparing any elements!
	 *
	 * @return Number of nodes in this subtree whose element must be LARGER than
	 *         the current node's.
	 */
	public int numberLargerNodesInSubtree() {
		return numberLargerNodesInSubtree(this, 0);
	}

	private int numberLargerNodesInSubtree(BSTEntry ent, int numLarger) {
		if (this.parent == null) {
			return 0;
		} else {
			return numberLargerNodesInSubtree(ent.parent, numLarger += 1);
		}
	}

	/**
	 * Returns the number of nodes in this subtree whose elements are SMALLER
	 * than the current node's. Using the properties of a BST, we can determine
	 * this count WITHOUT comparing any elements!
	 *
	 * @return Number of nodes in this subtree whose element must be LOWER than
	 *         the current node's.
	 */
	public int numberSmallerNodesInSubtree() {
		return numberSmallerNodesInSubtree(this, 0);
	}

	private int numberSmallerNodesInSubtree(BSTEntry ent, int numSmaller) {
		if (ent.left == null && ent.right == null) {
			return 0;
		} else if(ent.left!=null && ent.right!=null){
			numSmaller++;
			return numberSmallerNodesInSubtree(ent.left,numSmaller+=1) + numberSmallerNodesInSubtree(ent.right, numSmaller+=1);
		}
		else if(ent.left!=null){
			numSmaller++;
			return numberSmallerNodesInSubtree(ent.left, numSmaller+=1);
		}
		else{
			numSmaller++;
			return numberSmallerNodesInSubtree(ent.right, numSmaller+=1);
		}
	}
}
