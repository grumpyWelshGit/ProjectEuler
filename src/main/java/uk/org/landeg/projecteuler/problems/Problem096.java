package uk.org.landeg.projecteuler.problems;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import uk.org.landeg.projecteuler.FileLoader;
import uk.org.landeg.projecteuler.ProblemDescription;

@Order(96)
@Component
public class Problem096 implements ProblemDescription<Integer>{
  private static final Logger LOG = LoggerFactory.getLogger(Problem096.class);
  @Override
  public String getTask() {
    return "By solving all fifty puzzles find the sum of the 3-digit numbers found in the top left corner of each solution grid; for example, 483 is the 3-digit number found in the top left corner of the solution grid above"; 
  }

  @Override
  public String getDescribtion() {
    return "Su Doku";
  }

  int total =0;
  @Override
  public Integer solve() {
    int result = 0;
    int[][] game = new int[9][9];
    final List<String> lines = FileLoader.readLines("p096_sudoku.txt");
    int y = 0;
    int x = 0;
    for (String line : lines) {
      if (line.startsWith("Grid")) {
        LOG.info("Loading {}", line);
        y = 0;
      } else {
        final char[] numChars = line.toCharArray();
        x = 0;
        for (char ch : numChars) {
          game[x++][y] = ch - '0';
        }
        y++;
      }
      if (y == 9) {
        dosudoku(game);
      }
    }
    LOG.info("{}", total);
    return total;
  }

  private void dosudoku(int[][] game) {
    solve(game, 0, 0);
  }
  
  private void solve (final int [][] game, final int x, final int y) {
    for (int [] row : game) {
      LOG.trace("{}" , row);
    }
    LOG.trace("solving for {},{}", x,y);
    LOG.trace("---------------");
    if (y >= 9) {
      for (int [] row : game) {
        LOG.debug("{}" , row);
      }
      int result = game[0][0] * 100 + game[1][0]*10 + game[2][0];
      LOG.info("game result {}", result);
      total += result;
      return;
    }
    if (game[x][y] > 0) {
      solveNext(game, x, y);
      return;
    }
    if (game[x][y] == 0) {
      for (int n = 1; n <=9 ; n++) {
        boolean isCandidate = isCandidate(game, x, y, n);
        if (isCandidate) {
          game[x][y] = n;
          solveNext(game, x, y);
          game[x][y] = 0;
        }
      }
    }
  }
  
  boolean isCandidate (final int game[][], final int x, final int y, final int candidate) {
    boolean isCandidate = true;
      for (int r = 0 ; r < 9 ; r++) {
        if (game[x][r] == candidate || game[r][y] == candidate) {
          LOG.trace("{} is not a candadate, row/col violation {}", candidate, r);
          isCandidate = false;
          break;
        }
      }
      if (isCandidate) {
        // locate the square
        int cx = x / 3;
        int cy = y / 3;
        for (int r = 0 ; r < 9 ; r++) {
          if (game[(cx*3) + (r %3)][(cy * 3) + (r / 3)] == candidate) {
            LOG.trace("{} is not a candadate, square violation {},{} - {} {}", candidate, cx, cy, (cx*3) + (r %3), (cy * 3) + (r / 3));
            isCandidate = false;
            break;
          }
        }
      }
      return isCandidate;
  }
  void solveNext (final int[][] game, final int x, final int y) {
    int nextx = x + 1;
    int nexty = y;
    if (nextx >= 9) {
      nexty++;
      nextx = 0;
    }
    solve(game,nextx,nexty);
  }
}
