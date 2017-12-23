package uk.org.landeg.projecteuler.problems;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.FileLoader;
import uk.org.landeg.projecteuler.ProblemDescription;

@Component
@Order(79)
public class Problem079 implements ProblemDescription<String>{

	private static final Logger LOG = LoggerFactory.getLogger(Problem076.class);
	@Override
	public String getTask() {
		return "Given that the three characters are always asked for in order, analyse the file so as to determine the shortest possible secret passcode of unknown length";
	}
	@Override
	public String getDescribtion() {
		return "A common security method used for online banking is to ask the user for three random characters from a passcode. For example, if the passcode was 531278, they may ask for the 2nd, 3rd, and 5th characters; the expected reply would be: 317";
	}
	@Override
	public String solve() {
		final StringBuilder resultBuilder = new StringBuilder();
		final List<String> lines = FileLoader.readLines("p079_keylog.txt");
		final Map<Character, Integer> scores = new LinkedHashMap();
		int[] digitScores = new int[10];
		final List<Integer[]> intLines = new ArrayList<>();
		final Set<Integer> usedDigits = new HashSet<>();
		for (String line : lines ) {
			final Integer[] lineInt = new Integer[3];
			lineInt[0] = (int) (line.toCharArray()[0] - '0');
			lineInt[1] = (int) (line.toCharArray()[1] - '0');
			lineInt[2] = (int) (line.toCharArray()[2] - '0');
			usedDigits.add(lineInt[0]);
			usedDigits.add(lineInt[1]);
			usedDigits.add(lineInt[2]);
			intLines.add(lineInt);
		}
		
		
		for (int i = 0 ; i < digitScores.length ; i++) {
			if (usedDigits.contains(i)) {
				LOG.info("{} : {}", i, digitScores[i]);
			}
		}
		
		final Map<Integer, Set<Integer>> charsAfter = new LinkedHashMap<>();

		for (int i : usedDigits) {
			Set<Integer> charsAfterI = new HashSet<>();
			charsAfter.put(i, charsAfterI);
			for (Integer[] line : intLines ) {
				boolean found = false;
				for (int j = 0 ; j < line.length ; j++) {
					if (found) {
						charsAfterI.add(line[j]);
					}
					if (line[j] == i) {
						found=true;
					} 
				}
			}
		}
		charsAfter.entrySet().stream()
			.forEach(e -> LOG.info("chars after {} : {}", e.getKey(), e.getValue()));
		final int maxAfter = charsAfter
				.entrySet()
				.stream()
				.mapToInt(e -> e.getValue().size())
				.max()
				.getAsInt();
		final Map<Integer, Integer> charsAfterFreq = 
			charsAfter
			.entrySet()
			.stream()
			.collect(Collectors.toMap(e->e.getValue().size(), e->e.getKey()));
		for (int i = maxAfter ; i >=0 ; i--) {
			resultBuilder.append(charsAfterFreq.get(i));
		}
		return resultBuilder.toString();
	}
}
