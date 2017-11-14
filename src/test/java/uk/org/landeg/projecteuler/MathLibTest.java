package uk.org.landeg.projecteuler;

import java.math.BigInteger;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class MathLibTest {
	@Test
	public void assertNumericLength () {
		Assert.assertEquals(1, Mathlib.obtainNumberLength(1));
		Assert.assertEquals(1, Mathlib.obtainNumberLength(9));
		Assert.assertEquals(2, Mathlib.obtainNumberLength(11));
		Assert.assertEquals(2, Mathlib.obtainNumberLength(99));
		Assert.assertEquals(6, Mathlib.obtainNumberLength(111111));
		Assert.assertEquals(6, Mathlib.obtainNumberLength(999999));
	}
	
	@Test
	public void assertPalindromeTestAsExpected () {
		Assert.assertTrue(Mathlib.isPalindrome(123321));
		Assert.assertFalse(Mathlib.isPalindrome(123322));
	}
	
	@Test
	public void assertPrimeFactorsAsExpected () {
		final Set<Integer> primes = PrimeLib.primes((int)Math.sqrt(76576500));
		Assert.assertEquals(4, Mathlib.divisorCount(21l, primes));
		Assert.assertEquals(2, Mathlib.divisorCount(71l, primes));
		int divs = 0;
		for (int idx = 1 ; idx <= 120 ; idx++) {
			if (120 % idx == 0) {
				divs++;
			}
		}
		Assert.assertEquals(divs, Mathlib.divisorCount(120, primes));
		divs = 0;
		for (int idx = 1 ; idx <= 76576500 ; idx++) {
			if (76576500 % idx == 0) {
				divs++;
			}
		}
		Assert.assertEquals(divs, Mathlib.divisorCount(76576500, primes));
		
	}
	
	@Test
	public void assertFactorialAsExpected () {
		Assert.assertEquals(1, Mathlib.factorial(0));
		Assert.assertEquals(1, Mathlib.factorial(1));
		Assert.assertEquals(2, Mathlib.factorial(2));
		Assert.assertEquals(6, Mathlib.factorial(3));
	}
	
	@Test
	public void maxFactorialRange () {
		for (int idx = 1; idx < 200 ; idx++) {
			final long factorial = Mathlib.factorial(idx);
			if (factorial < 0) {
				System.out.println("overflow at " + idx);
				break;
			}
		}
	}
	
	@Test
	public void assertBigDigitSumAsExpected () {
		Assert.assertEquals(1, Mathlib.digitSum(BigInteger.valueOf(100000l)));
		Assert.assertEquals(21, Mathlib.digitSum(BigInteger.valueOf(123456)));
		Assert.assertEquals(0, Mathlib.digitSum(BigInteger.valueOf(0)));
		Assert.assertEquals(1, Mathlib.digitSum(BigInteger.valueOf(1)));
	}
	
	@Test
	public void assertDivisors () {
		final Set<Integer> primes = PrimeLib.primes(1000);
		final Set<Integer> divsors100 = Mathlib.divisors(100, primes);
		Assert.assertTrue(divsors100.contains(1));
		Assert.assertTrue(divsors100.contains(100));
		Assert.assertTrue(divsors100.contains(2));
		Assert.assertTrue(divsors100.contains(4));
		Assert.assertTrue(divsors100.contains(5));
		Assert.assertTrue(divsors100.contains(10));
		Assert.assertTrue(divsors100.contains(20));
		Assert.assertTrue(divsors100.contains(25));
		Assert.assertTrue(divsors100.contains(50));
		Assert.assertTrue(divsors100.size() == 9);
		
		final Set<Integer> divsors23 = Mathlib.divisors(23, primes);
		Assert.assertTrue(divsors23.size() == 2);
		Assert.assertTrue(divsors23.contains(1));
		Assert.assertTrue(divsors23.contains(23));
	}


	@Test
	public void assertDivisors2 () {
		final Set<Integer> primes = PrimeLib.primes(2000);
		final int test = 1184;
		final Set<Integer> divisors = Mathlib.properDivisors(test, primes);
		System.out.println(divisors);
		System.out.println(Mathlib.sum(divisors));
	}
}
