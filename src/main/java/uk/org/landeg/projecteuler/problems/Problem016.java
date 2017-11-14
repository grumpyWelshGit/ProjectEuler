package uk.org.landeg.projecteuler.problems;

import java.math.BigInteger;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.ProblemDescription;

@Order(16)
@Component
public class Problem016 implements ProblemDescription<Long>{

	@Override
	public String getTask() {
		return "What is the sum of the digits of the number 2^1000?";
	}

	@Override
	public String getDescribtion() {
		return "2^15 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.";
	}

	@Override
	public Long solve() {
		final BigInteger TEN = BigInteger.valueOf(10l);
		int sum = 0;
		BigInteger n = BigInteger.valueOf(2l).pow(1000);
		boolean finished = false;
		do {
			sum += n.mod(TEN).intValue();
			n = n.divide(TEN);
			if (n.compareTo(BigInteger.ZERO) == 0) {
				finished = true;
			}
		} while (!finished);
		return (long) sum;
	}

}
