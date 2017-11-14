package uk.org.landeg.projecteuler.problems;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.PrimeLib;
import uk.org.landeg.projecteuler.ProblemDescription;

@Component
@Order(10)
public class Problem010 implements ProblemDescription<Long>{

	@Override
	public String getTask() {
		return "Find the sum of all the primes below two million";
	}

	@Override
	public String getDescribtion() {
		return "The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17";
	}

	@Override
	public Long solve() {
		final boolean[] primes = PrimeLib.primesAsArray(2000000);
		long sum = 0;
		for (int idx = 0 ; idx < primes.length ; idx++) {
			if (primes[idx]) { sum += idx; }
		}
		return sum;
	}

}
