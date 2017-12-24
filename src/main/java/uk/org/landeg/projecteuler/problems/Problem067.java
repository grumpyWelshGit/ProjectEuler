package uk.org.landeg.projecteuler.problems;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.FileLoader;
import uk.org.landeg.projecteuler.ProblemDescription;

@Order(67)
@Component
public class Problem067 extends Problem018 implements ProblemDescription<Integer>{

	@Override
	public String getTask() {
		return "Find the maximum total from top to bottom in triangle.txt";
	}

	@Override
	public String getDescribtion() {
		return "By starting at the top of the triangle below and moving to adjacent numbers on the row below, the maximum total from top to bottom is 23";
	}


	@Override
	public int[][] parseNumbers() {
		final List<List<Integer>> linesList = FileLoader.readLines("p067_triangle.txt")
			.stream()
			.map(line -> {
				String toks[] = line.split("\\s");
				return Arrays.stream(toks)
					.map(n -> Integer.parseInt(n))
					.collect(Collectors.toList());
				
			})
			.collect(Collectors.toList());
		final int[][] numbers = new int[linesList.size()][];
		int lineId = 0;
		for (final List<Integer> line : linesList) {
			int numId = 0;
			numbers[lineId] = new int[line.size()];
			for (final Integer n : line) {
				numbers[lineId][numId++] = n;
			}
			lineId++;
		}
		return numbers;
	}
}
