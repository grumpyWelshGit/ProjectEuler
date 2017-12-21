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
@Order(71)
public class Problem073 implements ProblemDescription<Integer>{
	private static final Logger LOG = LoggerFactory.getLogger(Problem071.class);

	@Override
	public String getTask() {
		return "How many elements would be contained in the set of reduced proper fractions for d ≤ 1,000,000?\n";
	}

	@Override
	public String getDescribtion() {
		// TODO Auto-generated method stub
		return null;
	}

	private static final int limit = 12000;
	@Override
	public Integer solve() {
		final double ul = .5d;
		final double ll = 1d/3d;
		final Set<Integer> primes = PrimeLib.primes(limit);
		final Set<Fraction> fractions = new HashSet<>();
		final boolean[][] available = new boolean [limit+1][limit+1];
		for (boolean[] d : available) {
			Arrays.fill(d, true);
		}
		for (int d = 2 ; d <= limit ; d++) {
			LOG.debug("Solving for d={}", d);
			for (int n = d/3 ; n < d ; n++) {
				if (!available[n][d]) {
					break;
				}
				int nn = n;
				int dd = d;
				if (nn > 1) {
					for (final int i : primes) {
						while (nn % i == 0 && dd % i == 0) {
							nn /= i;
							dd /= i;
						}
						if (i >= nn || nn == 1) {
							break;
						}
					}
				}
				double eval = (double) nn / (double) dd;
				
				if (eval > ll && eval < ul) {
					final Fraction f = new Fraction(nn, dd);
					LOG.trace("match {}", f);
					fractions.add(f);
					int mult = 2;
					int ed;
					int en;
					double eeval;
					do {
						eeval = mult * eval;
						ed = dd * mult;
						en = nn * mult;
						if (ed < available.length) {
							LOG.trace("eliminating {}/{}", en, ed);
							available[ed][en] = false;
						}
						mult++;
					} while (eeval < ul);
				} else if (eval > ul) {
					break;
				}
			}
		}
		return fractions.size();
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
