package uk.org.landeg.projecteuler;

import java.math.BigInteger;

public class Convergent {
	private final BigInteger n;
	private final BigInteger d;
	public Convergent(BigInteger n, BigInteger d) {
		super();
		this.n = n;
		this.d = d;
	}

	public BigInteger getN() {
		return n;
	}
	public BigInteger getD() {
		return d;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Convergent [n=").append(n).append(", d=").append(d).append("]");
		return builder.toString();
	}
}
