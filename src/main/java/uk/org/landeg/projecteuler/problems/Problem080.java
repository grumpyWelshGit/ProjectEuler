package uk.org.landeg.projecteuler.problems;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.ContinuedFraction;
import uk.org.landeg.projecteuler.Convergent;
import uk.org.landeg.projecteuler.ConvergentState;
import uk.org.landeg.projecteuler.Mathlib;
import uk.org.landeg.projecteuler.ProblemDescription;

@Order(80)
@Component
public class Problem080 implements ProblemDescription<Long>{
	private static final Logger LOG = LoggerFactory.getLogger(Problem080.class);
	@Override
	public String getTask() {
		return "For the first one hundred natural numbers, find the total of the digital sums of the first one hundred decimal digits for all the irrational square roots";
	}

	@Override
	public String getDescribtion() {
		return "It is well known that if the square root of a natural number is not an integer, then it is irrational";
	}

	@Override
	public Long solve() {

		int sum = 0;
		for (int i = 1 ; i <= 100 ; i++) {
			boolean converged = false;
			final int sqrt = (int) Math.sqrt(i);
			if (sqrt * sqrt == i) {
				LOG.debug("skipping perfect square {}", i);
				continue;
			}
			final List<Long> aValues = new ArrayList<>();
			ConvergentState s = new ConvergentState(i);
			String lastEval = "";
			do {
				LOG.trace("{}",s);
				aValues.add(s.getA().longValue());
				s = ContinuedFraction.evaluateNext(s);
				Convergent c = ContinuedFraction.evaluate(aValues);
				LOG.trace("i {} : {}   {}", i, ContinuedFraction.evaluate(aValues)/*, eval.toString()*/);
				if (aValues.size() > 1) {
					BigDecimal eval = new BigDecimal(c.getN()).divide(new BigDecimal(c.getD()), 200, BigDecimal.ROUND_DOWN);
					if (eval.toString().compareTo(lastEval.toString()) == 0) {
						converged = true;
						LOG.trace(eval.toString());
					}
					LOG.trace("{}", eval);
					lastEval = eval.toString();
				}

			} while (!converged);
			final String decimalDigits = lastEval.replace(".", "").substring(0, 100);
			final int iSum = (int) Mathlib.digitalSum(decimalDigits);
			sum += iSum;
			LOG.debug("{} sum {}  {}", i, iSum, decimalDigits);
		}
		LOG.info("sum {}", sum);
		return (long) sum;
	}

}
