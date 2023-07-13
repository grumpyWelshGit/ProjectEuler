package uk.org.landeg.projecteuler.problems;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class Problem10xTest {
  @Test
  void testProblem102Solution() {
    assertEquals(228, new Problem102().solve().intValue());
  }
}
