package uk.org.landeg.projecteuler.problems;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.Mathlib;
import uk.org.landeg.projecteuler.ProblemDescription;

@Component
@Order(6)
public class Problem006 implements ProblemDescription<Long>{
	final int DEFAULT_START_VALUE = 100;

	int startValue = DEFAULT_START_VALUE;

	@Override
	public String getTask() {
		return "Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum.";
	}

	@Override
	public String getDescribtion() {
		// TODO Auto-generated method stub
		return "The sum of the squares of the first ten natural numbers is," +
			"12 + 22 + ... + 102 = 385"
			+ "The square of the sum of the first ten natural numbers is,"
 			+ "(1 + 2 + ... + 10)2 = 552 = 3025";
	}

	@Override
	public Long solve() {
		final int sum = Mathlib.sum(startValue);
		final int sumSquare = sum * sum;
		int squareSum = 0;
		for (int idx = 1 ; idx <= startValue ; idx++) {
			squareSum += idx * idx;
		}
		return (long) sumSquare - (long) squareSum;
	}
	
}
