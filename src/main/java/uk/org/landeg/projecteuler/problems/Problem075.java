package uk.org.landeg.projecteuler.problems;

import java.util.Arrays;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.PrimeLib;
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
	private final boolean[][] MN_CACHE = new boolean[max][];
			
	@Override
	public Integer solve() {
		final int[] solutionCount = new int [max];
		primes = PrimeLib.primes(max);
		final int mMax = (int)(Math.sqrt(max) / 4d);
		for (int m = 1 ; m < mMax ; m++) {
			int nMax = (max - 2 * m * m) / 2 / m;
			MN_CACHE[m] = new boolean[nMax];
		}

		for (int m = 1 ; m < mMax ; m++) {
			int nMax = (max - 2 * m * m) / 2 / m;
			if (m % 1000 == 0)  {
				LOG.debug("m = {}", m);
			}
			int nStart = 1;
			int nStep = 1;
			
			if (m % 2 == 1) {
				nStart = 2;
				nStep = 2;
			}
			for (int n = nStart ; n < nMax && n < m; n += nStep) {
				final int c = n * n + m * m;
				final int a = m * m - n * n;
				final int b = 2 * m * n;
				final int p = a + b + c;
				
				LOG.trace("p : a,b,c = {} {} -> {} : {} {} {}", m,n,p,a,b,c);

				if (MN_CACHE[m][n]) {
					continue;
				}

				if (p > max / 2 || p < 0) {
					break;
				}

				int k = 1;
				int pp = p;
				
				int nn = n; int mm = m;
				do {
						MN_CACHE[mm][nn] = true;
					mm += m;
					nn += n;
				} while (mm < MN_CACHE.length - 1 && nn < MN_CACHE[mm].length - 1);

				do {
					if (pp < max) {
						solutionCount[pp]++;
						k++;
					} else {
						break;
					}
					LOG.trace("pp a,b,c = {} {} {} {}", pp,a,b,c);
					if (pp == 120) {
						if (LOG.isInfoEnabled()) {
							LOG.info("120 :: pp k,a,b,c = {} {} -> {} {} {} : {} {} {} {} {}", m,n,a,b,c,pp,a*k,b*k,c*k);
						}
					}
					pp += p;
				} while (pp < max && pp > 0);
			}
		}
		LOG.info("120 : {}",solutionCount[120]);
		LOG.info("12 : {}",solutionCount[12]);
		LOG.info("24 : {}",solutionCount[24]);
		LOG.info("30 : {}",solutionCount[30]);
		LOG.info("36 : {}",solutionCount[36]);
		LOG.info("40 : {}",solutionCount[40]);
		LOG.info("48 : {}",solutionCount[48]);
		
		int count = (int) Arrays.stream(solutionCount).filter(x->x==1).count();
		LOG.info("single solution values : " + count);
		return count;
	}
	
	private boolean isCoprime (final int a, int b) {
		if (a == b || a== 0 || b == 0) {
			return false;
		}
		if (primes.contains(a) && primes.contains(b)) {
			return true;
		}
		int lo = Math.min(a, b);
		int hi = Math.max(a, b);
		if (hi % lo == 0) {
			return false;
		}

		for (int p : primes) {
			if (a % p == 0 && b % p == 0) {
				return false;
			}
		}
		return true;
	}
}
