package uk.org.landeg.projecteuler.problems;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


class Problem07xTest {
  @Test
  void assertProblem67Solution() {
		assertEquals(2772, new Problem085().solve().intValue());
		
	}
	
  @Test
  void assertProblem70Solution() {
		assertEquals(8319823, new Problem070().solve().intValue());
	}
  @Test
  void assertProblem71Solution() {
		assertEquals(428570, new Problem071().solve().intValue());
	}
  @Test
  void assertProblem72Solution() {
		assertEquals(303963552391l, new Problem072().solve().longValue());
	}
  @Test
  void assertProblem73Solution() {
		assertEquals(7295372l, new Problem073().solve().longValue());
	}
  @Test
  void assertProblem74Solution() {
		assertEquals(402, new Problem074().solve().longValue());
	}
  @Test
  void assertProblem75Solution() {
		assertEquals(161667, new Problem075().solve().longValue());
	}
  @Test
  void assertProblem76Solution() {
		assertEquals(190569291, new Problem076().solve().longValue());
	}
	
  @Test
  void assertProblem77Solution() {
		assertEquals(71, new Problem077().solve().intValue());
	}

  @Test
  void assertProblem78Solution() {
		assertEquals(55374, new Problem078().solve().intValue());
	}

  @Test
  void assertProblem79Solution() {
		assertEquals("73162890", new Problem079().solve());
	}
}
