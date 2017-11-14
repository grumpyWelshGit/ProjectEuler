package uk.org.landeg.projecteuler.problems;

import java.util.Iterator;
import java.util.Set;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.PrimeLib;
import uk.org.landeg.projecteuler.ProblemDescription;

@Component
@Order(7)
public class Problem007 implements ProblemDescription<Integer>{

	@Override
	public String getTask() {
		return "What is the 10 001st prime number?";
	}

	@Override
	public String getDescribtion() {
		return "By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.";
	}

	@Override
	public Integer solve() {
		final Set<Integer> primes = PrimeLib.primes(500000);
		int idx = 1;
		int thePrime = 0;
		final Iterator<Integer> iterator = primes.iterator();
		while (iterator.hasNext()) {
			thePrime = iterator.next();
			if (idx == 10001) {
				break;
			}
			idx++;
		}
		return thePrime;
	}
}
