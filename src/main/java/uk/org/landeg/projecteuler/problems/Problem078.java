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

import uk.org.landeg.projecteuler.ProblemDescription;

@Component
@Order(78)
public class Problem078 implements ProblemDescription<Long>{

	private static final Logger LOG = LoggerFactory.getLogger(Problem078.class);
	AtomicLong combinationCount = new AtomicLong();
	final Map<Integer, Integer> partitions = new HashMap<>();
	
	@Override
	public String getTask() {
		return "How many different ways can one hundred be written as a sum of at least two positive integers?";
	}

	@Override
	public String getDescribtion() {
		return " It is possible to write five as a sum in exactly six different ways";
	}

	private BigInteger[][] subPartitions;
	@Override
	public Long solve() {
		final int target = 100000;
		
		subPartitions = new BigInteger[target+1][];
		subPartitions[1] = new BigInteger[] {BigInteger.ZERO,BigInteger.ONE};
		for (int currentTarget = 2 ; currentTarget <= target ; currentTarget++) {
			if (currentTarget % 200 == 0) {
				LOG.info("Solving for {}", currentTarget);
			}
			subPartitions[currentTarget] = new BigInteger[currentTarget + 1];
			for (int i = 1 ; i < currentTarget ; i++) {
				int diff = currentTarget - i;
				BigInteger sum = BigInteger.ZERO;
				for (int j = 1 ; j <= i && j< subPartitions[diff].length ; j++) {
					sum = sum.add(subPartitions[diff][j]);
				}
				subPartitions[currentTarget][i] = sum;
			}
			subPartitions[currentTarget][currentTarget] = BigInteger.ONE;
			LOG.debug(Arrays.toString(subPartitions[currentTarget]));
			BigInteger sum = BigInteger.ZERO;
			for (BigInteger p : subPartitions[currentTarget]) {
				if (p != null) {
					sum = sum.add(p);
				}
			}
			LOG.debug("{} sum {}", currentTarget, sum);
			if (sum.compareTo(BigInteger.ZERO) > 0) {
				if (sum.mod(BigInteger.valueOf(1000000l)) == BigInteger.ZERO) {
					LOG.info("{} partitions found for {}", sum, target);
					return ((long) target);
				}
			}
		}
		return null;
	}
}
