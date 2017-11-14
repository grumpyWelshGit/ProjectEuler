package uk.org.landeg.projecteuler;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class PrimeLib {
	private PrimeLib() {
	}

	public static Set<Integer> primes (final int max) {
		final boolean[] primeSieve = primesAsArray(max);
		final Set<Integer> primes = new LinkedHashSet<>();
		for (int idx = 1 ; idx < primeSieve.length ; idx++) {
			if (primeSieve[idx]) {
				primes.add(idx);
			}
		}
		return primes;
	}

	public static boolean[] primesAsArray (final int max) {
		final Set<Integer> primes = new HashSet<>();
		final boolean[] candidates = new boolean[max];
		Arrays.fill(candidates, true);
		final int maxTest = (int) Math.sqrt(max) + 1;
		int currentPrime = 1;
		int multiple;
		boolean finished = false;
		
		candidates[0] = candidates[1] = false;
		do {
			if (candidates[currentPrime]) {
				primes.add(currentPrime);
				multiple = currentPrime;
				boolean eliminationFinished = false;
				do {
					multiple += currentPrime;
					if (multiple >= candidates.length) {
						eliminationFinished = true;
					} else {
						candidates[multiple] = false;
					}
				} while (!eliminationFinished);
			}

			do {
				currentPrime++;
				if (currentPrime >= maxTest) {
					finished = true;
					break;
				}
			} while (!candidates[currentPrime]);
			
		} while (!finished);
		return candidates;
	}
}
