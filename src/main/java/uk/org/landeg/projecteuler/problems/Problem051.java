package uk.org.landeg.projecteuler.problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.Mathlib;
import uk.org.landeg.projecteuler.PrimeLib;
import uk.org.landeg.projecteuler.ProblemDescription;

@Component
@Order(51)
public class Problem051 implements ProblemDescription<Integer>{
	private static final Logger LOG = LoggerFactory.getLogger(Problem051.class);
	@Override
	public String getTask() {
		return "Find the smallest prime which, by replacing part of the number (not necessarily adjacent digits) with the same digit, is part of an eight prime value family";
	}

	@Override
	public String getDescribtion() {
		// TODO Auto-generated method stub
		return "By replacing the 1st digit of the 2-digit number *3, it turns out that six of the nine possible values: 13, 23, 43, 53, 73, and 83, are all prime\n"
			+ "By replacing the 3rd and 4th digits of 56**3 with the same digit, " 
			+ "this 5-digit number is the first example having seven primes among "
			+ "the ten generated numbers, yielding the family: "
			+ "56003, 56113, 56333, 56443, 56663, 56773, and 56993. "
			+ "Consequently 56003, being the first member of this family, is the smallest prime with this property.	";
	}

	@Override
	public Integer solve() {
		final int max = 1000000;
		Integer result = null;
		final Set<Integer> primesSet  = PrimeLib.primes(max);
		final List<Integer> candidates = new ArrayList<>();
		for (int prime : primesSet) {
			final int [] digitFreq = (Mathlib.digitFrequency(prime));
			int maxFreq = 0;
			int reccuringDigit = -1;
			for (int idx = 0 ; idx < digitFreq.length ; idx++) {
				if (digitFreq[idx] > maxFreq) {
					maxFreq = digitFreq[idx];
					reccuringDigit = idx;
				}
			}

			if (maxFreq > 2) {
				candidates.clear();
				int hits = 0;
				for (int newDigit = 0 ; newDigit <= 9 ; newDigit++) {
					final String primeAsString = Integer.toString(prime);
					String candidatePrimeAsString = primeAsString.replace(Integer.toString(reccuringDigit), Integer.toString(newDigit));
					int candidatePrime  = Integer.parseInt(candidatePrimeAsString);
					if ((int)Math.log10(prime)==(int)Math.log10(candidatePrime) && primesSet.contains(candidatePrime)) {
						candidates.add(candidatePrime);
						hits++;
					}
				}
				if (hits >= 8) {
					LOG.debug("{}", candidates);
					break;
				}
			}
		}
		return result;
	}
}
