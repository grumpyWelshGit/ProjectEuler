package uk.org.landeg.projecteuler.problems;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.Mathlib;
import uk.org.landeg.projecteuler.ProblemDescription;

@Order(38)
@Component
public class Problem038 implements ProblemDescription<Long>{

	@Override
	public String getTask() {
		return "What is the largest 1 to 9 pandigital 9-digit number that can be formed as the concatenated product of an integer with (1,2, ... , n) where n > 1?";
	}

	@Override
	public String getDescribtion() {
		return "By concatenating each product we get the 1 to 9 pandigital, 192384576. We will call 192384576 the concatenated product of 192 and (1,2,3)";
	}

	@Override
	public Long solve() {
		int maxPandigital = 0;
		final List<Integer> products = new ArrayList<>();
		
		final BaseGenerator generator = new BaseGenerator();
		while (generator.hasNext()) {
			int base = generator.next();
			products.clear();
			int mult = 1;
			int totalLen = 0;
			do {
				int multiple = base * mult;
				products.add(multiple);
				totalLen += Mathlib.length(multiple);
				mult++;
			} while (totalLen < 9);
			if (totalLen == 9) {
				if (Mathlib.isPandigital(products, 9)) {
					int pandigital = 0;
					for (final int pan : products) {
						pandigital = Mathlib.concatinate(pandigital, pan);
					}
					if (pandigital > maxPandigital) {
						maxPandigital = pandigital;
						System.out.println(maxPandigital + ": " +  products);
					}
					maxPandigital =  (pandigital > maxPandigital) ? pandigital : maxPandigital;
				}
			}
		}
		return (long)maxPandigital;
	}

	static class BaseGenerator implements Iterator<Integer> {
		private int current = 90;

		@Override
		public boolean hasNext() {
			return current <= 91234;
		}

		@Override
		public Integer next() {
			current++;
			if (current == 98) current = 912;
			if (current == 987) current = 9123;
			if (current == 9876) current = 91234;
			return current;
		}
		
	}
}
