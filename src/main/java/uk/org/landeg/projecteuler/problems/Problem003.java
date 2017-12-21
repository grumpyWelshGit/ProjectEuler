package uk.org.landeg.projecteuler.problems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.PrimeLib;
import uk.org.landeg.projecteuler.ProblemContext;
import uk.org.landeg.projecteuler.ProblemDescription;

@Component
@Order(3)
public class Problem003 implements ProblemDescription<Long>{
	private static final Logger LOG = LoggerFactory.getLogger(Problem003.class);
	@Autowired
	private ProblemContext context;

	@Override
	public String getTask() {
		return "What is the largest prime factor of the number 600851475143";
	}

	@Override
	public String getDescribtion() {
		return "The prime factors of 13195 are 5, 7, 13 and 29";
	}

	@Override
	public Long solve() {
		final long max = 600851475143l;
		long currentValue = max;
		final boolean[] primes = PrimeLib.primesAsArray((int) Math.sqrt(max)); 
		int maxFactor = 0;
		for (int idx = primes.length - 1 ; idx >= 0 ; idx--) {
			if (primes[idx]) {
				if (currentValue % idx == 0 && idx > maxFactor) {
					LOG.debug("factor : {}", idx);
					maxFactor = idx;
				}
			}
		}
		return new Long(maxFactor);
	}
	
}
