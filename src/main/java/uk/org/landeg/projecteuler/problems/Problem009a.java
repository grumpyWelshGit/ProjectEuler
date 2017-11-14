package uk.org.landeg.projecteuler.problems;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.ProblemContext;
import uk.org.landeg.projecteuler.ProblemDescription;

@Component
@Order(9)
public class Problem009a implements ProblemDescription<Integer>{
	@Autowired
	private ProblemContext problemContext;

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
		int n,m;
		for (m = 1; m < 1000 ; m++) {
			for (n = 1; n < m ; n++) {
				int perimiter = 2*m * (m + n); 
				if (perimiter > 1000) {
					break;
				}
				if (perimiter == 1000) {
					problemContext.getSolution().getNotes().add(String.format("m = %d n=%d", m,n));
					final int a = m*m - n*n;
					final int b = 2*m*n;
					final int c = m*m + n*n;
					problemContext.getSolution().getNotes().add(String.format("a = %d b=%d c=%d", a,b,c));
					return a * b * c; 
				}
			}
		}
		return null;
	}
	
}
