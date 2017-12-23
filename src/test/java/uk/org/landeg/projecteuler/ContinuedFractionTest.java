package uk.org.landeg.projecteuler;

import java.lang.Thread.State;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;



public class ContinuedFractionTest {
	@Test
	public void evaluate () {
		final List<Long> source = Arrays.asList(new Long[] {1l,2l,3l});
		final int expectedN = 10;
		final int expectedD = 7;
		
		final Convergent convergent = ContinuedFraction.evaluate(source);
		Assert.assertEquals(expectedN, convergent.getN().intValue());
		Assert.assertEquals(expectedD, convergent.getD().intValue());
	}

	@Test
	public void evaluateRoot2 () {
		final List<Long> source = Arrays.asList(new Long[] {1l,2l,2l,2l});
		final int expectedN = 17;
		final int expectedD = 12;
		
		final Convergent convergent = ContinuedFraction.evaluate(source);
		Assert.assertEquals(expectedN, convergent.getN().intValue());
		Assert.assertEquals(expectedD, convergent.getD().intValue());
	}
	
	@Test
	public void assertEvaluateNextForRoot2 () {
		final int a0 = (int)Math.sqrt(2);
		final ConvergentState state = new ConvergentState(2,1,1);
		ConvergentState nextState = ContinuedFraction.evaluateNext(state);
		System.out.println(nextState);
		nextState = ContinuedFraction.evaluateNext(nextState);
		System.out.println(nextState);
	}
	
	@Test
	public void assertExpansionOfRoot7 () {
		ConvergentState state = new ConvergentState(7,1,2);
		final List<BigInteger> cf = new ArrayList<>();
		for (int idx = 0 ; idx < 20 ; idx++) {
			state = ContinuedFraction.evaluateNext(state);
			cf.add(state.getA());
		}
		System.out.println(cf.toString());
	}
	
	
	@Test
	public void assertExpansionOfRoot2 () {
		ConvergentState state = new ConvergentState(2,1,1);
		final List<BigInteger> cf = new ArrayList<>();
		for (int idx = 0 ; idx < 20 ; idx++) {
			cf.add(state.getA());
			state = ContinuedFraction.evaluateNext(state);
		}
		System.out.println(cf.toString());
	}
	
	@Test
	public void assertExpansionOfRoot3 () {
		ConvergentState state = new ConvergentState(3,1,1);
		final List<BigInteger> cf = new ArrayList<>();
		for (int idx = 0 ; idx < 20 ; idx++) {
			state = ContinuedFraction.evaluateNext(state);
			cf.add(state.getA());
		}
		System.out.println(cf.toString());
	}
	
	@Test
	public void assertRoot60AsExpected () {
		ConvergentState s0 = new ConvergentState(50);
		final List<Long> cf = new ArrayList<>();
		for (int idx =0  ; idx < 10 ; idx++) {
			cf.add(s0.getA().longValue());
			s0 = ContinuedFraction.evaluateNext(s0);
		}
		System.out.println(ContinuedFraction.evaluate(cf));
	}
}
