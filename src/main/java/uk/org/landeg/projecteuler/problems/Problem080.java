package uk.org.landeg.projecteuler.problems;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.ContinuedFraction;
import uk.org.landeg.projecteuler.Convergent;
import uk.org.landeg.projecteuler.ConvergentState;
import uk.org.landeg.projecteuler.Mathlib;
import uk.org.landeg.projecteuler.ProblemDescription;

@Slf4j
@Order(80)
@Component
public class Problem080 implements ProblemDescription<Long>{

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
				log.debug("skipping perfect square {}", i);
				continue;
			}
			final List<Long> aValues = new ArrayList<>();
			ConvergentState s = new ConvergentState(i);
			String lastEval = "";
			do {
				log.trace("{}",s);
				aValues.add(s.getA().longValue());
				s = ContinuedFraction.evaluateNext(s);
				Convergent c = ContinuedFraction.evaluate(aValues);
				log.trace("i {} : {}   {}", i, ContinuedFraction.evaluate(aValues)/*, eval.toString()*/);
				if (aValues.size() > 1) {
					BigDecimal eval = new BigDecimal(c.getN()).divide(new BigDecimal(c.getD()), 200, BigDecimal.ROUND_DOWN);
					if (eval.toString().compareTo(lastEval.toString()) == 0) {
						converged = true;
						log.trace(eval.toString());
					}
					log.trace("{}", eval);
					lastEval = eval.toString();
				}

			} while (!converged);
			final String decimalDigits = lastEval.replace(".", "").substring(0, 100);
			final int iSum = (int) Mathlib.digitalSum(decimalDigits);
			sum += iSum;
			log.debug("{} sum {}  {}", i, iSum, decimalDigits);
		}
		log.info("sum {}", sum);
		return (long) sum;
	}

}
