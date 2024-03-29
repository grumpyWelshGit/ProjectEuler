package uk.org.landeg.projecteuler.problems;



import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.ProblemDescription;

@Component
@Order(63)
@Slf4j
public class Problem063 implements ProblemDescription<Integer>{


	@Override
	public String getTask() {
		return "How many n-digit positive integers exist which are also an nth power?\n";
	}

	@Override
	public String getDescribtion() {
		return "The 5-digit number, 16807=75, is also a fifth power. Similarly, the 9-digit number, 134217728=89, is a ninth power";
	}



	@Override
	public Integer solve() {
		int a;
		int b = 1;
		int count = 0;
		do {
			a = (int) Math.pow(10d, 1d-(1d/b));
			do {
				final double pow = Math.pow(a, b);
				final double logPow = Math.log10(pow);
				if (logPow >= b-1 && logPow < b) {
					log.debug("{}^{} = {}", a,b,pow);
					count++;
				}
				a++;
			} while (a <= 10);
			b++;
		} while (b <= 22);
		return count;
	}
	
}
