package uk.org.landeg.projecteuler.problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.PrimeLib;
import uk.org.landeg.projecteuler.ProblemDescription;

@Component
@Order(50)
@Slf4j
public class Problem050 implements ProblemDescription<Integer>{

	@Override
	public String getTask() {
		return "Which prime, below one-million, can be written as the sum of the most consecutive primes?";
	}

	@Override
	public String getDescribtion() {
		// TODO Auto-generated method stub
		return "The prime 41, can be written as the sum of six consecutive primes:\n"+
			"41 = 2 + 3 + 5 + 7 + 11 + 13\n"+
			"This is the longest sum of consecutive primes that adds to a prime below one-hundred.\n"+
			"The longest sum of consecutive primes below one-thousand that adds to a prime, contains 21 terms, and is equal to 953.";
	}

	@Override
	public Integer solve() {
		final int max = 1000000;
		final Set<Integer> primesSet  = PrimeLib.primes(max);
		final List<Integer> primes = new ArrayList<>(primesSet);
		
		int maxSum = 0;
		int maxCount = 0;
		List<Integer> primeList = new ArrayList<>();
		for (int p1 = 0 ; p1 < primes.size() ; p1++) {
			primeList.clear();
			int sum = primes.get(p1);
			primeList.add(sum);
			for (int p2 = p1 + 1 ; p2 < primes.size() ; p2++) {
				primeList.add(primes.get(p2));
				sum += primes.get(p2);
				if (sum > max) {
					break;
				}
				if (primesSet.contains(sum)) {
					if (primeList.size() > maxCount) {
						maxSum = sum;
						maxCount = primeList.size();
						log.debug("prime sum {} : {}", sum, primeList);
					}
				}
			}
		}

		return maxSum;
	}

}
