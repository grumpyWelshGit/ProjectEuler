package uk.org.landeg.projecteuler;

import java.math.BigInteger;
import java.util.Set;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MathLibTest {
  @Test
  void assertNumericLength() {
		assertEquals(1, Mathlib.obtainNumberLength(1));
		assertEquals(1, Mathlib.obtainNumberLength(0001));
		assertEquals(1, Mathlib.obtainNumberLength(9));
		assertEquals(2, Mathlib.obtainNumberLength(11));
		assertEquals(2, Mathlib.obtainNumberLength(99));
		assertEquals(6, Mathlib.obtainNumberLength(111111));
		assertEquals(6, Mathlib.obtainNumberLength(999999));
		assertEquals(8, Mathlib.obtainNumberLength(12345678));
		assertEquals(9, Mathlib.obtainNumberLength(123456789));
		assertEquals(10, Mathlib.obtainNumberLength(1234567890));
	}
	
  @Test
  void assertPalindromeTestAsExpected() {
		assertTrue(Mathlib.isPalindrome(123321));
		assertFalse(Mathlib.isPalindrome(123322));
	}
	
  @Test
  void assertPrimeFactorsAsExpected() {
		final Set<Integer> primes = PrimeLib.primes((int)Math.sqrt(76576500));
		assertEquals(4, Mathlib.divisorCount(21l, primes));
		assertEquals(2, Mathlib.divisorCount(71l, primes));
		int divs = 0;
		for (int idx = 1 ; idx <= 120 ; idx++) {
			if (120 % idx == 0) {
				divs++;
			}
		}
		assertEquals(divs, Mathlib.divisorCount(120, primes));
		divs = 0;
		for (int idx = 1 ; idx <= 76576500 ; idx++) {
			if (76576500 % idx == 0) {
				divs++;
			}
		}
		assertEquals(divs, Mathlib.divisorCount(76576500, primes));
		
	}
	
  @Test
  void assertFactorialAsExpected() {
		assertEquals(1, Mathlib.factorial(0));
		assertEquals(1, Mathlib.factorial(1));
		assertEquals(2, Mathlib.factorial(2));
		assertEquals(6, Mathlib.factorial(3));
	}
	
  @Test
  void maxFactorialRange() {
		for (int idx = 1; idx < 200 ; idx++) {
			final long factorial = Mathlib.factorial(idx);
			if (factorial < 0) {
				System.out.println("overflow at " + idx);
				break;
			}
		}
	}
	
  @Test
  void assertBigDigitSumAsExpected() {
		assertEquals(1, Mathlib.digitSum(BigInteger.valueOf(100000l)));
		assertEquals(21, Mathlib.digitSum(BigInteger.valueOf(123456)));
		assertEquals(0, Mathlib.digitSum(BigInteger.valueOf(0)));
		assertEquals(1, Mathlib.digitSum(BigInteger.valueOf(1)));
	}
	
  @Test
  void assertDivisors() {
		final Set<Integer> primes = PrimeLib.primes(1000);
		final Set<Integer> divsors100 = Mathlib.divisors(100, primes);
		assertTrue(divsors100.contains(1));
		assertTrue(divsors100.contains(100));
		assertTrue(divsors100.contains(2));
		assertTrue(divsors100.contains(4));
		assertTrue(divsors100.contains(5));
		assertTrue(divsors100.contains(10));
		assertTrue(divsors100.contains(20));
		assertTrue(divsors100.contains(25));
		assertTrue(divsors100.contains(50));
		assertTrue(divsors100.size() == 9);
		
		final Set<Integer> divsors23 = Mathlib.divisors(23, primes);
		assertTrue(divsors23.size() == 2);
		assertTrue(divsors23.contains(1));
		assertTrue(divsors23.contains(23));
	}


  @Test
  void assertDivisors2() {
		final Set<Integer> primes = PrimeLib.primes(2000);
		final int test = 1184;
		final Set<Integer> divisors = Mathlib.properDivisors(test, primes);
		System.out.println(divisors);
		System.out.println(Mathlib.sum(divisors));
	}
	
  @Test
  void assertNumericDigits() {
		assertArrayEquals(new int[] {1,2,3,4,5}, Mathlib.digits(12345));
	}
	
  @Test
  void assertDigitFrequencyAsExpected() {
		assertArrayEquals(new int[] {1,1,1,1,1,1,0,0,0,0}, Mathlib.digitFrequency(123450));
	}
	
  @Test
  void assertTruncateRightAsExpected() {
		assertEquals(12345, Mathlib.truncateRight(112345));
		assertEquals(1, Mathlib.truncateRight(11));
		assertEquals(1, Mathlib.truncateRight(1));
	}
	
  @Test
  void assertIntegerConcatination() {
		assertEquals(1234, Mathlib.concatinate(12, 34));
		assertEquals(37, Mathlib.concatinate(3, 7));
		assertEquals(73, Mathlib.concatinate(7, 3));
	}
	
  @Test
  void assertPowiAsExpected() {
		assertEquals(10, Mathlib.powi(10, 1));
		assertEquals(100, Mathlib.powi(10, 2));
		assertEquals(100000, Mathlib.powi(10, 5));
	}

  @Test
  void assertReplaceDigit() {
		assertEquals(12345, Mathlib.replaceDigit(12245, 2, 3));
	}
	
  @Test
  void assertDigitalSumAsExpected() {
		assertEquals(6, Mathlib.digitalSum("1230000"));
		assertEquals(0, Mathlib.digitalSum("00000"));
	}
	
  @Test
  void assertGcd() {
	  assertEquals(1, Mathlib.gcd(5, 7));
	  assertEquals(1, Mathlib.gcd(1, 7));
	  assertEquals(2, Mathlib.gcd(2, 8));
	  assertEquals(3, Mathlib.gcd(6, 9));
	  assertEquals(2, Mathlib.gcd(10, 8));
	}
}
