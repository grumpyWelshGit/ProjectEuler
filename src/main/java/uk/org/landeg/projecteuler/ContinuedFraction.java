package uk.org.landeg.projecteuler;

import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;



@Slf4j
public final class ContinuedFraction {

	private ContinuedFraction() {}
	
	public static Convergent evaluate (Iterable<Long> source) {
		final List<Long> reversed = new ArrayList<>();
		final Iterator<Long> it = source.iterator();
		while (it.hasNext()) {
			reversed.add(it.next());
		}
		Collections.reverse(reversed);

		BigInteger n = BigInteger.ONE;
		BigInteger d = BigInteger.ZERO;
		log.debug("Evaluating continued fraction {}", source);
		for (long nextTermAsLong : reversed) {
			final BigInteger tempD = d;
			d = n;
			n = tempD;
			log.trace("flipped to {}/{}", n,d);
			final BigInteger nextTerm = BigInteger.valueOf(nextTermAsLong);
			log.trace("next term is {}", nextTerm);
			BigInteger nNext = n.add(nextTerm.multiply(d));
			BigInteger dNext = d;
			n = nNext;
			d = dNext;
			log.trace("current convergent is {}/{}", n,d);
		}
		final Convergent convergent = new Convergent(n, d);
		log.debug("Evaluated continued fraction {}", convergent);
		return convergent;
	}
	
	public static ConvergentState evaluateNext (ConvergentState start) {
		log.debug("evaluating next state for convergent {}", start);
		ConvergentState next;
		BigInteger yStart = start.getY().abs();
		BigInteger d = start.getR().subtract(start.getY().multiply(start.getY()));
		log.trace("d set to {}", d);
		BigInteger gcd;
		BigInteger n = new BigInteger(start.getX().toString());
		while ((gcd = d.gcd(n)).compareTo(BigInteger.ONE) > 0) {
			log.trace("reducing {}/{}",n,d);
			n = n.divide(gcd);
			d = d.divide(gcd);
			log.trace("reduced to {}/{}",n,d);
		}

		BigInteger a = n.multiply(start.getA0().add(yStart)).divide(d);
		BigInteger x = d;
		BigInteger y = yStart.subtract(d.multiply(a));
		next = new ConvergentState(start.getR(), x, y, start.getA0());
		next.setA(a);
		log.debug("evaluated next convergent state : {}", next);
		return next;
	}
}
