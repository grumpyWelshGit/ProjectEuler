package uk.org.landeg.projecteuler;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class PermutationsTest {
  @Test
  void assertPermutationsAsExpected() {
		assertArrayEquals(new char[]{'0','1','2','3'}, Permutations.lexiograph(new char[] {'0','1','2','3'}, 0));
		assertArrayEquals(new char[]{'1','3','0','2'}, Permutations.lexiograph(new char[] {'0','1','2','3'}, 10));
	}
}
