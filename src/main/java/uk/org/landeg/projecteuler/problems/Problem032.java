package uk.org.landeg.projecteuler.problems;

import java.util.HashSet;
import java.util.Set;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.Mathlib;
import uk.org.landeg.projecteuler.ProblemDescription;

@Component
@Order(32)
public class Problem032 implements ProblemDescription<Integer>{

	@Override
	public String getTask() {
		return "Find the sum of all products whose multiplicand/multiplier/product identity can be written as a 1 through 9 pandigital";
	}

	@Override
	public String getDescribtion() {
		return "The product 7254 is unusual, as the identity, 39 × 186 = 7254, containing multiplicand, multiplier, and product is 1 through 9 pandigital";
	}

	@Override
	public Integer solve() {
		int sum = 0;
		final Set<Long> products = new HashSet<>();
		for (int a = 1 ; a < 2500 ; a++) {
			int[] digitsa = Mathlib.digitFrequency(a);
			if (isCandidate(digitsa)) 
			{
				for (int b = a; b < 9000 ; b++) {
					int[] digitsb = Mathlib.digitFrequency(b);
					if (isCandidate(digitsb)) 
					{
						long product = a * b;
						
						if (product > 9876) {
							break;
						}
						int [] digitsProd = Mathlib.digitFrequency(product);
						if (isCandidate(digitsProd)) {
							boolean isPandigital = true;
							for (int idx = 1 ; idx <= 9 ; idx++) {
								if (digitsa[idx] + digitsb[idx] + digitsProd[idx] != 1) {
									isPandigital = false;
									break;
								}
							}
							{
							if (isPandigital)
								if (products.add(product)) {
									sum += product;
								}
							}
						}
					}
				}
			}
		}
		return sum;
	}
	
	private boolean isCandidate (final int[] digits) {
		if (digits[0] > 0) return false;
		for (int idx = 1; idx <=9 ; idx++) {
			if (digits[idx] > 1) {
				return false;
			}
		}
		return true;
	}
}
