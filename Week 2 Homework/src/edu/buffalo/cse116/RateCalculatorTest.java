package edu.buffalo.cse116;

import static org.junit.Assert.*;
import org.junit.Test;

public class RateCalculatorTest {
	@Test
	public void allZeros() {
		RateCalculator rc = new RateCalculator();
		double rate1 = 0;
		double rate2 = 0;
		double rate3 = 0;
		double time1 = 0;
		double time2 = 0;
		double time3 = 0;
		double totalDistance = rc.totalDistance(rate1, time1, rate2, time2, rate3, time3);
		assertEquals(0.0, totalDistance, 0.00001);
	}
	
	@Test
	public void smallNumbers() {
		RateCalculator rc = new RateCalculator();
		double rate1 = 1;
		double rate2 = 2;
		double rate3 = 3;
		double time1 = 1;
		double time2 = 2;
		double time3 = 3;
		double totalDistance = rc.totalDistance(rate1, time1, rate2, time2, rate3, time3);
		assertEquals(14, totalDistance, 0.00001);
	}
}
