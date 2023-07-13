package uk.org.landeg.projecteuler.problems;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Problem01xTest {
  @Test
  void assertSolutions() {
		assertEquals(Long.valueOf(142913828922l), new Problem010().solve());
		assertEquals(Long.valueOf(70600674), new Problem011().solve());
		assertEquals(Long.valueOf(76576500), new Problem012().solve());
		assertEquals(Long.valueOf(5537376230l), new Problem013().solve());
		assertEquals(Long.valueOf(837799), new Problem014().solve());
		assertEquals(Long.valueOf(137846528820l), new Problem015().solve());
		assertEquals(Long.valueOf(1366), new Problem016().solve());
		assertEquals(Integer.valueOf(21124), new Problem017().solve());
		assertEquals(Integer.valueOf(1074), new Problem018().solve());
		assertEquals(Integer.valueOf(171), new Problem019().solve());
	}
}
