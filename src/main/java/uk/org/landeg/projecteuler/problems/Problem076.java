package uk.org.landeg.projecteuler.problems;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.ProblemDescription;
import uk.org.landeg.projecteuler.UniqueSolution;

@Component
@Order(76)
@UniqueSolution
public class Problem076 implements ProblemDescription<Integer>{
	private static final Logger LOG = LoggerFactory.getLogger(Problem076.class);
	@Override
	public String getTask() {
		return "How many different ways can one hundred be written as a sum of at least two positive integers?";
	}

	@Override
	public String getDescribtion() {
		return " It is possible to write five as a sum in exactly six different ways";
	}

	@Override
	public Integer solve() {
		final int target = 100;
		int count = 0;
		int[] parts = new int[target];
		Arrays.fill(parts, 0);
		parts[0] = target;
		do {
			int i;
			for (i = parts.length - 1 ; i >= 0 ; i--) {
				if (parts[i] > 1) {
					parts[i]--;
					break;
				}
			}
			int sum = 0;
			for (int j = 0 ; j < parts.length - 1 ; j++) {
				if (j > i) {
					parts[j] = 0;
				}
				sum += parts[j];
			}
			for (int j = i + 1 ; j < parts.length ; j++) {
				int min = parts[j-1];
				int next = Math.min(min, target - sum);
				if (next == 0) {
					break;
				}
				parts[j] = next; 
				sum += parts[j];
			}
			LOG.debug("{}",parts);
			count++;
		} while (parts[parts.length - 1] == 0);
		return count;
	}
}
