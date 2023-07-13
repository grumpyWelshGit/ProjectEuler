package uk.org.landeg.projecteuler;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PandigitalGeneratorTest {
  @Test
  void assertPandigitalCreation() {
		final PandigitalGenerator generator = new PandigitalGenerator(new int[] {0,1,2,5,4,3});
		generator.next();
		generator.next();
		generator.next();
		generator.next();
		generator.next();
		generator.next();
		generator.next();
		generator.next();
	}
	
  @Test
  void assertHasNextTrueOnHasNext() {
		final PandigitalGenerator generator = new PandigitalGenerator(new int[] {0,1,2,5,4,3});
		assertTrue(generator.hasNext());
	}

  @Test
  void assertHasNextFalseOnSequenceEnd() {
		final PandigitalGenerator generator = new PandigitalGenerator(new int[] {5,4,3,2,1});
		assertFalse(generator.hasNext());
	}
	
  @Test
  void assertHasNextTrueOnMidSequence() {
		final PandigitalGenerator generator = new PandigitalGenerator(new int[] {1, 9, 8, 7, 6, 5, 4, 3, 2, 0});
		assertTrue(generator.hasNext());
		
	}
}
