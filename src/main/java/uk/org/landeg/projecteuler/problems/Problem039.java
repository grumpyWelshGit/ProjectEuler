package uk.org.landeg.projecteuler.problems;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.ProblemDescription;

@Order(39)
@Component
public class Problem039 implements ProblemDescription<Integer>{

	@Override
	public String getTask() {
		return "For which value of p ≤ 1000, is the number of solutions maximised?";
	}

	@Override
	public String getDescribtion() {
		return "If p is the perimeter of a right angle triangle with integral length sides, {a,b,c}, there are exactly three solutions for p = 120";
	}

	@Override
	public Integer solve() {
		int maxp = 0;
		int maxSolutions = 0;
		for (int p = 120 ; p <= 1000 ; p++) {
			int solutions = 0;
			for (int a = 1 ; a < 900 ; a++) {
				for (int b = 1 ; b < 900 ; b++) {
					if (a + b > p) {
						break;
					}
					int c = p - a - b;
					if (a*a + b*b == c*c) {
						solutions++;
					}
				}
			}
			if (solutions > maxSolutions) {
				maxSolutions = solutions;
				maxp = p;
				System.out.println("p = " + p);
			}
		}
		return maxp;
	}
}
