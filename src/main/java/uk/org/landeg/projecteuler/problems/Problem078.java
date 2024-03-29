package uk.org.landeg.projecteuler.problems;

import java.util.concurrent.atomic.AtomicLong;


import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.ProblemDescription;

@Slf4j
@Component
@Order(78)
public class Problem078 implements ProblemDescription<Long>{

	AtomicLong combinationCount = new AtomicLong();
	
	@Override
	public String getTask() {
		return "How many different ways can one hundred be written as a sum of at least two positive integers?";
	}

	@Override
	public String getDescribtion() {
		return " It is possible to write five as a sum in exactly six different ways";
	}
	private static final int pentagonCacheSize = 2000;
	private final long partitions [] = new long [1000000];
	private final long pentagonNumbers[] = new long[pentagonCacheSize];
	@Override
	public Long solve() {
		int n = 0;
		partitions[n] = 1;
		partitions[++n] = 1;
		do {
			n++;
			int sign = -1;
			for (int k = 1 ; k < n ; k++) {
				sign *= -1;
				long pk = pentagon(k);
				if (pk > n) {
					break;
				}
				int nextTermId = n - (int) pk;
				partitions[n] += (nextTermId >= 0) ? sign * partitions[nextTermId] : 0;
				nextTermId = n - (int) pk-k;
				partitions[n] += (nextTermId >= 0) ? sign * partitions[nextTermId] : 0;
			}
			partitions[n] = partitions[n] % 1000000;
		} while (partitions[n] % 1000000 != 0);
		log.info("N={}" ,n);
		return (long)n;
	}
	
	private long pentagon (final int n) {
		if (n < pentagonCacheSize && pentagonNumbers[n] > 0) {
			return pentagonNumbers[n];
		} 
		final long p = (long)n * (3l * n - 1l) / 2l;
		if (n < pentagonCacheSize) {
			pentagonNumbers[n] = p;
		}
		return p;
	}
}
