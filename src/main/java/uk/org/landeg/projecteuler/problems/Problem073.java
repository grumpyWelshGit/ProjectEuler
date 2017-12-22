package uk.org.landeg.projecteuler.problems;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.PrimeLib;
import uk.org.landeg.projecteuler.ProblemDescription;

@Component
@Order(73)
public class Problem073 implements ProblemDescription<Integer>{
	private static final Logger LOG = LoggerFactory.getLogger(Problem073.class);

	@Override
	public String getTask() {
		return "How many elements would be contained in the set of reduced proper fractions for d ≤ 1,000,000?\n";
	}

	@Override
	public String getDescribtion() {
		return null;
	}

	private static final int LIMIT_REAL = 12000;
	private static final int LIMIT_TEST = 32;
	private static final int LIMIT = LIMIT_REAL;
	
	
	@Override
	public Integer solve() {
		final Set<Integer> primes = PrimeLib.primes(LIMIT);
		final boolean[][] imProperFractions = new boolean[LIMIT + 1][LIMIT + 1];
		LOG.info("Selecting proper fractions");
		for (int d = 2; d <= LIMIT / 2 + 1; d++) {
			for (int n = 1 ; n <= d ; n++) {
				if (!imProperFractions[d][n]) {
					boolean finished = false;
					int mult = 2;
					int dd;
					do {
						dd = d * mult;
						if (dd < imProperFractions.length) {
							imProperFractions[dd][n * mult] = true;
						} else {
							finished = true;
						}
						mult++;
					} while (!finished);
				}
			}
		}
		final Set<Double> used = new HashSet<>();
		final double ll = 1d/3d;
		final double ul = 1d/2d;
		final int stats[] = new int[4];
		int count = 0;
		for (int d = 2; d <= LIMIT ; d++) {
			Arrays.fill (stats, 0);
			boolean dPrime = primes.contains(d);
			if (d%1000 == 0) {
				LOG.info("{}",d);
			}
			for (int n = 1 ; n < d ; n++) {
				stats[0]++;
				final double eval = (double) n / (double) d;
				if (eval >= ul) {
					break;
				}
				if (eval > ll) {
					if (dPrime) {
						LOG.trace("prime denominator, assuming proper {}/{}", n,d);
						count++;
					} else {
						if (imProperFractions[d][n]) {
							stats[1]++;
							LOG.trace("{}/{} flagged as improper - ignoring", n , d);
						}
						else if (true) {
							stats[2]++;
							LOG.trace("checked proper fraction {}/{}", n,d);
							count++;
						} else {
							stats[3]++;
							LOG.debug("rejected improper fraction {}/{}", n,d);
						}
					}
				}
			}
			if (d%1000 == 0) {
				LOG.info("stats : total {} | improper (skipped) {} | confirmed proper {} | rejected {}", stats[0],stats[1],stats[2], stats[3]);
			}
		}
		int  i = 0;
		LOG.info("fraction count {}", count);
		return count;
	}

	public boolean isProper (int a, int b, final Set<Integer> primes) {
		for (int p : primes) {
			if (a % p == 0 && b % p == 0) {
				return false;
			}
		}
		return true;
	}

	private static class Fraction{
		private int n;
		private int d;
		
		
		public Fraction(int n, int d) {
			super();
			this.n = n;
			this.d = d;
		}
		public int getN() {
			return n;
		}
		public void setN(int n) {
			this.n = n;
		}
		public int getD() {
			return d;
		}
		public void setD(int d) {
			this.d = d;
		}
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Fraction [n=").append(n).append(", d=").append(d).append("]");
			return builder.toString();
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + d;
			result = prime * result + n;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Fraction other = (Fraction) obj;
			if (d != other.d)
				return false;
			if (n != other.n)
				return false;
			return true;
		}
	}
}
