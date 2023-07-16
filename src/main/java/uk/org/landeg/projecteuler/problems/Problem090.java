package uk.org.landeg.projecteuler.problems;

import lombok.extern.slf4j.Slf4j;


import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import uk.org.landeg.projecteuler.FileLoader;
import uk.org.landeg.projecteuler.ProblemDescription;
import uk.org.landeg.projecteuler.RomanNumeral;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Order(90)
@Slf4j
public class Problem090 implements ProblemDescription<Integer> {


  @Override
  public String getTask() {
    return "Find the number of characters saved by writing each of these in their minimal form";
  }

  @Override
  public String getDescribtion() {
    return "For a number written in Roman numerals to be considered valid there are basic rules which must be followed";
  }

  @Override
  public Integer solve() {
    // 01 04 09 16 25 36 49 64 81
    // 0 .. 1,4,6
    // 1 .. 0,6,8
    // 2 .. 5
    // 3 .. 6
    // 4 .. 0, 6
    // 5 .. 2
    // 6 .. 0, 3
    // 7 .. -
    // 8 .. 1

    // 9 .. 0, 1, 3, 4

    // 1,2,3,4,5,6 -> 0,2,3,5,6,8

    // 0,5,6,7,8,9 -> 1,4,9,2,0,3,1,0,1,3,4  -> 0,1,2,3,4,6

    var die1 = new ArrayList<Byte>();
    var die2 = new ArrayList<Byte>();
    var combinations = 0;

    die1.addAll(List.of((byte)0,(byte)1,(byte)2,(byte)3,(byte)4,(byte)5));
    log.info("die1 : {}", hash(die1));
    die1.clear();
    die1.addAll(List.of((byte)0,(byte)1,(byte)2,(byte)3,(byte)4,(byte)6));
    log.info("die1 : {}", hash(die1));
    return 0;
  }

  private int hash (ArrayList<Byte> die) {
    int hash = 0;
    for (byte b : die) {
      hash |= 0x1 << ((int) b);
    }
    return hash;
  }
}
