package uk.org.landeg.projecteuler;

import java.util.ArrayList;
import java.util.List;

public class Permutations {
	/**
	 * finds the nth lexiographic combination of the specified character array
	 * @param chars
	 * @param n
	 * @return
	 */
	public static char[] lexiograph (char[] chars, int n) {
		final List<Character> charsList = new ArrayList<>();
		final List<Character> lexiograph = new ArrayList<>();
		for (char c : chars) {
			charsList.add(c);
		}
		int offset = n;
		do {
			long factorial = Mathlib.factorial(charsList.size() - 1);
			int index = offset / (int) factorial;
			offset = offset % (int) factorial;
			lexiograph.add(charsList.remove(index));
		} while (charsList.size() > 0);
		final char[] lexiographArray = new char[lexiograph.size()];
		int idx = 0;
		for (char ch : lexiograph) {
			lexiographArray[idx++] = ch;
		}
		return lexiographArray;
	}
}
