package uk.org.landeg.projecteuler.problems;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import uk.org.landeg.projecteuler.Mathlib;

class Problem10xTest {
  @Test
  void testProblem102Solution() {
    assertEquals(228, new Problem102().solve().intValue());
  }

  @Test
  void testProblem101Solution() {
    assertEquals(37076114526L, new Problem101().solve().longValue());
  }

  @Test
  void polyTest() {
    Mathlib.PointLong[] points = Mathlib.PointLong.of(1,1,2,8,3,27,4,64);

    long sum = 0;
    for (int degree = 1 ; degree <= points.length ; degree++) {
      for (int x = 1 ; x <= 10 ; x++) {
        var interpolated = Problem101.waringEvaluation(degree, x, points);
        long expected = x * x * x;
        System.out.println(String.format("d: %d x: %d   fx: %d", degree, x, interpolated));
        if (interpolated != expected) {
          sum += interpolated;
          break;
        }
      }
    }
    assertEquals(74, sum);
  }
}
