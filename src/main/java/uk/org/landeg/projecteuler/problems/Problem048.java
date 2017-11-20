package uk.org.landeg.projecteuler.problems;

import java.math.BigInteger;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.ProblemDescription;

@Order(48)
@Component
public class Problem048 implements ProblemDescription<Long>{

	@Override
	public String getTask() {
		return "Find the last ten digits of the series, 11 + 22 + 33 + ... + 10001000.";
	}

	@Override
	public String getDescribtion() {
		// TODO Auto-generated method stub
		return "11 + 22 + 33 + ... + 1010 = 10405071317.";
	}

	@Override
	public Long solve() {
		BigInteger sum = BigInteger.ZERO;
		for (int idx = 1 ; idx < 1000 ; idx++) {
			sum = sum.add(BigInteger.valueOf(idx).pow(idx));
		}
		return Long.parseLong(sum.toString().substring(sum.toString().length() - 10));
	}
	

}
