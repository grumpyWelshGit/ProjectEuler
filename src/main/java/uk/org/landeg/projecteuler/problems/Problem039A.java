package uk.org.landeg.projecteuler.problems;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.ProblemDescription;

@Order(39)
@Component
public class Problem039A implements ProblemDescription<Integer>{

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
		int mMax = (int) Math.sqrt(1000);
		for (int p = 12 ; p < 1000 ; p++) {
			for (int m = 2 ; m < mMax ; m++) {
				int n = (p - 2 * m * m) / (2 * m);
				if (n < 1 || n > m) {
					break;
				}
				final int c = m*m + n*n;
				final int a = m*m - n*n;
				final int b = 2*m*n;
				
				
				System.out.println("a=" + a + " b=" + b + " c=" + c);
			}
		}
			

		return maxp;
	}
}
