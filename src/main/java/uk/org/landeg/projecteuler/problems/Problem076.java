package uk.org.landeg.projecteuler.problems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;


import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.ProblemDescription;

@Component
@Order(76)
@Slf4j
public class Problem076 implements ProblemDescription<Long>{

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
			log.debug(Arrays.toString(subPartitions[target]));
		}
		int sum = 0;
		for (int p : subPartitions[100]) {
			sum += p;
		}
		
		return (long)(sum -1);
	}
}
