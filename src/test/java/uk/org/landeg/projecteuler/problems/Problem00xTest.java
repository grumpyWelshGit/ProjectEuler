package uk.org.landeg.projecteuler.problems;

import org.junit.Assert;
import org.junit.Test;

public class Problem00xTest {
	@Test
	public void assertSolutions () {
		Assert.assertEquals(new Integer(233168), new Problem001().solve());
		Assert.assertEquals(new Long(4613732), new Problem002().solve());
		Assert.assertEquals(new Long(6857), new Problem003().solve());
		Assert.assertEquals(new Integer(906609), new Problem004().solve());
		Assert.assertEquals(new Integer(232792560), new Problem005().solve());
		Assert.assertEquals(new Long(25164150), new Problem006().solve());
		Assert.assertEquals(new Integer(104743), new Problem007().solve());
		Assert.assertEquals(new Long(23514624000l), new Problem008().solve());
		Assert.assertEquals(new Integer(31875000), new Problem009().solve());
	}
}
