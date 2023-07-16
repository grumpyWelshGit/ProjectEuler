package uk.org.landeg.projecteuler.problems;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.ProblemDescription;

@Component
@Order(9)
public class Problem009 implements ProblemDescription<Integer>{

	@Override
	public String getTask() {
		return "There exists exactly one Pythagorean triplet for which a + b + c = 1000. Find the product abc.";
	}

	@Override
	public String getDescribtion() {
		// TODO Auto-generated method stub
		return "A Pythagorean triplet is a set of three natural numbers, a < b < c, for which, a^2+b^2=c^2";
	}

	@Override
	public Integer solve() {

		int a = 1,b = 1,c = 1;
		int a2 , b2, c2;
		for (c = 1 ; c < 1000; c++) {
			c2 = c *c;
			for (b = 1 ; b < c ; b++) {
				b2 = b * b;
				for (a = 1 ; a < c ; a++) {
					a2 = a * a;
					if (a + b + c == 1000 && a2 + b2 == c2) {
						return a * b * c;
					}
				}
			}
		}
		return null;
	}
	
}
