package uk.org.landeg.projecteuler.problems;

import java.util.Arrays;


import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.PandigitalGenerator;
import uk.org.landeg.projecteuler.ProblemDescription;

@Order(43)
@Component
@Slf4j
public class Problem043 implements ProblemDescription<Long> {

	
	@Override
	public String getTask() {
		return "Find the sum of all 0 to 9 pandigital numbers with this property";
	}

	@Override
	public String getDescribtion() {
		return "The number, 1406357289, is a 0 to 9 pandigital number because it is made up of each of the digits 0 to 9 in some order, but it also has a rather interesting sub-string divisibility property";
	}

	@Override
	public Long solve() {
		long n =0;
		long result = 0;
		final PandigitalGenerator generator = new PandigitalGenerator(new int [] {1,0,2,3,4,5,6,7,8,9});
		while (generator.hasNext()) {
			final int[] digits = generator.next();
			
			if (log.isDebugEnabled()) {
				log.trace("checking {}", Arrays.toString(digits));
			}
			if (digits[3] % 2 == 0) {
				if ((digits[2] + digits[3] + digits[4]) % 3 == 0) {
					if (digits[5] ==0 || digits[5]==5) {
						if ((digits[4] * 100 + digits[5] * 10 + digits[6]) % 7 == 0) {
							if ((digits[5] * 100 + digits[6] * 10 + digits[7]) % 11 == 0) {
								if ((digits[6] * 100 + digits[7] * 10 + digits[8]) % 13 == 0) {
									if ((digits[7] * 100 + digits[8] * 10 + digits[9]) % 17 == 0) {
										n = 0;
										for (final int digit : digits) {
											n*= 10;
											n += digit;
										}
										result += n;
									}
								}
							}
						}
					}
				}
			}
		} 

		return result;
	}
}
