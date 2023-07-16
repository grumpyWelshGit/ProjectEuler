package uk.org.landeg.projecteuler.problems;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Problem00xTest {
	@Test
	void problem001Test() {
		assertEquals(Integer.valueOf(233168), new Problem001().solve());
	}
	@Test
	void problem002Test() {
		assertEquals(Long.valueOf(4613732), new Problem002().solve());
	}
	@Test
	void problem003Test() {
		assertEquals(Long.valueOf(6857), new Problem003().solve());
	}
	@Test
	void problem004Test() {
		assertEquals(Integer.valueOf(906609), new Problem004().solve());
	}
	@Test
	void problem005Test() {
		assertEquals(Integer.valueOf(232792560), new Problem005().solve());
	}
	@Test
	void problem006Test() {
		assertEquals(Long.valueOf(25164150), new Problem006().solve());
	}
	@Test
	void problem007Test() {
		assertEquals(Integer.valueOf(104743), new Problem007().solve());
	}
	@Test
	void problem008Test() {
		assertEquals(Long.valueOf(23514624000l), new Problem008().solve());
	}
	@Test
	void problem009Test() {
		assertEquals(Integer.valueOf(31875000), new Problem009().solve());
	}
}
