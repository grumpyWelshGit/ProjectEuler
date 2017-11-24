package uk.org.landeg.projecteuler;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class Mathlib {
	public static int sum (final int max) {
		final int sum = (int)(((1 + max) * ((double) max / 2.0d)));
		return sum;
	}
	
	public static long sum (final long max) {
		final long sum = (long)(((1 + max) * ((double) max / 2.0d)));
		return sum;
	}
	
	public static boolean isPalindrome (final int candidate) {
		final List<Integer> digits = new ArrayList<>();
		int value = candidate;
		do {
			digits.add(value % 10);
			value /= 10;
		} while (value > 0);
		
		boolean isPalindrome = true;
		for (int idx  = 0 ; idx < digits.size() / 2 + 1; idx++) {
			if (digits.get(idx) != digits.get(digits.size() - idx -1)) {
				isPalindrome = false;
			}
		}
		
		return isPalindrome;
	}

	public static boolean isPalindrome (final long candidate) {
		final List<Integer> digits = new ArrayList<>();
		long value = candidate;
		do {
			digits.add((int)(value % 10));
			value /= 10;
		} while (value > 0);
		
		boolean isPalindrome = true;
		for (int idx  = 0 ; idx < digits.size() / 2 + 1; idx++) {
			if (digits.get(idx) != digits.get(digits.size() - idx -1)) {
				isPalindrome = false;
			}
		}
		
		return isPalindrome;
	}
	
	public static int obtainNumberLength (final int value) {
		int len = 0;
		int currentValue = value;
		do {
			currentValue /= 10;
			len++;
		} while (currentValue > 0);
		return len;
	}
	
	public static int divisorCount (final long value, final Collection<Integer> primes) {
		final Map<Long, Integer> primeFactors = primeFactors(value, primes);
		AtomicInteger valueHolder = new AtomicInteger(1);
		primeFactors.entrySet().stream().forEach(entry -> valueHolder.set(valueHolder.get() * (entry.getValue() + 1)));
		return valueHolder.get();
	}
	
	public static Map<Long, Integer> primeFactors (
			final long value,
			final Collection<Integer> primes) {
		long currentValue = value;
		final Map<Long, Integer> primeFactors = new HashMap<>();
		if (primes.contains(value)) {
			primeFactors.put(value, 1); 
		} else {
			for (int prime : primes) {
				int power = 0;
				while (currentValue % prime == 0) {
					currentValue /= prime;
					power++;
				}
				if (power > 0) {
					primeFactors.put(new Long(prime), power);
				}
				if (currentValue == 1) {
					break;
				}
			}
		}
		return primeFactors;
	}
	
	public static long factorial (final int n) {
		long nCurrent = (long) n;
		long factorial = 1;
		if (n == 0) {
			return 1;
		}
		do {
			factorial *=  nCurrent--;
		} while (nCurrent > 1);
		return factorial;
	}
	
	public static BigInteger bigFactorial (final int n) {
		BigInteger factorial;
		
		if (n < 20) {
			factorial = BigInteger.valueOf(factorial(n));
		} else {
			int val = 20;
			factorial = BigInteger.valueOf(factorial(val++));
			do {
				factorial = factorial.multiply(BigInteger.valueOf(val++));
			} while (val <= n);
		}
		return factorial;
	}
	
	public static int digitSum (final BigInteger val) {
		BigInteger n = new BigInteger(val.toString());
		final BigInteger TEN = BigInteger.valueOf(10L);
		int sum = 0;
		do {
			sum += n.mod(TEN).intValue();
			n = n.divide(TEN);
		} while (n.compareTo(BigInteger.ZERO) > 0);
		return sum;
	}
	
	public static Set<Integer> properDivisors (final int n, final Collection<Integer> primes) {
		final Set<Integer> divisors = new HashSet<>();
		divisors.add(1);
		if (!primes.contains(n)) {
			final int maxScan = (int)Math.sqrt(n) + 1;
			for (int prime : primes) {
				int mult = prime;
				while (mult < maxScan) {
					if (n % mult == 0 ) {
						divisors.add(mult);
						divisors.add(n / mult);
					}
					mult += prime;
				}
				if (prime > maxScan) {
					break;
				}
			}
		}
		return divisors;
	}

	public static Set<Integer> divisors (final int n, final Collection<Integer> primes) {
		final Set<Integer> divisors = properDivisors(n, primes);
		divisors.add(n);
		return divisors;
	}
	
	public static int sum (final Collection<Integer> numbers) {
		int sum = 0;
		for (int n : numbers) {
			sum += n;
		}
		return sum;
	}
	
	public static int[] digits (final int n) {
		final int max[] = new int [20];
		int val = n;
		int idx = 0;
		do {
			max[idx] = val % 10;
			val /= 10;
			idx++;
		} while (val > 0);
		idx--;
		final int digits[] = new int[idx+1];
		do {
			digits[digits.length - idx -1] = max[idx];
		} while (idx-- > 0);
		return digits;
	}
	
	public static int[] digitFrequency (final int n) {
		final int digits[] = new int[10];
		int currentN = n;
		do {
			digits[currentN % 10]++;
			currentN /= 10;
		} while (currentN > 0);
		return digits;
	}

	public static int[] digitFrequency (final long n) {
		final int digits[] = new int[10];
		long currentN = n;
		do {
			digits[(int) (currentN % 10)]++;
			currentN /= 10;
		} while (currentN > 0);
		return digits;
	}
	
	public static int length (final int n) {
		if (n < 10) {return 1;}
		if (n < 100) {return 2;}
		if (n < 1000) {return 3;}
		if (n < 10000) {return 4;}
		if (n < 100000) {return 5;}
		if (n < 1000000) {return 6;}
		if (n < 10000000) {return 7;}
		if (n < 100000000) {return 8;}
		if (n < 1000000000) {return 9;}
		else throw new IllegalArgumentException("Number n is out of range");
	}
	
	public static int truncateLeft (final int n) {
		return n / 10;
	}
	
	public static int truncateRight (final int n) {
		int truncated = 0;
		int multiplier = 1;
		int value = n;
		
		do {
			truncated += multiplier * (value % 10);
			value /= 10;
			multiplier *= 10;
		} while (value > 9);
		
		return truncated;
	}
	
	public static int powi (final int base, final int power) {
		int pow = power;
		int result = base;
		for (int p = 1; p < pow ; p++) {
			result *= base;
		}
		return result;
	}

	public static int concatinate (final int n, final int m) {
		int concatinated = n;
		concatinated *= powi (10, (int) Math.log10(m) + 1);
		concatinated += m;
		return concatinated;
	}
	
	
	public static boolean isPandigital (
			final int start, 
			final int n, 
			final int base) {
		final int[] digits = digitFrequency(n);
		return isPandigital(digits, start, base);
	}

	public static boolean isPandigital (final int n, final int base) {
		final int[] digits = digitFrequency(n);
		return isPandigital(digits, base);
	}

	public static boolean isPandigital (final int[] digits, final int start, final int base) {
		if (start == 1) {
			if (digits[0] > 0) {
				return false;
			}
		}
		for (int d = 1 ; d <= base ; d++) {
			if (digits[d] != 1) {
				return false;
			}
		}
		return true;
	}

	public static boolean isPandigital (final int[] digits, final int base) {
		return isPandigital(digits, 1, base);
	}
	
	public static boolean isPandigital (Iterable<Integer> numbers, int base) {
		final int[] combinedDigits = new int [10];
		for (int n : numbers) {
			final int[] digits = digitFrequency(n);
			for (int idx = 0 ; idx < combinedDigits.length ; idx++) {
				combinedDigits[idx] += digits[idx];
			}
		}
		return isPandigital(combinedDigits, base);
	}
	
	public static int replaceDigit (int n, int order, int newval) {
		int sub = n / Mathlib.powi(10, order + 1) % 10 * Mathlib.powi(10, order);
		return n - sub + newval * Mathlib.powi(10, order);
	}

}
