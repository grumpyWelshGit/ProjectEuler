package uk.org.landeg.projecteuler.problems;



import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.ProblemDescription;

@Component
@Order(71)
@Slf4j
public class Problem071 implements ProblemDescription<Integer>{

	
	@Override
	public String getTask() {
		return "It can be seen that 2/5 is the fraction immediately to the left of 3/7.";
	}

	@Override
	public String getDescribtion() {
		return "By listing the set of reduced proper fractions for d ≤ 1,000,000 in ascending order of size, find the numerator of the fraction immediately to the left of 3/7";
	}

	
	@Override
	public Integer solve() {
		double target = 3d/7d;
		int n;
		double mindiff = 1;
		int nMinDiff = 1;
		for (int d = 999997 ; d < 1000000 ; d++) {
			n = d / 7 * 3 - 1;
			do {
				n++;
			} while ((n+1d)/(double)d < target);
			double diff = target - ((double)n / (double)d);
			if (diff > 0 && diff < mindiff) {
				log.debug("{}/{} diff = {}",n,d,diff);
				mindiff = diff;
				nMinDiff = n;
			}
		}
		return nMinDiff;
	}
}
