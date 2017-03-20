//@@author A0135795R
package seedu.address.commons.util;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Helper function for handling String Date and Time.
 * Will return null when date and time are not detected.
 */
public class TimeUtil {
	
	public static final String DATE_GROUP = "date";
	public static final String TIME_GROUP = "time";
	public static final String DATE_DOES_NOT_EXIST_WARNING = "Date does not exist";
	public static final String TIME_DOES_NOT_EXIST_WARNING = "Time does not exist";
	
    public static final Pattern TIME_REGEX_DATE_ONLY = Pattern.compile("?<date>\\d{6}");
    public static final Pattern TIME_REGEX_TIME_ONLY = Pattern.compile("?<time>\\d{4}");
    public static final Pattern TIME_REGEX_DATE_TIME = Pattern.compile("(?<date>\\d{6})(\\s+)(?<time>\\d{4})");
    public static final Pattern TIME_REGEX_TIME_DATE = Pattern.compile("(?<time>\\d{4})(\\s+)(?<date>\\d{6})");
	
	/**
     * Returns the date from a String.
     * If no date is found, null will be returned.
     */
	public static LocalDate getDate(String date) {
		if (!doesDateFitRegex(date)) {
			return null;
		}
		Matcher matcher;
		String dateString;
		try {
			if (TIME_REGEX_DATE_TIME.matcher(date).find()) {
				matcher = TIME_REGEX_DATE_TIME.matcher(date);
				matcher.find();
				dateString = matcher.group(DATE_GROUP);
				return transformDateString(dateString);
			}
			else if (TIME_REGEX_TIME_DATE.matcher(date).find()) {
				matcher = TIME_REGEX_TIME_DATE.matcher(date);
				matcher.find();
				dateString = matcher.group(DATE_GROUP);
				return transformDateString(dateString);
			}
			else{
				matcher = TIME_REGEX_DATE_ONLY.matcher(date);
				matcher.find();
				dateString = matcher.group(DATE_GROUP);
				return transformDateString(dateString);
			}
		} catch (DateTimeException e) {
			return null;
		}
	}
	
	/**
     * Returns the time from a String.
     * If no time is found, null will be returned.
     */
	public static LocalTime getTime(String time) {
		if (!doesTimeFitRegex(time)){
			return null;
		}
		Matcher matcher;
		String timeString;
		try {
			if (TIME_REGEX_DATE_TIME.matcher(time).find()) {
				matcher = TIME_REGEX_DATE_TIME.matcher(time);
				matcher.find();
				timeString = matcher.group(TIME_GROUP);
				return transformTimeString(timeString);
			}
			else if (TIME_REGEX_TIME_DATE.matcher(time).find()) {
				matcher = TIME_REGEX_TIME_DATE.matcher(time);
				matcher.find();
				timeString = matcher.group(TIME_GROUP);
				return transformTimeString(timeString);
			}
			else{
				matcher = TIME_REGEX_TIME_ONLY.matcher(time);
				matcher.find();
				timeString = matcher.group(TIME_GROUP);
				return transformTimeString(timeString);
			}
		} catch (DateTimeException e) {
			return null;
		}
	}
	
	/**
     * Helper method to transform from string into LocalDate
     */
	private static LocalDate transformDateString(String date) {
		int day = Integer.parseInt(date.substring(0, 2));
		int month = Integer.parseInt(date.substring(2, 4));
		int year = Integer.parseInt(date.substring(4, 6)) + 2000;
		return LocalDate.of(year, month, day);
	}
	
	/**
     * Helper method to transform from string into LocalTime
     */
	private static LocalTime transformTimeString(String time) {
		int hour = Integer.parseInt(time.substring(0, 2));
		int minute = Integer.parseInt(time.substring(2, 4));
		return LocalTime.of(hour,minute);
	}
	
	/**
     * Returns true if date fits regex.
     */
	private static Boolean doesDateFitRegex(String date_time) {
		if (TIME_REGEX_DATE_TIME.matcher(date_time).find()) {
			return true;
		}
		else if (TIME_REGEX_TIME_DATE.matcher(date_time).find()) {
			return true;
		}
		else if (TIME_REGEX_DATE_ONLY.matcher(date_time).find()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
     * Returns true if time fits regex.
     */
	private static Boolean doesTimeFitRegex(String date_time) {
		if (TIME_REGEX_DATE_TIME.matcher(date_time).find()) {
			return true;
		}
		else if (TIME_REGEX_TIME_DATE.matcher(date_time).find()) {
			return true;
		}
		else if (TIME_REGEX_TIME_ONLY.matcher(date_time).find()) {
			return true;
		}
		else {
			return false;
		}
	}

}
