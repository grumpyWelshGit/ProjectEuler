package uk.org.landeg.projecteuler;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;


public class PrimeLibTest {
	@Test
	public void testPrimeSieve () {
		final int maxValue = 20;
		final Integer[] expectedPrimes = new Integer[] {2,3,5,7,11,13,17,19};
		final Set<Integer> expectedPrimesSet = new HashSet<>(Arrays.asList(expectedPrimes));
		final Set<Integer> primes = PrimeLib.primes(maxValue);
		Assert.assertEquals(expectedPrimesSet.size(), primes.size());
		Assert.assertTrue(primes.containsAll(expectedPrimesSet));
	}
}
