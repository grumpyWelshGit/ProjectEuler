package uk.org.landeg.projecteuler.problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.Mathlib;
import uk.org.landeg.projecteuler.PrimeLib;
import uk.org.landeg.projecteuler.ProblemDescription;

@Order(41)
@Component
public class Problem041 implements ProblemDescription<Integer>{

	@Override
	public String getTask() {
		return "What is the largest n-digit pandigital prime that exists?";
	}

	@Override
	public String getDescribtion() {
		return "We shall say that an n-digit number is pandigital if it makes use of all the digits 1 to n exactly once. For example, 2143 is a 4-digit pandigital and is also prime";
	}

	@Override
	public Integer solve() {
		final Set<Integer> primes = PrimeLib.primes(9999999);
		final List<Integer> primesList = new ArrayList<>(primes);
		Collections.sort(primesList);
		Collections.reverse(primesList);
		int result = 0;
		for (final int prime : primesList) {
			if (Mathlib.isPandigital(prime, 7)) {
				result = prime;
				break;
			}
		}
		return result;
	}

}
