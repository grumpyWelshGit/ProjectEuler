package uk.org.landeg.projecteuler;

import org.junit.Assert;
import org.junit.Test;

public class PandigitalGeneratorTest {
	@Test
	public void assertPandigitalCreation () {
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
	public void assertHasNextTrueOnHasNext () {
		final PandigitalGenerator generator = new PandigitalGenerator(new int[] {0,1,2,5,4,3});
		Assert.assertTrue(generator.hasNext());
	}

	@Test
	public void assertHasNextFalseOnSequenceEnd () {
		final PandigitalGenerator generator = new PandigitalGenerator(new int[] {5,4,3,2,1});
		Assert.assertFalse(generator.hasNext());
	}
	
	@Test
	public void assertHasNextTrueOnMidSequence () {
		final PandigitalGenerator generator = new PandigitalGenerator(new int[] {1, 9, 8, 7, 6, 5, 4, 3, 2, 0});
		Assert.assertTrue(generator.hasNext());
		
	}
}
