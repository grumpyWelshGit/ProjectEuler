package uk.org.landeg.projecteuler.problems;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.PrimeLib;
import uk.org.landeg.projecteuler.ProblemDescription;

@Component
@Order(71)
public class Problem072 implements ProblemDescription<Long>{
	private static final Logger LOG = LoggerFactory.getLogger(Problem071.class);

	@Override
	public String getTask() {
		return "How many elements would be contained in the set of reduced proper fractions for d ≤ 1,000,000?\n";
	}

	@Override
	public String getDescribtion() {
		// TODO Auto-generated method stub
		return null;
	}

	private static final int TARGET = 1000000;
	@Override
	public Long solve() {
		final List<Integer> primes =
				PrimeLib.primes(TARGET)
				.stream()
				.collect(Collectors.toList());
		final long[] totients = new long[TARGET + 1];
		for (int i = 2; i < totients.length ; i++) {
			totients[i] = i;
		}
		primes.stream().forEach(p -> {
			int n = p;
			do {
				totients[n] /= (long)p;
				totients[n] *= (long)p - 1l;
				n+=p;
			} while (n <= TARGET);
		});
		
		long sum = 0;
		for (int i = 2; i < totients.length ; i++) {
			sum += totients[i];
		}

		return sum;
	}

}