package uk.org.landeg.projecteuler.problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import uk.org.landeg.projecteuler.PrimeLib;
import uk.org.landeg.projecteuler.ProblemDescription;

@Order(94)
@Component
@Slf4j
public class Problem095 implements ProblemDescription<Integer>{

  @Override
  public String getTask() {
    return "Find the smallest member of the longest amicable chain with no element exceeding one million"; 
  }

  @Override
  public String getDescribtion() {
    return "Amicable chains";
  }

  @Override
  public Integer solve() {
    int result = 0;
    int longestChain = 0;
    final int max = 1000000;
    List<Integer> chain = new ArrayList<>();
    final int[] sumOfDivisors = new int[max]; 
    final int[] knownLengths = new int[max];
    final Set<Integer> primes = PrimeLib.primes(max);
    int i;
    
    log.debug("Calculating sum of divisors");
    for (int n = 1 ; n < max / 2 ; n++) {
      int m = 2;
      int p;
      do {
        p = n * m;
        if (p < max) {
          sumOfDivisors[p] += n;
        } else {
          break;
        }
        m++;
      } while (true);
    }
    log.debug("Finished Calculating sum of divisors");
    for (int n = 4 ; n < max ; n++) {
      i = n;
      chain.clear();
      boolean bust = false;
      do {
        chain.add(i);
        final long iAsLong = sumOfDivisors[i];
        if (iAsLong > max) {
          bust = true;
          break;
        }
        if (i == 1) {
          bust = true;
        }
        i = (int) iAsLong;
      } while (!chain.contains(i) && !bust);
      if (!bust) {
        if (chain.size() > 3) {
        }
        if (chain.get(0).equals(i)) {
          if (chain.size() > longestChain) {
            longestChain = chain.size();
            result = chain.stream().mapToInt(x->x).min().getAsInt();
            log.debug("{} {} {}", chain.size(), result, chain);
          }
        }
      }
    }
    return result;
  }
}
