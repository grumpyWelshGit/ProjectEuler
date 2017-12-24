package uk.org.landeg.projecteuler.problems;

import java.math.BigInteger;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.ProblemDescription;

@Component
@Order(15)
public class Problem015 implements ProblemDescription<Long>{

	@Override
	public String getTask() {
		return "How many such routes are there through a 20×20 grid?";
	}

	@Override
	public String getDescribtion() {
		return "Starting in the top left corner of a 2×2 grid, and only being able to move to the right and down, there are exactly 6 routes to the bottom right corner";
	}

	@Override
	public Long solve() {
		final int n = 40;
		final int r = 20;

		final BigInteger nFact = factorial(n);
		final BigInteger rFact = factorial(r);
		final BigInteger nMinusRFact = factorial(n - r);
		final BigInteger result = nFact.divide(rFact).divide(nMinusRFact);
		return result.longValue();
	}
	
	private BigInteger factorial (final int n) {
		BigInteger f = BigInteger.valueOf((long)n);
		for (int factor = n - 1 ; factor > 1 ; factor --) {
			f = f.multiply(BigInteger.valueOf((long) factor));
		}
		return f;
	}
	
}
