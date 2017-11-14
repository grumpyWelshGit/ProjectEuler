package uk.org.landeg.projecteuler;

import org.junit.Assert;
import org.junit.Test;

public class PermutationsTest {
	@Test
	public void assertPermutationsAsExpected () {
		Assert.assertArrayEquals(new char[]{'0','1','2','3'}, Permutations.lexiograph(new char[] {'0','1','2','3'}, 0));
		Assert.assertArrayEquals(new char[]{'1','3','0','2'}, Permutations.lexiograph(new char[] {'0','1','2','3'}, 10));
	}
}
