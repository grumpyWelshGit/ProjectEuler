package uk.org.landeg.projecteuler.problems;

import java.util.ArrayList;
import java.util.List;


import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.ProblemDescription;

@Component
@Order(85)
@Slf4j
public class Problem085 implements ProblemDescription<Integer>{


	@Override
	public String getTask() {
		return "lthough there exists no rectangular grid that contains exactly two million rectangles, find the area of the grid with the nearest solution";
	}

	@Override
	public String getDescribtion() {
		return "By counting carefully it can be seen that a rectangular grid measuring 3 by 2 contains eighteen rectangles";
	}

	@Override
	public Integer solve() {
		final List<Integer> triangles = new ArrayList<>();
		triangles.add(0);
		log.info("Generating triangular numbers");
		{
			int i = 1;
			int t;
			do {
				t = (i + i*i) / 2;
				triangles.add(t);
				i++;
			} while (t <= 2000000);
		}
		log.info("Finished generating triangular numbers");
		int mindiff = Integer.MAX_VALUE;
		int mindiffArea = 0;
		for (int i = 2 ; i < triangles.size() - 1; i++) {
			final int it = triangles.get(i);
			for (int j = 1 ; j < i ; j++) {
				final int jt = triangles.get(j);
				final int numRects = it * jt;
				final int diff = Math.abs(numRects - 2000000);
				if (diff < mindiff) {
					mindiff = diff;
					mindiffArea = i * j;
					log.debug("minima found at i={} j={} diff={} area ={}", i, j, diff, mindiffArea);
				}
				if (numRects > 2000000) {
					break;
				}
			}
		}
		return mindiffArea;
	}

}
