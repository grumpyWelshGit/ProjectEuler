package uk.org.landeg.projecteuler.problems;

import java.util.ArrayList;
import java.util.List;


import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import uk.org.landeg.projecteuler.ProblemDescription;

@Order(91)
@Component
@Slf4j
public class Problem092 implements ProblemDescription<Integer>{

  static final int max = 10000000;
//  static final int max = 100;
  static int[] squares = {0,1,4,9,16,25,36,49,64,81};
  static int[] endNumbers = new int[1000];
  @Override
  public String getTask() {
    return "How many starting numbers below ten million will arrive at 89";
  }

  @Override
  public String getDescribtion() {
    return "Square digit chains";
  }

  @Override
  public Integer solve() {
    int count = 0;
    long iMax = 0;
    List<Long> chain = new ArrayList<>();
    long i;
    for (int idx = 1; idx < max ; idx++) {
      chain.clear();
      i = idx;
      do {
        chain.add(i);
        i = digitSquareSum(i);
        if (i < endNumbers.length && endNumbers[(int)i] > 0) {
          i=endNumbers[(int)i];
        }
        iMax = i > iMax ? i : iMax;
        if (i < 0) {
          throw new RuntimeException("overflow for idx = " + idx);
        }
      } while (i != 1 && i != 89);
      if (i == 89) {
        count++;
      } 
      final int iResult = (int)i;
      chain.stream().forEach(x -> {
        if (x.intValue() < endNumbers.length) {
          endNumbers[x.intValue()] = iResult;
        }
      });
      if (log.isDebugEnabled()) {
        if (chain.size() > 1) {
          log.debug("{}->{}    {}",idx, i, chain);
        }
      }
    }
    log.info("iMax {}", iMax);
    log.info("{}", count);
    return count;
  }
  
  private long digitSquareSum (final long n) {
    long i = n;
    long sum = 0;
    do {
      sum += squares[(int)(i % 10)];
      i /= 10;
    } while (i > 0);
    return sum;
  }
}
