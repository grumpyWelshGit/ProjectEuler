package uk.org.landeg.projecteuler;

public interface SolutionFactory {

	<T> ProblemSolution<T> obtainSolution(ProblemDescription description);

}
