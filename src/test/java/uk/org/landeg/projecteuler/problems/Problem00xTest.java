package uk.org.landeg.projecteuler.problems;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Problem00xTest {
	@Test
	void assertSolutions () {
		assertEquals(Integer.valueOf(233168), new Problem001().solve());
		assertEquals(Long.valueOf(4613732), new Problem002().solve());
		assertEquals(Long.valueOf(6857), new Problem003().solve());
		assertEquals(Integer.valueOf(906609), new Problem004().solve());
		assertEquals(Integer.valueOf(232792560), new Problem005().solve());
		assertEquals(Long.valueOf(25164150), new Problem006().solve());
		assertEquals(Integer.valueOf(104743), new Problem007().solve());
		assertEquals(Long.valueOf(23514624000l), new Problem008().solve());
		assertEquals(Integer.valueOf(31875000), new Problem009().solve());
	}
}
