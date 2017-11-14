package uk.org.landeg.projecteuler;

public interface Solver {
	<T> ProblemSolution<T> solve (ProblemDescription<T> description);
}
