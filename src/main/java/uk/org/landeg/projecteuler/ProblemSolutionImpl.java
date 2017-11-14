package uk.org.landeg.projecteuler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class ProblemSolutionImpl<T> implements ProblemSolution<T> {
	private ProblemDescription description;
	private List<String> notes = new ArrayList<>();
	private T solution;
	private long startTime;
	private long endTime;
	private long completionTimeMillis;
	
	@Override
	public List<String> getNotes() {
		return notes;
	}

	@Override
	public T getSolution() {
		return solution;
	}

	public void SetSolution (T solution) {
		this.solution = solution;
	}

	public void addNote (final String note) {
		this.getNotes().add(note);
	}
	
	public void setDescription(ProblemDescription description) {
		this.description = description;
	}
	
	@Override
	public ProblemDescription getDescription() {
		return this.description;
	}

	@Override
	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	@Override
	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public long getCompletionTimeMillis() {
		return completionTimeMillis;
	}

	public void setCompletionTimeMillis(long completionTimeMillis) {
		this.completionTimeMillis = completionTimeMillis;
	}

	public void setNotes(List<String> notes) {
		this.notes = notes;
	}

	public void setSolution(T solution) {
		this.solution = solution;
	}
}
