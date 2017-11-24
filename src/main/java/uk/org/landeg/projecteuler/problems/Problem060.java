package uk.org.landeg.projecteuler.problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.Mathlib;
import uk.org.landeg.projecteuler.PrimeLib;
import uk.org.landeg.projecteuler.ProblemDescription;
import uk.org.landeg.projecteuler.UniqueSolution;

@Component
@Order(60)
@UniqueSolution
public class Problem060 implements ProblemDescription<Long>{
	private static final Logger LOG = LoggerFactory.getLogger(Problem057.class);

	@Override
	public String getTask() {
		return "Find the lowest sum for a set of five primes for which any two primes concatenate to produce another prime. ";
	}

	@Override
	public String getDescribtion() {
		return "The primes 3, 7, 109, and 673, are quite remarkable. By taking any two primes and concatenating "
				+ "them in any order the result will always be prime. For example, taking 7 and 109, both 7109 and 1097 "
				+ "are prime. The sum of these four primes, 792, represents the lowest sum for a set of four primes "
				+ "with this property";
	}
	
	List<Integer> primesList = new ArrayList<>();
	final List<Integer> primeList1 = new ArrayList<>();
	final List<Integer> primeList2 = new ArrayList<>();
	List<Integer> primeListInUse;
	Set<Integer> allPrimes;
	
	private List<List<Integer>> setsDiscovered = new ArrayList<>();
	static final int TARGET_PRIME_COUNT = 5;
	static final int MAX_CANDIDATE = 10000;
	static final int MAX_PRIME = MAX_CANDIDATE * MAX_CANDIDATE;

	private void attemptPrimeConcatination (final List<Integer> list, final int primePosition) {
		LOG.trace("Attempting concatination for the set {}", list);
		for (int pos = primePosition ; pos < primeListInUse.size() ; pos++ ) {
			final int prime = primeListInUse.get(pos);
			if (prime > MAX_CANDIDATE) {
				return;
			}
			boolean concatinates = true;
			for (int currentPrime : list) {
				LOG.trace("Testing {} + {} ", list, prime);
				if (!concatinatesToPrime(currentPrime, prime)) {
					concatinates = false;
					break;
				}
			}
			if (list.isEmpty() || concatinates) {
				list.add(prime);
				if (list.size() == TARGET_PRIME_COUNT) {
					LOG.debug("Set discovered {} ({})", list, 0);
					setsDiscovered.add(new ArrayList<>(list));
					list.remove(list.size() - 1);
				}
				else {
					attemptPrimeConcatination(list, primePosition + 1);
					list.remove(list.size() - 1);
				}
			}
		}
		list.remove(list.size() - 1);
	}

	private boolean concatinatesToPrime (final int n, final int m) {
		return (allPrimes.contains(Mathlib.concatinate(n, m)) && allPrimes.contains(Mathlib.concatinate(m, n)));
	}

	@Override
	public Long solve() {
		int lowestSum = Integer.MAX_VALUE;
		LOG.debug("Generating prime list");
		allPrimes = PrimeLib.primes(MAX_PRIME);
		primesList.addAll(allPrimes);
		for (Integer p : allPrimes) {
			
			if (p == 3) {
				primeList1.add(p);
				primeList2.add(p);
			}
			final int mod = p % 3;
			if (mod == 1) {
				primeList1.add(p);
			} else {
				primeList2.add(p);
			}
			if (p > 100000) 
				break;
		}
		
		LOG.debug("Finished Generating prime list");
		primeListInUse = primeList1;
		List<Integer> lowestSumSet = null;
		attemptPrimeConcatination(new ArrayList<>(), 0);
		for (final List<Integer> set : setsDiscovered) {
			LOG.debug("Discovered {} ", set);
			Optional<Integer> sum = set.stream().reduce(new BinaryOperator<Integer>() {
				
				@Override
				public Integer apply(Integer t, Integer u) {
					return t + u;
				}
			});
			if (sum.isPresent()) {
				if (sum.get() < lowestSum) {
				lowestSum = sum.get();
				lowestSumSet = set;
				}
			}
		}
		LOG.info("Lowest sum set {} " , lowestSumSet);
		return (long)lowestSum;
	}
}
