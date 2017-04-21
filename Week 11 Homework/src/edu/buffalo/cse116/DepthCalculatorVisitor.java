package edu.buffalo.cse116;

/**
 * When correctly implemented, this class will complete the Visitor pattern and
 * create a system which calculates and sets node's depths. It is important that
 * each of the methods only calculate and set the depth in the AVLEntry provided
 * as a parameter.
 *
 * @author Matthew Hertz
 */
public class DepthCalculatorVisitor<E> implements TreeVisitor<E> {

	/**
	 * Sets the depth of the leaf node to the value that had been calculated.
	 */
	@Override
	public int visitLeaf(AVLEntry<E> leaf, int depth) {
		leaf.setDepth(depth);
		// This visitor does not need a return value, so we will always return 0
		return 0;
	}

	@Override
	public int visitInterior(AVLEntry<E> node, int depth) {
		node.setDepth(depth);
		depth += 1;
		if (node.getLeft() != null) {
			node.getLeft().apply(this, depth);
		}
		if (node.getRight() != null) {
			node.getRight().apply(this, depth);
		}
		// This visitor does not need a return value, so we will always return 0
		return 0;
	}

}
