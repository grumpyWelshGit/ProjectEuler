package uk.org.landeg.projecteuler.problems;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.Mathlib;
import uk.org.landeg.projecteuler.PrimeLib;
import uk.org.landeg.projecteuler.ProblemDescription;

@Component
@Order(60)
@Slf4j
public class Problem060 implements ProblemDescription<Long>{


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
	List<Integer> primeList1 = new ArrayList<>();
	List<Integer> primeList2 = new ArrayList<>();
	List<Integer> primeListInUse;
	Set<Integer> allPrimes;
	
	private List<List<Integer>> setsDiscovered = new ArrayList<>();
	static final int TARGET_PRIME_COUNT = 5;
	static final int MAX_CANDIDATE = 10000;
	static final int MAX_PRIME = MAX_CANDIDATE * MAX_CANDIDATE;


	private boolean concatinatesToPrime (final int n, final int m) {
		return (isPrime(Mathlib.concatinate(n, m)) && isPrime(Mathlib.concatinate(m, n)));
	}

	@Override
	public Long solve() {
		log.debug("Generating prime list");
		allPrimes = PrimeLib.primes(MAX_CANDIDATE);
		primesList.addAll(allPrimes);
		log.debug("Finished Generating prime list");

		log.debug("Segregating primes");
		primeList1 = primesList
				.stream()
				.filter(x -> x != 5)
				.filter(x -> x % 3 == 1)
				.collect(Collectors.toList());
		primeList2 = primesList
				.stream()
				.filter(x -> x != 5)
				.filter(x -> x % 3 == 2)
				.collect(Collectors.toList());
		log.debug("Finished segregating primes");

//		primesList = primeList1;
		log.debug("Generating concatinable list");
		final Map<Integer, List<Integer>> contatinatable = new LinkedHashMap<>();
		for (int n = 1 ; n < primesList.size() ; n++) {
			final int prime1 = primesList.get(n);
			if (prime1 > MAX_CANDIDATE) {
				break;
			}
			for (int m = n + 1 ; m < primesList.size() ; m++) {
				final int prime2 = primesList.get(m);
				if (prime2 > MAX_CANDIDATE) {
					break;
				}
				if (concatinatesToPrime(prime1, prime2)) {
					log.trace("discovered {} and {} are concatinatable", prime1,prime2);
					if (contatinatable.containsKey(prime1)) {
						contatinatable.get(prime1).add(prime2);
					}
					else {
						final List<Integer> list = new ArrayList<>();
						list.add(prime2);
						contatinatable.put(prime1, list);
					}
				}
			}
		}
		searchConcatination (contatinatable, new ArrayList<Integer>());
		final AtomicInteger minSumHolder= new AtomicInteger(0);
		setsDiscovered
			.stream()
			.mapToInt(set -> set.stream().reduce(0, Integer::sum))
			.reduce(Integer::min)
			.ifPresent(v -> minSumHolder.set(v));
		log.info("Lowest sum set {} " , minSumHolder.get());
		return (long)minSumHolder.get();
	}

	private boolean isPrime (final int n) {
		if (n < MAX_CANDIDATE) {
			return allPrimes.contains(n);
		}
		else if (n > MAX_CANDIDATE * MAX_CANDIDATE) {
			throw new RuntimeException("Number too big for prime test");
		}
		else {
			final int maxCheck = (int) Math.sqrt(n) + 1;
			boolean isPrime = true;
			for (int p : allPrimes) {
				if (n % p == 0) {
					isPrime = false;
					break;
				}
				if (p > maxCheck) {
					break;
				}
			}
			return isPrime;
		}
	}

	private void searchConcatination(
			Map<Integer, List<Integer>> contatinatable, 
			ArrayList<Integer> arrayList) {
		boolean continueSearching = false;
		if (arrayList.size() == TARGET_PRIME_COUNT) {
			log.debug("discovered latest set {} ", arrayList);
			setsDiscovered.add(new ArrayList<>(arrayList));
			continueSearching = true;
		}
		Integer latestPrime = null;
		if (arrayList.isEmpty()) {
			for (Integer firstPrime : contatinatable.keySet()) {
				arrayList.add(firstPrime);
				searchConcatination(contatinatable, arrayList);
				arrayList.remove(firstPrime);
			}
		}
		else {
			latestPrime = arrayList.get(arrayList.size() - 1);
		}
		final List<Integer> primeCandidateList = contatinatable.get(latestPrime);
		if (primeCandidateList != null) {
			for (Integer nextPrime : primeCandidateList) {
				boolean concatinatable = true;
				for (Integer i : arrayList) {
					if (!i.equals(latestPrime)) {
						if (!contatinatable.get(i).contains(nextPrime)) {
							concatinatable = false;
							break;
						}
					}
				}
				if (concatinatable) {
					arrayList.add(nextPrime);
					searchConcatination(contatinatable, arrayList);
					arrayList.remove(nextPrime);
				}
			}
		}
		if (continueSearching) {
			arrayList.remove(arrayList.size()-1);
		}
	}
	
}
