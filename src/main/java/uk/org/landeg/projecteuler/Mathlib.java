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
}
