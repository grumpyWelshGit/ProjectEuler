package uk.org.landeg.projecteuler.problems;



import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import uk.org.landeg.projecteuler.ProblemDescription;

import static uk.org.landeg.projecteuler.Mathlib.gcd;

@Order(91)
@Component
@Slf4j
public class Problem091 implements ProblemDescription<Long>{

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
    int sideLength = 2;
    int count = 0;
    log.info("counting triangles for {}*{} grid", sideLength, sideLength);
    count += (sideLength) * (sideLength) * 3;
    
    for (int x = 1 ; x <= sideLength ; x++) {
      for (int y = 1 ; y <= sideLength ; y++) {
        log.debug("checking [{},{}]",x,y);
        int gcd = gcd(x,y);
        log.debug("gcd {} {}={}", x,y,gcd);
        // down direction
        int countDown = x / (y / gcd);
        if (x % (y/gcd) == 0) {
          countDown--;
          log.debug("removing degenerate triangle");
        }
        log.debug("downwards direction count {} ", countDown);
        int countUp = y / (x / gcd);
        if (y % (x/gcd) == 0) {
          log.debug("removing degenerate triangle");
          countUp--;
        }

        log.debug("upwards direction count {} ", countUp);
        count += countDown;
        count += countUp;
      }
    }
    long pTotal = 0l;
    log.info("{}", count);
    return (long)count;
  }
}
