package edu.buffalo.cse116;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A simple iterator class that can be used to access each character in a {@code String}.
 *
 * @author Matthew Hertz
 */
public class CharacterIterator implements Iterator<Character> {
  /** The String whose characters will be returned by this Iterator. */
  private String characterSource;

  /** The next character in the String which will be returned by the Iterator. */
  private int cursor;

  /**
   * Create a new Iterator which can be used to go through the characters in this String.
   *
   * @param str Source of characters over which we will be iterating.
   */
  public CharacterIterator(String str) {
    cursor = 0;
    characterSource = str;
  }

  /** Returns whether the iterator has a next object. */
  public boolean hasNext() {
	  return cursor!=characterSource.length();
  }

  /** Returns the next object in the iterator. */
  public Character next() throws NoSuchElementException {
	  if(hasNext()){
		  Character c = characterSource.charAt(cursor);
		  cursor++;
		  return c;
	  }
	  else{
		  throw new NoSuchElementException();
	  }
  }

  public void remove() {
    throw new UnsupportedOperationException();
  }
}
