package uk.org.landeg.projecteuler.problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.Mathlib;
import uk.org.landeg.projecteuler.ProblemDescription;

@Component
@Order(74)
@Slf4j
public class Problem074 implements ProblemDescription<Integer>{


	@Override
	public String getTask() {
		return "How many chains, with a starting number below one million, contain exactly sixty non-repeating terms\n"; 
	}

	@Override
	public String getDescribtion() {
		return "The number 145 is well known for the property that the sum of the factorial of its digits is equal to 145:\n";
	}

	private static final Map<Long, Integer> stats = new TreeMap<>();
	private static final Map<Integer, Integer> chainLengths = new HashMap<>();
	private static final boolean gatherStats = false;

	final int[] factorials = new int[10];

	@Override
	public Integer solve() {
		
		for (int i = 0 ; i<= 9 ; i++) {
			factorials[i] = (int) Mathlib.factorial(i);
		}
		final int[] lengthOfChain = new int[3000000];
		final int[] fullChainLengths = new int[100];
		final List<Long> history = new ArrayList<>();

		int count60 = 0;
		long nn;
		int cachedLen = 0;
		for (int n = 2 ; n < 1000000 ; n++) {
			boolean finished = false;
			nn = n;
			history.clear();
			cachedLen = 0;
			int len = 1;
			do {
				history.add(nn);
				nn = factorialSum(nn);
				if (lengthOfChain[(int)nn] > 0) {
					cachedLen = lengthOfChain[(int) nn];
					len += cachedLen;
					finished = true;
				} else {
					if (gatherStats) {
						stats.put(nn, stats.getOrDefault(nn, 0) + 1);
					}
					if (history.contains(nn)) {
						finished = true;
					} else {
						len++;
					}
				}
			} while (!finished);
			if (lengthOfChain[history.get(0).intValue()] == 0) {
				for (int i = history.size() - 1 ; i >= 0 ; i--) {
					lengthOfChain[history.get(i).intValue()] = cachedLen + history.size() - i;
				}
			}
			fullChainLengths[len]++;
			
			if (gatherStats) {
				chainLengths.put(len / 10, chainLengths.getOrDefault(len / 10, 0) + 1);
			}

			log.trace("{} ({}) : {}", n, len, history);
			if (len == 60) {
				count60++;
			}
		}
		if (log.isDebugEnabled()) {
			for (int i = 0 ; i < fullChainLengths.length ; i++) {
				if (fullChainLengths[i] > 0) {
					log.debug("{}    : {}", i, fullChainLengths[i]);
				}
			}
		}
		log.info("Numbers with chains of length 60 = {}", count60);
		if (gatherStats) {
			stats.entrySet().stream()
				.filter(e -> e.getValue() > 1000)
				.sorted((b,a) -> a.getValue().compareTo(b.getValue()))
				.forEach(e -> log.info("{}   {}->{}", e.getValue(), e.getKey(), factorialSum(e.getKey())));
			log.info("chain length frequencies");
			chainLengths.entrySet().stream()
				.sorted((a,b) -> a.getKey().compareTo(a.getKey()))
				.forEach(a -> log.info("{}  {}", a.getKey() * 10 - a.getKey() * 10 + 9, a.getValue()));
		}
		return count60;
	}

	public void showChain (final long n) {
		final List<Long> chain = new ArrayList<>();
		long nn = n;
		boolean finished = false;
		do {
			chain.add(nn);
			nn = factorialSum(nn);
			if (chain.contains(nn)) {
				finished = true;
			}
		} while (!finished);
		log.debug("{}", chain);
	}
	private static final Map<Long,Long> FS_CACHE = new HashMap<>();

	final int cacheMax = 50000;
	public long factorialSum (long n) {
		if (n < cacheMax && FS_CACHE.containsKey(n)) {
			return FS_CACHE.get(n);
		}
		long sum = 0;
		long nn = n;
		do {
			sum += factorials[(int)nn % 10];
			nn /= 10;
		} while (nn >= 1);
		if (nn < cacheMax) {
			FS_CACHE.put(n, sum);
		}
		return sum;
	}
}
