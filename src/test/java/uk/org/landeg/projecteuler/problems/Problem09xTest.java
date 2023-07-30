package uk.org.landeg.projecteuler.problems;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import uk.org.landeg.projecteuler.Mathlib;

class Problem09xTest {
  @Test
  void assertProblem090Solution() {
    assertEquals(1217, new Problem090().solve().longValue());
  }

  @Test
  void assertProblem091Solution() {
    assertEquals(14234L, new Problem091().solve().longValue());
  }

  @Test
  void assertProblem092Solution() {
    assertEquals(8581146, new Problem092().solve().longValue());
  }

  @Test
  void assertProblem093Solution() {
    assertEquals(0, new Problem093().solve().longValue());
  }

  @Test
  void assertProblem094Solution() {
    assertEquals(518408346L, new Problem094().solve().longValue());
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
