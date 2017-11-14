package uk.org.landeg.projecteuler;

public interface ProblemContext {

	void setSolution(ProblemSolution<?> solution);

	ProblemSolution<?> getSolution();

}
