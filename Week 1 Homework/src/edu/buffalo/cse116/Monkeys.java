package edu.buffalo.cse116;
/**
 * Class that is used to print out the lyrics to "10 little monkeys"
 *
 * @author Matthew Hertz
 */
public class Monkeys {
	public static void main(String[] args) {
		for(int i=10; i>0; i--){
			if(i!=1){
				System.out.println(i + " little monkeys jumping on the bed.");
			}
			else{
				System.out.println(i + " little monkey jumping on the bed.");
			}
			System.out.println("One fell off and bumped his head.");
			System.out.println("Mama called the doctor and the doctor said,");
			System.out.println("\"No more monkeys jumping on the bed!\"");
			System.out.println();
		}
	}
}