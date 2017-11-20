package uk.org.landeg.projecteuler.problems;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.ProblemDescription;

@Order(40)
@Component
public class Problem040 implements ProblemDescription<Integer>{

	@Override
	public String getTask() {
		return "An irrational decimal fraction is created by concatenating the positive integers: 0.123456789101112131415161718192021..";
	}

	@Override
	public String getDescribtion() {
		return "If dn represents the nth digit of the fractional part, find the value of the following expression. d1 × d10 × d100 × d1000 × d10000 × d100000 × d1000000";
	}

	@Override
	public Integer solve() {
		final StringBuilder builder = new StringBuilder();
		int n = 1;
		do {
			builder.append(n++);
		} while (builder.length() <= 1000000);
		int product = 1;
		int loc = 1;
		do {
			product *= Integer.parseInt(new String (""+ builder.charAt(loc - 1)));
			loc *= 10;
		} while (loc <= 1000000);
		return product;
	}

}
