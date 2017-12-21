package uk.org.landeg.projecteuler.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.ProblemDescription;

@Component
@Order(76)
public class Problem076 implements ProblemDescription<Long>{

	private static final Logger LOG = LoggerFactory.getLogger(Problem076.class);
	private static final int TARGET = 100;
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

	private List<Integer> solution = new ArrayList<>();

	private int[][] subPartitions;
	@Override
	public Long solve() {
		subPartitions = new int[TARGET+1][];
		subPartitions[1] = new int[] {0,1};
		for (int target = 2 ; target <= TARGET ; target++) {
			subPartitions[target] = new int[target + 1];
			for (int i = 1 ; i < target ; i++) {
				int diff = target - i;
				int sum = 0;
				for (int j = 1 ; j <= i && j< subPartitions[diff].length ; j++) {
					sum += subPartitions[diff][j];
				}
				subPartitions[target][i] = sum;
			}
			subPartitions[target][target] = 1;
			LOG.debug(Arrays.toString(subPartitions[target]));
		}
		int sum = 0;
		for (int p : subPartitions[100]) {
			sum += p;
		}
		
		return (long)(sum -1);
	}
	
	public void cascadeSolution (final int target) {
		final int currentSum = (solution.isEmpty()) ? 0 : solution.stream().mapToInt(x->x).sum();
		if (currentSum == target) {
			LOG.trace("solution discovered {} ", solution);
			final int start = solution.get(0);
			Integer count = partitions.getOrDefault(start, 0);
			count++;
			partitions.put(start, count);
			combinationCount.incrementAndGet();
			return;
		}
		if (currentSum > target) {
			return;
		}
		
		int diff = target - currentSum;
		int maxNext = diff;
		if (!solution.isEmpty()) {
			maxNext = solution.get(solution.size() - 1);
		}
		
		for (int nexNum = maxNext; nexNum >= 1 ; nexNum--) {
			solution.add(nexNum);
			cascadeSolution(target);
			solution.remove(solution.lastIndexOf(nexNum));
		}
	}
}
