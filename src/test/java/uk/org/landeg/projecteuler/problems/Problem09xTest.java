package uk.org.landeg.projecteuler.problems;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class Problem09xTest {
  @Test
  void assertProblem091Solution() {
    assertEquals(0l, new Problem091().solve().longValue());
  }

  @Test
  void assertProblem092Solution() {
    assertEquals(8581146, new Problem092().solve().longValue());
  }
  
  @Test
  void assertProblem094Solution() {
    assertEquals(518408346l, new Problem094().solve().longValue());
  }

  @Test
  void assertProblem095Solution() {
    assertEquals(14316, new Problem095().solve().longValue());
  }

  @Test
  void assertProblem096Solution() {
    assertEquals(24702, new Problem096().solve().longValue());
  }

  @Test
  void assertProblem097Solution() {
    assertEquals(8739992577l, new Problem097().solve().longValue());
  }

  @Test
  void assertProblem098Solution() {
    assertEquals(18769, new Problem098().solve().longValue());
  }
}
