package uk.org.landeg.projecteuler.problems;

import java.math.BigInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.ProblemDescription;

@Component
@Order(56)
public class Problem056 implements ProblemDescription<Integer>{
	@Override
	public String getTask() {
		return "Considering natural numbers of the form, ab, where a, b < 100, what is the maximum digital sum?";
	}

	@Override
	public String getDescribtion() {
		return "A googol (10100) is a massive number: one followed by one-hundred zeros; 100100 is almost unimaginably large: one followed by two-hundred zeros. Despite their size, the sum of the digits in each number is only 1";
	}

	@Override
	public Integer solve() {
		int count = 0;
		int sum = 0;
		for (int a = 1 ; a < 100 ; a++) {
			for (int b = 1 ; b < 100 ; b++) {
				String pow = BigInteger.valueOf(a).pow(b).toString();
				sum = 0;
				for (int idx = 0; idx < pow.length() ; idx++) {
					sum += (pow.charAt(idx) - '0');
					count = (sum > count) ? sum : count;
				}
			}
		}
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
