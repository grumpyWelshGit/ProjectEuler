package uk.org.landeg.projecteuler.problems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import uk.org.landeg.projecteuler.ProblemDescription;

@Order(97)
@Component
public class Problem097 implements ProblemDescription<Long>{
  private static final Logger LOG = LoggerFactory.getLogger(Problem097.class);
  @Override
  public String getTask() {
    return "However, in 2004 there was found a massive non-Mersenne prime which contains 2,357,207 digits: 28433×27830457+1.\n" + 
        "\n" + 
        "Find the last ten digits of this prime number.\n" + 
        "\n" + 
        ""; 
  }

  @Override
  public String getDescribtion() {
    return "Large non-Mersenne prime";
  }

  @Override
  public Long solve() {
    long n = 2;
    int p = 1;
    do {
      n *= 2;
      n %= 1000000000000l;
      p++;
    } while (p < 7830457);
    n *= 28433;
    n += 1;
    final long result = n % 10000000000l;
    LOG.info("{}",result);
    return result;
  }
}
