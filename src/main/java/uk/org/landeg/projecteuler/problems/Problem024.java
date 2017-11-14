package uk.org.landeg.projecteuler.problems;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.Permutations;
import uk.org.landeg.projecteuler.ProblemDescription;

@Order(24)
@Component
public class Problem024 implements ProblemDescription<String>{

	@Override
	public String getTask() {
		return "What is the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?";
	}

	@Override
	public String getDescribtion() {
		// TODO Auto-generated method stub
		return "The lexicographic permutations of 0, 1 and 2 are: 012   021   102   120   201   210";
	}

	@Override
	public String solve() {
		return new String (Permutations.lexiograph("0123456789".toCharArray(), 1000000 -1)) ;
	}

}
