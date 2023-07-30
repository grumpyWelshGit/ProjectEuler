package uk.org.landeg.projecteuler.problems;

import java.util.concurrent.atomic.AtomicInteger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import uk.org.landeg.projecteuler.FileLoader;
import uk.org.landeg.projecteuler.ProblemDescription;

@Order(102)
@Component
@Slf4j
public class Problem102 implements ProblemDescription<Integer>{

  @Override
  public String getTask() {
    return "find the number of triangles for which the interior contains the origin";
  }

  @Override
  public String getDescribtion() {
    return "Triangle containment";
  }

  
  @Override
  public Integer solve() {
    final AtomicInteger count = new AtomicInteger(0);
    FileLoader.readLines("p102_triangles.txt")
      .stream()
      .forEach(s -> {
        String[] toks = s.split(",");
        int tokId = 0; 
        int ax = Integer.parseInt(toks[tokId++]);
        int ay = Integer.parseInt(toks[tokId++]);
        int bx = Integer.parseInt(toks[tokId++]);
        int by = Integer.parseInt(toks[tokId++]);
        int cx = Integer.parseInt(toks[tokId++]);
        int cy = Integer.parseInt(toks[tokId++]);
        boolean oa = ax * by - ay * bx > 0;
        boolean ob = bx * cy - by * cx > 0;
        boolean oc = cx * ay - cy * ax > 0;
        if (oa == ob && oa==oc) {
          count.getAndIncrement();
        }
      });
    return count.get();
  }

}
