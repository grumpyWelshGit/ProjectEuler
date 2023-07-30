package uk.org.landeg.projecteuler.problems;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import uk.org.landeg.projecteuler.FileLoader;
import uk.org.landeg.projecteuler.Mathlib;
import uk.org.landeg.projecteuler.ProblemDescription;
import uk.org.landeg.projecteuler.RationalNumber;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.function.Function;

@Order(101)
@Component
@Slf4j
public class Problem101 implements ProblemDescription<Long>{

  @Override
  public String getTask() {
    return "Find the sum of FITs for the BOPs";
  }

  @Override
  public String getDescribtion() {
    return "Find the sum of FITs for the BOPs";
  }

    Function<Integer, Long> generator;
    int polynomialDegree;
    @Override
    public Long solve() {
        RationalNumber.strictChecking = true;
        generator = sequenceGenerator;
//        generator = testSequenceGenerator;
        polynomialDegree = 3;

        int pointCount = 12;

        Mathlib.PointLong[] points = new Mathlib.PointLong[pointCount];
        for (int i = 0 ; i < pointCount ; i++) {
            points[i] = new Mathlib.PointLong(Long.valueOf(i + 1), generator.apply(i + 1));
        }
        log.info("generated data points [{}]", Arrays.toString(points));

        long sum = 0;
        long thisError = 0;
        for (int degree = 1 ; degree <= points.length ; degree++) {
            thisError = 0;
            for (int x = 1 ; x <= degree + 2 ; x++) {
                var interpolated = waringEvaluation(degree, x, points);
                long expected = generator.apply(x);
                if (interpolated != expected) {
                    log.info("error for degree {} = {} (expected {} at x={})", degree, interpolated, expected, x);
                    thisError = interpolated;
                    sum += thisError;
                    break;
                }
            }
            if (thisError == 0) {
                break;
            }
        }

        return sum;
    }


    private Function<Integer, Long> testSequenceGenerator = a -> (long) Math.pow(a, 3);

    private Function<Integer, Long> sequenceGenerator = a -> {
        var d = 0l;
        var ab = a;

        for (int p = 0 ; p <= 10 ; p++) {
            var pow = (long) Math.pow(ab, p);
            var testPow = BigInteger.valueOf(ab).pow(p);
            if (testPow.longValue() != pow) {
                String message = String.format("%s cannot be represented by type long (%d)", testPow.toString(), pow);
                throw new NumberFormatException(message);
            }
            var sign = p % 2 == 0 ? 1 : -1;
            d = d + (sign * pow);
        }
        return d;
    };

    public static long waringEvaluation(int degree, int x, Mathlib.PointLong... nodes) {
        RationalNumber lagrange = RationalNumber.ZERO;
        for (int k = 0 ; k < degree ; k++) {
            var lk = RationalNumber.of(1);
            for (int i = 0 ; i < degree ; i++) {
                var weight = 1l;
                if (i != k) {
                    weight = nodes[k].getX() - nodes[i].getX();
                    lk = lk.multiply(x - nodes[i].getX()).divide(weight).normalize();
                }
            }
            lagrange = lagrange.add(lk.multiply(nodes[k].getY())).normalize();
            log.info("lk k:{} x:{} lk:{}   [{}]", k, x, lk, lagrange);
        }
        return lagrange.getTop() / lagrange.getBot();
    }
}
