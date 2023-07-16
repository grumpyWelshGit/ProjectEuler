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
    int sideLength = 50;
    int count = 0;
    log.info("counting triangles for {}*{} grid", sideLength, sideLength);

    for (int x1 = 0 ; x1 <= sideLength ; x1++) {
      for (int y1 = 0 ; y1 <= sideLength ; y1++) {
        for (int x2 = 0 ; x2 <= sideLength ; x2++) {
          for (int y2 = 0 ; y2 <= sideLength ; y2++) {
            double a = lend(0, 0, x2, y2);
            double b = lend(x1, y1, 0, 0);
            double c = lend(x1, y1, x2, y2);


            if (a >= c) {
              double t = c;
              c = a;
              a = t;
            }

            if (b >= c) {
              double t = c;
              c = b;
              b = t;
            }

            double delta = Math.abs(a * a + b * b - c * c);
            boolean match = (Math.abs(a * a + b * b - c * c) < 0.001) && (a > 0)&& (b > 0)&& (c > 0);

            if (match) {
              log.debug("[{},{}] [{},{}] {} {} {} {}    [{}]", x1, y1, x2, y2, a,b,c,match, delta);
              count++;
            }
          }
        }
      }
    }
    return (long)count / 2;
  }

  private double lend(double x1, double y1, double x2, double y2) {
    return Math.sqrt((x1 - x2) * (x1 - x2) + (y2 - y1) * (y2 - y1));
  }

  private int len(double x1, double y1, double x2, double y2) {
    var accurate = Math.sqrt(Math.abs(x1 - x2) + Math.abs(y2 - y1));
    return ((int) (accurate + 0.5));
  }
}
