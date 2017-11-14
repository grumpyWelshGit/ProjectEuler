package uk.org.landeg.projecteuler.problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.FileLoader;
import uk.org.landeg.projecteuler.ProblemDescription;

@Order(22)
@Component
public class Problem022 implements ProblemDescription<Long>{

	@Override
	public String getTask() {
		return "What is the total of all the name scores in the file?";
	}

	@Override
	public String getDescribtion() {
		return "For example, when the list is sorted into alphabetical order, COLIN, which is worth 3 + 15 + 12 + 9 + 14 = 53, is the 938th name in the list. So, COLIN would obtain a score of 938 × 53 = 49714.";
	}

	@Override
	public Long solve() {
		final String namesString = FileLoader.readLines("022.txt").get(0).replace("\"", "");
		
		StringTokenizer tokenizer = new StringTokenizer(namesString, ",");
		final List<String> names = new ArrayList<>(6000);
		while (tokenizer.hasMoreTokens()) {
			names.add(tokenizer.nextToken());
		}
		int score = 0;
		Collections.sort(names);
		int order = 1;
		for (final String name : names) {
			int nameScore = 0;
			for (char ch : name.toCharArray()) {
				nameScore += ch - 'A' + 1;
			}
			nameScore *= order;
			order++;
			if (name.equals("COLIN")) {
				System.out.println("COLIN : " + nameScore);
			}
			score += nameScore;
		}
		return (long) score;
	}

}
