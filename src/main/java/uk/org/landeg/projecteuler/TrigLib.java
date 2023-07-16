package uk.org.landeg.projecteuler;

public final class TrigLib {
    private TrigLib() {
    }

    private int[] triple(int n, int m) {
        var triple = new int[3];
        final var msq = m * m;
        final var nsq = n * n;
        triple[0] = msq - nsq;
        triple[1] = 2 * m * n;
        triple[2] = msq + nsq;
        return triple;
    }
}
