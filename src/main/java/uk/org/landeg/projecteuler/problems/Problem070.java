package uk.org.landeg.projecteuler.problems;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import uk.org.landeg.projecteuler.Mathlib;
import uk.org.landeg.projecteuler.PrimeLib;
import uk.org.landeg.projecteuler.ProblemDescription;

@Component
@Order(70)
public class Problem070 implements ProblemDescription<Integer>{
	private static final Logger LOG = LoggerFactory.getLogger(Problem070.class);
	
	@Override
	public String getTask() {
		return "Euler's Totient function, φ(n) [sometimes called the phi function], is used to determine the number of numbers less than n which are relatively prime to n. For example, as 1, 2, 4, 5, 7, and 8, are all less than nine and relatively prime to nine, φ(9)=6";
	}

	@Override
	public String getDescribtion() {
		return "Find the value of n, 1 < n < 107, for which φ(n) is a permutation of n and the ratio n/φ(n) produces a minimum";
	}

	
	@Override
	public Integer solve() {
		LOG.info("From observation, phi(n)/n occurs when n = p1*p2, where p1 and p2 are distinct primes");
		LOG.info("phi(n)= n * PI(1-1/p) for distinct prime factors, p");
		LOG.info("phi(n)=n * ( (p1-1)/p1 * (p2-1)/p2 ) ");
		LOG.info("phi(n)=n * (p1p2 - p1 - p2 + 1)/ (p1*p2)");
		LOG.info("phi(n)= (n - p1 - p2 + 1)");
		final AtomicInteger minNRaio = new AtomicInteger(0);
		final AtomicReference<Double> minRatio = new AtomicReference<>(1000d);
		final int max = 10000000;
		final int maxPrime = 10000000/2;
		final List<Integer> primesAsList = PrimeLib.primes(maxPrime)
				.stream()
				.filter(x -> x > 1000)
				.collect(Collectors.toList());
		for (int i = 0 ; i < primesAsList.size() ; i++) {
			int pi = primesAsList.get(i);
			Mathlib.digitFrequency(pi);
			for (int j = i ; j < primesAsList.size() ; j++) {
				int pj = primesAsList.get(j);
				int candidate = pi * pj;
				if (candidate > max || candidate < 0) {
					break;
				}
				int phi = candidate - pi - pj + 1;
				if (phi < 0) {
					break;
				}
				int[] candidateDigits = Mathlib.digitFrequency(candidate);
				int[] totientDigits = Mathlib.digitFrequency(phi);
				if (Arrays.equals(candidateDigits, totientDigits)) {
					final double ratio = (double)candidate / (double)phi;
					if (ratio < minRatio.get()) {
						minNRaio.set(candidate);
						minRatio.set(ratio);
						LOG.info("{} = {}*{} {} {}",candidate, pi,pj,phi,ratio);
					}
				}
			}
		}
		
		return minNRaio.get();
	}
}
