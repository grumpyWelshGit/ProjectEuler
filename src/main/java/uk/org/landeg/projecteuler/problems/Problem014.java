package uk.org.landeg.projecteuler.problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.ProblemDescription;

@Component
@Order(14)
public class Problem014 implements ProblemDescription<Long>{

	@Override
	public String getTask() {
		return "Which starting number, under one million, produces the longest chain?";
	}

	@Override
	public String getDescribtion() {
		return "n → n/2 (n is even) n → 3n + 1 (n is odd)";
	}

	@Override
	public Long solve() {
		long n = 0;
		int maxIterations = 0;
		int iterations;
		int maxN = 0;
//		final Set<Integer> primes = PrimeLib.primes(1000000);
		final List<Long> currentSequence = new ArrayList<>();
		final Map<Integer, Integer> remainingSteps = new HashMap<>();
		final int max = 1000000;
		for (int test = 2 ; test < max ; test++) {
			currentSequence.clear();
			iterations = 1;
			n = test;
			do {
				if (n < test && remainingSteps.containsKey((int) (n))) {
					iterations += remainingSteps.get((int) n);
					n = 1;
				} else {
					iterations++;
					if (n % 2 == 0) {
						n /= 2;
					} else {
						n = 3 * n + 1;
					}
				}
			} while (n != 1);
			remainingSteps.put(test, iterations);
			if (iterations > maxIterations) {
				
				maxIterations = iterations;
				maxN = test;
			}
			remainingSteps.put((int) n, iterations);
		}
		System.out.println(String.format("%d iterations : %d", maxN, maxIterations));
		return new Long(maxN);
	}
}
