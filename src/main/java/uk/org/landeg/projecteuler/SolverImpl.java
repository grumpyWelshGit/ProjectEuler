package uk.org.landeg.projecteuler;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SolverImpl implements Solver {
	@Autowired
	private List<ProblemDescription<?>> problemDescriptions;

	@Autowired
	private ProblemContext context;

	@PostConstruct
	public void doSomeShit () {
		problemDescriptions
			.stream()
			.map(description -> solve(description))
			.forEach(solution -> {
				System.out.println(solution.getDescription().getDescribtion());
				System.out.println(solution.getDescription().getTask());
				long solutionTimeMillis = solution.getEndTime() - solution.getStartTime();
				solutionTimeMillis /= 1000000;
				System.out.println("Solution calculated in " + (solutionTimeMillis) + "ms");
				System.out.println("==============" +solution.getSolution()+ "===============");
				if (!solution.getNotes().isEmpty()) {
					solution.getNotes().stream().forEach(note -> System.out.println("Note " + note));
				}
			});

	}

	@Override
	public <T> ProblemSolution<T> solve(ProblemDescription<T> description) {
		final ProblemSolutionImpl<T> solution = new ProblemSolutionImpl<>();
		context.setSolution(solution);
		solution.setDescription(description);
		solution.setStartTime(System.nanoTime());
		solution.setSolution(description.solve());
		solution.setEndTime(System.nanoTime());
		return solution;
	}
}