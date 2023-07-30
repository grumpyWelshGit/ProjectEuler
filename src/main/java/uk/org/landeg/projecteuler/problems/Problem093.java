package uk.org.landeg.projecteuler.problems;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import uk.org.landeg.projecteuler.Permutations;
import uk.org.landeg.projecteuler.ProblemDescription;

import java.util.*;

import static uk.org.landeg.projecteuler.Permutations.choose;
import static uk.org.landeg.projecteuler.Permutations.lexiograph;

@Slf4j
@Component
@Order(93)
public class Problem093 implements ProblemDescription<Long>{
	@Override
	public String getTask() {
		return "Find the set of four distinct digits, \n" +
				", for which the longest set of consecutive positive integers, \n" +
				" to \n" +
				", can be obtained, giving your answer as a string: abcd.";
	}

	@Override
	public String getDescribtion() {
		return "By using each of the digits from the set, \n" +
				", exactly once, and making use of the four arithmetic operations (\n" +
				") and brackets/parentheses, it is possible to form different positive integer targets.\n" +
				"\n";
	}

	@Override
	public Long solve() {
		final Permutations.UniqueSelectionContext context = new Permutations.UniqueSelectionContext(new Integer[]{1,2,3,4,5,6,7,8,9}, 4);
		List<Integer> digits;

		final List<char[]> combiners = buildCombiners();

		long count = 0;
		long maxConsecutive = 0;
		List<Integer> maxDigits = new ArrayList<>();

		while ((digits = choose(context)) != null) {
			log.debug("{}", digits);
			int consecutive = consecutiveResults(digits, combiners);
			if (consecutive > maxConsecutive) {
				maxConsecutive = consecutive;
				maxDigits.clear();
				maxDigits.addAll(digits);
				log.debug("new master maxima {} : {}", maxDigits, maxConsecutive);
			}
		}
		log.info("RESULT {} : {}", maxDigits, maxConsecutive);
		return count;
	}

	private int consecutiveResults(List<Integer> digits, List<char[]> combiners) {
		var emptyInts = new Integer[]{};
		Set<Integer> digitCombinations = new HashSet<>();
		var maxCheck = 0;
		for (int lex = 0 ; lex < 24 ; lex++) {
			var asArray = digits.toArray(emptyInts);
			var ordered = lexiograph(asArray, lex);
			for (var combiner : combiners) {
				var results = apply2(ordered, combiner);
				for (var result : results) {
					if (result > 0) {
						digitCombinations.add(result);
					}
				}
			}
			int check = 0;
			do {
				int next = check + 1;
				if(!digitCombinations.contains(next)) {
					break;
				}
				check = next;
			} while (true);
			if (check > maxCheck) {
				maxCheck = check;
				log.trace("new maxima : {} {} {}", ordered, maxCheck, digitCombinations);
			}
			maxCheck = (check > maxCheck) ? check : maxCheck;
		}
		return maxCheck;
	}

	private int apply(List<Integer> digits, char[] combiner) {
		// a b c d
		// (a b) c d
		// (a b c) d
		// ((a b) c ) d
		int eval = digits.get(0);
		eval = apply(eval, digits.get(1), combiner[0]);
		eval = apply(eval, digits.get(2), combiner[1]);
		eval = apply(eval, digits.get(3), combiner[2]);
		return eval;
	}

	private List<Integer> apply2(List<Integer> digits, char[] combiner) {
		double d1 = digits.get(0);
		double d2 = digits.get(1);
		double d3 = digits.get(2);
		double d4 = digits.get(3);

		// a b c d
		List<Integer> results = new ArrayList<>();
		double eval = d1;
		eval = apply(eval, d2, combiner[0]);
		eval = apply(eval, d3, combiner[1]);
		eval = apply(eval, d4, combiner[2]);
		log.trace("{} {} {} ", digits, Arrays.toString(combiner), eval);
		addCandidateInteger(results, eval);

		// (a b) c d
		double e1 = apply(d1, d2, combiner[0]);
		double e2 = apply(d3, d4, combiner[2]);
		eval = apply(e1, e2, combiner[1]);
		log.trace("{} {} {} ", digits, Arrays.toString(combiner), eval);
		addCandidateInteger(results, eval);

		// ((a b) c ) d
		e1 = apply(d1, d2, combiner[0]);
		e2 = apply(e1, d3, combiner[1]);
		eval = apply(e2, d4, combiner[2]);
		log.trace("{} {} {} ", digits, Arrays.toString(combiner), eval);
		addCandidateInteger(results, eval);

		log.debug("eval results for {} : {}", digits, results);
		return results;
	}

	private void addCandidateInteger(Collection<Integer> results, double candidate) {
		if (candidate < 0.0d) {
			log.trace("rejecting {}", candidate);
			return;
		}
		int asInt = (int)(candidate);
		double delta = Math.abs(candidate - (double) asInt);
		if (delta < 0.0000001) {
			results.add(asInt);
		} else {
			log.trace("rejecting {}", candidate);
		}
	}


	private double apply(double a, double b, char operator) {
		if(operator == '+') {
			return a + b;
		}
		if(operator == '-') {
			return a - b;
		}
		if(operator == '/') {
			return a / b;
		}
		if(operator == '*') {
			return a * b;
		} else throw new RuntimeException("unknown operator " + operator);
	}

	private int apply(int a, int b, char operator) {
		if(operator == '+') {
			return a + b;
		}
		if(operator == '-') {
			return a - b;
		}
		if(operator == '/') {
			return a / b;
		}
		if(operator == '*') {
			return a * b;
		} else throw new RuntimeException("unknown operator " + operator);
	}

	private List<char[]> buildCombiners() {
		List<char[]> combiners = new ArrayList<>();
		char[] ops = new char[] {'+', '*', '-', '/'};
		int op1 = 0;
		do {
			int op2 = 0;
			do {
				int op3 = 0;
				do {
					var thisOps = new char[3];
					thisOps[0] = (ops[op1]);
					thisOps[1] = (ops[op2]);
					thisOps[2] = (ops[op3]);
					combiners.add(thisOps);
				} while (++ op3 < 4);
			} while (++op2 < 4);
		} while (++op1 < 4);

		for (var op : combiners) {
			log.trace("combiner : {}", Arrays.toString(op));
		}
		return combiners;
	}
}
