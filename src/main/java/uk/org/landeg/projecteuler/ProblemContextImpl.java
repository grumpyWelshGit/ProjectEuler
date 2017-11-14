package uk.org.landeg.projecteuler;

import org.springframework.stereotype.Component;

@Component
public class ProblemContextImpl implements ProblemContext {
	
	private ProblemSolution<?> solution;
	
	@Override
	public ProblemSolution<?> getSolution () {
		return solution;
	}
	
	@Override
	public void setSolution (final ProblemSolution<?> solution) {
		this.solution = solution;
	}
}
