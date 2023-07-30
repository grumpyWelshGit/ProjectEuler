package uk.org.landeg.projecteuler;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrimeContextTest {
    @Test
    void initialPrimeTest() {
        var pc = new PrimeContext(10);
        assertTrue(pc.isPrime(2));
        assertTrue(pc.isPrime(3));
        assertTrue(pc.isPrime(1013));
    }
}