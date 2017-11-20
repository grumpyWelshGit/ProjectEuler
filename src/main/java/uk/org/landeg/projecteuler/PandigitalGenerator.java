package uk.org.landeg.projecteuler;

import java.util.Arrays;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PandigitalGenerator implements Iterator<int[]>{
	private static final Logger LOG = LoggerFactory.getLogger(PandigitalGenerator.class);

	private int[] digits;
	private int[] buffer;
	
	public PandigitalGenerator(final int[] digits) {
		this.digits = new int[digits.length];
		System.arraycopy(digits, 0, this.digits, 0, digits.length);
		this.buffer = new int[digits.length];
	}
	
	public PandigitalGenerator(byte low, byte hi) {
		super();
		this.digits = new int[hi - low + 1];
		this.buffer = new int[hi - low + 1];
		for (int idx = low; idx <= hi ; idx++) {
			digits[idx - low] = idx;
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug("populated digits {} ", Arrays.toString(digits));
		}
	}

	@Override
	public boolean hasNext() {
		int i = digits.length - 1;
		boolean hasNext = false;
		for (int idx = 0 ; idx < digits.length - 2 ; idx++) {
			if (digits[idx + 1] > digits[idx]) {
				hasNext = true;
				break;
			}
		}
		return hasNext;
	}

	@Override
	public int[] next() {
		if (LOG.isDebugEnabled()) {
			LOG.debug("next {}", Arrays.toString(digits));
		}
		
		int i = digits.length - 1;
		int j = i;
		while (digits[i - 1] > digits[i]) { i--; }
		i--;
		while (digits[j] < digits[i]) {j--;}
		if (LOG.isTraceEnabled()) {
			LOG.trace("Generating next step i={} j={} ", i, j);
		}
		int tmp = digits[i];
		digits[i] = digits[j];
		digits[j] = tmp;

		if (LOG.isTraceEnabled()) {
			LOG.trace("Swapped digits {} ", Arrays.toString(digits));
		}
		
		
		for (int d = i+1 ; d < digits.length ; d++ ) {
			buffer[d] = digits[d];
		}
		if (LOG.isTraceEnabled()) {
			LOG.trace("populating buffer {}", Arrays.toString(buffer));
		}
		for (int d = 0 ; d < digits.length - i - 1 ; d++) {
			digits[i + d + 1] = buffer[buffer.length  - 1 - d]; 
		}
		if (LOG.isTraceEnabled()) {
			LOG.trace("filled from buffer {}", Arrays.toString(buffer));
		}

		if (LOG.isDebugEnabled()) {
			LOG.debug("next lexiograph digits {} ", Arrays.toString(digits));
		}
		LOG.debug("end next");
		return digits;
	}

}
