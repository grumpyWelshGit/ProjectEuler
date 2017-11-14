package uk.org.landeg.projecteuler;

public interface ProblemDescription<T> {
	String getTask();
	String getDescribtion ();
	T solve();
}
