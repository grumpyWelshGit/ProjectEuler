package uk.org.landeg.projecteuler.problems;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.Mathlib;
import uk.org.landeg.projecteuler.PrimeLib;
import uk.org.landeg.projecteuler.ProblemDescription;

@Component
@Order(5)
public class Problem005 implements ProblemDescription<Integer>{

	@Override
	public String getTask() {
		return "What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?";
	}

	@Override
	public String getDescribtion() {
		return "2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder";
	}

	@Override
	public Integer solve() {
		final int testValue = 20;
		final Set<Integer> primes = PrimeLib.primes(testValue);
		final Map<Long, Integer> compositePprimeFactors = new HashMap<>();
		Map<Long, Integer> primeFactors = new HashMap<>();
		for (int idx = 2 ; idx <= testValue ; idx++) {
			if (primes.contains(idx)) {
				primeFactors.clear();
				primeFactors.put((long)idx, 1);
			} else {
				primeFactors = Mathlib.primeFactors(idx, primes);
			}
			for (Long base : primeFactors.keySet()) {
				if (!compositePprimeFactors.containsKey(base) || compositePprimeFactors.get(base) < primeFactors.get(base)) {
					compositePprimeFactors.put(base, primeFactors.get(base));
				}
			}
		}
		int answer = 1;
		for (Long base : compositePprimeFactors.keySet()) {
			answer *= Math.pow(base, compositePprimeFactors.get(base));
		}
		return answer;
	}

}
