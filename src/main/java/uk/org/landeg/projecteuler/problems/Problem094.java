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

@Order(94)
@Component
public class Problem094 implements ProblemDescription<Long>{
  private static final Logger LOG = LoggerFactory.getLogger(Problem094.class);
  @Override
  public String getTask() {
    return "Find the sum of the perimeters of all almost equilateral triangles with integral side lengths and area and whose perimeters do not exceed one billion (1,000,000,000)."; 
  }

  @Override
  public String getDescribtion() {
    return "Almost equilateral triangles";
  }

  @Override
  public Long solve() {
    int max = 1000000000;
    LOG.info("THis is a complete bugger because the ratio of a/b at high P causes serious rounding errors.");
    LOG.info("we can show that the area is of form x^2 - 3y^2 = 1 with x=(3a+-1)/2 and y = h");
    LOG.info("This is pell's equation (again) with D=-3");

    ConvergentState state = new ConvergentState(3);
    final List<Long> convergents = new ArrayList<>();
    long pTotal = 0l;
    int n = 3;
    long b;
    long p;
    long a = 0;
    boolean finished = false;
    do {
      convergents.add(state.getA().longValue());
      state = ContinuedFraction.evaluateNext(state);
      final Convergent eval = ContinuedFraction.evaluate(convergents);
      final BigInteger nc = eval.getN();
      final BigInteger dc = eval.getD();
      final BigInteger nAsLong = BigInteger.valueOf((long) n);
      if (nc.multiply(nc).subtract(dc.multiply(dc).multiply(nAsLong)).equals(BigInteger.ONE)) {
        if ((2 * nc.longValue() + 1) % 3 == 0) {
          a = (2 * nc.longValue() + 1) / 3;
          b = a - 1;
        } else {
          a = (2 * nc.longValue() - 1) / 3;
          b = a + 1;
        }
        long s= (2* a + b) / 2;
        boolean degenerate = (s==a || s==b);
        if (!degenerate) {
          p = 2 * b + a;
          if (p < max) {
            pTotal += p;
          } else {
            finished = true;
          }
        }
        LOG.debug("{} :  {}  : {} {}", state, eval,a,b);
      }
    } while (!finished);
    
    return pTotal;
  }
}
