package uk.org.landeg.projecteuler.problems;

import org.junit.Assert;
import org.junit.Test;


public class Problem002Test {
	@Test
	public void assertCorrectSolution () {
		Assert.assertEquals(4613732, new Problem002().solve().intValue());
	}
}
