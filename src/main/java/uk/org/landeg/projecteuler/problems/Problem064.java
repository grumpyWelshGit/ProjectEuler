package uk.org.landeg.projecteuler.problems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.ProblemDescription;

@Component
@Order(64)
public class Problem064 implements ProblemDescription<Integer>{
	private static final Logger LOG = LoggerFactory.getLogger(Problem064.class);
	@Override
	public String getTask() {
		return "In the first one-thousand expansions, how many fractions contain a numerator with more digits than denominator?";
	}

	@Override
	public String getDescribtion() {
		return " is possible to show that the square root of two can be expressed as an infinite continued fraction";
	}

	@Override
	public Integer solve() {
		int count = 0;
		long top;
		long bot;
		long tmp;
		
		bot = 2;
		top = 1;
		
		float ratio = 1f;
		
		for (int idx = 2 ; idx < 30 ; idx++) {
			tmp = bot;
			bot  = 2 * bot + top;
			top = tmp;
			long tt = bot + top;
			long bb = bot; 
			LOG.debug("{} / {} -> {}/{}, {} {}", top,bot, tt, bb, (int) Math.log10 (tt), (int) Math.log10 (bb)) ;
			if ((int) Math.log10(tt) > (int) Math.log10(bb)) {
				LOG.debug("{} {} / {} -> {}/{}", idx,top,bot, tt, bb);
				count++;
				ratio = (float)idx/(float)count;
			}
		}
		return (int) (1000 / ratio);
	}
}
