package edu.buffalo.cse116;

/**
 * This class was created to include a method used by students to recursively
 * determine if a String is a palindrome.
 *
 * @author Matthew Hertz
 */
public class Palindrome {

	/**
	 * Method that checks if {@code str} is a palindrome.<br/>
	 * A String IS a palindrome if it has a length of 1 or 0 (a really boring
	 * case).<br/>
	 * A String IS NOT a palindrome if the first character of the string
	 * (str.charAt(0)) is NOT EQUAL to the (str.length() - 1)-th character.<br/>
	 * A String MIGHT be a palindrome if the first character is equal to the
	 * (str.length() - 1)-th character of the String AND the substring of
	 * {@str} that cuts off the first and last characters (str.substring(1,
	 * str.length()-1)) is a palindrome.
	 *
	 * @param str
	 *            String that we are testing if it is a palindrome.
	 * @return True if str is a palindrome; false otherwise.
	 */
	public static boolean isPalindrome(String str) {
		if (str.length() == 1 || str.length() == 0) {
			return true;
		}
		if (str.charAt(0) == str.charAt(str.length() - 1)) {
			if (str.length() == 2 || str.length()==3) {
				return true;
			}
			String nextStr = str.substring(1,str.length()-1);
			if(isPalindrome(nextStr)){
				return true;
			}
		}
		return false;
	}
}
