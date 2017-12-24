package uk.org.landeg.projecteuler.problems;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.PrimeLib;
import uk.org.landeg.projecteuler.ProblemDescription;

@Component
@Order(87)
public class Problem087 implements ProblemDescription<Integer>{
	private static final Logger LOG = LoggerFactory.getLogger(Problem087.class);

	@Override
	public String getTask() {
		return "How many numbers below fifty million can be expressed as the sum of a prime square, prime cube, and prime fourth power?";
	}

	@Override
	public String getDescribtion() {
		return "The smallest number expressible as the sum of a prime square, prime cube, and prime fourth power is 28";
	}

	private static final int MAX_TEST = 50;
	private static final int MAX_PROBLEM = 50000000;
	private static final int MAX = MAX_PROBLEM;
	
	@Override
	public Integer solve() {
		final List<Integer> cubes = new ArrayList<>();
		final List<Integer> quads = new ArrayList<>();
		final List<Integer> squares = new ArrayList<>();
		final int maxSquare = (int) Math.pow(MAX, 1d/2d);
		final int maxCube = (int) Math.pow(MAX, 1d/3d);
		final int maxQuad = (int) Math.pow(MAX, 1d/4d);
		final Set<Integer> primes = PrimeLib.primes(maxSquare);
		for (int n : primes) {
			int pow = n * n;
			squares.add(pow);
			if (n < MAX/pow) {
				pow *= n;
				cubes.add(pow);
				if (n < MAX/pow) {
					pow *= n;
					quads.add(pow);
				}
			}
		}
		LOG.debug("{}", squares);
		LOG.debug("{}", cubes);
		LOG.debug("{}", quads);
		LOG.info("{} {} {}", maxSquare*maxSquare, maxCube*maxCube*maxCube, maxQuad*maxQuad*maxQuad*maxQuad);
		int sum = 0;
		int count = 0;
		final Set<Integer> candidates = new HashSet<>();
		for (int q : quads) {
			for (int c : cubes) {
				if (MAX - q < c ) {
					break;
				}
				sum = q + c;
				for (int s : squares) {
					if (MAX - q < c + s) {
						break;
					}
					sum = s + c + q;
					LOG.debug("candidate {}", sum);
					if (candidates.add(sum)) {
						count++;
					}
				}
			}
		}
		return count;
	}

}
