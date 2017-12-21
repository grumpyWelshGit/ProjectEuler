package uk.org.landeg.projecteuler.problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.ProblemDescription;

@Component
@Order(61)
public class Problem061 implements ProblemDescription<Long>{
	private static final Logger LOG = LoggerFactory.getLogger(Problem061.class);

	@Override
	public String getTask() {
		return "Find the sum of the only ordered set of six cyclic 4-digit numbers for which each polygonal type: triangle, square, pentagonal, hexagonal, heptagonal, and octagonal, is represented by a different number in the set";
	}

	@Override
	public String getDescribtion() {
		return "The ordered set of three 4-digit numbers: 8128, 2882, 8281, has three interesting properties";
	}

	
	final Map<Integer, Function<Integer, Integer>> functions = new HashMap<>();
	final Map<Integer, Set<PolyNumber>> numbersStart = new HashMap<>();
	final List<PolyNumber> numbers = new ArrayList<>(); 
	Integer result = 0;

	@Override
	public Long solve() {
		functions.put(3, x -> 1 * x * (x + 1) / 2);
		functions.put(4, x -> 1 * x * x);
		functions.put(5, x -> 1 * x * (3 * x - 1) / 2);
		functions.put(6, x -> 1 * x * (2 * x - 1));
		functions.put(7, x -> 1 * x * (5 * x - 3) / 2);
		functions.put(8, x -> 1 * x * (3 * x - 2));
		
		int t;
		functions
			.entrySet()
			.stream()
			.forEach(f ->{
				IntStream.range(1, 10000)
				.forEach(i -> {
					int v = f.getValue().apply(i);
					if (v > 1000 && v <= 9999) {
						final PolyNumber n = new PolyNumber(i, v, f.getKey());
						numbers.add(n);
					}
				});
			});
		numbers.stream()
			.forEach(poly -> {
				Set<PolyNumber> startsWith = numbersStart.get(poly.startsWith);
				if (startsWith == null) {
					startsWith = new HashSet<>();
					numbersStart.put(poly.startsWith, startsWith);
				}
				startsWith.add(poly);
			});
		
		discoverCyclicSet(null);
		return (long)result;
	}
	
	private void discoverCyclicSet (final List<PolyNumber> set) {
//		if (result != null) { return; }
		if (set == null) {
			discoverCyclicSet(new ArrayList<>());
			return;
		}
		if (set.size() == 6) {
			int sum = set.stream().mapToInt(PolyNumber::getValue).sum();
			
			if (set.get(set.size() - 1).endsWith == set.get(0).startsWith) {
				LOG.debug("Set discovered [{}] {}", sum, set);
				result = sum;
				return;
			}
			
		}
		if (set.isEmpty()) {
			for (PolyNumber poly : numbers) {
				set.add(poly);
				discoverCyclicSet(set);
				set.remove(poly);
			}
		} else {
			final int endsWith = set.get(set.size() - 1).endsWith;
			final Set<PolyNumber> candidates = numbersStart.get(endsWith);
			if (candidates != null) {
				candidates.stream()
				.filter(c -> !set.contains(c))
				.filter(c -> {
					for (PolyNumber p : set) {
						if (p.order == c.order) {
							return false;
						}
					}
					return true;
				})
				.forEach(poly -> {
						set.add(poly);
						discoverCyclicSet(set);
						set.remove(poly);
				});
			}
		}
		
	}
	
	int getNumberStart (final long n, final int max) {
		long val = n;
		do {
			val /= 10;
		} while (val >= max);
		return (int) val;
	}
	
	static class PolyNumber { 
		final int n;
		final int value;
		final int startsWith;
		final int endsWith;
		final int order;

		public PolyNumber(int n, int value, int order) {
			super();
			this.n = n;
			this.value = value;
			this.order = order;
			this.startsWith = value/100;
			this.endsWith = value % 100;
		}

		public int getN() {
			return n;
		}

		public int getValue() {
			return value;
		}

		public int getStartsWith() {
			return startsWith;
		}

		public int getEndsWith() {
			return endsWith;
		}

		public int getOrder() {
			return order;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + endsWith;
			result = prime * result + n;
			result = prime * result + order;
			result = prime * result + startsWith;
			result = prime * result + value;
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
			PolyNumber other = (PolyNumber) obj;
			if (endsWith != other.endsWith)
				return false;
			if (n != other.n)
				return false;
			if (order != other.order)
				return false;
			if (startsWith != other.startsWith)
				return false;
			return (value == other.value);
		}

		@Override
		public String toString() {
			return String.format("P%d,%d : %d", order, n, value);
		}
		
		
	}
}
