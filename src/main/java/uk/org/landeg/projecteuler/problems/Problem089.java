package uk.org.landeg.projecteuler.problems;

import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import uk.org.landeg.projecteuler.FileLoader;
import uk.org.landeg.projecteuler.ProblemDescription;
import uk.org.landeg.projecteuler.RomanNumeral;

@Component
@Order(89)
public class Problem089 implements ProblemDescription<Integer> {
  private static final Logger LOG = LoggerFactory.getLogger(Problem089.class);

  @Override
  public String getTask() {
    return "Find the number of characters saved by writing each of these in their minimal form";
  }

  @Override
  public String getDescribtion() {
    return "For a number written in Roman numerals to be considered valid there are basic rules which must be followed";
  }

  @Override
  public Integer solve() {
    AtomicInteger charsInRef = new AtomicInteger();
    AtomicInteger charsOutRef = new AtomicInteger();
    FileLoader.readLines("p089_roman.txt").stream().forEach(numAsString -> {
      charsInRef.set(charsInRef.get() + numAsString.length());
      int eval = RomanNumeral.evaluate(numAsString);
      final String asNumerals = RomanNumeral.toString(eval);
      charsOutRef.set(charsOutRef.get() + asNumerals.length());
      LOG.debug("{} -> {} ({} {}) ... {} -> {}", 
          numAsString, asNumerals, eval, RomanNumeral.evaluate(asNumerals), 
          numAsString.length(), asNumerals.length());
    });
    return charsInRef.get() - charsOutRef.get();
  }
}
