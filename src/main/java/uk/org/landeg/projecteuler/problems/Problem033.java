package uk.org.landeg.projecteuler.problems;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.ProblemDescription;

@Order(33)
@Component
public class Problem033 implements ProblemDescription<Long>{

	@Override
	public String getTask() {
		return "There are exactly four non-trivial examples of this type of fraction, less than one in value, and containing two digits in the numerator and denominator.\n" +
				"If the product of these four fractions is given in its lowest common terms, find the value of the denominator";
	}

	@Override
	public String getDescribtion() {
		return "Digit cancelling fractions";
	}

	@Override
	public Long solve() {
		int topProd = 1;
		int botProd = 1;
		for (int a = 1; a < 10 ; a++) {
			for (int b = 1; b < 10 ; b++) {
				for (int c = a; c < 10 ; c++) {
					if (a == c) {
						continue;
					}
					for (int d = 1; d < 10 ; d++) {
						if (d == b) {
							continue;
						}
						if (a == d || b == c ) {
							int top = 10*a + b;
							int bot = 10*c + d;
							final double value = (double) top/ (double) bot;
							if (value == (double) a/ (double) d || value == (double) b/(double) c) {
								System.out.println(top + "/" + bot);
								topProd *= top;
								botProd *= bot;
							}
						}
					}
				}
			}
		}
		final int[] prod = new int[]{topProd,botProd};
		reduce(prod);
		return (long)prod[1];
	}

	private void reduce (final int[] numbers) {
		int cm = 2;
		boolean finished = false;
		do {
			boolean isMultiple = true;
			do {
				for (int idx = 0 ; idx < numbers.length ; idx++) {
					if (numbers[idx] % cm != 0) {
						isMultiple = false;
					}
				}
				if (isMultiple) {
					for (int idx = 0 ; idx < numbers.length ; idx++) {
						numbers[idx] = numbers[idx] / cm;
					}
				}
				for (int idx = 0 ; idx < numbers.length ; idx++) {
					if (cm >= numbers[idx]) {
						finished = true;
					}
				}
			} while (isMultiple);
			cm++;
		} while (!finished);
	}
}
