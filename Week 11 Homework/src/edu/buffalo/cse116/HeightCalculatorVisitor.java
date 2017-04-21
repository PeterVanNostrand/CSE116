package edu.buffalo.cse116;

/**
 * When correctly implemented, this class will complete the Visitor pattern and
 * create a system which calculates and sets node's heights. It is important
 * that each of the methods only calculate and set the height in the AVLEntry
 * provided as a parameter.
 *
 * @author Matthew Hertz
 */
public class HeightCalculatorVisitor<E> implements TreeVisitor<E> {

	/**
	 * Sets the height of the leaf node and then returns this height. By
	 * definition, leaves have a height of 0.
	 */
	@Override
	public int visitLeaf(AVLEntry<E> leaf, int data) {
		leaf.setHeight(0);
		return 0;
	}

	@Override
	public int visitInterior(AVLEntry<E> node, int data) {
		int x =0;
		int y = 0;
		if(node.getLeft()!=null){
			x = node.getLeft().apply(this, data);
		}
		if(node.getRight()!=null){
			y = node.getRight().apply(this, data);
		}
		int retVal = Math.max(x, y)+1;
		node.setHeight(retVal);
		return retVal;
	}

}
