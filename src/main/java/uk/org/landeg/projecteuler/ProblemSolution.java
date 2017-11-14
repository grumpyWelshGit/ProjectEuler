package uk.org.landeg.projecteuler;

import java.util.List;

public interface ProblemSolution<T> {
	public List<String> getNotes ();
	public T getSolution ();
	public ProblemDescription<T> getDescription ();
	long getStartTime();
	long getEndTime();
}
