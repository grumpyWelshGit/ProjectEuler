package uk.org.landeg.projecteuler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RationalNumberTest {
    @BeforeEach
    void setup() {
        RationalNumber.strictChecking = true;
    }

    @Test
    void testAdd() {
        var half = RationalNumber.oneOver(2);
        var third = RationalNumber.oneOver(3);
        var quart = RationalNumber.oneOver(4);
        assertEquals(RationalNumber.of(5, 6), half.add(third));
        assertEquals(RationalNumber.of(5, 6), third.add(half));
        assertEquals(RationalNumber.of(1, 2), quart.add(quart));
    }

    @Test
    void testSub() {
        var half = RationalNumber.oneOver(2);
        var one = RationalNumber.of(1);
        var quart = RationalNumber.oneOver(4);
        var third = RationalNumber.oneOver(3);
        assertEquals(RationalNumber.oneOver(6), half.sub(third));
        assertEquals(RationalNumber.oneOver(-6), third.sub(half));
    }


    @Test
    void testMultiply() {
        var half = RationalNumber.oneOver(2);
        var quart = RationalNumber.oneOver(4);
        assertEquals(quart, half.multiply(half));
        assertEquals(half, quart.multiply(2).normalize());
    }

    @Test
    void testDivide() {
        var half = RationalNumber.oneOver(2);
        var quart = RationalNumber.oneOver(4);
        assertEquals(RationalNumber.ONE, half.divide(half).normalize());
        assertEquals(quart, half.divide(2).normalize());
    }

    @Test
    void testNormalize() {
        assertEquals(RationalNumber.ONE, RationalNumber.ONE.normalize());
        assertEquals(RationalNumber.oneOver(2), RationalNumber.of(2, 4).normalize());
        assertEquals(RationalNumber.oneOver(3), RationalNumber.of(3, 9).normalize());
        assertEquals(RationalNumber.oneOver(3), RationalNumber.of(3, 9).normalize());
        assertEquals(RationalNumber.of(5, 3), RationalNumber.of(15, 9).normalize());
        assertEquals(RationalNumber.of(-5, 3), RationalNumber.of(15, -9).normalize());
    }

}