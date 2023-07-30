package uk.org.landeg.projecteuler.problems;

import java.util.Arrays;


import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import uk.org.landeg.projecteuler.ProblemDescription;

@Component
@Order(87)
@Slf4j
public class Problem088 implements ProblemDescription<Integer> {


  @Override
  public String getTask() {
    return "What is the sum of all the minimal product-sum numbers for 2≤k≤12000?\n" + "\n" + "";
  }
  @Override
  public String getDescribtion() {
    return "Product-sum numbers  : A natural number, N, that can be written as the sum and product of a given set of at least two natural numbers, {a1, a2, ... , ak} is called a product-sum number: N = a1 + a2 + ... + ak = a1 × a2 × ... × ak";
  }

  private static final int MAX_PROBLEM = 50000000;
  private static final int MAX = MAX_PROBLEM;

  int kMax = 12000 + 1;
//  int kMax = 10 + 1;
  int kMin[] = new int[2*kMax];

  @Override
  public Integer solve() {
    productSum(1, 1, 1, 2);
    log.debug("{}", kMin);
    final int sum = Arrays.stream(kMin).distinct().sum() - kMin[0] - kMin[1];
    log.info("{}", sum);
    return sum; 
  }
  
  private void productSum (int p, int sum, int count, int start) {
    log.trace("{} {} {} {}", p, sum, count, start);
    int k = p - sum + count;
    if (k < kMax) {
      if (kMin[k] == 0 || p < kMin[k]) {
        kMin[k] = p;
        log.debug("setting kMin[{}]={}",k,p);
      }
      for (int i = start ; i <= 2* kMax/p + 1; i++) {
        productSum(p*i, sum+i, count+1, i);
      }
    }
  }
}
