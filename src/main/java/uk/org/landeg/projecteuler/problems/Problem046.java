package uk.org.landeg.projecteuler.problems;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.PrimeLib;
import uk.org.landeg.projecteuler.ProblemDescription;

@Order(46)
@Component
public class Problem046 implements ProblemDescription<Long>{

	@Override
	public String getTask() {
		return "What is the smallest odd composite that cannot be written as the sum of a prime and twice a square?";
	}

	@Override
	public String getDescribtion() {
		return "It was proposed by Christian Goldbach that every odd composite number can be written as the sum of a prime and twice a square";
	}

	@Override
	public Long solve() {
		final Set<Integer> primes = PrimeLib.primes(100000);
		final Set<Integer> doubleSquares = new LinkedHashSet<>();
		int s = 1, ds;
		do {
			ds = (s * s) * 2;
			doubleSquares.add(ds);
			s++;
		} while (ds < 100000);
		
		int n = 5;
		boolean isPrime = false;
		boolean foundSum = false;
		do {
			foundSum = false;
			isPrime = true;
			if (!primes.contains(n)) {
				isPrime = false;
				for (int prime : primes) {
					if (prime > n) {
						break;
					}
					for (int dsq : doubleSquares) {
						int sum = dsq + prime;
						if (sum == n) {
							foundSum = true;
							break;
						} else if (sum > n) {
							break;
						}
					}
					if (foundSum) {
						break;
					}
				}
			}
			if (!isPrime && !foundSum) {
				break;
			} else {
				n+=2;
			}
		} while (isPrime || foundSum);
		return (long) n;
	}

}
