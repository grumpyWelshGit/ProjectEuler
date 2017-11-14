package uk.org.landeg.projecteuler.problems;

import org.junit.Test;

import junit.framework.Assert;


public class Problem001Test {
	@Test
	public void assertSolution () {
		Assert.assertEquals(233168, new Problem001().solve().intValue());
	}
}
