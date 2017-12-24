package uk.org.landeg.projecteuler.problems;

import org.junit.Assert;
import org.junit.Test;


public class Problem08xTest {
	@Test
	public void assertProblem080Solution () {
		Assert.assertEquals(40886, new Problem080().solve().intValue());
	}
	@Test
	public void assertProblem081Solution () {
		Assert.assertEquals(427337, new Problem081().solve().intValue());
	}

	@Test
	public void assertProblem085Solution () {
		Assert.assertEquals(2772, new Problem085().solve().intValue());
	}

	@Test
	public void assertProblem087Solution () {
		Assert.assertEquals(1097343, new Problem087().solve().intValue());
	}
}
