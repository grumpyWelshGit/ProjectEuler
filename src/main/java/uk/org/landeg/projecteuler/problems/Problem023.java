package uk.org.landeg.projecteuler.problems;

import java.util.Set;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.Mathlib;
import uk.org.landeg.projecteuler.PrimeLib;
import uk.org.landeg.projecteuler.ProblemDescription;

@Component
@Order(23)
public class Problem023 implements ProblemDescription<Long>{

	@Override
	public String getTask() {
		return "Find the sum of all the positive integers which cannot be written as the sum of two abundant numbers.";
	}

	@Override
	public String getDescribtion() {
		return "A number n is called deficient if the sum of its proper divisors is less than n and it is called abundant if this sum exceeds n.";
	}

	@Override
	public Long solve() {
		int max = 22000;
		final boolean[] abundantNumbers = new boolean[max];
		final boolean[] sumOfAbundant = new boolean[max];

		final Set<Integer> primes = PrimeLib.primes(max);
		for (int n = 2; n < max ; n++) {
			if (Mathlib.sum(Mathlib.properDivisors(n, primes)) > n) {
				abundantNumbers[n] = true;
			}
		}

		for (int n1 = 0 ; n1 < abundantNumbers.length ; n1++) {
			if (abundantNumbers[n1]) {
				for (int n2 = n1 ; n2 < abundantNumbers.length ; n2++) {
					if (abundantNumbers[n2]) {
						int sum = n1 + n2;
						if (sum >= sumOfAbundant.length) {
							break;
						}
						sumOfAbundant[n1 + n2] = true;
					}
				}
			}
		}
		long sum = 0;
		for (int n = 1; n < sumOfAbundant.length ; n++) {
			if (!sumOfAbundant[n]) {
				sum += n;
			}
		}
		
		return sum;
	}

}
