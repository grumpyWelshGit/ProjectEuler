package uk.org.landeg.projecteuler.problems;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Problem001Test {
  @Test
  void assertSolution() {
		assertEquals(233168, new Problem001().solve().intValue());
	}
}
