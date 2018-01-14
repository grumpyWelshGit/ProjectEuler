package uk.org.landeg.projecteuler.problems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import uk.org.landeg.projecteuler.ProblemDescription;

@Order(91)
@Component
public class Problem091 implements ProblemDescription<Long>{
  private static final Logger LOG = LoggerFactory.getLogger(Problem091.class);
  @Override
  public String getTask() {
    return "Given that 0 ≤ x1, y1, x2, y2 ≤ 50, how many right triangles can be formed?\n" + 
        "\n" + 
        "";
  }

  @Override
  public String getDescribtion() {
    return "Right triangles with integer coordinates";
  }

  @Override
  public Long solve() {
    int sideLength = 50;
    int count = 0;
    LOG.info("counting triangles for {}*{} grid", sideLength, sideLength);
    count += (sideLength) * (sideLength) * 3;
    
    for (int x = 1 ; x <= sideLength ; x++) {
      for (int y = 1 ; y <= sideLength ; y++) {
        LOG.debug("checking [{},{}]",x,y);
        int gcd = gcd(x,y);
        LOG.debug("gcd {} {}={}", x,y,gcd);
        // down direction
        int countDown = x / (y / gcd);
        if (x % (y/gcd) == 0) {
          countDown--;
          LOG.debug("removing degenerate triangle");
        }
        LOG.debug("downwards direction count {} ", countDown);
        int countUp = y / (x / gcd);
        if (y % (x/gcd) == 0) {
          LOG.debug("removing degenerate triangle");
          countUp--;
        }

        LOG.debug("upwards direction count {} ", countUp);
        count += countDown;
        count += countUp;
      }
    }
    long pTotal = 0l;
    LOG.info("{}", count);
    return (long)count;
  }

  private int gcd(int x, int y) {
    if (x == 0 || y == 0) {
      return 0;
    }
    if (x == 1 || y == 1) {
      return 1;
    }
    if (x == y) {
      return x;
    }
    int min = Math.min(x, y);
    int max = Math.max(x, y);
    for (int i = min ; i >= 1 ; i--) {
      if (min % i == 0 && max % i == 0) {
        return i;
      }
    }
    return 0;
  }
}
