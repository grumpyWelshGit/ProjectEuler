package uk.org.landeg.projecteuler.problems;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.ProblemDescription;

@Component
@Order(53)
public class Problem053 implements ProblemDescription<Integer>{
	@Override
	public String getTask() {
		return "How many, not necessarily distinct, values of  nCr, for 1 ≤ n ≤ 100, are greater than one-million?";
	}

	@Override
	public String getDescribtion() {
		return "nCr =	n!/r!(n−r)! ,where r ≤ n, n! = n×(n−1)×...×3×2×1, and 0! = 1";
	}

	@Override
	public Integer solve() {
		List<Integer> rows1 = new ArrayList<>();
		List<Integer> rows2 = new ArrayList<>();
		List<Integer> prevRow = rows1;
		List<Integer> currentRow = rows2;
		
		int rowId  = 2;
		prevRow.add(1);
		prevRow.add(1);
		int count = 0;
		do {
			currentRow.add(0,1);
			for (int idx = 0 ; idx < prevRow.size() -1 ; idx++) {
				int value = prevRow.get(idx) + prevRow.get(idx+1);
				if (value > 1000000) {
					value = 1000000;
					count++;
				}
				currentRow.add(value);
			}
			currentRow.add(1);
			rowId++;
			if (currentRow == rows1) {
				currentRow = rows2;
				prevRow = rows1;
			} else {
				currentRow = rows1;
				prevRow = rows2;
			}
			currentRow.clear();
		} while (rowId <= 100);
		
		
		return count;
	}
}
