package uk.org.landeg.projecteuler.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.Mathlib;
import uk.org.landeg.projecteuler.ProblemDescription;

@Order(43)
@Component
public class Problem043a implements ProblemDescription<Long> {
	private static final Logger LOG = LoggerFactory.getLogger(Problem043a.class);
	final Map<Integer,List<Integer>> multiples = new LinkedHashMap<>();
	
	@Override
	public String getTask() {
		return "Find the sum of all 0 to 9 pandigital numbers with this property";
	}

	@Override
	public String getDescribtion() {
		return "The number, 1406357289, is a 0 to 9 pandigital number because it is made up of each of the digits 0 to 9 in some order, but it also has a rather interesting sub-string divisibility property";
	}

	final int[] primes = new int[]{2,3,5,7,11,13,17};

	@Override
	public Long solve() {
		long result = 0;
		initialiseMultiples();
		final int[] digits = new int[10];
		for (int d17 : multiples.get(17)) {
//			d17=289;
			Arrays.fill(digits, -1);
			digits[9] = d17 % 10;
			digits[8] = (d17 / 10) % 10;
			digits[7] = (d17 / 100);
			for (int d13 : multiples.get(13)) {
				digits[6] = digits[5] = digits[4] = digits[3] = digits[2] = digits [1] = digits[0] = -1;  
				if (d13 % 100 == d17 /10 && !contains(digits, d13 / 100)) {
					digits[6] = d13 / 100;
					for (int d11 : multiples.get(11)) {
						digits[5] = digits[4] = digits[3] = digits[2] = digits [1] = digits[0] = -1;
						if (d11 % 100 == d13 /10 && !contains(digits, d11 / 100)) {
							digits[5] = d11 / 100;
							for (int d7 : multiples.get(7)) {
								digits[4] = digits[3] = digits[2] = digits [1] = digits[0] = -1;
								if (d7 % 100 == d11 /10 && !contains(digits, d7 / 100)) {
									digits[4] = d7 / 100;
									for (int d5 : multiples.get(5)) {
										digits[3] = digits[2] = digits [1] = digits[0] = -1;
										if (d5 % 100 == d7 /10 && !contains(digits, d5 / 100)) {
											digits[3] = d5 / 100;
											for (int d3 : multiples.get(3)) {
												digits[2] = digits [1] = digits[0] = -1;
												if (d3 % 100 == d5 /10 && !contains(digits, d3 / 100)) {
													digits[2] = d3 / 100;
													for (int d2 : multiples.get(2)) {
														digits [1] = digits[0] = -1;
														if (d2 % 100 == d3 /10 && !contains(digits, d2 / 100)) {
															digits[0] = -1;
															digits[1] = d2 / 100;
															completeMissingDigit(digits);
															LOG.debug("digits used {} ", digits);
															LOG.debug("candidate {} {} {} {} {} {} {}", d2,d3,d5, d7, d11, d13, d17 );
															long theNumber = arrayToLong(digits);
															LOG.info("Discovered {}", theNumber);
															result += theNumber;
														}
													}
												}
											}
										}
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
	
	private void initialiseMultiples () {
		for (int idx = 0 ; idx < primes.length ; idx++) {
			int multiple = primes[idx];
			multiples.put(primes[idx],new ArrayList<>());
			do {
				boolean repeatNumbers = false;
				int[] frequency = Mathlib.digitFrequency(multiple);
				for (int digitFrequecy : frequency) {
					if (digitFrequecy > 1) {
						repeatNumbers = true;
						break;
					}
				}
				if (!repeatNumbers) {
					multiples.get(primes[idx]).add(multiple);
				}
				multiple += primes[idx];
			} while (multiple < 1000);
			LOG.debug("initialised multiples sequence {} {}", primes[idx], multiples.get(primes[idx]));
		}
		LOG.debug("Initialising number array{}", multiples);

	}
	
	private boolean contains (final int[] digits, final int n) {
		for (int digit : digits) {
			if (digit == n) {
				return true;
			}
		}
		return false;
	}
	
	private long arrayToLong (int [] digits) {
		long result = 0;
		for (int d : digits) {
			result *= 10;
			result += d;
		}
		return result;
	}
	private void completeMissingDigit (final int[] digits) {
		final int[] expectedDigits = new int[] {0,1,2,3,4,5,6,7,8,9};
		for (int expeced : expectedDigits) {
			if (!contains(digits, expeced)) {
				digits[0] = expeced;
			}
		}
	}

	public static void main (String args[]) {
		final long start = System.nanoTime();
		System.out.println(new Problem043a().solve());
		final long end = System.nanoTime();
		System.out.println("solved in " + (end - start) / 1000 / 1000 + "ms");
	}
}
