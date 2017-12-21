package uk.org.landeg.projecteuler.problems;

import org.junit.Assert;
import org.junit.Test;

public class Problem01xTest {
	@Test
	public void assertSolutions () {
		Assert.assertEquals(new Long(142913828922l), new Problem010().solve());
		Assert.assertEquals(new Long(70600674), new Problem011().solve());
		Assert.assertEquals(new Long(76576500), new Problem012().solve());
		Assert.assertEquals(new Long(5537376230l), new Problem013().solve());
		Assert.assertEquals(new Long(837799), new Problem014().solve());
		Assert.assertEquals(new Long(137846528820l), new Problem015().solve());
		Assert.assertEquals(new Long(1366), new Problem016().solve());
		Assert.assertEquals(new Integer(21124), new Problem017().solve());
		Assert.assertEquals(new Integer(1074), new Problem018().solve());
		Assert.assertEquals(new Integer(171), new Problem019().solve());
	}
}
