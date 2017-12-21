package uk.org.landeg.projecteuler;

import java.math.BigInteger;

/**
 * represents a convergent state in the form a + x / (sqrt(r) - y)
 * where a0 is an initial approximation to the square root of r.
 * 
 * @author andy
 *
 */
public class ConvergentState {
	private final BigInteger r;
	private final BigInteger a0;
	private final BigInteger x;
	private final BigInteger y;
	private BigInteger a;
	Convergent convergent;
	
	public ConvergentState (final int r) {
		this (r, 1, (int) Math.sqrt(r));
	}
	
	public ConvergentState (final int r, final int x, final int y) {
		final long rootAprox = (long) Math.sqrt(r);
		this.a0 = BigInteger.valueOf(rootAprox);
		this.a = BigInteger.valueOf(rootAprox);
		this.x = BigInteger.valueOf((long) x);
		this.y = BigInteger.valueOf((long) y);
		this.r = BigInteger.valueOf((long) r);
		convergent = new Convergent(a0, BigInteger.ONE);
	}

	public ConvergentState(
			final BigInteger r, 
			final BigInteger x, 
			final BigInteger y,
			final BigInteger a0) {
		this.r = r;
		this.x = x;
		this.y = y;
		this.a0 = a0;
	}

	public BigInteger getA() {
		return a;
	}

	public void setA(BigInteger a) {
		this.a = a;
	}

	public BigInteger getR() {
		return r;
	}

	public BigInteger getA0() {
		return a0;
	}

	public BigInteger getX() {
		return x;
	}

	public BigInteger getY() {
		return y;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ConvergentState [r=").append(r).append(", a0=").append(a0).append(", x=").append(x)
				.append(", y=").append(y).append(", a=").append(a).append("]");
		return builder.toString();
	}
	
	
}
