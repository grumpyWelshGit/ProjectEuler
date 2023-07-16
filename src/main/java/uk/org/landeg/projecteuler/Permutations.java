package uk.org.landeg.projecteuler;

import lombok.Getter;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
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

	/**
	 * finds the nth lexiographic combination of the specified character array
	 * @param chars
	 * @param n
	 * @return
	 */
	public static <T> List<T> lexiograph (T[] chars, int n) {
		final List<T> charsList = new ArrayList<>();
		final List<T> lexiograph = new ArrayList<>();
		for (T c : chars) {
			charsList.add(c);
		}
		int offset = n;
		do {
			long factorial = Mathlib.factorial(charsList.size() - 1);
			int index = offset / (int) factorial;
			offset = offset % (int) factorial;
			lexiograph.add(charsList.remove(index));
		} while (charsList.size() > 0);
		final List<T> lexiographArray = new ArrayList<T>();
		for (T ch : lexiograph) {
			lexiographArray.add(ch);
		}
		return lexiographArray;
	}


	/**
	 * finds the nth lexiographic combination of the specified character array
	 * @param chars
	 * @param n
	 * @return
	 */
	public static <T> List<T> choose (SelectionContext<T> context) {
		if (!context.hasNext()) {
			return null;
		}
		var next = new ArrayList<T>();
		var selection = context.next();
		for (var i : selection) {
			next.add(context.choices[i]);
		}
		return next;
	}

	public static class SelectionContext<T> {
		private T[] choices;
		private int[] selection;

		private int[] maxSelections;

		private boolean first;
		private int select;

		public SelectionContext(T[] choices, int select) {
			this.choices = choices;
			this.select = select;
			first = true;
			initialiseSelection();
		}

		public SelectionContext(SelectionContext<T> selectionContext) {
			this.choices = selectionContext.choices;
			this.first = selectionContext.first;
			this.maxSelections = selectionContext.maxSelections;
			this.selection = Arrays.copyOf(selectionContext.selection, selectionContext.selection.length);
		}

		void initialiseSelection() {
			selection = new int[select];
			maxSelections = new int[select];

			for (int i = 0 ; i < select ; i++) {
				selection[i] = i;
			}
			int maxSelection = choices.length - 1;
			for (int i = maxSelections.length - 1 ; i >= 0 ; i--) {
				maxSelections[i] = maxSelection--;
			}
		}

		boolean hasNext() {
			for (int n = selection.length - 1 ; n >= 0 ; n--) {
				if (selection[n] < maxSelections[n]) {
					return true;
				}
			}
			return false;
		}

		int[] next() {
			if (first) {
				first = false;
				return selection;
			}
			boolean escape = false;
			for (int i = selection.length - 1 ; i >= 0 ; i--) {
				if (selection[i] < maxSelections[i]) {
					selection[i]++;
					escape = true;
					for (int j = i + 1 ; j < selection.length ; j++) {
						selection[j] = selection[j - 1] + 1;
					}
				}
				if (escape) {
					break;
				}
			}
			return selection;
		}
	}
}
