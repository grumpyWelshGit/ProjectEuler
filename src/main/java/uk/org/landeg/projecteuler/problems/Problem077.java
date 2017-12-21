package uk.org.landeg.projecteuler.problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.PrimeLib;
import uk.org.landeg.projecteuler.ProblemDescription;

@Component
@Order(77)
public class Problem077 implements ProblemDescription<Long>{

	private static final Logger LOG = LoggerFactory.getLogger(Problem076.class);
	private static final int MAX_TARGET = 100;
	private static final int MAX_SOLUTIONS = 5000;
	private int count = 0;
	@Override
	public String getTask() {
		return "What is the first value which can be written as the sum of primes in over five thousand different ways?";
	}
	@Override
	public String getDescribtion() {
		return "It is possible to write ten as the sum of primes in exactly five different ways";
	}
	@Override
	public Long solve() {
		int result = 0;
		final Set<Integer> primes = PrimeLib.primes(MAX_TARGET * 10);
		final List<Integer> primeList = new ArrayList<>(primes);
		for (int i = 2; i < MAX_TARGET ; i++) {
			count = 0;
			solutionSearch(primeList, i);
			LOG.debug("{} {} ", i, count);
			if (count > MAX_SOLUTIONS) {
				result = i;
				break;
			}
		}
		return (long)result;
	}

	public void solutionSearch (
			final List<Integer> primes, 
			final int target) {
		final List<Integer> solution = new ArrayList<>();
		solutionSearch(primes,  solution, target);
	}
	
	
	public void solutionSearch (
			final List<Integer> primes, 
			final List<Integer> solution, 
			final int target) {
		final int sum = solution.stream().mapToInt(x->x).sum();
		final int diff = target - sum;
		final int lowest = solution.stream().mapToInt(x->x).min().orElse(target);
		
		if (sum > target) {
			return;
		}
		if (diff == 0) {
			LOG.trace("{}", solution);
			count++;
			return;
		}

		// find the next value that does not exceed either diff or lowest
		int pid = 0;
		final int maxPrime = Math.min(lowest, diff);
		int nextPrime;
		while ((nextPrime = primes.get(pid)) < maxPrime) {
			pid++;
		}
		if (nextPrime > diff) {
			pid--;
		}

		for (int i = pid ; i >= 0 ; i--) {
			solution.add(primes.get(i));
			solutionSearch(primes, solution, target);
			solution.remove(solution.size()-1);
		}
	}
}
