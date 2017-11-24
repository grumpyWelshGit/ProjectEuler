package uk.org.landeg.projecteuler.problems;

import java.math.BigInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.ProblemDescription;

@Component
@Order(55)
public class Problem055 implements ProblemDescription<Integer>{
	private static final Logger LOG = LoggerFactory.getLogger(Problem055.class);
	@Override
	public String getTask() {
		return "How many Lychrel numbers are there below ten-thousand?";
	}

	@Override
	public String getDescribtion() {
		return "If we take 47, reverse and add, 47 + 74 = 121, which is palindromic";
	}

	@Override
	public Integer solve() {
		int count = 0;
		boolean isLychrel;
		BigInteger idx = BigInteger.valueOf(1l);
		do {
			LOG.debug("testing {}", idx);
			isLychrel = true;
			BigInteger val = idx;
			for (int it = 0 ; it < 50 ; it++) {
				val = val.add(new BigInteger(reverse(val.toString())));
				LOG.trace("current val {} ", val);
				if (val.toString().equals(reverse(val.toString()))) {
					isLychrel = false;
					break;
				}
			}
			if (isLychrel) {
				LOG.debug("{} is a Lychrel number");
				count++;
			} else {
				LOG.debug("{} is NOT a Lychrel number");
			}
			idx = idx.add(BigInteger.ONE);
		} while (idx.intValue() < 10000);
		return count;
	}

	long reverseNumber (final long n) {
		long reversed = 0;
		long value = n;
		do {
			reversed *= 10;
			reversed += value % 10;
			value /= 10;
		} while (value > 0);
		return reversed;
	}
	
	String reverse (final String str) {
		final char[] chars = new char[str.length()];
		for (int idx = str.length() - 1 ; idx >= 0 ; idx--) {
			chars[idx] = str.charAt(str.length() - 1 - idx);
		}
		return new String (chars);
	}
}
