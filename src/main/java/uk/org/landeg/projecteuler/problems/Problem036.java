package uk.org.landeg.projecteuler.problems;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.Mathlib;
import uk.org.landeg.projecteuler.ProblemDescription;

@Order(36)
@Component
public class Problem036 implements ProblemDescription<Long>{

	@Override
	public String getTask() {
		return "Find the sum of all numbers, less than one million, which are palindromic in base 10 and base 2.";
	}

	@Override
	public String getDescribtion() {
		return "The decimal number, 585 = 10010010012 (binary), is palindromic in both bases";
	}

	@Override
	public Long solve() {
		long sum = 0;
		for (int n = 1 ; n < 1000000 ; n+=2) {
			if (Mathlib.isPalindrome(n)) {
				final char[] binChars = Integer.toBinaryString(n).toCharArray();
				boolean isBase2Palindrome = true;
				for (int ch  = 0 ; ch <= binChars.length / 2 ; ch++) {
					if (binChars[ch] != binChars[binChars.length - 1 - ch]) {
						isBase2Palindrome = false;
					}
				}
				if (isBase2Palindrome) {
					sum += n;
				}
			}
		}
		return sum;
	}

}
