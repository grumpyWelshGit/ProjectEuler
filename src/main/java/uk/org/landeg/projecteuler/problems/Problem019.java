package uk.org.landeg.projecteuler.problems;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.ProblemDescription;

@Order(19)
@Component
public class Problem019 implements ProblemDescription<Integer>{

	@Override
	public String getTask() {
		return "How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000)?";
	}

	@Override
	public String getDescribtion() {
		return "1 Jan 1900 was a Monday.\n"
			+ " Thirty days has September,\n"
			+ " April, June and November.\n"
			+ " All the rest have thirty-one,\n"
			+ " Saving February alone,\n"
			+ " Which has twenty-eight, rain or shine.\n"
			+ " And on leap years, twenty-nine.\n"
			+ " A leap year occurs on any year evenly divisible by 4, but not on a century unless it is divisible by 400.";
	}

	@Override
	public Integer solve() {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
		final Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 1901);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 01);
		int sundayFirstCount = 0;
		boolean finished = false;
		do {
			if (
					cal.get(Calendar.YEAR) == 2000 && 
					cal.get(Calendar.MONTH) == Calendar.DECEMBER && 
					cal.get(Calendar.DAY_OF_MONTH) == 31) {
				finished = true;
			}
			if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY 
					&& cal.get(Calendar.DAY_OF_MONTH) == 1) {
				sundayFirstCount++;
			}
			cal.add(Calendar.DAY_OF_MONTH, 1);
		} while (!finished);
		return sundayFirstCount;
	}
	
}
