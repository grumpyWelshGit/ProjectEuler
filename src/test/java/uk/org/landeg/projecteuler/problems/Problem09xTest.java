package uk.org.landeg.projecteuler.problems;

import org.junit.Assert;
import org.junit.Test;

public class Problem09xTest {
  @Test
  public void assertProblem091Solution () {
    Assert.assertEquals(0l, new Problem091().solve().longValue());
  }

  @Test
  public void assertProblem092Solution () {
    Assert.assertEquals(8581146, new Problem092().solve().longValue());
  }
  
  @Test
  public void assertProblem094Solution () {
    Assert.assertEquals(518408346l, new Problem094().solve().longValue());
  }

  @Test
  public void assertProblem095Solution () {
    Assert.assertEquals(14316, new Problem095().solve().longValue());
  }

  @Test
  public void assertProblem096Solution () {
    Assert.assertEquals(24702, new Problem096().solve().longValue());
  }

  @Test
  public void assertProblem097Solution () {
    Assert.assertEquals(8739992577l, new Problem097().solve().longValue());
  }

  @Test
  public void assertProblem098Solution () {
    Assert.assertEquals(18769, new Problem098().solve().longValue());
  }
}
