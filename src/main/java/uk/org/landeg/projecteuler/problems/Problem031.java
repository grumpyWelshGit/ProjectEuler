package uk.org.landeg.projecteuler.problems;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.ProblemDescription;

@Component
@Order(31)
public class Problem031 implements ProblemDescription<Integer>{

	@Override
	public String getTask() {
		return "How many different ways can £2 be made using any number of coins?";
	}

	@Override
	public String getDescribtion() {
		return "It is possible to make £2 in the following way: 1×£1 + 1×50p + 2×20p + 1×5p + 1×2p + 3×1p";
	}

	@Override
	public Integer solve() {
		final int target = 200;
		final int[] coinsValues = new int[] {200,100,50,20,10,5,2,1};
		final int coinCount[] = new int [coinsValues.length];
		final int maxCount [] = new int [coinsValues.length];
		
		int combinations = 0;
		// max counts
		for (int i = 0 ; i < coinsValues.length ; i++) {
			maxCount[i] = target / coinsValues[i];
		}
		int sum;
		for (int c200 = 0 ; c200 <= 1 ; c200++) {
			for (int c100 = 0 ; c100 <= 2 ; c100++) {
				for (int c50 = 0 ; c50 <= 4 ; c50++) {
					for (int c20 = 0 ; c20 <= 10 ; c20++) {
						for (int c10 = 0 ; c10 <= 20 ; c10++) {
							for (int c5 = 0 ; c5 <= 40 ; c5++) {
								for (int c2 = 0 ; c2 <= 100 ; c2++) {
									for (int c1 = 0 ; c1 <= 200 ; c1++) {
										sum = 200 * c200
												+ 100 * c100
												+ 50 * c50
												+ 20 * c20
												+ 10 * c10
												+ 5 * c5
												+ 2 * c2
												+ c1;
										if (sum == 200) {
											combinations++;
											break;
										}
										if (sum > 200) {
											break;
										}
									}					
								}					
							}					
						}					
					}					
				}
			}
		}
		return combinations;
	}

}
