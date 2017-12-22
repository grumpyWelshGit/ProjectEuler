package uk.org.landeg.projecteuler.problems;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.ProblemDescription;

@Component
@Order(29)
public class Problem029 implements ProblemDescription<Integer> {

	@Override
	public String getTask() {
		return "How many distinct terms are in the sequence generated by a^b for 2 ≤ a ≤ 100 and 2 ≤ b ≤ 100?";
	}

	@Override
	public String getDescribtion() {
		return "Consider all integer combinations of ab for 2 ≤ a ≤ 5 and 2 ≤ b ≤ 5:\n"
				+ "If they are then placed in numerical order, with any repeats removed, we get the following sequence of 15 distinct terms: "
				+ "4, 8, 9, 16, 25, 27, 32, 64, 81, 125, 243, 256, 625, 1024, 3125";
	}

	@Override
	public Integer solve() {
		final Set<BigInteger> distinct = new HashSet<>(); 
		for (int a = 2; a <= 100 ; a++) {
			for (int b =2; b <= 100 ; b++) {
				distinct.add(BigInteger.valueOf(a).pow(b));
			}
		}
		return distinct.size();
	}

}