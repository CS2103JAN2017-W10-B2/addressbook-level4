//@@author A0135795R
package seedu.address.commons.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import com.joestelmach.natty.*;

/**
 * Helper function for handling String Date and Time.
 * Will return null when date and time are not detected.
 * Thanks given to natty for their date time parser
 */
public class TimeUtil {
	public static final String DATE_DOES_NOT_EXIST_WARNING = "DateTime does not exist";
	public static final String MORE_THAN_ONE_DATE_DETECTED_WARNING_MESSAGE = "Date format is too difficult to "
			+ "understand, please try again with an easier to understand sentence.";
	
    private static Parser parser = new Parser();
    
    /**
     * Test to see if input string is a valid DateTime
     */
    public static Boolean isValidDateTimeExist(String input) {
    	List<DateGroup> groups = parser.parse(input);
    	
    	try {
    		if (groups.get(0).getDates().size() > 1) {
    			return false;
    		}
    		groups.get(0).getDates().get(0);
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
    	
    	try {
    		if (groups.get(0).getDates().size() > 1) {
    			return null;
    		}
    		Date mostLikelyDate = groups.get(0).getDates().get(0);
    		return mostLikelyDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    	} catch (Exception e) {
    		return null;
    	}
    }


}
