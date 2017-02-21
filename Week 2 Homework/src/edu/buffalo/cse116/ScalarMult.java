package edu.buffalo.cse116;

/**
 * Class which contains a method which takes in a 2-dimensional array and a scalar value and returns their product.
 *
 * @author Matthew Hertz
 */
public class ScalarMult {

  /**
   * Allocates a new 2-d array and then sets each entry to be the product of {@code scalar} and the entry in
   * {@code matrix} at the same row and column. This was inspired by the only "joke" told by my calculus teacher:<br/>
   * Why can't you cross a mountain climber and a grape? Because you cannot cross a scalar.<br/>
   * <br/>
   * Yeah, I did not find it funny either.
   *
   * @param matrix 2-d array which we would like to have multiplied by the given value.
   * @param scalar The new matrix will be equal to having each entry in {@code matrix} multipled by this value.
   * @return The product of this matrix and constant value.
   */
  public int[][] scalarMult(int[][] matrix, int scalar) {
	  int[][] scaledArray = new int[matrix.length][matrix[0].length];
	  for(int r=0; r<matrix.length; r++){
		  for(int c=0; c<matrix[0].length; c++){
			  scaledArray[r][c] = matrix[r][c]*scalar;
		  }
	  }
	  return scaledArray;
  }

}