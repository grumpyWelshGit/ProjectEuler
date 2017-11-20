package uk.org.landeg.projecteuler.problems;

import java.util.Set;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.PrimeLib;
import uk.org.landeg.projecteuler.ProblemDescription;

@Component
@Order(37)
public class Problem037 implements ProblemDescription<Integer>{

	@Override
	public String getTask() {
		return "Find the sum of the only eleven primes that are both truncatable from left to right and right to left";
	}

	@Override
	public String getDescribtion() {
		return "The number 3797 has an interesting property. Being prime itself, it is possible to continuously remove digits from left to right, and remain prime at each stage: 3797, 797, 97, and 7. Similarly we can work from right to left: 3797, 379, 37, and 3";
	}

	@Override
	public Integer solve() {
		final Set<Integer> primes = PrimeLib.primes(1000000);
		int sum = 0;
		for (int prime : primes) {
			int val = prime;
			boolean isTruncatable = true;
			do {
				val /= 10;
				if (!primes.contains(val)) {
					isTruncatable = false;
				}
			} while (val >= 10);
			if (isTruncatable) {
				int truncatedVal = prime;
				do {
					val = truncatedVal;
					int mult = 1;
					int truncRight = 0;
					do {
						truncRight += val % 10 * mult;
						mult *= 10;
						val /= 10;
					} while (val > 10);
					if (!primes.contains(truncRight)) {
						isTruncatable = false;
						break;
					}
					truncatedVal = truncRight;
				} while (truncatedVal > 10);
			}
			if (isTruncatable) {
				sum += prime;
				System.out.println(prime);
			}
		}
		return sum;
	}

}
