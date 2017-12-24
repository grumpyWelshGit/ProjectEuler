package uk.org.landeg.projecteuler.problems;

import java.util.Set;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.Mathlib;
import uk.org.landeg.projecteuler.PrimeLib;
import uk.org.landeg.projecteuler.ProblemDescription;

@Component
@Order(12)
public class Problem012 implements ProblemDescription<Long>{

	@Override
	public String getTask() {
		return "What is the value of the first triangle number to have over five hundred divisors?";
	}

	@Override
	public String getDescribtion() {
		return "What is the value of the first triangle number to have over five hundred divisors?";
	}

	@Override
	public Long solve() {
		final Set<Integer> primes = PrimeLib.primes(100000);
		int divs = 0;long t;int maxDivs= 0;
		int n = 1;
		do {
			n++;
			t = n * (n + 1) / 2;
			divs = Mathlib.divisorCount(t, primes);
			if (divs > maxDivs) {
				maxDivs = divs;
			}
			
		} while (divs < 500);
		final long solution = t;
		return solution;
	}

}
