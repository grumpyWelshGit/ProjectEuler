package uk.org.landeg.projecteuler.problems;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.ProblemDescription;

@Order(17)
@Component
public class Problem017 implements ProblemDescription<Integer>{
	final Map<Integer, String> units = new HashMap<>(); 
	final Map<Integer, String> numbers = new HashMap<>(); 
	final Map<Integer, String> tens = new HashMap<>();
	final String HUNDRED = "HUNDRED";

	@Override
	public String getTask() {
		return "If all the numbers from 1 to 1000 (one thousand) inclusive were written out in words, how many letters would be used?";
	}

	@Override
	public String getDescribtion() {
		return "If the numbers 1 to 5 are written out in words: one, two, three, four, five, then there are 3 + 3 + 5 + 4 + 4 = 19 letters used in total.";
	}

	@Override
	public Integer solve() {
		units.put(0, "");
		units.put(1, "one");
		units.put(2, "two");
		units.put(3, "three");
		units.put(4, "four");
		units.put(5, "five");
		units.put(6, "six");
		units.put(7, "seven");
		units.put(8, "eight");
		units.put(9, "nine");

		numbers.put(10, "ten");
		numbers.put(11, "eleven");
		numbers.put(12, "twelve");
		numbers.put(13, "thirteen");
		numbers.put(14, "fourteen");
		numbers.put(15, "fifteen");
		numbers.put(16, "sixteen");
		numbers.put(17, "seventeen");
		numbers.put(18, "eighteen");
		numbers.put(19, "nineteen");
		
		tens.put(0, "");
		tens.put(10, "ten");
		tens.put(20, "twenty");
		tens.put(30, "thirty");
		tens.put(40, "forty");
		tens.put(50, "fifty");
		tens.put(60, "sixty");
		tens.put(70, "seventy");
		tens.put(80, "eighty");
		tens.put(90, "ninety");
		tens.put(100, "hundred");
		
		final String THOUSAND = "onethousand";
		
		int n1to9 = "one".length()+"two".length()+ "three".length()
				+ "four".length()
				+ "five".length()
				+ "six".length()
				+ "seven".length()
				+ "eight".length()
				+ "nine".length();
		int n10to19 = 0;
		for (int n = 10 ; n <= 19 ; n++) {
			n10to19 += numbers.get(n).length();
		}
		int n20to99 = 8 * n1to9;
		for (int n = 20 ; n <= 90 ; n += 10) {
			n20to99 += tens.get(n).length() * 10;
		}
		int nHundred = tens.get(100).length();
		int nHundredAnd = (tens.get(100).length() + "and".length());
		
		int chars = 0;
		int n1to99 = 0;
		n1to99 += n1to9;
		n1to99 += n10to19;
		n1to99 += n20to99;
		
		int n100to999 = 0;
		// xxx hundred
		n100to999 += nHundred * 9;

		/// xxx hundred and
		n100to999 += nHundredAnd * 99 * 9;
		n100to999 += n1to9 * 100;

		/// xxx hundred and XXXX
//		n100to999 += n1to9 * 9;
		n100to999 += n1to99 * 9;

		chars += n1to99;
		chars += n100to999;
		chars += THOUSAND.length();
		return chars;
		
		
	}
}
