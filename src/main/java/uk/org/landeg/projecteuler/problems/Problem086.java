package uk.org.landeg.projecteuler.problems;



import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import uk.org.landeg.projecteuler.ProblemDescription;

@Component
@Order(86)
@Slf4j
public class Problem086 implements ProblemDescription<Integer>{

  public static int M = 100;
  @Override
  public String getTask() {
    return "Find the least value of M such that the number of solutions first exceeds one million";
  }

  @Override
  public String getDescribtion() {
    return "By travelling on the surfaces of the room the shortest \"straight line\" distance from S to F is 10 and the path is shown on the diagram";
  }

  @Override
  public Integer solve() {
    int count = 0;
    
    int l = 0;
    int targetCount = 1000000;
    do {
      l++;
      log.debug("l = {}", l);
      for (int wh = 1 ; wh < 2 * l ; wh++) {
        int sp2 = wh * wh + l * l;
        double sp = Math.sqrt(sp2);
        if (sp == (int)sp) {
          for (int w = wh - 1 ; w >= 1 ; w--) {
            int h = wh - w;
            if (l >= w && w >=h) {
              count++;
            }
          }
        }
      }
    } while (count <= targetCount);
    return l;
  }
}
