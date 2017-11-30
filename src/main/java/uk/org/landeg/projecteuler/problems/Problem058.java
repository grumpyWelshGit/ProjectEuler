package uk.org.landeg.projecteuler.problems;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.PrimeLib;
import uk.org.landeg.projecteuler.ProblemDescription;

@Component
@Order(58)
public class Problem058 implements ProblemDescription<Long>{
	private static final Logger LOG = LoggerFactory.getLogger(Problem058.class);

	@Override
	public String getTask() {
		return "If one complete new layer is wrapped around the spiral above, a square spiral with side length 9 will be formed. If this process is continued, what is the side length of the square spiral for which the ratio of primes along both diagonals first falls below 10%?";
	}

	@Override
	public String getDescribtion() {
		return "Starting with 1 and spiralling anticlockwise in the following way, a square spiral with side length 7 is formed.\n" +
		"It is interesting to note that the odd squares lie along the bottom right diagonal, but what is more interesting is that 8 out of the 13 numbers lying along both diagonals are prime; that is, a ratio of 8/13 ≈ 62%";
	}

	int maxPrime = 1000000;
	@Override
	public Long solve() {
		float ratio = 1.0f;
		long n = 1;
		long totalCount = 1;
		long primeCount = 0;
		LOG.debug("Generating primes...");
		final Set<Integer> primes = PrimeLib.primes(maxPrime);
		LOG.debug("{} primes generated ...", primes.size());
		do {
			long n2 = 2 * n;
			long nsq = (n2 + 1) * (n2 + 1);
			totalCount += 4;
			if (isPrime(nsq - n2, primes)) {
				primeCount++;
				LOG.trace("diagonal value {} is prime", (nsq - n2));
			}
			if (isPrime(nsq - 2 * n2, primes)) {
				LOG.trace("diagonal value {} is prime", (nsq - 2 * n2));
				primeCount++;
			}
			if (isPrime(nsq - 3 * n2, primes)) {
				LOG.trace("diagonal value {} is prime", (nsq - 3 * n2));
				primeCount++;
			}
			ratio = (float) primeCount / (float) totalCount;
			LOG.trace("n {} ratio is currently {} ", n, ratio);
			n++;
		} while (ratio >= 0.1);
		return (2 * n -1);
	}

	
	private boolean isPrime (final long n, final Set<Integer> primes) {
		if (n < maxPrime) {
			return  (primes.contains(new Integer ((int)n)));
		} else  if (n > (long)((long)maxPrime * maxPrime)) {
			throw new IllegalArgumentException();
		}
		int max = (int) Math.sqrt(n) + 1; 
		for (int p : primes ) {
			if (n % p == 0) {
				return false;
			}
			if (p > max) {
				break;
			}
		}
		return true;
	}
}
