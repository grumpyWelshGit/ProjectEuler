package uk.org.landeg.projecteuler.problems;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.Mathlib;
import uk.org.landeg.projecteuler.PrimeLib;
import uk.org.landeg.projecteuler.ProblemDescription;

@Order(49)
@Component
public class Problem049 implements ProblemDescription<String>{
	private static final Logger LOG = LoggerFactory.getLogger(Problem049.class);
	@Override
	public String getTask() {
		return 
			"The arithmetic sequence, 1487, 4817, 8147, in which each of the terms increases by 3330, is unusual in two ways: \n"
			+ "(i) each of the three terms are prime, and, \n"
			+ "(ii) each of the 4-digit numbers are permutations of one another.\n"
			+ "There are no arithmetic sequences made up of three 1-, 2-, or 3-digit primes, exhibiting this property, but there is one other 4-digit increasing sequence. \n" +
			"What 12-digit number do you form by concatenating the three terms in this sequence?";
	}

	@Override
	public String getDescribtion() {
		return "The arithmetic sequence, 1487, 4817, 8147, in which each of the terms increases by 3330, is unusual in two ways: (i) each of the three terms are prime, and, (ii) each of the 4-digit numbers are permutations of one another.";
	}

	@Override
	public String solve() {
		final boolean[] primeFlags = PrimeLib.primesAsArray(9999);
		int primeCount = 0;
		for ( int idx = 0 ; idx < primeFlags.length ; idx++) {
			if (primeFlags[idx] && idx >= 1000) {
				primeCount++;
			}
		}
		final int[] primes = new int[primeCount];
		int primeId = 0;
		for ( int idx = 0 ; idx < primeFlags.length ; idx++) {
			if (primeFlags[idx] && idx >= 1000) {
				primes[primeId++] = idx;
			}
		}
		
		String result = null;
		for (int p1 = 67 ; p1 < primes.length ; p1++) {
			final int[] p1freq = Mathlib.digitFrequency(primes[p1]);
			for (int p2 = p1 + 1 ; p2 < primes.length ; p2++) {
				final int[] p2freq = Mathlib.digitFrequency(primes[p2]);
				if (Arrays.equals(p1freq, p2freq)) {
					for (int p3 = p2 + 1 ; p3 < primes.length ; p3++) {
						if (primes[p3] - primes[p2] == primes[p2] - primes[p1]) {
							final int[] p3freq = Mathlib.digitFrequency(primes[p3]);
							if (Arrays.equals(p1freq, p3freq)) {
								LOG.debug("{} {} {}", primes[p1], primes[p2], primes[p3]);
								result = String.format("%d%d%d", primes[p1], primes[p2], primes[p3]);
							}
						}
					}
				}
			}
		}
		return result;
	}
}
