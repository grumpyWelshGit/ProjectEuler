package uk.org.landeg.projecteuler.problems;

import java.util.HashSet;
import java.util.Set;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.Mathlib;
import uk.org.landeg.projecteuler.PrimeLib;
import uk.org.landeg.projecteuler.ProblemDescription;

@Order(21)
@Component
public class Problem021 implements ProblemDescription<Long>{

	@Override
	public String getTask() {
		return "Evaluate the sum of all the amicable numbers under 10000.";
	}

	@Override
	public String getDescribtion() {
		return "For example, the proper divisors of 220 are 1, 2, 4, 5, 10, 11, 20, 22, 44, 55 and 110; therefore d(220) = 284. The proper divisors of 284 are 1, 2, 4, 71 and 142; so d(284) = 220";
	}

	@Override
	public Long solve() {
		final int max = 10000;
		final Set<Integer> primes = PrimeLib.primes(max);

		final Set<Integer> amicableNumbers = new HashSet<>();
		for (int n = 2; n < 10001 ; n++) {
			int sumOfDivisors = Mathlib.sum(Mathlib.properDivisors(n, primes));
			int sumOfDivisors2 = Mathlib.sum(Mathlib.properDivisors(sumOfDivisors, primes));
			if (sumOfDivisors2 == n && sumOfDivisors != n) {
				amicableNumbers.add(n);
				amicableNumbers.add(sumOfDivisors);
			}
		}
		return (long) Mathlib.sum(amicableNumbers);
	}

}
