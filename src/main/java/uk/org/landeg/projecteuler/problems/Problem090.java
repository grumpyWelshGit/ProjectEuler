package uk.org.landeg.projecteuler.problems;

import lombok.extern.slf4j.Slf4j;


import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import uk.org.landeg.projecteuler.FileLoader;
import uk.org.landeg.projecteuler.Permutations;
import uk.org.landeg.projecteuler.ProblemDescription;
import uk.org.landeg.projecteuler.RomanNumeral;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Order(90)
@Slf4j
public class Problem090 implements ProblemDescription<Integer> {


  @Override
  public String getTask() {
    return "How many distinct arrangements of the two cubes allow for all of the square numbers to be displayed?";
  }

  @Override
  public String getDescribtion() {
    return "Each of the six faces on a cube has a different digit (\n"
            + " to \n"
            + ") written on it; the same is done to a second cube. By "
            + " placing the two cubes side-by-side in different positions we can form a variety of \n"
            + "-digit numbers.\n"
            + "\n";
  }

  @Override
  public Integer solve() {
    final var numbers = new Integer[]{0,1,2,3,4,5,6,7,8,9};

    int count =  0;
    var context1 = new Permutations.SelectionContext<>(numbers, 6);

    List<Integer> dice1, dice2;
    while ((dice1 = Permutations.choose(context1)) != null) {
      var context2 = new Permutations.SelectionContext<>(context1);
      while ((dice2 = Permutations.choose(context2)) != null) {
        if (isValid(dice1, dice2)) {
            count++;
            log.debug("{} : {} {}", count, dice1, dice2);
        }
      }
    }
    return count;
  }

  boolean isValid(Collection<Integer> d1, Collection<Integer> d2) {
    if (!canDisplay(d1, d2, 0, 1)) return false;
    if (!canDisplay(d1, d2, 0, 4)) return false;
    if (!canDisplay(d1, d2, 0, 6)) return false; // 9 -> 6
    if (!canDisplay(d1, d2, 1, 6)) return false;
    if (!canDisplay(d1, d2, 2, 5)) return false;
    if (!canDisplay(d1, d2, 3, 6)) return false;
    if (!canDisplay(d1, d2, 4, 6)) return false; // 49 -> 46
    if (!canDisplay(d1, d2, 8, 1)) return false;
    return true;
  }

  boolean canDisplay(Collection<Integer> d1, Collection<Integer> d2, int t1, int t2) {
    var valid =
        (d1.contains(t1) && d2.contains(t2)) ||
                (d1.contains(t2) && d2.contains(t1));
    if (t2 == 6) {
      valid |= (d1.contains(t1) && d2.contains(9)) ||
              (d1.contains(9) && d2.contains(t1));
    }
    return valid;
  }
}
