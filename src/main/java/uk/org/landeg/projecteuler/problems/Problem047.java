package uk.org.landeg.projecteuler.problems;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.Mathlib;
import uk.org.landeg.projecteuler.PrimeLib;
import uk.org.landeg.projecteuler.ProblemDescription;

@Order(47)
@Component
public class Problem047 implements ProblemDescription<Integer>{
	private static final Logger LOG = LoggerFactory.getLogger(Problem045.class);

	@Override
	public String getTask() {
		return "Find the first four consecutive integers to have four distinct prime factors each. What is the first of these numbers?";
	}

	@Override
	public String getDescribtion() {
		return "The first three consecutive numbers to have three distinct prime factors are 644 645 and 646";
	}

	@Override
	public Integer solve() {
		final Set<Integer> primes = PrimeLib.primes(1000);
		int result = 0;
		for (int idx = 400 ; idx < 1000000 ; idx++) {
			if (
					getDistinctPrimeFactors(idx, primes) >= 4
					&& getDistinctPrimeFactors(idx+1, primes) >= 4
					&& getDistinctPrimeFactors(idx+2, primes) >= 4
					&& getDistinctPrimeFactors(idx+3, primes) >= 4) {
				result = idx;
				break;
			}
		}
		return result;
	}
	
	final Map<Integer, Integer> primeFactorCache = new HashMap<>();
	
	private int getDistinctPrimeFactors (final int n, final Set<Integer> primes) {
		LOG.debug("Determining factors of {}", n);
		if (primeFactorCache.containsKey(n)) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("Cache hit, returning  {}", primeFactorCache.get(n));
			}
			return primeFactorCache.get(n);
		}
		int count = 0;
//		if (primes.contains(n)) {
//			LOG.debug("{} is prime, returning 1");
//			count = 1;
//		} else {
			int max = n;
			LOG.debug("Checking primes to a maximum of {}", max);
			count = Mathlib.primeFactors(n, primes).keySet().size();
//		}
		primeFactorCache.put(n, count);
		return count;
	}

}
