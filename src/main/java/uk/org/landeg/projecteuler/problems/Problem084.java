package uk.org.landeg.projecteuler.problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import uk.org.landeg.projecteuler.ProblemDescription;

@Order(84)
@Component
public class Problem084 implements ProblemDescription<Integer>{
  private static final Logger LOG = LoggerFactory.getLogger(Problem084.class);
  @Override
  public String getTask() {
    return "If, instead of using two 6-sided dice, two 4-sided dice are used, find the six-digit modal string.";
  }

  @Override
  public String getDescribtion() {
    return "Monopoly odds";
  }

  private enum Square {
    GO (0),
    A1 (1),
    CC1 (2),
    A2 (3),
    T1 (4),
    R1 (5),
    B1 (6),
    CH1 (7),
    B2 (8),
    B3 (9),
    JAIL (10),
    C1 (11),
    U1 (12),
    R2 (15),
    CC2 (17),
    CH2 (22),
    E3 (24),
    R3 (25),
    U2 (28),
    GTJ (30),
    CC3 (33),
    R4 (35),
    CH3 (36),
    H2 (39);
    

    private final int id;
    
    private Square(final int id) {
      this.id = id;
    }
    
    public int getId() {
      return id;
    }
    
    public static Square find (final int id) {
      Square s = null;
      for (Square test : Square.values()) {
        if (test.id == id) {
          s = test;
        }
      }
      return s;
    }
  }
  final Random random = new Random(System.currentTimeMillis());

  private List<Function<Integer, Integer>> communityChestCards;
  private List<Function<Integer, Integer>> chanceCards;
  final Function<Integer, Integer> noMove = Function.identity();
  
  @Override
  public Integer solve() {
    intiCardDecks();
    int dieSides = 4;
    
    int[] sqFreq = new int[40];
    int[] rollFreq = new int[dieSides * 2 + 1];
    int rolls = 0;
    int target = 1000000;
    int d1, d2;
    int pos = 0;
    int doubleCount = 0;
    Function<Integer, Integer> moveInstruction;
    do {
      moveInstruction = null;
      d1 = random.nextInt(dieSides) + 1;
      d2 = random.nextInt(dieSides) + 1;
      rollFreq[d1 + d2]++;
      pos = (pos + d1 + d2) % 40;
      LOG.trace("rolls {}, moving to {}", d1+d2, pos);
      if (d1 == d2) {
        doubleCount++;
      } else {
        doubleCount = 0;
      }
      boolean moved = false;
      if (doubleCount == 3) {
        pos = GoToJail.apply(0);
      }
      else {
        do {
          moved = false;
          moveInstruction = getMoveInstruction(pos);
          int newPos = moveInstruction.apply(pos);
          if (newPos != pos) {
            moved = true;
            LOG.trace("Updating position to {} ", newPos);
          }
          pos = newPos;
        } while (moved);
      }

      sqFreq[pos]++;
      if (rolls % 10000 == 0) {
        LOG.debug("roll #{} {}" , rolls, sqFreq);
      }
    } while (++rolls < target);
    LOG.info("{}", sqFreq);
    final Map<Integer, Integer> freqMap = new HashMap<>();
    for (int idx = 0 ; idx < sqFreq.length ; idx++) {
      freqMap.put(sqFreq[idx], idx);
    }
    final List<Entry<Integer, Integer>> entryList = freqMap.
        entrySet().stream().collect(Collectors.toList());
    LOG.info("{}", rollFreq);
    entryList.sort((a,b) -> b.getKey().compareTo(a.getKey()));
    for (int order = 0 ; order < 10 ; order++) {
      LOG.info("{} {}", entryList.get(order), entryList.get(order).getKey() / (double)target);
    }
    int answer = 0;
    for (int n = 0 ; n < 3 ; n++) {
      answer*=100;
      answer+= entryList.get(n).getValue();
    }
    LOG.info("answer {}", answer);
    return answer;
  }
  
  private static final Function<Integer, Integer> GoToJail = x -> Square.JAIL.id;
  
  private void intiCardDecks() {
    final Function<Integer, Integer> moveToNextRail = pos -> {
      int newPos = Square.R1.id;
      if (pos < Square.R1.id ) {
        newPos = Square.R1.id;
      }
      else if (pos < Square.R2.id ) {
        newPos = Square.R2.id;
      }
      else if (pos < Square.R3.id ) {
        newPos = Square.R3.id;
      }
      else if (pos < Square.R4.id ) {
        newPos = Square.R4.id;
      }
      LOG.debug("Moving to next rail {}->{}", pos, newPos);
      return newPos;
    };

    final Function<Integer, Integer> moveToNextUtil = pos -> {
      int newPos = Square.U1.id;
      if (pos < Square.U1.id) {
        newPos = Square.U1.id;
      } else if (pos < Square.U2.id) {
        newPos = Square.U2.id;
      }
      LOG.debug("Moving to next util {}->{}", pos, newPos);
      return newPos;
    }; 
    communityChestCards = new ArrayList<>();
    chanceCards = new ArrayList<>();
    communityChestCards.add(buildMoveInstruction(Square.GO));
    communityChestCards.add(buildMoveInstruction(Square.JAIL));

    chanceCards.add(buildMoveInstruction(Square.JAIL));
    chanceCards.add(buildMoveInstruction(Square.GO));
    chanceCards.add(buildMoveInstruction(Square.C1));
    chanceCards.add(buildMoveInstruction(Square.E3));
    chanceCards.add(buildMoveInstruction(Square.H2));
    chanceCards.add(buildMoveInstruction(Square.R1));
    chanceCards.add(moveToNextRail);
    chanceCards.add(moveToNextRail);
    chanceCards.add(moveToNextUtil);
    chanceCards.add(pos -> {LOG.debug("Moving back 3 squares");return pos - 3;});
    while (communityChestCards.size() < 16) {
      communityChestCards.add(Function.identity());
    }
    while (chanceCards.size() < 16) {
      chanceCards.add(Function.identity());
    }
  }
  
  Function<Integer, Integer> buildMoveInstruction (final Square square) {
    return new Function<Integer, Integer> () {
      @Override
      public Integer apply(Integer t) {
        LOG.debug("move to square {}" , square.toString());
        return square.id;
      }
    };
  }

  int chestCardId = 0;
  int chanceCardId = 0;
  Function<Integer, Integer> getMoveInstruction (int pos) {
    Function<Integer, Integer> instruction = x -> x;
    if (pos == Square.CC1.id || pos == Square.CC2.id || pos == Square.CC3.id) {
      LOG.trace("Draw Chest, card {}", chestCardId);
      instruction = communityChestCards.get(chestCardId);
      chestCardId = (chestCardId + 1) % communityChestCards.size();
    } else if (pos == Square.CH1.id || pos == Square.CH2.id || pos == Square.CH3.id) {
      LOG.trace("Draw Chance, card {}", chanceCardId);
      instruction = chanceCards.get(chanceCardId);
      chanceCardId = (chanceCardId + 1) % chanceCards.size();
    } else if (pos == Square.GTJ.id) {
      instruction = GoToJail;
    }
    return instruction;
  }

  public Map<Integer, Double> getOdds (final int sides) {
    final Map<Integer, Double> dOdds = new HashMap<>();
    final Map<Integer, Integer> freq = new HashMap<>();
    final AtomicInteger total = new AtomicInteger();
    for (int d1 = 1 ; d1 <= 6 ; d1++) {
      for (int d2 = 1; d2 <= 6 ; d2++) {
        freq.put(d1+d2, freq.getOrDefault(d1 + d2, 0) + 1);
        total.set(total.get() + 1);
      }
    }
    freq.entrySet().stream().forEach(e -> dOdds.put(e.getKey(), (double) e.getValue() / (double) total.get()));
    return dOdds;
  }
}
