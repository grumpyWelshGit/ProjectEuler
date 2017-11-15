package uk.org.landeg.projecteuler.problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.PrimeLib;
import uk.org.landeg.projecteuler.ProblemDescription;

@Order(26)
@Component
public class Problem026 implements ProblemDescription<Integer>{

	@Override
	public String getTask() {
		return "Find the value of d < 1000 for which 1/d contains the longest recurring cycle in its decimal fraction part";
	}

	@Override
	public String getDescribtion() {
		return "It can be seen that 1/7 has a 6-digit recurring cycle";
	}

	@Override
	public Integer solve() {
		final Set<Integer> primes = PrimeLib.primes(1000);
		int maxCycleSize = 0;
		int nMaxCycleSize = 0;
		for (final Integer prime : primes) {
			if (prime < 500) {
				continue;
			}
			int n = 1;
			int divisor = prime;
			int divResult;
			int mutlResult;
			int max = 5000;
			int iteration = 0;
			int cycleSize = 0;
			final List<Integer> remainers = new ArrayList<>();
			boolean finished = false;
			do {
				divResult = n / divisor; // 1/20 = 0
				mutlResult = divResult * divisor; // 0 * 20 = 0
				n -= mutlResult; // n = 10
				n *= 10;    // n = 10;
				if (remainers.contains(n)) {
					cycleSize = remainers.size() - remainers.indexOf(n);
					finished = true;
				}
				remainers.add(n);
			} while (!finished && n > 0 && iteration++ < max);
			if (cycleSize > maxCycleSize) {
				nMaxCycleSize = prime;
				maxCycleSize = cycleSize;
			}
		}
		
		
		return nMaxCycleSize;
	}
	
}
