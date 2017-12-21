package uk.org.landeg.projecteuler.problems;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.ProblemDescription;

@Component
@Order(65)
public class Problem065 implements ProblemDescription<Integer>{
	private static final Logger LOG = LoggerFactory.getLogger(Problem065.class);
	@Override
	public String getTask() {
		return "What is most surprising is that the important mathematical constant,\n" + 
				"e = [2; 1,2,1, 1,4,1, 1,6,1 , ... , 1,2k,1, ...].";
	}

	@Override
	public String getDescribtion() {
		return "Find the sum of digits in the numerator of the 100th convergent of the continued fraction for e.";
	}

	@Override
	public Integer solve() {
		int result = 0;
		BigInteger n = null; 
		BigInteger d = null;
		final StringBuilder builder = new StringBuilder ();
		final List<Integer> terms = new ArrayList<>();
		
		for (int idx = 0 ; idx <= 100 ; idx++) {
			terms.add(continuedFractionGenerator.next());	
		}
		
		for (int idx = 99 ; idx >= 0 ; idx--) {
			BigInteger term = BigInteger.valueOf((long) terms.get(idx));
			if (n == null) {
				d = BigInteger.ONE;
				n = term;
			} else {
				BigInteger nt = new BigInteger(n.toString());
				n = d;
				d = nt;
				n = term.multiply(d).add(n);
				d = nt;
			}
			LOG.debug("{}/{}", n,d);
		}
		BigInteger TEN = BigInteger.valueOf(10l);
		
		do {
			result += n.mod(TEN).intValue();
			n = n.divide(TEN);
		} while (n.compareTo(BigInteger.ONE) > 0);
		return result;
	}
	
	private boolean isPerfectSquare(int root) {
		final int sqrt = (int) Math.sqrt(root);
		return sqrt * sqrt == root;
	}

	
	final Iterator<Integer> continuedFractionGenerator = new Iterator<Integer>() {
		int n = 0;
		int count = 0;
		@Override
		public boolean hasNext() {
			return true;
		}

		@Override
		public Integer next() {
			int ret;
			if (count == 0) {
				ret = 2;
			} else if ((count + 1) % 3 == 0) {
				n += 2;
				ret = n;
			} else {
				ret = 1;
			}
			count++;
			return ret;
		}
	};
}
