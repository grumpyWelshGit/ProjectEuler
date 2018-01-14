package uk.org.landeg.projecteuler.problems;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import uk.org.landeg.projecteuler.ContinuedFraction;
import uk.org.landeg.projecteuler.Convergent;
import uk.org.landeg.projecteuler.ConvergentState;
import uk.org.landeg.projecteuler.ProblemDescription;

@Component
@Order(66)
public class Problem066 implements ProblemDescription<Integer>{
	private static final Logger LOG = LoggerFactory.getLogger(Problem066.class);
	@Override
	public String getTask() {
		return "Find the value of D ≤ 1000 in minimal solutions of x for which the largest value of x is obtained";
	}

	@Override
	public String getDescribtion() {
		return " Consider quadratic Diophantine equations of the form x^2 - Dy^2 = 1, For example, when D=13, the minimal solution in x is 6492 – 13×1802 = 1.\n"
				+ "By finding minimal solutions in x for D = {2, 3, 5, 6, 7}, we obtain the following\n"
				+ "3^2 – 2×2^2 = 1\n"
				+ "2^2 - 3*1^2 = 1\n"
				+ "9^2 - 5*4^2 = 1\n"
				+ "5^2 - 6*2^2 = 1\n"
				+ "8^2 - 7*3^2 = 1\n\n"
				+ "Hence, by considering minimal solutions in x for D ≤ 7, the largest x is obtained when D=5";
	}

	@Override
	public Integer solve() {
		ConvergentState state;
		int dMax = 0;
		BigInteger xMax = BigInteger.ZERO;
		for (int n = 2 ; n <= 1000 ; n++) {
			int sqrtN = (int) Math.sqrt(n);
			if (sqrtN * sqrtN == n) {
				continue;
			}
			LOG.debug("Solving for {}",n);
			final List<Long> convergents = new ArrayList<>();
			state = new ConvergentState(n);
			boolean solutionFound = false;
			do {
				convergents.add(state.getA().longValue());
				state = ContinuedFraction.evaluateNext(state);
				final Convergent eval = ContinuedFraction.evaluate(convergents);
				final BigInteger nc = eval.getN();
				final BigInteger dc = eval.getD();
				final BigInteger nAsLong = BigInteger.valueOf((long) n);
				if (nc.multiply(nc).subtract(dc.multiply(dc).multiply(nAsLong)).equals(BigInteger.ONE)) {
					solutionFound = true;
					LOG.debug("{} :  {}", state, eval);
					if (nc.compareTo(xMax) > 0) {
						xMax = nc;
						dMax = n;
						LOG.info("x maxima found at  d={}, x/y={}/{}", n, xMax,state.getY());
					}
				}
			} while (!solutionFound);
			
			
			
		}
		LOG.info("dMax found at {} ", dMax);
		return dMax;
	}
}
