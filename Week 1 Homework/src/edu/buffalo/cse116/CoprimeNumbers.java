package edu.buffalo.cse116;

/**
 * Class that can be used to test if two integers are coprime. Numbers are considered coprime iff the largest divisor
 * shared by both numbers is 1. Identifying coprime (also called relatively prime) numbers is valuable for RSA public
 * key cryptography (and possibly other things, but the author has never seen any other uses).
 *
 * @author Matthew Hertz
 */
public class CoprimeNumbers {

  /**
   * Given two integers, this returns true if they are relatively prime and false if they are not. Based upon the first
   * webpage I found ({@link "https://primes.utm.edu/notes/faq/negative_primes.html"}), the primality of negative
   * numbers is up for debate. This method will not treat negatives differently.
   *
   * @param a First integer to be tested
   * @param b Second integer to be tested
   * @return True when the greatest common divisor of these numbers is 1; false otherwise.
   */
  public boolean isCoprime(int a, int b) {
    // Continue using Euclid's algorithm until we find a common divisor
    while (b != 0) {
      // Remember b's value
      int temp = b;
      // Set b to the remainder of dividing a by b (e.g., a mod b).
      b = a % b;
      // Set a equal to b's old value.
      a = temp;
    }
    // The gcd is the value in a. If this is 1 the numbers are coprime.
    if (a == 1) {
      return true;
    }
    // When they are not 1, they have a common divisor.
    else {
      return false;
    }
  }
}