package uk.org.landeg.projecteuler;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class RomanNumeral {
	private static final int I = 1;
	private static final int V = 5;
	private static final int X = 10;
	private static final int L = 50;
	private static final int C = 100;
	private static final int D = 500;
	private static final int M = 1000;
	
	private static final int[] ROMAN_NUMERAL_VALUES = {M,D,C,L,X,V,I};
	private static final Character[] ROMAN_NUMERALS = {'M','D','C','L','X','V','I'};
	private static Map<Character, Integer> numeralValues = null;
	private static TreeMap<Integer, String> numeralValueLookup = new TreeMap<>();

	public static int evaluate (final String numeralString) {
		if (numeralString == null || numeralString.isEmpty())
			return 0;
		final char[] chars = numeralString.toCharArray();

		int sum = 0;
		int localMax = 0;
		for (int id = chars.length - 1 ; id >= 0; id--) {
			int currentVal = getNumeralValues().get(new Character (chars[id]));
			int mult = 1;
			if (currentVal < localMax) {
				mult = -1;
			} else {
				localMax = currentVal;
			}
			sum += currentVal * mult;
		}
		return sum;
	}


	public static String toString (final int value) {
	  return toString(value, new StringBuilder()).toString();
	}
	
	private static StringBuilder toString (final int value, final StringBuilder builder) {
	  if (value == 0) {
	    return builder;
	  }
	  final Entry<Integer, String> entry = getNumeralValueLookup().floorEntry(value);
	  builder.append(entry.getValue());
	  toString(value - entry.getKey(), builder);
	  
	  return builder;
	}

	public static String toString_old (final int value) {
		final StringBuilder builder = new StringBuilder();
		int currVal = value;
		for (int idx = 0 ; idx < ROMAN_NUMERALS.length ; idx++) {
			while (currVal >= ROMAN_NUMERAL_VALUES[idx]) {
				builder.append(ROMAN_NUMERALS[idx]);
				currVal -= ROMAN_NUMERAL_VALUES[idx];
			}
		}
        String result = builder.toString()
            .replace("VIIII", "IX")
            .replace("IIII", "IV")
            .replace("XXXX", "XL")
            .replace("CCCC", "CD")
            ;
        int vIndex = -1;
        if ((vIndex = result.indexOf("V")) > -1) {
          if (result.indexOf("V", vIndex + 1) > -1) {
            throw new RuntimeException("Numeral cannot contain multiple V characters. " + result);
          }
        }
		return result;
	}

	private static Map<Character,Integer> getNumeralValues () {
		if (numeralValues == null) {
			numeralValues = new HashMap<>();
			for (int idx = 0 ; idx < ROMAN_NUMERALS.length ; idx++) {
				numeralValues.put(ROMAN_NUMERALS[idx], ROMAN_NUMERAL_VALUES[idx]);
			}
		}
		return numeralValues;
	}
	
	public static TreeMap<Integer, String> getNumeralValueLookup() {
	  if (numeralValueLookup.isEmpty()) {
	    numeralValueLookup.put(1000, "M");
	    numeralValueLookup.put(900, "CM");
	    numeralValueLookup.put(500, "D");
	    numeralValueLookup.put(400, "CD");
	    numeralValueLookup.put(100, "C");
	    numeralValueLookup.put(90, "XC");
	    numeralValueLookup.put(50, "L");
	    numeralValueLookup.put(40, "XL");
	    numeralValueLookup.put(10, "X");
	    numeralValueLookup.put(9, "IX");
	    numeralValueLookup.put(5, "V");
	    numeralValueLookup.put(4, "IV");
	    numeralValueLookup.put(1, "I");
	  }
      return numeralValueLookup;
    }
}
