package seedu.address.commons.util;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import com.joestelmach.natty.DateGroup;
import com.joestelmach.natty.Parser;

/**
 * Helper function for handling String Date and Time.
 * Will return null when date and time are not detected.
 * Thanks given to natty for their date time parser
 */
public class TimeUtil {
//@@author A0135795R
    public static final String DATE_DOES_NOT_EXIST_WARNING = "DateTime does not exist or is too difficult to "
            + "understand, please try again with an easier to understand sentence.";
    private static final String SEPARATOR = " ";
    private static final int NOON = 12;
    private static final int BEFORE_NOON_CUTOFF = 0;
    private static final String TIME_SEPARATOR = ":";
    private static final String AM = "am";
    private static final String PM = "pm";
    private static final String INVALID_MONTH = "You should not be seeing this.";
    private static final int DOUBLE_DIGIT = 10;
    private static final String DIGIT_FILLER = "0";

    private static final int JAN_NUM = 1;
    private static final String JAN = "Jan";
    private static final int FEB_NUM = 2;
    private static final String FEB = "Feb";
    private static final int MAR_NUM = 3;
    private static final String MAR = "Mar";
    private static final int APR_NUM = 4;
    private static final String APR = "Apr";
    private static final int MAY_NUM = 5;
    private static final String MAY = "May";
    private static final int JUN_NUM = 6;
    private static final String JUN = "Jun";
    private static final int JUL_NUM = 7;
    private static final String JUL = "Jul";
    private static final int AUG_NUM = 8;
    private static final String AUG = "Aug";
    private static final int SEP_NUM = 9;
    private static final String SEP = "Sep";
    private static final int OCT_NUM = 10;
    private static final String OCT = "Oct";
    private static final int NOV_NUM = 11;
    private static final String NOV = "Nov";
    private static final int DEC_NUM = 12;
    private static final String DEC = "Dec";

    private static Parser parser = new Parser();

    /**
     * Test to see if input string is a valid DateTime
     */
    public static Boolean isValidDateTimeExist(String input) {
        List<DateGroup> groups = parser.parse(input);

        try {
            if ((groups.size() != 1) || (groups.get(0).getDates().size() != 1)) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Returns the most likely DateTime from input string.
     * Else returns null if unable to detect DateTime from input string.
     */
    public static LocalDateTime getDateTime(String input) {
        List<DateGroup> groups = parser.parse(input);
        if (isValidDateTimeExist(input)) {
            Date mostLikelyDate = groups.get(0).getDates().get(0);
            return mostLikelyDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        } else {
            return null;
        }
    }

    public static String getFormattedDateTime(String input) {
        if (isValidDateTimeExist(input)) {
            LocalDateTime dateTime = getDateTime(input);
            return getStringTime(dateTime) + SEPARATOR + getStringDate(dateTime);
        } else {
            return null;
        }
    }

    private static String getStringTime(LocalDateTime dateTime) {
        String time;
        int minute = dateTime.getMinute();
        if (isBeforeNoon(dateTime)) {
            time = Integer.toString(dateTime.getHour());
            time = time.concat(TIME_SEPARATOR);
            if (minute < DOUBLE_DIGIT) {
                time = time.concat(DIGIT_FILLER);
            }
            time = time.concat(Integer.toString(dateTime.getMinute()));
            time = time.concat(AM);
        } else {
            int hour = dateTime.getHour();
            if (hour != NOON) {
                hour = hour - NOON;
            }
            time = Integer.toString(hour);
            time = time.concat(TIME_SEPARATOR);
            if (minute < DOUBLE_DIGIT) {
                time = time.concat(DIGIT_FILLER);
            }
            time = time.concat(Integer.toString(dateTime.getMinute()));
            time = time.concat(PM);
        }
        return time;
    }

    private static String getStringDate(LocalDateTime dateTime) {
        String date;
        String month = INVALID_MONTH;
        switch (dateTime.getMonthValue()) {
        case JAN_NUM:
            month = JAN;
            break;
        case FEB_NUM:
            month = FEB;
            break;
        case MAR_NUM:
            month = MAR;
            break;
        case APR_NUM:
            month = APR;
            break;
        case MAY_NUM:
            month = MAY;
            break;
        case JUN_NUM:
            month = JUN;
            break;
        case JUL_NUM:
            month = JUL;
            break;
        case AUG_NUM:
            month = AUG;
            break;
        case SEP_NUM:
            month = SEP;
            break;
        case OCT_NUM:
            month = OCT;
            break;
        case NOV_NUM:
            month = NOV;
            break;
        case DEC_NUM:
            month = DEC;
            break;
        }
        date = Integer.toString(dateTime.getDayOfMonth());
        date = date.concat(SEPARATOR + month);
        String year = Integer.toString(dateTime.getYear());
        date = date.concat(SEPARATOR + year);
        return date;
    }

    private static boolean isBeforeNoon(LocalDateTime dateTime) {
        return dateTime.toLocalTime().compareTo(LocalTime.NOON) < BEFORE_NOON_CUTOFF;
    }
//@@author A0135795R

}
