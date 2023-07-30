package uk.org.landeg.projecteuler.problems;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import uk.org.landeg.projecteuler.FileLoader;
import uk.org.landeg.projecteuler.ProblemDescription;

@Order(81)
@Component
@Slf4j
public class Problem081 implements ProblemDescription<Long> {

  private static final int[][] TEST_GRID = {
      {131, 673, 234, 103, 18}, 
      {201, 96, 342, 965, 150},
      {630, 803, 746, 422, 111}, 
      {537, 699, 497, 121, 956}, 
      {805, 732, 524, 37, 331}};
  private static int[][] THE_GRID = TEST_GRID;

  @Override
  public String getTask() {
    return "Find the minimal path sum, in matrix.txt, a 31K text file containing a 80 by 80 matrix, from the top left to the bottom right by only moving right and down";
  }

  @Override
  public String getDescribtion() {
    return "In the 5 by 5 matrix below, the minimal path sum from the top left to the bottom right, by only moving to the right and down, is indicated in bold red and is equal to 2427";
  }

  @Override
  public Long solve() {
    final List<String> lines = FileLoader.readLines("p081_matrix.txt");
    THE_GRID = loadGrid(lines);

    showGrid(THE_GRID);
    for (int i = THE_GRID.length - 1; i > 0; i--) {
      THE_GRID[i - 1][THE_GRID.length - 1] += THE_GRID[i][THE_GRID.length - 1];
      THE_GRID[THE_GRID.length - 1][i - 1] += THE_GRID[THE_GRID.length - 1][i];
    }
    for (int i = THE_GRID.length - 2; i >= 0; i--) {
      for (int j = THE_GRID.length - 2; j >= 0; j--) {
        THE_GRID[i][j] += Math.min(THE_GRID[i][j + 1], THE_GRID[i + 1][j]);
      }
    }

    showGrid(THE_GRID);
    return (long) THE_GRID[0][0];
  }

  static int[][] loadGrid(final List<String> lines) {
    final int[][] grid = new int[lines.size()][lines.size()];
    AtomicInteger lineCount = new AtomicInteger();
    {
      for (String line : lines) {
        final int[] lineArr = new int[lines.size()];
        grid[lineCount.getAndIncrement()] = lineArr;
        final String[] toks = line.split(",");
        for (int i = 0; i < toks.length; i++) {
          lineArr[i] = Integer.parseInt(toks[i]);
        }
      }
    }
    return grid;
  }

  protected void showGrid(final int[][] grid) {
    if (log.isTraceEnabled()) {
      log.trace("--------------------");
      for (int i = 0; i < grid.length; i++) {
        log.trace(Arrays.toString(grid[i]));
      }
      log.trace("--------------------");
    }
  }

}
