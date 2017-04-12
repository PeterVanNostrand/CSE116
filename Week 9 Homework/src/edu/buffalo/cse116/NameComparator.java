package edu.buffalo.cse116;

import java.util.Comparator;

/**
 * Class that can be used to alphabetize {@link Student}s. This should use the {@link Student#getName()} to get the two
 * students' names. It then uses the fact that {@code String} is already comparable to compare the Strings.
 *
 * @author Matthew Hertz
 * @author PEter VanNostrand
 */
public class NameComparator implements Comparator<Student> {

	@Override
	public int compare(Student o1, Student o2) {
		return o1.getName().compareTo(o2.getName());
	}

}
