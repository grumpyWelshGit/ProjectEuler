package uk.org.landeg.projecteuler.problems;

import java.util.Set;


import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.PrimeLib;
import uk.org.landeg.projecteuler.ProblemDescription;

@Component
@Order(69)
@Slf4j
public class Problem069 implements ProblemDescription<Integer>{

	
	@Override
	public String getTask() {
		return "Euler's Totient function, φ(n) [sometimes called the phi function], is used to determine the number of numbers less than n which are relatively prime to n. For example, as 1, 2, 4, 5, 7, and 8, are all less than nine and relatively prime to nine, φ(9)=6";
	}

	@Override
	public String getDescribtion() {
		return "Find the value of n ≤ 1,000,000 for which n/φ(n) is a maximum";
	}

	@Override
	public Integer solve() {
		final int max = 1000000;
		final Set<Integer> primes = PrimeLib.primes(max);
		 
		int maxPrimeNumber = 1;
		for (Integer prime : primes) {
			if (maxPrimeNumber * prime < max) {
				maxPrimeNumber *= prime;
			}
			else {
				break;
			}
		}
		log.info("{}", maxPrimeNumber);
		return maxPrimeNumber;
	}
}
