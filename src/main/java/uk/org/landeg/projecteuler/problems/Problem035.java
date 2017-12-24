package uk.org.landeg.projecteuler.problems;

import java.util.Set;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.PrimeLib;
import uk.org.landeg.projecteuler.ProblemDescription;

@Order(35)
@Component
public class Problem035 implements ProblemDescription<Integer>{

	@Override
	public String getTask() {
		return "How many circular primes are there below one million?";
	}

	@Override
	public String getDescribtion() {
		return "The number, 197, is called a circular prime because all rotations of the digits: 197, 971, and 719, are themselves prime";
	}

	@Override
	public Integer solve() {
		final int max = 1000000;
		int count = 0;
		final Set<Integer> primes = PrimeLib.primes(max);
		for (final int idx : primes) {
			int factor = 1;
			int rotations = 0;
			while (factor < idx) {
				factor *= 10;
				rotations++;
			}
		
			int rotated = idx;
			boolean isCircular = true;
			
			if (idx > 10) {
				do {
					int digit = rotated % 10;
					rotated += factor * digit;
					rotated /= 10;
					if (!primes.contains(rotated)) {
						isCircular = false;
						break;
					}
				} while (--rotations > 0);
			}
			if (isCircular) {
				count++;
			}
		}
		return count;
	}

}
