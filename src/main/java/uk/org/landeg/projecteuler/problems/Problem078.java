package uk.org.landeg.projecteuler.problems;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.MathLibTest;
import uk.org.landeg.projecteuler.Mathlib;
import uk.org.landeg.projecteuler.ProblemDescription;

@Component
@Order(78)
public class Problem078 implements ProblemDescription<Long>{

	private static final Logger LOG = LoggerFactory.getLogger(Problem078.class);
	AtomicLong combinationCount = new AtomicLong();
	
	@Override
	public String getTask() {
		return "How many different ways can one hundred be written as a sum of at least two positive integers?";
	}

	@Override
	public String getDescribtion() {
		return " It is possible to write five as a sum in exactly six different ways";
	}

	private Map<Long, Long> partitions = new HashMap<>(); 
	@Override
	public Long solve() {
		long n = 0;
		partitions.put(n, 1l);
		partitions.put(++n, 1l);
		long partition;
		do {
			n++;
			partition = 0;
			int sign = -1;
			partitions.put(n, 0l);
			for (int k = 1 ; k < n ; k++) {
				sign *= -1;
				long pk = pentagon(k);
				long nextTerm = 0;
				if (partitions.size() > pk) {
					nextTerm += sign * partitions.getOrDefault(n-pk, 0l);
					nextTerm += sign * partitions.getOrDefault(n-pk-k, 0l);
				}
				partition += nextTerm;
			}
			partitions.put(n, partition % 1000000);
		} while (partition % 1000000 != 0);
		LOG.info("N={}" ,n);
		return (long)n;
	}
	
	private Map<Integer, Long> P = new HashMap<>();
	private long pentagon (final int n) {
		final long p = (long)n * (3l * n - 1l) / 2l;
		return p;
	}
}
