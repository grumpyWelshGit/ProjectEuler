package uk.org.landeg.projecteuler;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;

@Slf4j
@Getter
@EqualsAndHashCode(of = {"top", "bot"})
@NoArgsConstructor
@AllArgsConstructor
public class RationalNumber {
    public static boolean strictChecking = false;
    private long top = 0;
    private long bot = 0;

    public static final RationalNumber ONE = RationalNumber.of(1);
    public static final RationalNumber ZERO = RationalNumber.of(0);
    public static RationalNumber of(long top, long bot) {
        if (bot < 0) {
            return of(top * -1, bot * -1);
        }
        return new RationalNumber(top, bot);
    }

    public static RationalNumber of(long top) {
        return new RationalNumber(top, 1);
    }

    public static RationalNumber of(RationalNumber n) {
        return new RationalNumber(n.top, n.bot);
    }

    public static RationalNumber oneOver(long bot) {
        return RationalNumber.of(1, bot);
    }

    public RationalNumber add(RationalNumber a) {
        log.debug("add {} + {}", a, this);
        RationalNumber result;
        if (a.bot == bot) {
            result = RationalNumber.of(a.top + top, bot);
        } else {
            long lcm = Mathlib.lcm(a.bot, bot);
            long factor = lcm / bot;
            long aFactor = lcm / a.bot;
            long rTop = a.top * aFactor + top * factor;
            if (rTop == 0l) {
                return RationalNumber.ZERO;
            }
            long rBot = lcm;
            long gcd = Mathlib.gcd(rTop, rBot);
            result = RationalNumber.of(rTop / gcd, rBot / gcd);
            if (strictChecking) {
                BigInteger expectedTop = BigInteger.valueOf(top).multiply(BigInteger.valueOf(aFactor));
                expectedTop = expectedTop.add(BigInteger.valueOf(top).multiply(BigInteger.valueOf(factor)));
                if (result.top != expectedTop.longValue()) {
                    throw new NumberFormatException(
                            String.format("cannot represent %s + %s, %s/%s is too big", this, a, expectedTop, result.bot));
                }
            }
        }
        return normalize(result);
    }

    public RationalNumber sub(RationalNumber a) {
        RationalNumber result;
        if (a.bot == bot) {
            result = RationalNumber.of(top - a.top, bot);
        } else {
            long lcm = Mathlib.lcm(a.bot, bot);
            long factor = lcm / bot;
            long aFactor = lcm / a.bot;
            long rTop = top * factor - a.top * aFactor;
            if (rTop == 0l) {
                return RationalNumber.ZERO;
            }
            long rBot = lcm;
            long gcd = Mathlib.gcd(rTop, rBot);
            result = RationalNumber.of(rTop / gcd, rBot / gcd);
        }
        return normalize(result);
    }

    public RationalNumber multiply(RationalNumber a) {
        var r = RationalNumber.of(top * a.top, bot * a.bot);
        if (strictChecking) {
            BigInteger expectedTop = BigInteger.valueOf(top).multiply(BigInteger.valueOf(a.top));
            BigInteger expectedBot = BigInteger.valueOf(bot).multiply(BigInteger.valueOf(a.bot));
            if (r.top != expectedTop.longValue() || r.bot != expectedBot.longValue()) {
                throw new NumberFormatException(
                        String.format("cannot represent {} * {}, {}/{} is too big", this, a, expectedTop, expectedBot));
            }
        }
        return r;
    }

    public RationalNumber divide(RationalNumber a) {
        var r = multiply(RationalNumber.of(a.bot, a.top));
        return r;
    }

    public RationalNumber multiply(long r) {
        return RationalNumber.of(top * r, bot);
    }

    public RationalNumber divide(long r) {
        return RationalNumber.of(top, bot * r);
    }

    public RationalNumber normalize() {
        return RationalNumber.normalize(this);
    }

    public static RationalNumber normalize(RationalNumber r) {
        if(Math.abs(r.top) <= 1 || Math.abs(r.bot) <= 1) {
            return r;
        }
        var gcd = Mathlib.gcd(r.top, r.bot);
        if (gcd == 1) {
            return r;
        }
        var rTop = r.top / gcd;
        var rBot = r.bot / gcd;
        if (strictChecking) {
            if ((double) (r.top / r.bot) != (double) (rTop/rBot)) {
                var msg = String.format("strict check failure normalizing [%s] -> top:%d, bot:%d gcd:%d", r, rTop, rBot, gcd);
                throw new IllegalStateException(msg);
            }
        }
        var result = RationalNumber.of(rTop, rBot);
        log.debug("normalizing {} -> {}", r, result);
        return result;
    }

    @Override
    public String toString() {
        if (bot == 1) {
            return String.format("[%d]", top);
        }
        return String.format("[%d/%d]", top, bot);
    }

    public boolean equals (long top, long bot) {
        return this.top == top && this.bot == bot;
    }
}
