package uk.org.landeg.projecteuler.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.ProblemDescription;

@Component
@Order(68)
@Slf4j
public class Problem068 implements ProblemDescription<String>{

	private static final int N = 5;
	private static final int N_MAX = N * 2;
	private static final List<List<Integer>> COMBINATIONS = new ArrayList<>();
	private static final List<List<List<Integer>>> SOLUTIONS = new ArrayList<>();
	private static final Map<Integer, List<List<Integer>>> SUB_PARTITIONS = new HashMap<>();
	
	@Override
	public String getTask() {
		return "Using the numbers 1 to 10, and depending on arrangements, it is possible to form 16- and 17-digit strings. What is the maximum 16-digit string for a \"magic\" 5-gon ring?";
	}

	@Override
	public String getDescribtion() {
		return "By concatenating each group it is possible to form 9-digit strings; the maximum string for a 3-gon ring is 432621513";
	}

	@Override
	public String solve() {
		log.debug("{}", COMBINATIONS);
		final int lowerBound = N_MAX + 1 +2;
		final int upperBound = N_MAX*3 -2 -3;
		for (int target = lowerBound -1 ; target < upperBound + 1; target++) {
			findSubPartitions(target);
		}
		SUB_PARTITIONS.entrySet().stream()
			.forEach(entry -> {
				log.debug("Finding candidate solutions for sum {}", entry.getKey());
				findSolutions(entry.getValue());
		});
		SOLUTIONS.stream().forEach(x -> log.info("{}", x));
		final List<String> stringSolutions = new ArrayList<>();
		SOLUTIONS.stream()	
				.forEach(solution-> {
						final StringBuilder builder = new StringBuilder();
						solution
							.stream()
							.flatMap(x -> x.stream())
							.forEach(x -> builder.append(x));
						stringSolutions.add(builder.toString());
				});
		Collections.sort(stringSolutions);
		Collections.reverse(stringSolutions);
		return stringSolutions.get(0);
	} 
	

	private void findSubPartitions(final int target) {
		log.debug("finding partitons for {}", target);
		final boolean[] numbers = new boolean[N_MAX+1];
		Arrays.fill(numbers, true);
		numbers[0] = false;
		final List<Integer> solution = new ArrayList<>();
		findSubPartitions(target, solution, numbers);
	}

	private void findSubPartitions(int target, List<Integer> solution, boolean[] numbers) {
		final int currentSum = solution.stream().mapToInt(x->x).sum();
		if (currentSum == target && solution.size() == 3) {
			log.trace("Partition solution for {} : {}", target, solution);
			List<List<Integer>> candidateSolutions;
			if (!SUB_PARTITIONS.containsKey(target)) {
				candidateSolutions = new ArrayList<>();
				SUB_PARTITIONS.put(target, candidateSolutions);
			} else {
				candidateSolutions = SUB_PARTITIONS.get(target);
			}
			candidateSolutions.add(new ArrayList<>(solution));
			return;
		}
		if (currentSum >= target) {
			return;
		}
		if (solution.size() >= 3) {
			return;
		}
		for (int numberId = numbers.length -1 ; numberId >= 1 ; numberId--) {
			if (numbers[numberId]) {
				numbers[numberId] = false;
				solution.add(numberId);
				findSubPartitions(target, solution, numbers);
				numbers[numberId] = true;
				solution.remove(solution.size() - 1);
			}
		}
	}

	private void findSolutions (final List<List<Integer>> partitions) {
		final List<List<Integer>> cleansedPartitions = partitions
			.stream()
			.filter(partition -> partition.get(2) != 10 && partition.get(1) != 10)
			.collect(Collectors.toList());
		final List<List<Integer>> solution = new ArrayList<>();
		findSolutions(cleansedPartitions, solution);
	}

	private void findSolutions (final List<List<Integer>> partitions, final List<List<Integer>> solution) {
		log.trace("Current solution is {}", solution.toString());
		
		if (solution.size() == N) {
			if (solution.get(solution.size() - 1).get(2).equals(solution.get(0).get(1))) {
				final List<List<Integer>> newSolution = new ArrayList<>();
				solution.stream().forEach(partition -> newSolution.add(partition));
				SOLUTIONS.add(newSolution);
				log.info("solution discovered {}", newSolution);
			}
			return;
		}
		for (int idx = 0 ; idx < partitions.size() ; idx++) {
			List<Integer> partition = partitions.get(idx);
			final boolean eligable = isEligable(solution, partition);
			if (eligable) {
				solution.add(partition);
				findSolutions(partitions,solution);
				solution.remove(partition);
			}
		}
	}
	
	private boolean isEligable (final List<List<Integer>> solution, final List<Integer> partition) {
		int minAllowed = 0;
		if (!solution.isEmpty()) {
			minAllowed = solution.get(0).get(0);
		}
		log.trace("checking eligability of {}", partition);
		boolean eligable = !solution.contains(partition);
		eligable &= solution.isEmpty() || partition.get(0) > solution.get(0).get(0);
		if (!eligable) {
			log.trace("rejecting : outer ring value is less than the minimum allowed");
			return false;
		}
		eligable &= !solution.isEmpty() || partition.get(0) <= N_MAX - N + 1; 
		if (!eligable) {
			log.trace("rejecting : intial outer ring value fails to exceed minimum allowed");
			return false;
		}
		eligable &= partition.get(0) > minAllowed;
		if (!eligable) {
			log.trace("rejecting : outer ring value is less than initial outer ring");
			return false;
		}

		eligable &= solution.isEmpty() || partition.get(1).intValue() == solution.get(solution.size() - 1).get(2).intValue();
		if (!eligable) {
			log.trace("rejecting : inner ring values dont match");
			return false;
		}
		
		if (eligable) {
			for (int i = 0 ; i < solution.size() ; i++) {
				if (solution.get(i).contains(partition.get(0))) {
					log.trace("rejecting : solution component {} already contains {}", i, partition.get(0));
					return false;
				}
			}
		}

		for (int i = 0 ; i < solution.size() - 1; i++) {
			for (int j = 0 ; j < partition.size() ; j++) {
				if (!(solution.size() == N-1 && i==0 && j == 2)) { // prevent ring closure from blocking this
					if (solution.get(i).contains(partition.get(j))) {
						log.trace("rejecting : solution component {} already contains {}", solution.get(i), partition.get(j));
						return false;
					}
					
				}
			}
		}
		return eligable;
	}

}
