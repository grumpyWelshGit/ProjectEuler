package uk.org.landeg.projecteuler.problems;

import org.junit.Assert;
import org.junit.Test;

public class Problem06xTest {
	@Test
	public void assertSolutions () {
		Assert.assertEquals(new Long(26033), new Problem060().solve());
		Assert.assertEquals(new Long(28684), new Problem061().solve());
		Assert.assertEquals(new Long(127035954683l), new Problem062().solve());
		Assert.assertEquals(new Integer(49), new Problem063().solve());
		
	}
	
	@Test
	public void assertProblem064Test () {
		Assert.assertEquals(new Integer(1322), new Problem064().solve());
	}
	@Test
	public void assertProblem065Test () {
		Assert.assertEquals(new Integer(272), new Problem065().solve());
	}

	@Test
	public void assertProblem066Test () {
		Assert.assertEquals(new Integer(661), new Problem066().solve());
	}
	@Test
	public void assertProblem067Test () {
		Assert.assertEquals (new Integer(7273), new Problem067().solve());
	}
	
	@Test
	public void assertProblem068 () {
		Assert.assertEquals(new String("6531031914842725"), new Problem068().solve());
	}
	@Test
	public void assertProblem069Test () {
		Assert.assertEquals (new Integer(510510), new Problem069().solve());
	}

}
