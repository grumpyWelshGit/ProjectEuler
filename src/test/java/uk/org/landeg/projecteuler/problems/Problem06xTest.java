package uk.org.landeg.projecteuler.problems;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Problem06xTest {
  @Test
  void assertSolutions() {
		assertEquals(Long.valueOf(26033), new Problem060().solve());
		assertEquals(Long.valueOf(28684), new Problem061().solve());
		assertEquals(Long.valueOf(127035954683l), new Problem062().solve());
		assertEquals(Integer.valueOf(49), new Problem063().solve());
		
	}
	
  @Test
  void assertProblem064Test() {
		assertEquals(Integer.valueOf(1322), new Problem064().solve());
	}
  @Test
  void assertProblem065Test() {
		assertEquals(Integer.valueOf(272), new Problem065().solve());
	}

  @Test
  void assertProblem066Test() {
		assertEquals(Integer.valueOf(661), new Problem066().solve());
	}
  @Test
  void assertProblem067Test() {
		assertEquals (Integer.valueOf(7273), new Problem067().solve());
	}
	
  @Test
  void assertProblem068() {
		assertEquals(new String("6531031914842725"), new Problem068().solve());
	}
  @Test
  void assertProblem069Test() {
		assertEquals (Integer.valueOf(510510), new Problem069().solve());
	}

}
