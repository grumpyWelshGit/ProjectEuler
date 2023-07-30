package uk.org.landeg.projecteuler;

import java.math.BigDecimal;


import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import uk.org.landeg.projecteuler.ProblemDescription;

@Slf4j
//@Order(91)
//@Component
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
    long pTotal = 0l;
    long p;
    double h;
    double asq;
    double a;
    int n = 1;
    int max = 1000000000;
    final BigDecimal bd025 = BigDecimal.valueOf(.25d);
    final BigDecimal bd05 = BigDecimal.valueOf(.5d);
    do {
      p = 0;
      int m = n - 1;
      BigDecimal bdN = BigDecimal.valueOf((double) n);
      BigDecimal bdNPlus1 = BigDecimal.valueOf((double) n + 1d);
      BigDecimal bdNSq = bdN.multiply(bdN);
      a = 0.5d * n * n * Math.sqrt( (n+1d)*(n+1d)/n*n - 0.25d );
      if (a > 0 && a == (long) a) {
        p = 3l * n - 2;
        if (p < max) {
          log.debug("{} {} {} {}", m,n,p,pTotal);
          pTotal += p;
        }
      }
      m = n + 1;
      a = 0.5d * n * n * Math.sqrt( (n+1d)*(n+1d)/n*n - 0.25d );
      if (a > 0 && a == (long) a) {
        p = 3l * n - 2;
        if (p < max) {
          log.debug("{} {} {} {}", m,n,p,pTotal);
          pTotal += p;
        }
      }      
//      m = n + 1;
//      h = Math.sqrt((double)(m*m - n*n*.25d));
//      a = 0.5d * n * h;
//      if (a > 0 && a == (long) a) {
//        p = 3 * n - 2;
//        if (p < max) {
//          log.debug("{} {} {} {}", m,n,p,pTotal);
//          pTotal += p;
//        }
//      }
      n++;
    } while (3*n-2 < 1000000000);
    log.info("pTotal {}", pTotal);
    return pTotal;
  }
//  518408346
//  536870910
}
