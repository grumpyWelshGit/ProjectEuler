package uk.org.landeg.projecteuler.problems;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.Mathlib;
import uk.org.landeg.projecteuler.ProblemDescription;

@Component
@Order(52)
public class Problem052 implements ProblemDescription<Integer>{
	private static final Logger LOG = LoggerFactory.getLogger(Problem052.class);
	@Override
	public String getTask() {
		return "It can be seen that the number, 125874, and its double, 251748, contain exactly the same digits, but in a different order.";
	}

	@Override
	public String getDescribtion() {
		// TODO Auto-generated method stub
		return "Find the smallest positive integer, x, such that 2x, 3x, 4x, 5x, and 6x, contain the same digits.";
	}

	@Override
	public Integer solve() {
		final int max = 10000000;
		int result = 0;
		for (int idx = 1 ; idx<max ; idx++) {
			boolean match = true;
			int[] f1 = Mathlib.digitFrequency(idx);
			for (int idx2 = 2 ; idx2 <= 6; idx2++){
				int[] f = Mathlib.digitFrequency(idx2 * idx);
				if (!Arrays.equals(f, f1)) {
					match = false;
					break;
				}
			}
			if (match) {
				result = idx;
				break;
			}
		}
		return result;
	}
}
