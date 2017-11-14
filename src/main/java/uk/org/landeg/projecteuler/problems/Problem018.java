package uk.org.landeg.projecteuler.problems;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.ProblemDescription;

@Order(18)
@Component
public class Problem018 implements ProblemDescription<Integer>{
	private static final Logger LOG = LoggerFactory.getLogger(Problem018.class);

	private static final String TEST_CASE[] = {
			"3",
			"7 4",
			"2 4 6",
			"8 5 9 3"
	};
	
	private static final String REAL_CASE[] = {
		"75",
		"95 64",
		"17 47 82",
		"18 35 87 10",
		"20 04 82 47 65",
		"19 01 23 75 03 34",
		"88 02 77 73 07 63 67",
		"99 65 04 28 06 16 70 92",
		"41 41 26 56 83 40 80 70 33",
		"41 48 72 33 47 32 37 16 94 29",
		"53 71 44 65 25 43 91 52 97 51 14",
		"70 11 33 28 77 73 17 78 39 68 17 57",
		"91 71 52 38 17 14 91 43 58 50 27 29 48",
		"63 66 04 68 89 53 67 30 73 16 69 87 40 31",
		"04 62 98 27 23 09 70 98 73 93 38 53 60 04 23"
	};

	private static final String NUMBERS[] = REAL_CASE;

	private int numbers[][];

	@Override
	public String getTask() {
		return "Find the maximum total from top to bottom of the triangle below";
	}

	@Override
	public String getDescribtion() {
		return "By starting at the top of the triangle below and moving to adjacent numbers on the row below, the maximum total from top to bottom is 23.";
	}

	@Override
	public Integer solve() {
		LOG.info("Hello world!");
		numbers = parseNumbers();
		for (int[] line : numbers) {
			if (LOG.isDebugEnabled()) {
				LOG.debug((Arrays.toString(line)));
			}
		}
		for (int lineId = numbers.length - 2 ; lineId >= 0 ; lineId--) {
			for (int numberId = 0 ; numberId < numbers[lineId].length ; numberId++) {
				numbers[lineId][numberId] += Math.max(numbers[lineId + 1][numberId], numbers[lineId + 1][numberId + 1]);
			}
			for (int lineId2 = 0 ; lineId2 < numbers.length ; lineId2++) {
				if (LOG.isDebugEnabled()) {
					LOG.debug(Arrays.toString(numbers[lineId2]));
				}				
				
			}
		}
		return numbers[0][0];
	}
	
	public int[][] parseNumbers () {
		final int[][] numbers = new int[NUMBERS.length][];
		int lineNumber = 0;
		for (String numberLineStr : NUMBERS) {
			final String[] numbersSplit = numberLineStr.split("\\s");	
			int[] line = new int[numbersSplit.length];
			for (int id = 0; id < numbersSplit.length ; id++) {
				line[id] = Integer.parseInt(numbersSplit[id]);
			}
			numbers[lineNumber++] = line;
		}
		return numbers;
	}
}
