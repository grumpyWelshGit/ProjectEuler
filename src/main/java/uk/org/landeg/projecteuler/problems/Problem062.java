package uk.org.landeg.projecteuler.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.Mathlib;
import uk.org.landeg.projecteuler.ProblemDescription;

@Component
@Order(62)
@Slf4j
public class Problem062 implements ProblemDescription<Long>{


	@Override
	public String getTask() {
		return "Find the smallest cube for which exactly five permutations of its digits are cube";
	}

	@Override
	public String getDescribtion() {
		return "The cube, 41063625 (3453), can be permuted to produce two other cubes: 56623104 (3843) and 66430125 (4053). In fact, 41063625 is the smallest cube which has exactly three permutations of its digits which are also cube";
	}


	final List<Long> cubes = new ArrayList<>();
	final Map<DigitContainer, List<Long>> permutations = new HashMap<>();
	Long result = null;

	@Override
	public Long solve() {
		int lastLen = 0;
		int len;
		for (int idx = 1 ; idx < 10000 ; idx++) {
			final Long cube = (long) idx * idx * idx; 
			cubes.add(cube);
			final int[] digits = Mathlib.digitFrequency(cube);
			final DigitContainer container = new DigitContainer(digits);
			List<Long> permutation = permutations.get(container);
			if (permutation == null) {
				permutation = new ArrayList<>();
			}
			else { 
				if (permutation.size() == 4) {
					result = permutation.get(0);
					log.debug("possible result {} " , result);
					log.debug("current set {} {}", permutation, cube);
					break;
				}
			}
			permutation.add(cube);
			permutations.put(container, permutation);
			len = (int) Math.log10(cube);
			if (len > 0 & len != lastLen) {
				permutations.clear();
			}
			lastLen = len;
		}
		return result;
	}
	
	static class DigitContainer {
		final int[] digits;
		public DigitContainer(final int[] digits) {
			this.digits = digits;
		}
		
		@Override
		public boolean equals(Object obj) {
			boolean equals = false;
			if (obj == this) {
				return true;
			}
			if (obj != null && obj.getClass().equals(this.getClass())) {
				return Arrays.equals(this.digits, ((DigitContainer)obj).digits);
			}
			return equals;
		}
		
		@Override
		public int hashCode() {
			int hashCode = 0;
			hashCode += digits[0] * 7;
			hashCode += digits[1] * 11;
			hashCode += digits[2] * 13;
			hashCode += digits[3] * 17;
			hashCode += digits[4] * 23;
			hashCode += digits[5] * 27;
			hashCode += digits[6] * 29;
			hashCode += digits[7] * 31;
			hashCode += digits[8] * 37;
			hashCode += digits[9] * 41;
			return hashCode;
		}
	}
}
