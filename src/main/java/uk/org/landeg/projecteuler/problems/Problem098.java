package uk.org.landeg.projecteuler.problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;


import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import uk.org.landeg.projecteuler.FileLoader;
import uk.org.landeg.projecteuler.ProblemDescription;

@Order(97)
@Component
@Slf4j
public class Problem098 implements ProblemDescription<Long>{

  @Override
  public String getTask() {
    return "find all the square anagram word pairs"; 
  }

  @Override
  public String getDescribtion() {
    return "Anagramic squares";
  }

  @Override
  public Long solve() {
    final String[] words = FileLoader.readLines("p098_words.txt").get(0).replaceAll("\"",  "").split(",");
    int max = 0;
    for (String w : words) {
      if (w.length() > max) {
        max = w.length();
      }
    }
    final Map<String, List<String>> anagrams = new HashMap<>();
    for (final String word : words) {
      final String ordered = ordererd(word);
      List<String> wordList;
      if (anagrams.containsKey(ordered)) {
        wordList = anagrams.get(ordered);
      } else {
        wordList = new ArrayList<>();
        anagrams.put(ordered, wordList);
      }
      wordList.add(word);
    }
    
    AtomicInteger longestAnagram = new AtomicInteger();
    anagrams.entrySet().stream()
      .filter(e -> e.getValue().size() > 1)
      .forEach(x -> log.debug("{}  {}", x.getKey(), x.getValue()));
    anagrams.entrySet()
      .stream()
      .filter(e -> e.getValue().size() > 1)
      .forEach(e -> longestAnagram.set(Math.max(e.getKey().length(), longestAnagram.get())));

    log.info("Max word length = {}", max);
    log.info("Longest anagram = {}", longestAnagram.get());
    
    final Map<Integer, TreeSet<Long>> squaresByLength = generateSquaresByLength(longestAnagram.get());
    AtomicLong result = new AtomicLong();
    anagrams.entrySet().stream()
      .filter(e->e.getValue().size() >= 2)
      .forEach(e -> {
        final TreeSet<Long> squares = squaresByLength.get(e.getKey().length());
        final List<String> candidates = e.getValue();
        for (int i = 1 ; i < candidates.size() ; i++) {
          for (int j = 0; j < i ; j++) {
            final Map<Integer, Integer> map = generateMap(candidates.get(i), candidates.get(j));
            log.debug("Checking words {} {}", candidates.get(i), candidates.get(j));
            for (long square : squares) {
              if (checkMap(map, candidates.get(i), square)) {
                final long candidateSquare = applyMap(square, map);
                if (candidateSquare != square && squares.contains(candidateSquare)) {
                  log.info("match {}->{}  {}->{}", candidates.get(i), candidates.get(j), square, candidateSquare);
                  Long mm = Math.max(candidateSquare, square);
                  if (mm > result.get()) {
                    log.info("Discovered new maxima {}" , mm);
                    result.set(mm);
                  }
                }
              }
            }
          }
        }
      });

    
    log.debug("long max {} {}", Long.MAX_VALUE, Math.log10(Long.MAX_VALUE));
    return result.get();
  }

  private boolean checkMap(Map<Integer, Integer> map, String string, long square) {
    char[] chars = string.toCharArray();
    char[] digits = Long.toString(square).toCharArray();
    char[] charmap = new char['Z'];
    for (int idx = 0 ; idx < digits.length ; idx++) {
      if (charmap[digits[idx]] > 0 && charmap[digits[idx]] != chars[idx]) {
        log.trace("{} {} not compatible", string, square);
        return false;
      }
      charmap[digits[idx]] = chars[idx];
    }
    return true;
  }

  private long applyMap (final long source, final Map<Integer, Integer> map) {
    final int[] nDigits = new int[(int)(Math.log10(source) + 1)];
    final int[] mDigits = new int[(int)(Math.log10(source) + 1)];
    int nId = nDigits.length - 1;
    long n = source;
    do {
      nDigits[nId--] = (int)(n % 10);
      n /= 10;
    } while (n > 0);
    map.entrySet().stream().forEach(e -> mDigits[e.getValue()] = nDigits[e.getKey()]);

    long m = 0;
    for (int mDigitId = 0 ; mDigitId < mDigits.length ; mDigitId++) {
      m *= 10;
      m += mDigits[mDigitId];
    }
    log.trace("Applying map {} to {} -> {}", map, source, m);
    return m;
  }

  private String ordererd (final String word) {
    final List<Character> charsList = new ArrayList();
    for (char ch : word.toCharArray()) {
      charsList.add(ch);
    }
    Collections.sort(charsList);
    final char[] ordered = new char[word.length()];
    int id = 0;
    for (char c : charsList ) {
      ordered[id++] = c;
    }
    return new String(ordered);
  }

  private Map<Integer, TreeSet<Long>> generateSquaresByLength (final int longestValue) {
    final Map<Integer, TreeSet<Long>> squaresByLength = new HashMap<>();
    int i=1;
    long s;
    log.debug("Generating squares");
    do {
      s = (long) i * i;
      final int len = (int) Math.log10(s) + 1;
      TreeSet<Long> squares;
      if (!squaresByLength.containsKey(len)) {
        squares = new TreeSet<>();
        squaresByLength.put(len, squares);
      } else {
        squares = squaresByLength.get(len);
      }
      squares.add(s);
      i++;
    } while (Math.log10(s) <= longestValue);
    squaresByLength.entrySet()
      .stream()
      .filter(e->e.getKey() < 5)
      .forEach(e->log.debug("{} -> {}", e.getKey(), e.getValue()));
    
    log.debug("finished Generating squares {} {} {}", i, s, Math.log10(s));
    return squaresByLength;
  }

  /**
   * Define a map strategy to transform s1->s2.
   */
  private Map<Integer, Integer> generateMap (final String s1 , final String s2) {
    
    final Map<Integer, Integer> map = new HashMap<>();
    int[] mappedChars = new int['Z'-'A'];
    final char[] chars1 = s1.toCharArray();
    final char[] chars2 = s2.toCharArray();
    for (int i = 0 ; i < chars1.length ; i++) {
      for (int j = mappedChars[chars1[i] - 'A']; j < chars2.length ; j++) {
        if (chars2[j] == chars1[i]) {
          mappedChars[chars1[i]-'A'] = i; 
          map.put(i, j);
        }
      }
    }
    log.trace("Obtaining mapping strategy {} -> {} : {}", s1,s2, map);
    return map;
  }
}
