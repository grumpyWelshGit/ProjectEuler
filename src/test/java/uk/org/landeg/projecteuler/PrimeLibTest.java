package uk.org.landeg.projecteuler;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


class PrimeLibTest {
  @Test
  void testPrimeSieve() {
		final int maxValue = 20;
		final Integer[] expectedPrimes = new Integer[] {2,3,5,7,11,13,17,19};
		final Set<Integer> expectedPrimesSet = new HashSet<>(Arrays.asList(expectedPrimes));
		final Set<Integer> primes = PrimeLib.primes(maxValue);
		assertEquals(expectedPrimesSet.size(), primes.size());
		assertTrue(primes.containsAll(expectedPrimesSet));
	}
}
