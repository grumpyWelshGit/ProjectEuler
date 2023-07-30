package uk.org.landeg.projecteuler;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class PrimeContext {
    long maxPrime = 0;
    long maxPrimeSq = 0;
    long maxTested = 0;
    private Set<Long> primes;

    private boolean[] sieve;

    public PrimeContext(int sieveSize) {
        primes = new TreeSet<>();
        this.sieve = new boolean[sieveSize];
        sievePrimes(0);
    }

    private void add(long p) {
        this.primes.add(p);
        this.maxPrime = p;
        this.maxTested = p;
        this.maxPrimeSq = maxPrime * maxPrime;
    }

    boolean isPrime(long n) {
        if (n <= maxPrime) {
            return primes.contains(n);
        }
        if (n < maxPrimeSq) {
            return checkExistingPrimes(n);
        }
        do {
            sievePrimes(maxTested + 1);
        } while (maxPrimeSq < n);
        return checkExistingPrimes(n);
    }

    boolean checkExistingPrimes (long n) {
        for (long p : primes) {
            if (n % p == 0) {
                return false;
            }
        }
        primes.add(n);
        return true;
    }

    public void sievePrimes(long start) {
        Arrays.fill(sieve, true);
        if (start == 0) {
            PrimeLib.primes(sieve.length)
                .stream()
                .map(i -> (long) i)
                .forEach(this::add);
            maxTested = sieve.length - 1;
            return;
        }

        long end = start + sieve.length -1 ;
        long maxCheck = (long) (Math.sqrt(end) + 1);
        for (long p : primes) {
            long f = start / p;
            long m;
            do {
                m = p * f;
                if (m >= end) {
                    break;
                }
                if (m >= start) {
                    sieve[(int) (m - start)] = false;
                }
                f++;
            } while (true);
        }
        int primeId = start % 2 == 0 ? 1 : 0;
        for (int i = 1 ; i < sieve.length ; i += 2) {
            if (sieve[i]) {
                log.info("discovered prime {}", i + start);
                add((long) (i + start));
            }
        }
        maxTested = end;
    }
}
