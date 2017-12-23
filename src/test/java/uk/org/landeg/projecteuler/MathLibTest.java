package uk.org.landeg.projecteuler;

import java.math.BigInteger;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class MathLibTest {
	@Test
	public void assertNumericLength () {
		Assert.assertEquals(1, Mathlib.obtainNumberLength(1));
		Assert.assertEquals(1, Mathlib.obtainNumberLength(0001));
		Assert.assertEquals(1, Mathlib.obtainNumberLength(9));
		Assert.assertEquals(2, Mathlib.obtainNumberLength(11));
		Assert.assertEquals(2, Mathlib.obtainNumberLength(99));
		Assert.assertEquals(6, Mathlib.obtainNumberLength(111111));
		Assert.assertEquals(6, Mathlib.obtainNumberLength(999999));
		Assert.assertEquals(8, Mathlib.obtainNumberLength(12345678));
		Assert.assertEquals(9, Mathlib.obtainNumberLength(123456789));
		Assert.assertEquals(10, Mathlib.obtainNumberLength(1234567890));
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
	
	@Test
	public void assertNumericDigits () {
		Assert.assertArrayEquals(new int[] {1,2,3,4,5}, Mathlib.digits(12345));
	}
	
	@Test
	public void assertDigitFrequencyAsExpected () {
		Assert.assertArrayEquals(new int[] {1,1,1,1,1,1,0,0,0,0}, Mathlib.digitFrequency(123450));
	}
	
	@Test
	public void assertTruncateRightAsExpected () {
		Assert.assertEquals(12345, Mathlib.truncateRight(112345));
		Assert.assertEquals(1, Mathlib.truncateRight(11));
		Assert.assertEquals(1, Mathlib.truncateRight(1));
	}
	
	@Test
	public void assertIntegerConcatination () {
		Assert.assertEquals(1234, Mathlib.concatinate(12, 34));
		Assert.assertEquals(37, Mathlib.concatinate(3, 7));
		Assert.assertEquals(73, Mathlib.concatinate(7, 3));
	}
	
	@Test
	public void assertPowiAsExpected () {
		Assert.assertEquals(10, Mathlib.powi(10, 1));
		Assert.assertEquals(100, Mathlib.powi(10, 2));
		Assert.assertEquals(100000, Mathlib.powi(10, 5));
	}

	@Test
	public void assertReplaceDigit () {
		Assert.assertEquals(12345, Mathlib.replaceDigit(12245, 2, 3));
	}
	
	@Test
	public void assertDigitalSumAsExpected () {
		Assert.assertEquals(6, Mathlib.digitalSum("1230000"));
		Assert.assertEquals(0, Mathlib.digitalSum("00000"));
	}
}
