package edu.buffalo.cse116;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class ListMaxTest {
	@Test
	public void findMaxPositive() {
		ArrayList<Double> findMaxOf = new ArrayList<Double>();
		findMaxOf.add(9.0);
		findMaxOf.add(7.0);
		findMaxOf.add(6.0);
		findMaxOf.add(9.0);
		findMaxOf.add(8.0);
		findMaxOf.add(10.0);
		findMaxOf.add(3.0);
		findMaxOf.add(6.0);
		ListMax lm = new ListMax(findMaxOf);
		assertEquals(10.0, lm.getLargest(),0.000001);
	}
	
	@Test
	public void findMaxNegative() {
		ArrayList<Double> findMaxOf = new ArrayList<Double>();
		findMaxOf.add(-9.0);
		findMaxOf.add(-7.0);
		findMaxOf.add(-6.0);
		findMaxOf.add(-9.0);
		findMaxOf.add(-8.0);
		findMaxOf.add(-10.0);
		findMaxOf.add(-3.0);
		findMaxOf.add(-6.0);
		ListMax lm = new ListMax(findMaxOf);
		assertEquals(-3.0, lm.getLargest(),0.000001);
	}
	
	@Test
	public void findMaxMixedSigns() {
		ArrayList<Double> findMaxOf = new ArrayList<Double>();
		findMaxOf.add(-9.0);
		findMaxOf.add(7.0);
		findMaxOf.add(-6.0);
		findMaxOf.add(9.0);
		findMaxOf.add(-8.0);
		findMaxOf.add(-10.0);
		findMaxOf.add(3.0);
		findMaxOf.add(6.0);
		ListMax lm = new ListMax(findMaxOf);
		assertEquals(9.0, lm.getLargest(),0.000001);
	}
	
	@Test
	public void findMaxWithNull() {
		ArrayList<Double> findMaxOf = new ArrayList<Double>();
		findMaxOf.add(-9.0);
		findMaxOf.add(7.0);
		findMaxOf.add(-6.0);
		findMaxOf.add(null);
		findMaxOf.add(-8.0);
		findMaxOf.add(-10.0);
		findMaxOf.add(3.0);
		findMaxOf.add(6.0);
		ListMax lm = new ListMax(findMaxOf);
		assertEquals(7.0, lm.getLargest(),0.000001);
	}
	
	@Test
	public void findMaxWithArraySizeOne() {
		ArrayList<Double> findMaxOf = new ArrayList<Double>();
		findMaxOf.add(1.0);
		ListMax lm = new ListMax(findMaxOf);
		assertEquals(1.0, lm.getLargest(),0.000001);
	}
	
	@Test
	public void findMaxWithHighestFirst() {
		ArrayList<Double> findMaxOf = new ArrayList<Double>();
		findMaxOf.add(11.0);
		findMaxOf.add(7.0);
		findMaxOf.add(6.0);
		findMaxOf.add(9.0);
		findMaxOf.add(8.0);
		findMaxOf.add(10.0);
		findMaxOf.add(3.0);
		findMaxOf.add(6.0);
		ListMax lm = new ListMax(findMaxOf);
		assertEquals(11.0, lm.getLargest(),0.000001);
	}
}
