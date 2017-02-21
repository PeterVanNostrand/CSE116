package edu.buffalo.cse116;

import static org.junit.Assert.*;

import org.junit.Test;

public class CoprimeNumbersTest {
	@Test
	public void testNonCoprimeNumbers(){
		CoprimeNumbers cn = new CoprimeNumbers();
		assertFalse(cn.isCoprime(25,125));
	}
	
	@Test
	public void testCoprimeNumbers(){
		CoprimeNumbers cn = new CoprimeNumbers();
		assertTrue(cn.isCoprime(6,5));
	}
}
