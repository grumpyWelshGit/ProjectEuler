package uk.org.landeg.projecteuler.problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.ProblemDescription;

@Component
@Order(64)
public class Problem064 implements ProblemDescription<Integer>{
	private static final Logger LOG = LoggerFactory.getLogger(Problem064.class);
	@Override
	public String getTask() {
		return "In the first one-thousand expansions, how many fractions contain a numerator with more digits than denominator?";
	}

	@Override
	public String getDescribtion() {
		return " is possible to show that the square root of two can be expressed as an infinite continued fraction";
	}

	@Override
	public Integer solve() {
		int a;
		int b;
		int oddPeriodCount = 0;
		List<Integer> convergents = new ArrayList<>();
		final List<ConvergentSolution> convergentSolutions = new ArrayList<>();
		
		int period;
		for (int root = 2 ; root <= 10000 ; root++) {
			period = 0;
			convergentSolutions.clear();
			convergents.clear();
			if (isPerfectSquare(root)) {
				continue;
			}
			int c0 = sqrtAsIn(root);
			int c = c0;
			a = 1;
			b = -c0;
			do {
				convergents.add(c);
				final ConvergentSolution convergent = new ConvergentSolution(a, b);
				if (convergentSolutions.contains(convergent)) {
					final int iLastSeen = convergentSolutions.lastIndexOf(convergent);
					period = convergentSolutions.size() - iLastSeen;
					LOG.debug("period {} : {}", root, period);
					if (period % 2 == 1) {
						oddPeriodCount++;
						break;
					}
				}
				convergentSolutions.add(convergent);
				LOG.trace("next convergent for {} : [{}] {} {}", root, c, a, b);
				c = a * (c0 -b) / (root - b * b);
				
				int n = a;
				int d = root - b*b;
				if (n > 1) {
					for (int i = 2 ; i <= Math.min(n, d) ; i++) {
						while (n % i == 0 && d % i == 0) {
							n /= i;
							d /= i;
							if (n == 1) {
								break;
							}
						}
					}
				}
				a = d;
				b = -b - d * c; 
			} while (period == 0);
			StringBuilder builder = new StringBuilder();
			if (LOG.isDebugEnabled()) {
				final AtomicReference<Character> sep = new AtomicReference<Character>();
				convergents.stream().map(i->Integer.toString(i)).forEach(s -> {
					if (sep.get() == null) {
						sep.set(',');
					} else {
						builder.append(sep.get());
					}
					builder.append(s);	
				});
				LOG.debug("{} : {}", root, builder);
			}
			
		}
		LOG.info("oddPeriodCount {} ", oddPeriodCount);
		return oddPeriodCount;
	}
	
	private boolean isPerfectSquare(int root) {
		final int sqrt = (int) Math.sqrt(root);
		return sqrt * sqrt == root;
	}

	final Map<Integer, Integer> sqrtCache = new HashMap<>();
	private int sqrtAsIn (final int n) {
		if (sqrtCache.containsKey(n)) {
			return sqrtCache.get(n);
		} else {
			final int sqrt = (int) Math.sqrt(n);
			sqrtCache.put(n, sqrt);
			return sqrt;
		}
	}
	
	static class ConvergentSolution {
		final int a;
		final int b;
		
		public ConvergentSolution(int a, int b) {
			super();
			this.a = a;
			this.b = b;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (obj == this) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (obj.getClass().equals(this.getClass())) {
				final ConvergentSolution other = (ConvergentSolution) obj;
				return this.a == other.a && this.b == other.b;
			}
			return false;
		}
		
		@Override
		public int hashCode() {
			return a * 97 + b * 51;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("ConvergentSolution [a=").append(a).append(", b=").append(b).append("]");
			return builder.toString();
		}
		
		
	}
}
