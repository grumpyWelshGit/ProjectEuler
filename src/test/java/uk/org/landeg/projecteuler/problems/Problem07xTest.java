package uk.org.landeg.projecteuler.problems;

import org.junit.Assert;
import org.junit.Test;


public class Problem07xTest {
	@Test
	public void assertProblem67Solution () {
		Assert.assertEquals(2772, new Problem085().solve().intValue());
		
	}
	
	@Test
	public void assertProblem70Solution () {
		Assert.assertEquals(8319823, new Problem070().solve().intValue());
	}
	@Test
	public void assertProblem71Solution () {
		Assert.assertEquals(428570, new Problem071().solve().intValue());
	}
	@Test
	public void assertProblem72Solution () {
		Assert.assertEquals(303963552391l, new Problem072().solve().longValue());
	}
	@Test
	public void assertProblem73Solution () {
		Assert.assertEquals(7295372l, new Problem073().solve().longValue());
	}
	@Test
	public void assertProblem74Solution () {
		Assert.assertEquals(402, new Problem074().solve().longValue());
	}
	@Test
	public void assertProblem75Solution () {
		Assert.assertEquals(161667, new Problem075().solve().longValue());
	}
	@Test
	public void assertProblem77Solution () {
		Assert.assertEquals(71, new Problem077().solve().intValue());
	}
}
