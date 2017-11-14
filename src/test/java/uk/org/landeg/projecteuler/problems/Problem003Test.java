package uk.org.landeg.projecteuler.problems;

import org.junit.Assert;
import org.junit.Test;


public class Problem003Test {
	@Test
	public void assertCorrectSolution () {
		Assert.assertEquals(6857, new Problem003().solve().intValue());
	}
}
