package uk.org.landeg.projecteuler.problems;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.Mathlib;
import uk.org.landeg.projecteuler.ProblemDescription;

@Component
@Order(30)
public class Problem030 implements ProblemDescription<Long>{

	@Override
	public String getTask() {
		return "Find the sum of all the numbers that can be written as the sum of fifth powers of their digits";
	}

	@Override
	public String getDescribtion() {
		return "Surprisingly there are only three numbers that can be written as the sum of fourth powers of their digits";
	}

	@Override
	public Long solve() {
		long sumOfPowerDigits = 0;
		for (int idx =  2 ; idx < 1000000 ; idx++) {
			final int[] digits = Mathlib.digits(idx);
			int sum = 0;
			for (int digit : digits) {
				sum += digit * digit * digit * digit * digit;
			}
			if (sum == idx) {
				System.out.println(idx);
				sumOfPowerDigits += idx;
			}
		}
		return sumOfPowerDigits;
	}

}
