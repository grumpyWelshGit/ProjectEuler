package uk.org.landeg.projecteuler.problems;

import java.math.BigInteger;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.Mathlib;
import uk.org.landeg.projecteuler.ProblemDescription;

@Order(20)
@Component
public class Problem020 implements ProblemDescription<Integer>{

	@Override
	public String getTask() {
		return "Find the sum of the digits in the number 100!";
	}

	@Override
	public String getDescribtion() {
		return "and the sum of the digits in the number 10! is 3 + 6 + 2 + 8 + 8 + 0 + 0 = 27";
	}

	@Override
	public Integer solve() {
		final BigInteger factorial = Mathlib.bigFactorial(100);
		return Mathlib.digitSum(factorial);
	}
}
