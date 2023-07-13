package uk.org.landeg.projecteuler.problems;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


class Problem08xTest {
  @Test
  void assertProblem080Solution() {
		assertEquals(40886, new Problem080().solve().intValue());
	}
  @Test
  void assertProblem081Solution() {
		assertEquals(427337, new Problem081().solve().intValue());
	}
    @Test
    public void assertProblem082Solution () {
        assertEquals(260324, new Problem082().solve().intValue());
    }
    @Test
    public void assertProblem083Solution () {
        assertEquals(425185, new Problem083().solve().intValue());
    }
    @Test
    public void assertProblem084Solution () {
      assertEquals(101524, new Problem084().solve().intValue());
    }
    @Test
    public void assertProblem086Solution () {
      assertEquals(1818, new Problem086().solve().intValue());
    }
  @Test
  void assertProblem085Solution() {
		assertEquals(2772, new Problem085().solve().intValue());
	}

    @Test
    public void assertProblem087Solution () {
        assertEquals(1097343, new Problem087().solve().intValue());
    }

    @Test
    public void assertProblem088Solution () {
        assertEquals(7587457, new Problem088().solve().intValue());
    }
  @Test
  void assertProblem089Solution() {
		assertEquals(743, new Problem089().solve().intValue());
	}
}
