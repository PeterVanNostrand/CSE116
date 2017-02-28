package edu.buffalo.cse116;

/**
 * This class was inspired by the TV show "American Ninja Warrior". Actually, it was based upon the show's name (I have
 * never seen the show), but from what I learned of through Wikipedia it cannot be any less fun. At any rate, it
 * provides a little more structure for a first homework assignment on Exceptions.
 *
 * @author Matthew Hertz
 */
public class AmericanExceptionWarrior {

  /**
   * Method which attempts to complete the city finals by conquering the Qualifying Course. QualifyingCourse has a
   * single method: {@code runCourse()}. When this method is called, it could throw a checked exception
   * ({@code RopeClimbException}), throw an unchecked exception ({@code TimeException}), or not throw any exception. If
   * an exception is thrown, the method should catch the exception and print "Caught checked exception" or "Caught
   * unchecked exception" (as appropriate). If no exception is thrown, the method should print "No exception thrown"
   *
   * @param exceptionGenerator Class whose method, runCourse(), will be used to help test this Exception Warrior.
   */
  public void cityFinals(QualifyingCourse exceptionGenerator) {
	try{
		exceptionGenerator.runCourse();
		System.out.println("No exception thrown");
	}
	catch(RopeClimbException rcex){
		System.out.println("Caught checked exception");
	}
	catch(TimeException tex){
		System.out.println("Caught unchecked exception");
	}
  }

  /**
   * Method to conquer the course at the grand finale -- throwing exceptions on your own. This has two parameters:
   * throwChecked and throwUnchecked. If throwChecked is true, the method should raise a {@code RopeClimbException}.
   * When throwUnchecked is true, the method should raise a {@code TimeException}. When both parameters are false, the
   * method should not raise any exceptions.
   *
   * @param throwChecked When true, the method should raise a {@RopeClimbException}.
   * @param throwUnchecked When true, the method should raise a {@TimeException}.
   */
  public void mountMidoriyama(boolean throwChecked, boolean throwUnchecked) throws RopeClimbException {
	  if(throwChecked){
		  throw new RopeClimbException();
	  }
	  if(throwUnchecked){
		  throw new TimeException();
	  }
  }
}
