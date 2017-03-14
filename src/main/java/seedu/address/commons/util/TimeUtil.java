package seedu.address.commons.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.regex.Pattern;

/**
 * Helper function for handling String Time
 */
public class TimeUtil {
	
    public static final Pattern TIME_REGEX_DATE_ONLY = Pattern.compile("?<date>\\d{6}");
    public static final Pattern TIME_REGEX_TIME_ONLY = Pattern.compile("?<time>\\d{4}");
    public static final Pattern TIME_REGEX_DATE_TIME = Pattern.compile("(?<date>\\d{6})(\\s+)(?<time>\\d{4})");
    public static final Pattern TIME_REGEX_TIME_DATE = Pattern.compile("(?<time>\\d{4})(\\s+)(?<date>\\d{6})"); 
	
	/**
     * Returns the date from a String.
     */
	public static LocalDate getDate(String date) {
		assert date.length() == 6 : "Date is of wrong length.";
		int day = Integer.parseInt(date.substring(0, 2));
		int month = Integer.parseInt(date.substring(2,4));
		int year = Integer.parseInt("20" + date.substring(4, 6));
		return LocalDate.of(year, month, day);
	}
	
	/**
     * Returns the time from a String.
     */
	public static LocalTime getTime(String time) {
		assert time.length() == 4 : "Time is of wrong length.";
		int hour = Integer.parseInt(time.substring(0, 2));
		int minute = Integer.parseInt(time.substring(2,4));
		return LocalTime.of(hour, minute);
	}

}
