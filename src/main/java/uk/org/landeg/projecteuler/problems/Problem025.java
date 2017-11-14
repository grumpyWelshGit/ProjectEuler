package uk.org.landeg.projecteuler.problems;

import java.math.BigInteger;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.ProblemDescription;

@Order(25)
@Component
public class Problem025 implements ProblemDescription<Integer>{

	@Override
	public String getTask() {
		return "What is the index of the first term in the Fibonacci sequence to contain 1000 digits?";
	}

	@Override
	public String getDescribtion() {
		return "The Fibonacci sequence is defined by the recurrence relation f1=1 f2=1 f3=2...";
	}

	@Override
	public Integer solve() {
		BigInteger a = BigInteger.ONE, b = BigInteger.ONE, next;
		int len = 0;
		int term = 2;
		while (len < 1000) {
			next = a.add(b);
			a = b;
			b = next;
			len = next.toString().length();
			term++;
		} ;
		return term;
	}

}
