package uk.org.landeg.projecteuler.problems;

import java.util.Set;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.PrimeLib;
import uk.org.landeg.projecteuler.ProblemDescription;

@Order(27)
@Component
public class Problem027 implements ProblemDescription<Integer>{

	@Override
	public String getTask() {
		return "Find the product of the coefficients, aa and bb, for the quadratic expression "
				+ "that produces the maximum number of primes for consecutive values of |n|, starting with n=0";
	}

	@Override
	public String getDescribtion() {
		return "n^2 + an + b produces primes";
	}

	@Override
	public Integer solve() {
		final Set<Integer> primes = PrimeLib.primes(100000);
		int primeCount = 0;
		int maxPrimeCount = 0;
		int maxProduct = 0;
//		for (final int b : primes) {
		for (int b = -999 ; b < 1000 ; b++) {
			for (int a = -999 ; a <= 999 ; a++) {
//		int b = 1601; {
//			int a = -79; {
			
				int n = 0;
				int candidate = 0;
				primeCount = 0;
				boolean finished = false;
				do {
					candidate = n * n + a * n + b;
					
					if (!primes.contains(candidate)) {
//						System.out.println(candidate);
						finished = true;
					} else {
						primeCount++;
						n++;
					}
				} while (!finished);
				if (primeCount > maxPrimeCount) {
//					System.out.println(a + "\t" + b + "\t" + (a*b) + "\t" + primeCount);
					maxPrimeCount = primeCount;
					maxProduct = a * b;
				}
			}
		}
		return maxProduct;
	}

}
