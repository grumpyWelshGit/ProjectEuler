package uk.org.landeg.projecteuler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SolutionFactoryImpl implements SolutionFactory {
	@Autowired
	private ApplicationContext appContext;
	
	@Override
	public <T> ProblemSolution<T> obtainSolution (final ProblemDescription description) {
		final ProblemSolutionImpl<T> solution = (ProblemSolutionImpl<T>) appContext.getBean(ProblemSolution.class);
		solution.setDescription(description);
		return solution;
	}
}

