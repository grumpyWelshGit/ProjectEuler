package uk.org.landeg.projecteuler.problems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.Mathlib;
import uk.org.landeg.projecteuler.ProblemContext;
import uk.org.landeg.projecteuler.ProblemDescription;

@Order(4)
@Component
public class Problem004 implements ProblemDescription<Integer>{
	private static final Logger LOG = LoggerFactory.getLogger(Problem003.class);

	@Autowired
	private ProblemContext context;

	@Override
	public String getTask() {
		return "Find the largest palindrome made from the product of two 3-digit numbers";
	}

	@Override
	public String getDescribtion() {
		return " palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99";
	}

	@Override
	public Integer solve() {
		// assume the palindrome begins (and therefore ends...) with 9.
		// there are only two ways to do this : 3*3 and 1*9
		final int[][] startConditions = {
				{993,993},
				{991,999}
		};
		int highestPalindrome = 0;
		for (int[] start : startConditions) {
			int a = start[0];
			int b = start[1];
			int product;
			do {
				a = b;
					do {
						product = a * b;
						if (highestPalindrome == 0 || product > highestPalindrome) {
							if (Mathlib.isPalindrome(product)) {
								if (product > highestPalindrome) {
									highestPalindrome = product;
									LOG.debug("a = {}, b = {}", a,b);
								}
							}
						}
					} while ((a-=10) > 500);
			} while ((b -= 10) > 500);
		}

		return highestPalindrome;
	}

}
