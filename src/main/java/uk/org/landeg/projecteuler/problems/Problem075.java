package uk.org.landeg.projecteuler.problems;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.ProblemDescription;

@Component
@Order(75)
public class Problem075 implements ProblemDescription<Integer>{
	private static final Logger LOG = LoggerFactory.getLogger(Problem075.class);

	@Override
	public String getTask() {
		return "Given that L is the length of the wire, for how many values of L ≤ 1,500,000 can exactly one integer sided right angle triangle be formed";
	}

	@Override
	public String getDescribtion() {
		return "It turns out that 12 cm is the smallest length of wire that can be bent to form an integer sided right angle triangle in exactly one way, but there are many more examples"; 
	}

	static final int max = 1500000;
	private Set<Integer> primes;
	private Set<Triple> triplesUsed = new HashSet<>();
			
	@Override
	public Integer solve() {
		final int[] solutionCount = new int [max];
		final int mMax = 1 * (int) Math.sqrt(8l * max) /2;
		for (int m = 1 ; m <= mMax ; m++) {
			if (m %  100 == 0) {
				LOG.info("m {} " , m);
			}
			int nStart = (m % 2 == 0) ? 1 : 2;
			int nStep = 2;
			for (int n = nStart ; n < m ; n += nStep) {
				if (hcf(m,n) != 1) {
					continue;
				}
				final int c = n * n + m * m;
				final int a = m * m - n * n;
				final int b = 2 * m * n;
				final int p = a + b + c;
				if (p > max) {
					break;
				}
				boolean finished = false;
				int k=1;
				do {
					final int pp = k * p;
					if (pp == 24) {
						LOG.info("m {} n {} k {} a{} b{} c{}",m,n,k,a,b,c);
					}
					if (pp < max) {
						if (triplesUsed.add(new Triple(a*k,b*k,c*k))) {
							solutionCount[pp]++;
						}
						k++;
					} else {
						finished = true;
					}
				} while (!finished);
			}
		}
		for (int i : new int[] {12,24,30,36,40,48}) {
			LOG.info("{} {} ",i , solutionCount[i]);
		}
		
		final int count = (int) Arrays.stream(solutionCount).filter(x -> x == 1).count();
		LOG.info("Single solution coujnts {} ", count);
		return count;
	}
	
	private int hcf (int a, int b) {
		int lo = Math.min(a, b);
		int hi = Math.max(a, b);
		for (int i = lo ; i >= 1 ; i--) {
			if (hi % i == 0 && lo % i == 0) {
				return i;
			}
		}
		return 1;
	}
	/**
	 * @author andy
	 *
	 */
	private class Triple {
		private final int a;
		private final int b;
		private final int c;
		
		public Triple(int a, int b, int c) {
			super();
			this.a = a;
			this.b = b;
			this.c = c;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + a;
			result = prime * result + b;
			result = prime * result + c;
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
			Triple other = (Triple) obj;
			if (a != other.a)
				return false;
			if (b != other.b)
				return false;
			if (c != other.c)
				return false;
			return true;
		}

		
		
	}

}
