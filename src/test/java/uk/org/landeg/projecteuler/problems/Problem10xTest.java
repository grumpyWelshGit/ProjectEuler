package uk.org.landeg.projecteuler.problems;

import org.junit.Assert;
import org.junit.Test;

public class Problem10xTest {
  @Test
  public void testProblem102Solution () {
    Assert.assertEquals(228, new Problem102().solve().intValue());
  }
}
