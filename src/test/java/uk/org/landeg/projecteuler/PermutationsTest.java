package uk.org.landeg.projecteuler;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static uk.org.landeg.projecteuler.Permutations.choose;

class PermutationsTest {
	@Test
	void assertPermutationsAsExpected() {
		assertArrayEquals(new char[]{'0','1','2','3'}, Permutations.lexiograph(new char[] {'0','1','2','3'}, 0));
		assertArrayEquals(new char[]{'1','3','0','2'}, Permutations.lexiograph(new char[] {'0','1','2','3'}, 10));
	}

	@Test
	void chooseHasNextTest() {
		var selectionContext = new Permutations.UniqueSelectionContext<>(List.of(0,1,2,3,4).toArray(), 5);
		assertFalse(selectionContext.hasNext());
		selectionContext = new Permutations.UniqueSelectionContext<>(List.of(0,1,2,3,4,5).toArray(), 5);
		assertTrue(selectionContext.hasNext());
	}

	@Test
	void chooseNextTest() {
		var selectionContext = new Permutations.UniqueSelectionContext<>(List.of(0,1,2,3).toArray(), 3);
		assertEquals( List.of(0,1,2), choose(selectionContext));
		assertEquals( List.of(0,1,3), choose(selectionContext));
		assertEquals( List.of(0,2,3), choose(selectionContext));
		assertEquals( List.of(1,2,3), choose(selectionContext));
		assertNull(choose(selectionContext));
	}
}
