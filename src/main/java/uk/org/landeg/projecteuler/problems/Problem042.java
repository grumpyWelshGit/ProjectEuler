package uk.org.landeg.projecteuler.problems;

import java.util.HashSet;
import java.util.Set;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.FileLoader;
import uk.org.landeg.projecteuler.ProblemDescription;

@Order(42)
@Component
public class Problem042 implements ProblemDescription<Integer>{

	@Override
	public String getTask() {
		return "Using words.txt (right click and 'Save Link/Target As...'), a 16K text file containing nearly two-thousand common English words, how many are triangle words";
	}

	@Override
	public String getDescribtion() {
		return "By converting each letter in a word to a number corresponding to its alphabetical position and adding these values we form a word value. For example, the word value for SKY is 19 + 11 + 25 = 55 = t10. If the word value is a triangle number then we shall call the word a triangle word";
	}

	@Override
	public Integer solve() {
		final Set<Integer> triangleNumbers = new HashSet<>();
		for (int idx = 0; idx <= 500 ; idx++) {
			int t = idx * (idx + 1) / 2;
			if (t < 0) {
				break;
			}
			triangleNumbers.add(t);
		}
		int count = 0;
		final String[] words = FileLoader.readLines("p042_words.txt").get(0).replaceAll("\"", "").toLowerCase().split(",");
		for (final String word : words) {
			int score = 0;
			for (int charidx = 0 ; charidx < word.length() ; charidx++) {
				score += word.charAt(charidx) - 'a' + 1;
			}
			if (triangleNumbers.contains(score)) {
				count++;
			}
		}
		return count;
	}
}
