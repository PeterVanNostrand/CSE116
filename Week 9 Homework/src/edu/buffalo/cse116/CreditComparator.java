package edu.buffalo.cse116;

import java.util.Comparator;

/**
 * Class that can be used to compare two {@link Student}s by the number of credits they have earned. Since the number of
 * credits is related to the student's standing, this could be used when trying to sort students and then split them
 * into teams.
 *
 * @author Matthew Hertz
 * @author Peter VanNostrand
 */
public class CreditComparator implements Comparator<Student> {

	@Override
	public int compare(Student stud1, Student stud2) {
		double d = stud1.getCredits() - stud2.getCredits();
		if(d<0){
			return -1;
		}
		else if(d>0){
			return 1;
		}
		else{
			return 0;
		}
	}
	
}
