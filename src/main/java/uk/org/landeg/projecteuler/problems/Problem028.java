package uk.org.landeg.projecteuler.problems;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.ProblemDescription;

@Order(28)
@Component
public class Problem028 implements ProblemDescription<Long>{

	@Override
	public String getTask() {
		return "What is the sum of the numbers on the diagonals in a 1001 by 1001 spiral formed in the same way?";
	}

	@Override
	public String getDescribtion() {
		// TODO Auto-generated method stub
		return "it can be verified that the sum of the numbers on the diagonals is 101";
	}

	@Override
	public Long solve() {
		// each corner of each spriral layer sums to 4(2n+1)^2 - 6(2n) for n > 1
		// limit case when 2n+1=1001 or n=200
		// S(1..500) = 1 + S(16n^2 + 4n + 4)
		//           = 1 + 16 * (500^3/3 + 500^2/2 + 500/6) + 4*500*501/2 + 4*500
		//           = 1 + 16 * (2*500*500*500 + 3*500*500 + 500) / 6
		return 1l + 16l * (2l*500l*500l*500l + 3l*500l*500l + 500l) / 6l + 4l*500l*501l/2l + 4l*500l;
	}

}
