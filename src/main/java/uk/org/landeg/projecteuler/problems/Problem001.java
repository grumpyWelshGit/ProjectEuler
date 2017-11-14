package uk.org.landeg.projecteuler.problems;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.Mathlib;
import uk.org.landeg.projecteuler.ProblemDescription;

@Order(1)
@Component
public class Problem001 implements ProblemDescription <Integer>{

	@Override
	public String getTask() {
		return "Find the sum of all the multiples of 3 or 5 below 1000";
	}

	@Override
	public String getDescribtion() {
		return "If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.";
	}

	@Override
	public Integer solve() {
		int sum = Mathlib.sum(999 / 3) * 3 + Mathlib.sum(999 / 5) * 5 - Mathlib.sum(999/15) * 15;
		return sum;
	}
}
