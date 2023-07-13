package uk.org.landeg.projecteuler.problems;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class Problem002Test {
  @Test
  void assertCorrectSolution() {
		assertEquals(4613732, new Problem002().solve().intValue());
	}
}
