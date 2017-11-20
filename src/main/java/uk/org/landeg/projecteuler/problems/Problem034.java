package uk.org.landeg.projecteuler.problems;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.Mathlib;
import uk.org.landeg.projecteuler.ProblemDescription;

@Order(34)
@Component
public class Problem034 implements ProblemDescription<Long>{

	@Override
	public String getTask() {
		return "Find the sum of all numbers which are equal to the sum of the factorial of their digits";
	}

	@Override
	public String getDescribtion() {
		return "145 is a curious number, as 1! + 4! + 5! = 1 + 24 + 120 = 145";
	}

	@Override
	public Long solve() {
		final int [] factorials = new int[10];
		int f = 1;
		factorials[0] = 1;
		for (int idx = 1 ; idx <= 9 ; idx++) {
			factorials[idx] = (f *= idx);
		}
		long result = 0;
		for (int idx = 5 ; idx < 50000; idx++) {
			final int[] digits = Mathlib.digits(idx);
			int sum = 0;
			for (int digit : digits) {
				sum += factorials[digit];
			}
			if (sum == idx) {
				System.out.println(sum);
				result += sum;
			}
		}
		
		return result;
	}
}
